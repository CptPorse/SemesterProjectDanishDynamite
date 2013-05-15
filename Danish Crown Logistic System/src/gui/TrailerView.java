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
		this.setBounds(630, 30, 604, 347);
		this.setTitle("Trailer View");

		InitContent();
		this.setVisible(true);

	}

	private Controller controller;

	private JPanel contentPane;
	private JLabel lblTrailerView;

	private JList<Trailer> lstArrivingTrailers;
	private DefaultListModel<Trailer> lstArrivingTrailersModel;
	private JScrollPane scpArriving;

	private JList<Trailer> lstDeparturingTrailers;
	private DefaultListModel<Trailer> lstDeparturingTrailersModel;
	private JScrollPane scpDeparture;

	private JLabel lblNewLabel;
	private JLabel lblDeparture;
	private JButton btnHasArrived, btnCheckDeparture, btnRefreshListstemp;

	private void InitContent()
	{
		controller = new Controller();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTrailerView = new JLabel("Trailer View");
		lblTrailerView.setBounds(5, 5, 553, 14);
		contentPane.add(lblTrailerView);

		lstArrivingTrailersModel = new DefaultListModel<Trailer>();
		lstArrivingTrailers = new JList<Trailer>(lstArrivingTrailersModel);
		lstArrivingTrailers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpArriving = new JScrollPane(lstArrivingTrailers);
		scpArriving.setBounds(5, 55, 145, 230);
		contentPane.add(scpArriving);

		lstDeparturingTrailersModel = new DefaultListModel<Trailer>();
		lstDeparturingTrailers = new JList<Trailer>(lstDeparturingTrailersModel);
		lstDeparturingTrailers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpDeparture = new JScrollPane(lstDeparturingTrailers);
		contentPane.add(scpDeparture);
		scpDeparture.setBounds(285, 55, 145, 230);

		btnHasArrived = new JButton("hasArrived");
		btnHasArrived.setBounds(170, 265, 89, 23);
		contentPane.add(btnHasArrived);
		btnHasArrived.addActionListener(controller);

		btnCheckDeparture = new JButton("Check Departure");
		btnCheckDeparture.setBounds(464, 265, 113, 23);
		contentPane.add(btnCheckDeparture);
		btnCheckDeparture.addActionListener(controller);

		lblNewLabel = new JLabel("Arrivingtrailers");
		lblNewLabel.setBounds(5, 30, 89, 14);
		contentPane.add(lblNewLabel);

		lblDeparture = new JLabel("Trailer ready for departure");
		lblDeparture.setBounds(285, 30, 134, 14);
		contentPane.add(lblDeparture);

		btnRefreshListstemp = new JButton("Refresh lists(temp)");
		btnRefreshListstemp.setBounds(464, 177, 124, 23);
		contentPane.add(btnRefreshListstemp);
		btnRefreshListstemp.addActionListener(controller);

		controller.fillArrivingLst();
		controller.fillDepartureLst();
	}

	public class Controller implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{

			if (e.getSource() == btnRefreshListstemp) {
				fillArrivingLst();
				fillDepartureLst();
			}
			if (e.getSource() == btnHasArrived) {
				if (lstArrivingTrailers.isSelectionEmpty() == true) {
					System.out.println("Tag dig sammen, du mangler at vælge et object fra listen");
				} else {
					// Changes the state from being outside the gate to being
					// inside
					(lstArrivingTrailers.getSelectedValue()).setTrailerState(TrailerState.ARRIVED);
					for (SubOrder s : (lstArrivingTrailers.getSelectedValue()).getSubOrders()) {
						s.getLoadingInfo().setState(LoadingInfoState.READY_TO_LOAD);
					}
					// refreshes the list
					controller.fillArrivingLst();

					//Updates Loadingbayview current view
					LoadingBayView.fillInfo(LoadingBayView.getSelecetedLoadingBay());

				}
			}
			if (e.getSource() == btnCheckDeparture) {
				if (lstDeparturingTrailers.isSelectionEmpty() == true) {
					System.out.println("Tag dig sammen, du mangler at vælge et object fra listen");
				} else {
					// changes selected trailer to being departed
					(lstDeparturingTrailers.getSelectedValue()).setTrailerState(TrailerState.DEPARTED);
					// set the departuretime of the trailer
					(lstDeparturingTrailers.getSelectedValue()).setTimeOfDeparture(DU.createDate());
					// refreshes the list
					controller.fillDepartureLst();

				}
			}
		}

		public void fillArrivingLst()
		{
			if (Dao.getTrailer().size() > 0) {

				ArrayList<Trailer> arraylistTrailer = new ArrayList<>();
				for (Trailer t : Dao.getTrailer()) {
					if (t.getTrailerState() == TrailerState.ENROUTE) {
						arraylistTrailer.add(t);
					}
				}
				Trailer[] arrayTrailer = arraylistTrailer.toArray(new Trailer[0]);

				lstArrivingTrailers.setListData(arrayTrailer);
			}
		}

		public void fillDepartureLst()
		{
			if (Dao.getTrailer().size() > 0) {

				ArrayList<Trailer> arraylistTrailer = new ArrayList<>();
				for (Trailer t : Dao.getTrailer()) {
					if (t.getTrailerState() == TrailerState.LOADED) {
						arraylistTrailer.add(t);
					}
				}
				Trailer[] arrayTrailer = arraylistTrailer.toArray(new Trailer[0]);

				lstDeparturingTrailers.setListData(arrayTrailer);
			}
		}

	}
}
