package gui;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class LoadingBayView extends JFrame
{

	public LoadingBayView()
	{
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(600, 500, 569, 347);
		this.setTitle("Loading Bay View");
		InitContent();
		this.setVisible(true);
	}

	private JPanel contentPane;
	private JLabel lblTime, lbLoadingBay;
	private JTable table;

	private void InitContent()
	{

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTime = new JLabel("09:00");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTime.setBounds(485, 30, 68, 17);
		contentPane.add(lblTime);

		lbLoadingBay = new JLabel("Loading Bay view");
		lbLoadingBay.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbLoadingBay.setBounds(211, 11, 164, 14);
		contentPane.add(lbLoadingBay);

		table = new JTable();
		table.setBounds(47, 77, 506, 231);
		contentPane.add(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
}
