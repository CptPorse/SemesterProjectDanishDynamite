package gui;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoadingBayView extends JFrame {

	public LoadingBayView() {
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(600, 500, 569, 347);
		this.setTitle("Loading Bay View");
		InitContent();
		this.setVisible(true);
	}

	private JPanel contentPane;
	private JLabel lblTime, lbLoadingBay;
	private JComboBox comboBox;
	private JList lstLoadingInfo;

	private void InitContent() {

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBox = new JComboBox();
		comboBox.setBounds(175, 30, 178, 20);
		contentPane.add(comboBox);

		lstLoadingInfo = new JList();
		lstLoadingInfo.setBounds(30, 65, 503, 230);
		contentPane.add(lstLoadingInfo);

		lblTime = new JLabel("09:00");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTime.setBounds(485, 30, 68, 17);
		contentPane.add(lblTime);

		lbLoadingBay = new JLabel("Loading Bay");
		lbLoadingBay.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbLoadingBay.setBounds(211, 11, 99, 14);
		contentPane.add(lbLoadingBay);
	}
}
