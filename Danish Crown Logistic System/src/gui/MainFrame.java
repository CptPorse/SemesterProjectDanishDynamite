package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Order;
import model.SubOrder;

import org.eclipse.jdt.annotation.NonNullByDefault;

import service.Service;
import dao.Dao;

//Author: Jens Nyberg Porse
public class MainFrame extends JFrame {

	private static MainFrame frame;
	private Controller controller;

	public static void main(String[] args) {

		frame = new MainFrame();
		frame.pack();
		frame.setVisible(true);
	}

	public MainFrame() {
		this.setResizable(true);
		this.setTitle("Logistic System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 569, 347);
		this.getContentPane().getSize();
		this.controller = new Controller();
		Service.startUpData();
		InitContent();
		System.out.println(Dao.getProductTypes());
		System.out.println(Dao.getTrailer());
		System.out.println(Dao.getDrivers());
		System.out.println(Dao.getOrders());

	}

	public static MainFrame getInstance() {
		return frame;
	}

	private JPanel contentPane;
	private JPanel panelExternalSystem;
	private JPanel panelDanishCrown;
	private JTabbedPane tabbedPane;
	private JTextField txfOrderID;
	private JTextField txfWeightMargin;
	private JTextField txfSubOrderArrival;
	private JTextField txfSubProduct;
	private JTextField txfSubWeight;
	private JTextField txfSubTrailer;
	private JTextField txfLoadingTime;
	private JLabel lblOrderList, lblOrderId, lblWeightMargin,
			lblSubOrderArrival, lblSuborders, lblSubProduct, lblSubWeight,
			lblSubTrailer, lblLoadingTime;
	private JList<Order> lstOrders;
	private JList<SubOrder> lstSubOrders;
	private DefaultListModel<SubOrder> lstSubOrderModel;
	private DefaultListModel<Order> lstOrderModel;
	private JButton btnNewTrailer, btnNewDriver, btnNewOrder;
	private JLabel lblWeighKilo;
	private JLabel lblMinuts;
	private JLabel lblMarginKilo;

	private void InitContent() {

		lstOrders = new JList<Order>();
		lstSubOrders = new JList<SubOrder>();

		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(555, 322));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 555, 322);
		contentPane.add(tabbedPane);

		panelExternalSystem = new JPanel();
		panelExternalSystem.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("External System", null, panelExternalSystem, null);
		panelExternalSystem.setLayout(null);

		btnNewTrailer = new JButton("New Trailer");
		btnNewTrailer.setBounds(398, 245, 105, 35);
		panelExternalSystem.add(btnNewTrailer);
		btnNewTrailer.addActionListener(controller);

		btnNewDriver = new JButton("New Driver");
		btnNewDriver.setBounds(230, 245, 105, 35);
		panelExternalSystem.add(btnNewDriver);
		btnNewDriver.addActionListener(controller);

		btnNewOrder = new JButton("New Order");
		btnNewOrder.setBounds(64, 245, 105, 35);
		panelExternalSystem.add(btnNewOrder);
		btnNewOrder.addActionListener(controller);

