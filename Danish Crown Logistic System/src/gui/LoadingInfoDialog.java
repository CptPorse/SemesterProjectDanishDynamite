package gui;

import java.awt.Color;
import java.awt.Window;
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

import model.LoadingBay;
import model.LoadingInfo;
import model.LoadingInfoState;
import model.SubOrder;
import model.TrailerState;
import service.Service;

//Author: Jens "Il duce" Nyberg Porse
public class LoadingInfoDialog extends JDialog
{

	public LoadingInfoDialog(JFrame owner)
	{
		super(owner);
		System.out.println("Created new Window");
		setTitle("Loading Info");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(30, 30, 569, 298);
		InitContent();
		this.setVisible(true);

	}

	private JPanel contentPanel = new JPanel();
	private JLabel lblLoadingBay, lblTrailer, lblProductType, lblBeganLoading, lblEndedLoading, lblWarning;
	private JTextField txfLoadingBay, txfTrailer, txfProductType, txfBeganLoading, txfEndedLoading;
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
		txfProductType.setBounds(20, 146, 155, 20);
		contentPanel.add(txfProductType);
		txfProductType.setColumns(10);
		txfProductType.setEditable(false);

		lblBeganLoading = new JLabel("Begin Loading:");
		lblBeganLoading.setBounds(274, 24, 94, 14);
		contentPanel.add(lblBeganLoading);

		txfBeganLoading = new JTextField("");
		txfBeganLoading.setBounds(274, 43, 170, 20);
		contentPanel.add(txfBeganLoading);
		txfBeganLoading.setColumns(10);

		lblEndedLoading = new JLabel("Ended Loading:");
		lblEndedLoading.setBounds(277, 104, 86, 14);
		contentPanel.add(lblEndedLoading);

		txfEndedLoading = new JTextField("");
		txfEndedLoading.setBounds(277, 123, 170, 20);
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
		btnCancel.setBounds(219, 218, 89, 23);
		contentPanel.add(btnCancel);
		btnCancel.addActionListener(controller);

