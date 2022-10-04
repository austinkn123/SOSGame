package productSOSgame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import productSOSgame.Board;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.GridLayout;

public class GUI extends JFrame implements MouseListener{
	private Board board;
	private JPanel contentPane;
	private JTextField textField;
	private int size;
	private JButton[][] cellButtons;
	private JFrame frame = new JFrame(); 
//	public static JFrame frame = new JFrame(); 
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public GUI(Board board) {
		this.board = board;
		//frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		setVisible(true);
		setTitle("SOS Game");
		setBounds(100, 100, 574, 440);
		frame.setSize(500, 500);
		
		//jpanel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//label
		JLabel lblNewLabel = new JLabel("Size of board (3 or greater):");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 195, 47);
		contentPane.add(lblNewLabel);
		
		//input
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(215, 17, 47, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if(!(Character.isDigit(c)) || (c==KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
		
		//create board panel
		boardPanel();
		
		//Enter Click
		JButton btnNewButton = new JButton("Enter");
		contentPane.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(272, 14, 92, 39);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = textField.getText();
				size = Integer.parseInt(text);
				if(size > 2) {
					drawBoard(size);
//					Validating a container means laying out its subcomponents.
//					Layout-related changes, such as setting the bounds of a component, or adding a component to the container, 
//					invalidate the container automatically
//					**Only works after one use**
					validate();
					textField.setEditable(false);
					btnNewButton.setEnabled(false);
					btnNewButton.removeMouseListener(this);
				}
				
			}
		});

	}
	
	public Board getBoard(){
		return board;
	}
	
	public void boardPanel() {
		panel = new JPanel();
		panel.setBackground(new Color(128, 255, 128));
		panel.setBounds(104, 67, 300, 300);
		contentPane.add(panel);
	}
	
	//buttons to create board
	public void drawBoard(int numSize) {
		panel.setLayout(new GridLayout(numSize, numSize));
		
		System.out.print(numSize);
		System.out.println();
		cellButtons = new JButton[numSize][numSize];
		for(int row = 0; row < numSize; row++) {
			for(int col = 0; col < numSize; col++) {
				cellButtons[row][col] = new JButton("-");
				panel.add(cellButtons[row][col]);
				
			}
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//board size static for now
					new GUI(new Board(3));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
