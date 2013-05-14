package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.LoadingInfo;
import dateutil.DU;

//Author: Jens Nyberg Porse
public class LoadingInfoDialog extends JDialog
{

	public LoadingInfoDialog(JFrame owner)
	{
		super(owner);
		System.out.println("Created new Window");
		setTitle("Loading Info");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(600, 500, 569, 347);
		InitContent();
		this.setVisible(true);

	}

	private JPanel contentPanel = new JPanel();
	private JLabel lblLoadingBay, lblTrailer, lblProductType, lblBeganLoading,
			lblEndedLoading;
	private JTextField txfLoadingBay, txfTrailer, txfProductType,
			txfBeganLoading, txfEndedLoading;
	private JButton btnBeginLoading, btnEndLoading, btnCancel;

	private Controller controller;

	private void InitContent()
	{

		controller = new Controller();

		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		getContentPane().setLayout(null);

		lblLoadingBay = new JLabel("Loading Bay:");
		lblLoadingBay.setBounds(20, 24, 122, 14);
		contentPanel.add(lblLoadingBay);

		txfLoadingBay = new JTextField();
		txfLoadingBay.setBounds(20, 43, 86, 20);
		contentPanel.add(txfLoadingBay);
		txfLoadingBay.setColumns(10);
		txfLoadingBay.setEditable(false);

		lblTrailer = new JLabel("Trailer:");
		lblTrailer.setBounds(20, 74, 86, 14);
		contentPanel.add(lblTrailer);

		txfTrailer = new JTextField();
		txfTrailer.setBounds(20, 93, 86, 20);
		contentPanel.add(txfTrailer);
		txfTrailer.setColumns(10);
		txfTrailer.setEditable(false);

		lblProductType = new JLabel("Product Type:");
		lblProductType.setBounds(20, 124, 86, 14);
		contentPanel.add(lblProductType);

		txfProductType = new JTextField();
		txfProductType.setBounds(20, 146, 86, 20);
		contentPanel.add(txfProductType);
		txfProductType.setColumns(10);
		txfProductType.setEditable(false);

		lblBeganLoading = new JLabel("Began Loading:");
		lblBeganLoading.setBounds(139, 24, 94, 14);
		contentPanel.add(lblBeganLoading);

		txfBeganLoading = new JTextField();
		txfBeganLoading.setBounds(139, 43, 107, 20);
		contentPanel.add(txfBeganLoading);
		txfBeganLoading.setColumns(10);

		lblEndedLoading = new JLabel("Ended Loading:");
		lblEndedLoading.setBounds(142, 104, 86, 14);
		contentPanel.add(lblEndedLoading);

		txfEndedLoading = new JTextField();
		txfEndedLoading.setBounds(142, 123, 104, 20);
		contentPanel.add(txfEndedLoading);
		txfEndedLoading.setColumns(10);

		btnBeginLoading = new JButton("Begin Loading");
		btnBeginLoading.setBounds(139, 70, 107, 23);
		contentPanel.add(btnBeginLoading);

		btnEndLoading = new JButton("End Loading");
		btnEndLoading.setBounds(139, 150, 107, 23);
		contentPanel.add(btnEndLoading);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(86, 195, 89, 23);
		contentPanel.add(btnCancel);

	}

//Author: Soren Moller Nielsen
	private LoadingInfo loadingInfo;

	public void fillModel(LoadingInfo lInfo)
	{
		this.loadingInfo = lInfo;
		txfTrailer.setText(lInfo.getSubOrder().getTrailer().getTrailerID());
		txfProductType.setText(lInfo.getSubOrder().getProductType()
				.getDescription());
		txfLoadingBay.setText("" + lInfo.getLoadingBay().getLoadingBayNumber());
		txfBeganLoading.setText(lInfo.getTimeOfLoadingStart().toString());
		txfEndedLoading.setText(lInfo.getTimeOfLoadingEnd().toString());
	}

	private class Controller implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == btnBeginLoading) {
				Date tempDate = DU.createDate();
				loadingInfo.setTimeOfLoadingStart(tempDate);
				txfBeganLoading.setText(tempDate.toString());
			}
			if (e.getSource() == btnEndLoading) {
				//skal rettes til
				//skal sendes til trailerview, hvis alle subordre er loaded
				Date tempDate = DU.createDate();
				loadingInfo.setTimeOfLoadingEnd(tempDate);
				txfEndedLoading.setText(tempDate.toString());
			}
			if (e.getSource() == btnCancel) {

			}

		}
	}
}
