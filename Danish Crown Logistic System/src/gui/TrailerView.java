package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import model.LoadingInfoState;
import model.SubOrder;
import model.Trailer;
import model.TrailerState;
import dao.Dao;
import dateutil.DU;

//Author: Christian Møller Pedersen
public class TrailerView extends JFrame
{

	public TrailerView()
	{
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(630, 30, 705, 350);
		this.setTitle("Trailer View");
		InitContent();
		this.setVisible(true);
	}

	private Controller controller;

	private JPanel contentPane;
	private static JList<Trailer> lstEnRoute, lstReady, lstLoading, lstLoaded, lstDeparted;
	private static DefaultListModel<Trailer> EnRouteModel, ReadyModel, LoadingModel, LoadedModel, DepartedModel;
	private static JScrollPane scpEnRoute, scpReady, scpLoading, scpLoaded, scpDeparted;
	private JLabel lblEnRoute, lblReady, lblLoading, lblLoaded, lblDeparted;
	private JButton btnArrived, btnApprove;

	private void InitContent()
	{
		controller = new Controller();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		EnRouteModel = new DefaultListModel<Trailer>();
		lstEnRoute = new JList<Trailer>(EnRouteModel);
		lstEnRoute.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpEnRoute = new JScrollPane(lstEnRoute);
		scpEnRoute.setBounds(5, 55, 130, 230);
		contentPane.add(scpEnRoute);

		ReadyModel = new DefaultListModel<Trailer>();
		lstReady = new JList<Trailer>(ReadyModel);
		lstReady.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpReady = new JScrollPane(lstReady);
		contentPane.add(scpReady);
		scpReady.setBounds(145, 55, 130, 230);

		LoadingModel = new DefaultListModel<Trailer>();
		lstLoading = new JList<Trailer>(LoadingModel);
		lstLoading.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpLoading = new JScrollPane(lstLoading);
		contentPane.add(scpLoading);
		scpLoading.setBounds(285, 55, 130, 230);

		LoadedModel = new DefaultListModel<Trailer>();
		lstLoaded = new JList<Trailer>(LoadedModel);
		lstLoaded.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpLoaded = new JScrollPane(lstLoaded);
		contentPane.add(scpLoaded);
		scpLoaded.setBounds(425, 55, 130, 230);

		DepartedModel = new DefaultListModel<Trailer>();
		lstDeparted = new JList<Trailer>(DepartedModel);
		lstDeparted.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpDeparted = new JScrollPane(lstDeparted);
		contentPane.add(scpDeparted);
		scpDeparted.setBounds(565, 55, 130, 230);

		lblEnRoute = new JLabel("En route:");
		lblEnRoute.setBounds(5, 40, 130, 15);
		contentPane.add(lblEnRoute);

		lblReady = new JLabel("Ready:");
		lblReady.setBounds(145, 40, 130, 15);
		contentPane.add(lblReady);

		lblLoading = new JLabel("Loading:");
		lblLoading.setBounds(285, 40, 130, 15);
		contentPane.add(lblLoading);

		lblLoaded = new JLabel("Loaded:");
		lblLoaded.setBounds(425, 40, 130, 15);
		contentPane.add(lblLoaded);

		lblDeparted = new JLabel("Departed:");
		lblDeparted.setBounds(565, 40, 130, 15);
		contentPane.add(lblDeparted);

		btnArrived = new JButton("Arrived");
		btnArrived.setBounds(5, 290, 130, 25);
		contentPane.add(btnArrived);
		btnArrived.addActionListener(controller);

		btnApprove = new JButton("Approve");
		btnApprove.setBounds(425, 290, 130, 25);
		contentPane.add(btnApprove);
		btnApprove.addActionListener(controller);

		fillModel(TrailerState.ENROUTE);
	}

	public static void fillModel(TrailerState trailerState)
	{
		DefaultListModel<Trailer> model = null;
		switch (trailerState) {
		case ARRIVED:
			model = ReadyModel;
			break;
		case BEING_LOADED:
			model = LoadingModel;
			break;
		case DEPARTED:
			model = DepartedModel;
			break;
		case ENROUTE:
			model = EnRouteModel;
			break;
		case IDLE:
			break;
		case LOADED:
			model = LoadedModel;
			break;
		default:
			break;
		}
		if (model != null)
		{
			model.clear();
			for (Trailer trailer : Dao.getTrailer())
			{
				if (trailer.getTrailerState() == trailerState)
				{
					model.addElement(trailer);
				}
			}
		}
	}

	public class Controller implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == btnArrived)
			{
				if (lstEnRoute.isSelectionEmpty() == true)
				{
					System.out.println("Tag dig sammen, du mangler at vælge et object fra listen");
				}
				else
				{
					(lstEnRoute.getSelectedValue()).setTrailerState(TrailerState.ARRIVED);
					for (SubOrder subOrder : (lstEnRoute.getSelectedValue()).getSubOrders())
					{
						subOrder.getLoadingInfo().setState(LoadingInfoState.READY_TO_LOAD);
					}
					fillModel(TrailerState.ENROUTE);
					fillModel(TrailerState.ARRIVED);

					//Updates Loadingbayview current view
					LoadingBayView.fillInfo(null);

				}
			}
			if (e.getSource() == btnApprove)
			{
				if (lstLoaded.isSelectionEmpty() == true)
				{
					System.out.println("Tag dig sammen, du mangler at vælge et object fra listen");
				}
				else
				{
					// changes selected trailer to being departed
					(lstLoaded.getSelectedValue()).setTrailerState(TrailerState.DEPARTED);
					// set the Departedtime of the trailer
					(lstLoaded.getSelectedValue()).setTimeOfDeparture(DU.createDate());
					// refreshes the list
					fillModel(TrailerState.LOADED);
					fillModel(TrailerState.DEPARTED);

				}
			}
		}

	}
}