		lstOrderModel = new DefaultListModel<Order>();
		lstOrders = new JList<Order>(lstOrderModel);
		lstOrders.setBounds(18, 30, 180, 200);
		lstOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstOrders.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				if (!evt.getValueIsAdjusting()) {
					if (lstOrders.getSelectedIndex() != -1) {
						updateOrderView(lstOrders.getSelectedValue());
					}
				}
			}
		});

		panelExternalSystem.add(lstOrders);

		lblOrderList = new JLabel("Orders:");
		lblOrderList.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrderList.setBounds(18, 10, 151, 14);
		panelExternalSystem.add(lblOrderList);

		lblOrderId = new JLabel("Order ID:");
		lblOrderId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrderId.setBounds(220, 10, 146, 14);
		panelExternalSystem.add(lblOrderId);

		txfOrderID = new JTextField();
		txfOrderID.setEditable(false);
		txfOrderID.setBounds(220, 30, 146, 20);
		panelExternalSystem.add(txfOrderID);
		txfOrderID.setColumns(10);

		lblWeightMargin = new JLabel("Weight Margin:");
		lblWeightMargin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWeightMargin.setBounds(220, 55, 146, 14);
		panelExternalSystem.add(lblWeightMargin);

		txfWeightMargin = new JTextField();
		txfWeightMargin.setEditable(false);
		txfWeightMargin.setBounds(220, 75, 94, 20);
		panelExternalSystem.add(txfWeightMargin);
		txfWeightMargin.setColumns(10);

		lblSubOrderArrival = new JLabel("Sub-Order ET Arrival:");
		lblSubOrderArrival.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubOrderArrival.setBounds(220, 100, 146, 14);
		panelExternalSystem.add(lblSubOrderArrival);

		txfSubOrderArrival = new JTextField();
		txfSubOrderArrival.setEditable(false);
		txfSubOrderArrival.setBounds(220, 120, 146, 20);
		panelExternalSystem.add(txfSubOrderArrival);
		txfSubOrderArrival.setColumns(10);

		lstSubOrderModel = new DefaultListModel<SubOrder>();
		lstSubOrders = new JList<SubOrder>(lstSubOrderModel);
		lstSubOrders.setBounds(388, 30, 146, 110);
		panelExternalSystem.add(lstSubOrders);
		lstSubOrders.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				if (!evt.getValueIsAdjusting()) {
					if (lstSubOrders.getSelectedIndex() != -1) {
						updateSubOrderView(lstSubOrders.getSelectedValue());
					}
				}
			}
		});

		lblSuborders = new JLabel("Sub-Orders:");
		lblSuborders.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSuborders.setBounds(388, 10, 160, 14);
		panelExternalSystem.add(lblSuborders);

		lblSubProduct = new JLabel("Sub-Order Product Type:");
		lblSubProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubProduct.setBounds(220, 145, 146, 14);
		panelExternalSystem.add(lblSubProduct);

		txfSubProduct = new JTextField();
		txfSubProduct.setEditable(false);
		txfSubProduct.setBounds(220, 165, 146, 20);
		panelExternalSystem.add(txfSubProduct);
		txfSubProduct.setColumns(10);

		txfSubWeight = new JTextField();
		txfSubWeight.setEditable(false);
		txfSubWeight.setColumns(10);
		txfSubWeight.setBounds(220, 210, 105, 20);
		panelExternalSystem.add(txfSubWeight);

		lblSubWeight = new JLabel("Sub-Order Weight");
		lblSubWeight.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubWeight.setBounds(220, 190, 146, 14);
		panelExternalSystem.add(lblSubWeight);

		txfSubTrailer = new JTextField();
		txfSubTrailer.setEditable(false);
		txfSubTrailer.setColumns(10);
		txfSubTrailer.setBounds(388, 165, 146, 20);
		panelExternalSystem.add(txfSubTrailer);

		lblSubTrailer = new JLabel("Sub-Order Trailer");
		lblSubTrailer.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubTrailer.setBounds(388, 145, 146, 14);
		panelExternalSystem.add(lblSubTrailer);

		lblLoadingTime = new JLabel("Estimated Loading Time");
		lblLoadingTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLoadingTime.setBounds(388, 190, 146, 14);
		panelExternalSystem.add(lblLoadingTime);

		txfLoadingTime = new JTextField();
		txfLoadingTime.setEditable(false);
		txfLoadingTime.setColumns(10);
		txfLoadingTime.setBounds(388, 211, 94, 20);
		panelExternalSystem.add(txfLoadingTime);

		lblWeighKilo = new JLabel("Kilo");
		lblWeighKilo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblWeighKilo.setBounds(328, 213, 25, 14);
		panelExternalSystem.add(lblWeighKilo);

		lblMinuts = new JLabel("Minuts");
		lblMinuts.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMinuts.setBounds(484, 213, 37, 14);
		panelExternalSystem.add(lblMinuts);

		lblMarginKilo = new JLabel("Kilo");
		lblMarginKilo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMarginKilo.setBounds(317, 78, 25, 14);
		panelExternalSystem.add(lblMarginKilo);

		panelDanishCrown = new JPanel();
		tabbedPane.addTab("Danish Crown", null, panelDanishCrown, null);
		panelDanishCrown.setLayout(null);

		controller.fillLstOrders();
	}

	public void updateLstOrder() {
		System.out.println("I updated from " + this.getName());
		controller.fillLstOrders();
	}

	public void updateOrderView(Order order) {
		txfOrderID.setText("" + order.getOrderNumber());
		txfWeightMargin.setText("" + order.getWeightMargin());
		txfSubOrderArrival.setText("");
		txfLoadingTime.setText("");
		txfSubTrailer.setText("");
		txfSubWeight.setText("");
		txfSubProduct.setText("");

		controller.fillLstSubOrder(order);
	}

	public void updateSubOrderView(SubOrder subOrder) {
		txfSubOrderArrival.setText(""
				+ subOrder.getTrailer().getTimeOfArrival().toLocaleString());
		txfLoadingTime.setText("" + subOrder.getEstimatedLoadingTime());
		txfSubTrailer.setText("" + subOrder.getTrailer());
		txfSubWeight.setText("" + subOrder.getEstimatedWeight());
		txfSubProduct.setText("" + subOrder.getProductType());
	}

	public class Controller implements ActionListener {

		private void fillLstOrders() {
			Order[] arrayOrder = Dao.getOrders().toArray(new Order[0]);
			lstOrders.setListData(arrayOrder);
		}

		private void fillLstSubOrder(Order order) {
			SubOrder[] arraySubOrder = order.getSubOrders().toArray(
					new SubOrder[0]);
			lstSubOrders.setListData(arraySubOrder);
		}

		@Override
		@NonNullByDefault(false)
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnNewOrder) {
				NewOrderDialog createOrderDialog = new NewOrderDialog(
						MainFrame.getInstance());
				createOrderDialog.setVisible(true);

				// waiting for dialog NewOrderDialog to close

			}

			if (e.getSource() == btnNewTrailer) {
				NewTrailerDialog createTrailerDialog = new NewTrailerDialog(
						MainFrame.getInstance());
				createTrailerDialog.setVisible(true);

				// waiting for dialog NewOrderDialog to close

			}

			if (e.getSource() == btnNewDriver) {
				NewDriverDialog createDriverDialog = new NewDriverDialog(
						MainFrame.getInstance());
				createDriverDialog.setVisible(true);

				// waiting for dialog NewOrderDialog to close

			}
		}
	}
}
