package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Driver;
import model.ProductType;
import model.Trailer;
import service.Service;
import dao.Dao;

//Author: Jens Nyberg Porse
public class NewTrailerDialog extends JDialog {

	private JPanel contentPanel;
	private JPanel buttonPane;
	private JButton btnRegister;
	private JButton btnCancel;
	private JLabel lblNewTrailer;
	private JLabel lblTrailerID;
	private JLabel lblDriver;
	private JLabel lblKilo;
	private JLabel lblMaxWeight;
	private JLabel lblProduct;
	private JLabel lblArrivalTime;
	private JLabel lblAt;
	private JLabel lblHourMinuts;
	private JTextField txfTrailerID;
	private JTextField txfMaxLoad;
	private JComboBox<Driver> cmbDriver;
	private JComboBox cmbMonth;
	private JComboBox cmbDate;

	private Controller controller;

	private JList<ProductType> lstProductType;
	private DefaultListModel lstProductTypeModel;
	private List<ProductType> products;
	private ArrayList<Driver> drivers;
	private JTextField txfHour;
	private JTextField txfMinuts;
	String[] months = { "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" };
	Integer[] dates = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
			17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };

	public NewTrailerDialog(JFrame owner) {
		super(owner);
		System.out.println("Created new Window");
		setTitle("Create New Trailer");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		InitContent();
	}

	private void InitContent() {
		products = new ArrayList<ProductType>();
		drivers = new ArrayList<Driver>();
		controller = new Controller();

		contentPanel = new JPanel();
		contentPanel.setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 385, 318);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblNewTrailer = new JLabel("Create New Trailer");
		lblNewTrailer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewTrailer.setBounds(10, 11, 152, 25);
		contentPanel.add(lblNewTrailer);

		lblTrailerID = new JLabel("Trailer ID:");
		lblTrailerID.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTrailerID.setBounds(20, 47, 150, 14);
		contentPanel.add(lblTrailerID);

		txfTrailerID = new JTextField();
		txfTrailerID.setColumns(10);
		txfTrailerID.setBounds(20, 65, 150, 20);
		contentPanel.add(txfTrailerID);

		lblMaxWeight = new JLabel("Maximum Load:");
		lblMaxWeight.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaxWeight.setBounds(20, 96, 150, 14);
		contentPanel.add(lblMaxWeight);

		txfMaxLoad = new JTextField();
		txfMaxLoad.setColumns(10);
		txfMaxLoad.setBounds(20, 115, 45, 20);
		contentPanel.add(txfMaxLoad);

		lblDriver = new JLabel("Designated Driver:");
		lblDriver.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDriver.setBounds(20, 146, 150, 14);
		contentPanel.add(lblDriver);

		lblKilo = new JLabel("Kilo");
		lblKilo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKilo.setBounds(70, 118, 46, 14);
		contentPanel.add(lblKilo);

		cmbDriver = new JComboBox<Driver>();
		cmbDriver.setBounds(20, 165, 152, 20);
		contentPanel.add(cmbDriver);
		cmbDriver.addActionListener(controller);

		lblProduct = new JLabel("Product Types:");
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProduct.setBounds(204, 47, 150, 14);
		contentPanel.add(lblProduct);

		lstProductTypeModel = new DefaultListModel<ProductType>();
		lstProductType = new JList<ProductType>(lstProductTypeModel);
		lstProductType.setBounds(204, 65, 150, 120);
		contentPanel.add(lstProductType);

		lblArrivalTime = new JLabel("Scheduled Arrival Time:");
		lblArrivalTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArrivalTime.setBounds(20, 196, 169, 14);
		contentPanel.add(lblArrivalTime);

		cmbMonth = new JComboBox(months);
		cmbMonth.setBounds(20, 216, 96, 20);
		contentPanel.add(cmbMonth);

		cmbDate = new JComboBox(dates);
		cmbDate.setBounds(126, 216, 44, 20);
		contentPanel.add(cmbDate);

		txfHour = new JTextField();
		txfHour.setBounds(204, 216, 32, 20);
		contentPanel.add(txfHour);
		txfHour.setColumns(10);

		lblAt = new JLabel("at:");
		lblAt.setBounds(180, 219, 46, 14);
		contentPanel.add(lblAt);

		txfMinuts = new JTextField();
		txfMinuts.setBounds(246, 216, 32, 20);
		contentPanel.add(txfMinuts);
		txfMinuts.setColumns(10);

		lblHourMinuts = new JLabel(":");
		lblHourMinuts.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHourMinuts.setBounds(239, 219, 5, 14);
		contentPanel.add(lblHourMinuts);

		buttonPane = new JPanel();
		buttonPane.setBackground(Color.GRAY);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnRegister = new JButton("Register");
		btnRegister.setActionCommand("OK");
		buttonPane.add(btnRegister);
		btnRegister.addActionListener(controller);

		btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("Cancel");
		buttonPane.add(btnCancel);
		btnCancel.addActionListener(controller);

		controller.fillCmbDrivers();
		controller.fillProductList();
	}

	private class Controller implements ActionListener {

		private void fillProductList() {
			ProductType[] arrayProducts = Dao.getProductTypes().toArray(
					new ProductType[0]);
			lstProductType.setListData(arrayProducts);
		}

		private void fillCmbDrivers() {
			for (int i = 0; i < Dao.getDrivers().size(); i++) {
				if (Dao.getDrivers().get(i).getTrailer() == null) {
					System.out.println("Added " + i);
					drivers.add(Dao.getDrivers().get(i));
				}
			}
			DefaultComboBoxModel<Driver> cmbDriverModel = new DefaultComboBoxModel<Driver>(
					drivers.toArray(new Driver[0]));
			cmbDriver.setModel(cmbDriverModel);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnRegister) {

				@SuppressWarnings("deprecation")
				Date date1 = new Date(113, cmbMonth.getSelectedIndex(),
						cmbDate.getSelectedIndex() + 1,
						Integer.parseInt(txfHour.getText()),
						Integer.parseInt(txfMinuts.getText()));
				Trailer t1 = Service.createTrailer(txfTrailerID.getText(),
						Integer.parseInt(txfMaxLoad.getText()), date1);
				Driver d1 = (Driver) cmbDriver.getSelectedItem();
				products = lstProductType.getSelectedValuesList();
				for (int i = 0; i < products.size(); i++) {
					t1.addProductType(products.get(i));
				}
				t1.setDriver(d1);
				NewTrailerDialog.this.setVisible(false);
			}

			if (e.getSource() == btnCancel) {

				NewTrailerDialog.this.setVisible(false);
			}

		}

	}
}
