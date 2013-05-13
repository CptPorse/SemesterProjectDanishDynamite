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

public class LoadingBayView extends JFrame
{

	private Controller controller;

	public LoadingBayView()
	{
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(600, 500, 569, 347);
		this.setTitle("Loading Bay View");
		InitContent();
		this.setVisible(true);
	}

	private JPanel contentPane;
	private static JLabel lblTime, lbLoadingBay;
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

		lblTime = new JLabel("09:00");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTime.setBounds(485, 30, 68, 17);
		contentPane.add(lblTime);

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
		public void actionPerformed(ActionEvent arg0)
		{

		}

		@Override
		public void mouseClicked(MouseEvent arg0)
		{

		}

		@Override
		public void mouseEntered(MouseEvent arg0)
		{

		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{

		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{

		}

		@Override
		public void mouseReleased(MouseEvent arg0)
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
