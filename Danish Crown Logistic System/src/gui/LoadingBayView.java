package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoadingBayView extends JFrame {

	public LoadingBayView() {
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(600, 500, 569, 347);
		this.setTitle("Loading Bay View");
		this.setVisible(true);
		InitContent();
	}

	private JLabel lblLoadingBayView;
	private JPanel contentPane;

	private void InitContent() {

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		lblLoadingBayView = new JLabel("Loading Bay View");
		contentPane.add(lblLoadingBayView, BorderLayout.NORTH);
	}

}
