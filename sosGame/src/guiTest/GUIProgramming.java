package guiTest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import java.awt.Color;

public class GUIProgramming extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIProgramming frame = new GUIProgramming();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIProgramming() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Something:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(59, 80, 161, 59);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Press Left");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(167, 149, 130, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Press Right");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_1.setBounds(398, 145, 130, 68);
		contentPane.add(btnNewButton_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Press This Radio Button");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		rdbtnNewRadioButton.setBounds(179, 295, 260, 48);
		contentPane.add(rdbtnNewRadioButton);
		
		JFormattedTextField txtMsg = new JFormattedTextField();
		txtMsg.setBounds(265, 94, 216, 38);
		contentPane.add(txtMsg);
		
		JLabel lblNewLabel_1 = new JLabel("S Player");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(21, 205, 78, 38);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("O Player");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(33, 271, 78, 38);
		contentPane.add(lblNewLabel_1_1);
	}
}
