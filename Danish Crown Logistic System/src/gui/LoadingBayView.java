package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import model.LoadingBay;
import model.LoadingInfo;
import dao.Dao;

//Author Christian "sexy" Møller Pedersen
public class LoadingBayView extends JFrame
{

	private Controller controller;
	private static LoadingInfoDialog loadingInfoDialog;

	public LoadingBayView()
	{
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(30, 390, 569, 347);
		this.setTitle("Loading Bay View");
		InitContent();
		this.setVisible(true);
		loadingInfoDialog = new LoadingInfoDialog(null);
		loadingInfoDialog.setVisible(false);
	}

	private JPanel contentPane;
	private static JLabel lbLoadingBay;
	private JList<LoadingInfo> lstBayList;
	private static JComboBox<LoadingBay> cmbBays;
	private static DefaultListModel<LoadingInfo> infoModel;
	private JScrollPane scpInfo;

	private void InitContent()
	{

		controller = new Controller();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lbLoadingBay = new JLabel("Loading Bay view");
		lbLoadingBay.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbLoadingBay.setBounds(211, 11, 164, 24);
		contentPane.add(lbLoadingBay);

		cmbBays = new JComboBox<LoadingBay>();
		cmbBays.setBounds(200, 41, 180, 20);
		contentPane.add(cmbBays);
		cmbBays.addItemListener(controller);

		infoModel = new DefaultListModel<LoadingInfo>();
		lstBayList = new JList<LoadingInfo>(infoModel);
		lstBayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpInfo = new JScrollPane(lstBayList);
		contentPane.add(scpInfo);
		scpInfo.setBounds(5, 75, 555, 240);
		lstBayList.addMouseListener(controller);

		fillBays();

	}

	public static LoadingBay getSelecetedLoadingBay()
	{
		LoadingBay lb = (LoadingBay)cmbBays.getSelectedItem();
		return lb;
	}

	public static void fillBays()
	{
		cmbBays.removeAll();
		for (LoadingBay lb : Dao.getLoadingBays())
		{
			cmbBays.addItem(lb);
		}
	}

	public static void fillInfo(LoadingBay lb)
	{
		infoModel.clear();
		for (LoadingInfo li : lb.getLoadingInfos())
		{
			infoModel.addElement(li);
		}
	}

	private class Controller implements ActionListener, MouseListener, ItemListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{

		}

		// author Soren Moller Nielsen
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if (e.getSource() == (lstBayList.getSelectedValue()) && e.getClickCount() == 3 || e.getClickCount() == 2)
			{

				System.out.println((lstBayList.getSelectedValue()));
				loadingInfoDialog.fillModel((lstBayList.getSelectedValue()));
				loadingInfoDialog.setVisible(true);

			}

		}

		@Override
		public void mouseEntered(MouseEvent e)
		{

		}

		@Override
		public void mouseExited(MouseEvent e)
		{

		}

		@Override
		public void mousePressed(MouseEvent e)
		{

		}

		@Override
		public void mouseReleased(MouseEvent e)
		{

		}

		@Override
		public void itemStateChanged(ItemEvent arg0)
		{
			if (arg0.getSource() == cmbBays)
			{
				fillInfo((LoadingBay)cmbBays.getSelectedItem());
			}
		}

	}
}