		lblWarning = new JLabel("WARNING: ");
		lblWarning.setBounds(69, 184, 431, 23);
		contentPanel.add(lblWarning);
		lblWarning.setVisible(false);

	}

	// Author: Soren Moller Nielsen

	private LoadingInfo loadingInfo;

	public void fillModel(LoadingInfo lInfo)
	{
		this.loadingInfo = lInfo;
		txfTrailer.setText(lInfo.getSubOrder().getTrailer().getTrailerID());
		txfProductType.setText(lInfo.getSubOrder().getProductType().getDescription());
		txfLoadingBay.setText("" + lInfo.getLoadingBay().getLoadingBayNumber());
		txfBeganLoading.setText(Service.getDateToStringTime(lInfo.getTimeOfLoadingStart()));
		txfEndedLoading.setText(Service.getDateToStringTime(lInfo.getTimeOfLoadingEnd()));
		if (lInfo.getState() == LoadingInfoState.PENDING || lInfo.getState() == LoadingInfoState.FINISHED)
		{
			txfBeganLoading.setEditable(false);
			txfEndedLoading.setEditable(false);
			btnBeginLoading.setEnabled(false);
			btnEndLoading.setEnabled(false);
			lblWarning.setVisible(false);
		}

		if (lInfo.getState() == LoadingInfoState.READY_TO_LOAD)
		{
			txfBeganLoading.setEditable(true);
			btnBeginLoading.setEnabled(true);
			txfEndedLoading.setEditable(false);
			btnEndLoading.setEnabled(false);
			lblWarning.setVisible(false);

		}
		if (lInfo.getState() == LoadingInfoState.LOADING)
		{
			txfBeganLoading.setEditable(false);
			btnBeginLoading.setEnabled(false);
			txfEndedLoading.setEditable(true);
			btnEndLoading.setEnabled(true);
			lblWarning.setVisible(false);
		}

		if (lInfo.getSubOrder().getTrailer().getTrailerState() == TrailerState.BEING_LOADED)
		{

			LoadingBay lb = null;
			for (SubOrder s : lInfo.getSubOrder().getTrailer().getSubOrders())
			{

				if (s.getLoadingInfo().getState() == LoadingInfoState.LOADING && s.getLoadingInfo() != lInfo)
				{
					lb = s.getLoadingInfo().getLoadingBay();
					txfBeganLoading.setEditable(false);
					txfEndedLoading.setEditable(false);
					btnBeginLoading.setEnabled(false);
					btnEndLoading.setEnabled(false);
					lblWarning.setText("Trailer is Already being loaded at LoadingBay" + lb);
					lblWarning.setForeground(Color.RED);
					lblWarning.setVisible(true);
				}
			}

		}

	}

	// Author: Soren Moller Nielsen
	private class Controller implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{

			if (e.getSource() == btnBeginLoading)
			{

				loadingInfo.setTimeOfLoadingStart(Service.getTimeStringToDate(txfBeganLoading.getText()));
				loadingInfo.setTimeOfLoadingEnd(Service.getEndTime(loadingInfo.getTimeOfLoadingStart(), loadingInfo.getSubOrder().getEstimatedLoadingTime()));
				loadingInfo.setState(LoadingInfoState.LOADING);

				//Updating textboxes and buttons
				txfEndedLoading.setText(Service.getDateToStringTime(loadingInfo.getTimeOfLoadingEnd()));
				txfBeganLoading.setEditable(false);
				btnBeginLoading.setEnabled(false);
				txfEndedLoading.setEditable(true);
				btnEndLoading.setEnabled(true);

				//Updating LoadingBays nextAvailabeTime. This is used for re-sorting LoadingInfos
				loadingInfo.getLoadingBay().setNextAvailableTime(loadingInfo.getTimeOfLoadingEnd());
				Service.refreshLoadingBays(loadingInfo.getSubOrder().getProductType());

				LoadingBayView.fillInfo(null);

				//Update the trailer view and trailer state - Christian
				loadingInfo.getSubOrder().getTrailer().setTrailerState(TrailerState.BEING_LOADED);
				TrailerView.fillModel(TrailerState.ARRIVED);
				TrailerView.fillModel(TrailerState.BEING_LOADED);
			}

			if (e.getSource() == btnEndLoading)
			{

				loadingInfo.setTimeOfLoadingEnd(Service.getTimeStringToDate(txfEndedLoading.getText()));

				//Updating LoadingBays nextAvailabeTime. This is used for re-sorting LoadingInfos
				loadingInfo.getLoadingBay().setNextAvailableTime(loadingInfo.getTimeOfLoadingEnd());
				Service.refreshLoadingBays(loadingInfo.getSubOrder().getProductType());

				// sets the loadingInfo as finished
				loadingInfo.setState(LoadingInfoState.FINISHED);

				// sets the subOrder as loaded
				loadingInfo.getSubOrder().setLoaded(true);

				ArrayList<SubOrder> subOrders = loadingInfo.getSubOrder().getTrailer().getSubOrders();

				// searches if any of the attached sub orders to the trailer, aren't done loading, if any, it will set their LoadingInfoStat
				boolean trailerFullyLoaded = true;
				boolean changed = false;
				for (SubOrder s : subOrders)
				{
					if (s.isLoaded() == false)
					{
						if (changed == false)
						{
							s.getLoadingInfo().setState(LoadingInfoState.READY_TO_LOAD);
							changed = true;
						}
						trailerFullyLoaded = false;
					}
				}
				// if all the sub orders are done, trailer changes trailerstate to: loaded
				if (trailerFullyLoaded == true)
				{
					loadingInfo.getSubOrder().getTrailer().setTrailerState(TrailerState.LOADED);
					loadingInfo.getSubOrder().getTrailer().setTimeOfDeparture(loadingInfo.getTimeOfLoadingEnd());
					SmsDialog sms = new SmsDialog(loadingInfo);
				}
				else
				{
					loadingInfo.getSubOrder().getTrailer().setTrailerState(TrailerState.ARRIVED);
				}
				LoadingBayView.fillInfo(null);
				((Window)btnEndLoading.getTopLevelAncestor()).dispose();

				//Update the trailer view and trailer state - Christian
				TrailerView.fillModel(TrailerState.ARRIVED);
				TrailerView.fillModel(TrailerState.BEING_LOADED);
				TrailerView.fillModel(TrailerState.LOADED);
			}

			if (e.getSource() == btnCancel)
			{
				((Window)btnCancel.getTopLevelAncestor()).dispose();

			}
		}
	}
}
