package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

//Author: Soren Moller Nielsen
public class TrailerView extends JFrame
{

	public TrailerView()
	{
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(630, 30, 640, 350);
		this.setTitle("Trailer View");
		InitContent();
		this.setVisible(true);
	}

	private Controller controller;

	private JPanel contentPane;

	private JList<Trailer> lstEnRouteTrailers;
	private DefaultListModel<Trailer> EnRouteModel;
	private JScrollPane scpEnRoute;

	private JList<Trailer> lstReadyTrailers;
	private DefaultListModel<Trailer> ReadyModel;
	private JScrollPane scpReady;

	private JList<Trailer> lstLoadedTrailers;
	private DefaultListModel<Trailer> LoadedModel;
	private JScrollPane scpLoaded;

	private JList<Trailer> lstDepartedTrailers;
	private DefaultListModel<Trailer> DepartedModel;
	private JScrollPane scpDeparted;

	private JLabel lblEnRoute, lblReady, lblLoaded, lblDeparted;

	private JButton btnArrived, btnApprove;

	private void InitContent()
	{
		controller = new Controller();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		EnRouteModel = new DefaultListModel<Trailer>();
		lstEnRouteTrailers = new JList<Trailer>(EnRouteModel);
		lstEnRouteTrailers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpEnRoute = new JScrollPane(lstEnRouteTrailers);
		scpEnRoute.setBounds(5, 55, 130, 230);
		contentPane.add(scpEnRoute);

		ReadyModel = new DefaultListModel<Trailer>();
		lstReadyTrailers = new JList<Trailer>(ReadyModel);
		lstReadyTrailers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpReady = new JScrollPane(lstReadyTrailers);
		contentPane.add(scpReady);
		scpReady.setBounds(170, 55, 130, 230);

		LoadedModel = new DefaultListModel<Trailer>();
		lstLoadedTrailers = new JList<Trailer>(LoadedModel);
		lstLoadedTrailers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpLoaded = new JScrollPane(lstLoadedTrailers);
		contentPane.add(scpLoaded);
		scpLoaded.setBounds(335, 55, 130, 230);

		DepartedModel = new DefaultListModel<Trailer>();
		lstDepartedTrailers = new JList<Trailer>(DepartedModel);
		lstDepartedTrailers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpDeparted = new JScrollPane(lstDepartedTrailers);
		contentPane.add(scpDeparted);
		scpDeparted.setBounds(500, 55, 130, 230);

		lblEnRoute = new JLabel("En route:");
		lblEnRoute.setBounds(5, 40, 130, 15);
		contentPane.add(lblEnRoute);

		lblReady = new JLabel("Ready:");
		lblReady.setBounds(170, 40, 130, 15);
		contentPane.add(lblReady);

		lblLoaded = new JLabel("Loaded:");
		lblLoaded.setBounds(335, 40, 130, 15);
		contentPane.add(lblLoaded);

		lblDeparted = new JLabel("Departed:");
		lblDeparted.setBounds(500, 40, 130, 15);
		contentPane.add(lblDeparted);

		btnArrived = new JButton("Arrived");
		btnArrived.setBounds(5, 290, 130, 25);
		contentPane.add(btnArrived);
		btnArrived.addActionListener(controller);

		btnApprove = new JButton("Approve");
		btnApprove.setBounds(335, 290, 130, 25);
		contentPane.add(btnApprove);
		btnApprove.addActionListener(controller);
	}

	public class Controller implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == btnArrived)
			{
				if (lstEnRouteTrailers.isSelectionEmpty() == true)
				{
					System.out.println("Tag dig sammen, du mangler at vælge et object fra listen");
				}
				else
				{
					// Changes the state from being outside the gate to being
					// inside
					(lstEnRouteTrailers.getSelectedValue()).setTrailerState(TrailerState.ARRIVED);
					for (SubOrder s : (lstEnRouteTrailers.getSelectedValue()).getSubOrders())
					{
						s.getLoadingInfo().setState(LoadingInfoState.READY_TO_LOAD);
					}
					// refreshes the list
					controller.fillEnRouteLst();

					//Updates Loadingbayview current view
					LoadingBayView.fillInfo(LoadingBayView.getSelecetedLoadingBay());

				}
			}
			if (e.getSource() == btnApprove)
			{
				if (lstLoadedTrailers.isSelectionEmpty() == true)
				{
					System.out.println("Tag dig sammen, du mangler at vælge et object fra listen");
				}
				else
				{
					// changes selected trailer to being departed
					(lstLoadedTrailers.getSelectedValue()).setTrailerState(TrailerState.DEPARTED);
					// set the Departedtime of the trailer
					(lstLoadedTrailers.getSelectedValue()).setTimeOfDeparture(DU.createDate());
					// refreshes the list
					controller.fillDepartedLst();

				}
			}
		}

		public void fillEnRouteLst()
		{
			if (Dao.getTrailer().size() > 0)
			{

				ArrayList<Trailer> arraylistTrailer = new ArrayList<>();
				for (Trailer t : Dao.getTrailer())
				{
					if (t.getTrailerState() == TrailerState.ENROUTE)
					{
						arraylistTrailer.add(t);
					}
				}
				Trailer[] arrayTrailer = arraylistTrailer.toArray(new Trailer[0]);

				lstEnRouteTrailers.setListData(arrayTrailer);
			}
		}

		public void fillDepartedLst()
		{
			if (Dao.getTrailer().size() > 0)
			{

				ArrayList<Trailer> arraylistTrailer = new ArrayList<>();
				for (Trailer t : Dao.getTrailer())
				{
					if (t.getTrailerState() == TrailerState.LOADED)
					{
						arraylistTrailer.add(t);
					}
				}
				Trailer[] arrayTrailer = arraylistTrailer.toArray(new Trailer[0]);

				lstLoadedTrailers.setListData(arrayTrailer);
			}
		}

	}
}
