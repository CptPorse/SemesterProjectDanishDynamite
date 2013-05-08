package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

public class NewTrailerDialog extends JDialog {

	private JPanel contentPanel;
	private JPanel buttonPane;
	private JButton btnRegister;
	private JButton btnCancel;
	private JLabel lblNewTrailer;
	private JLabel lblTrailerID;
	private JLabel lblDriver;
	private JLabel lblTons;
	private JLabel lblMaxWeight;
	private JLabel lblProduct;
	private JTextField txfTrailerID;
	private JTextField txfMaxLoad;
	private JComboBox<Driver> cbbDriver;

	private Controller controller;

	private JList<ProductType> lstProductType;
	private DefaultListModel lstProductTypeModel;
	private List<ProductType> products;
	private ArrayList<Driver> drivers;

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
		setBounds(100, 100, 385, 270);
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

		lblTons = new JLabel("Tons");
		lblTons.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTons.setBounds(70, 118, 46, 14);
		contentPanel.add(lblTons);

		cbbDriver = new JComboBox<Driver>();
		cbbDriver.setBounds(20, 165, 152, 20);
		contentPanel.add(cbbDriver);
		cbbDriver.addActionListener(controller);

		lblProduct = new JLabel("Product Types:");
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProduct.setBounds(204, 47, 150, 14);
		contentPanel.add(lblProduct);

		lstProductTypeModel = new DefaultListModel<ProductType>();
		lstProductType = new JList<ProductType>(lstProductTypeModel);
		lstProductType.setBounds(204, 65, 150, 120);
		contentPanel.add(lstProductType);

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
			cbbDriver.setModel(cmbDriverModel);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnRegister) {

				Trailer t1 = Service.createTrailer(txfTrailerID.getText(),
						Integer.parseInt(txfMaxLoad.getText()));
				Driver d1 = (Driver) cbbDriver.getSelectedItem();
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
