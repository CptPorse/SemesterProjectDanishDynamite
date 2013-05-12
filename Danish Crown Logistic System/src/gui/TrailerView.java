package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TrailerView extends JFrame {

	public TrailerView() {
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(1000, 100, 569, 347);
		this.setTitle("Trailer View");
		this.setVisible(true);
		InitContent();

	}

	private JPanel contentPane;
	private JLabel lblTrailerView;

	private void InitContent() {

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		lblTrailerView = new JLabel("Trailer View");
		contentPane.add(lblTrailerView, BorderLayout.NORTH);
	}

}
