package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.LoadingInfo;
import model.LoadingInfoState;
import model.SubOrder;
import model.TrailerState;
import dateutil.DU;

//Author: Jens "Il duce" Nyberg Porse
public class LoadingInfoDialog extends JDialog {

	public LoadingInfoDialog(JFrame owner) {
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

	private void InitContent() {

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
		txfProductType.setBounds(20, 146, 155, 20);
		contentPanel.add(txfProductType);
		txfProductType.setColumns(10);
		txfProductType.setEditable(false);

		lblBeganLoading = new JLabel("Began Loading:");
		lblBeganLoading.setBounds(274, 24, 94, 14);
		contentPanel.add(lblBeganLoading);

		txfBeganLoading = new JTextField("");
		txfBeganLoading.setBounds(274, 43, 135, 20);
		contentPanel.add(txfBeganLoading);
		txfBeganLoading.setColumns(10);

		lblEndedLoading = new JLabel("Ended Loading:");
		lblEndedLoading.setBounds(277, 104, 86, 14);
		contentPanel.add(lblEndedLoading);

		txfEndedLoading = new JTextField("");
		txfEndedLoading.setBounds(277, 123, 135, 20);
		contentPanel.add(txfEndedLoading);
		txfEndedLoading.setColumns(10);

		btnBeginLoading = new JButton("Begin Loading");
		btnBeginLoading.setBounds(274, 70, 107, 23);
		contentPanel.add(btnBeginLoading);
		btnBeginLoading.addActionListener(controller);

		btnEndLoading = new JButton("End Loading");
		btnEndLoading.setBounds(274, 150, 107, 23);
		contentPanel.add(btnEndLoading);
		btnEndLoading.addActionListener(controller);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(274, 218, 89, 23);
		contentPanel.add(btnCancel);

	}

	// Author: Soren Moller Nielsen
	private LoadingInfo loadingInfo;

	public void fillModel(LoadingInfo lInfo) {
		this.loadingInfo = lInfo;
		txfTrailer.setText(lInfo.getSubOrder().getTrailer().getTrailerID());
		txfProductType.setText(lInfo.getSubOrder().getProductType()
				.getDescription());
		txfLoadingBay.setText("" + lInfo.getLoadingBay().getLoadingBayNumber());

	}

	// Author: Soren Moller Nielsen
	private class Controller implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnBeginLoading) {
				// Checks the format of the input
				if (txfBeganLoading.getText().isEmpty() == true) {
					txfBeganLoading.setText("invalid input");
				}
				// If no unregularities have been caught, starts loading the
				// truck
				else {

					loadingInfo.setTimeOfLoadingStart(DU
							.createDate(txfBeganLoading.getText()));
					loadingInfo.setState(LoadingInfoState.LOADING);
					System.out.println("Woop di doo!, began loading ze truck!");
				}

			}

			if (e.getSource() == btnEndLoading) {
				// Checks if the format is correct
				if (txfEndedLoading.getText().isEmpty() == true) {
					txfEndedLoading.setText("Invalid input");
				}
				// sets the loadinginfo as loaded, possible trailer aswell
				else {

					// sets the loadingInfo as finished
					loadingInfo.setState(LoadingInfoState.FINISHED);

					// sets the subOrder as loaded
					loadingInfo.getSubOrder().setLoaded(true);

					// checking, if the trailer is fully loaded
					boolean trailerFullyLoaded = true;
					ArrayList<SubOrder> subOrders = loadingInfo.getSubOrder()
							.getTrailer().getSubOrders();

					// seaches if any of the attached suborders to the trailer,
					// aren't done loading
					for (SubOrder s : subOrders) {
						if (s.isLoaded() == false) {
							trailerFullyLoaded = false;
						}
					}
					// if all the suborders are done, trailer changes
					// trailerstate to: loaded
					if (trailerFullyLoaded == true) {
						loadingInfo.getSubOrder().getTrailer()
								.setTrailerState(TrailerState.LOADED);

					}

				}

			}
			if (e.getSource() == btnCancel) {

			}

		}
	}
}
