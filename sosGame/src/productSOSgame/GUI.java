package productSOSgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import productSOSgame.Board;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;


public class GUI extends JFrame{
	
	public static final int CELL_SIZE = 100; 
	public static final int GRID_WIDTH = 8;
	public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; 

	public static final int CELL_PADDING = CELL_SIZE / 6;
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; 
	public static final int SYMBOL_STROKE_WIDTH = 8; 

	private int CANVAS_WIDTH;
	private int CANVAS_HEIGHT;

	private GameBoardCanvas gameBoardCanvas; 

	private Board board;
	private GeneralGameBoard generalGame;
	private SimpleGameBoard simpleGame;
	
	private JPanel initPanel = new JPanel();
	private JPanel modePanel = new JPanel();
	private JTextField textField = new JTextField();
	private JButton enterButton = new JButton("Enter");
	private Container contentPane = getContentPane();
	private static int size = 3;
	private ButtonGroup modeGroup = new ButtonGroup();
	private final JRadioButton simpleButton = new JRadioButton("Simple Mode  ");
	private final JRadioButton generalButton = new JRadioButton("General Mode  ");
	private String modeString = "Not Selected";
	/**
	 * Create the frame.
	 */
	public GUI(Board board) {
		this.board = board;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane();
		//set size for numbers
		pack(); 
		setTitle("SOS Game");
		setVisible(true);  
	}
	
	public Board getBoard(){
		return board;
	}
	
	private void setContentPane(){
		contentPane.setLayout(new BorderLayout());
		panel();

	}
	
	private void panel(){
		//SET INIT PANEL
		initPanel.setLayout(new BorderLayout());
		contentPane.add(initPanel, BorderLayout.NORTH);
		
		//TITLE LABEL
		JLabel titleLabel = new JLabel("SOS GAME");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titleLabel.setBounds(10, 10, 195, 47);
		initPanel.add(titleLabel, BorderLayout.NORTH);
		
		
		//MODE BUTTONS
		modePanel.setLayout(new BorderLayout());
		initPanel.add(modePanel, BorderLayout.EAST);
		modeGroup.add(generalButton);
		modeGroup.add(simpleButton);
		modePanel.add(generalButton, BorderLayout.NORTH);
		modePanel.add(simpleButton, BorderLayout.CENTER);

		//SIEZE LABEL
		JLabel sizeLabel = new JLabel("Enter Size(>2):");
		sizeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sizeLabel.setBounds(10, 10, 195, 47);
		initPanel.add(sizeLabel, BorderLayout.WEST);
		
		//TEXTFIELD SIZE
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(215, 17, 47, 33);
		textField.setColumns(5);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				//only accept numbers in text field
				if(!(Character.isDigit(c)) || (c==KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
		initPanel.add(textField, BorderLayout.CENTER);
		

		//ENTER BUTTON
		enterButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		enterButton.setBounds(272, 14, 92, 39);
		initPanel.add(enterButton, BorderLayout.SOUTH);
		
		actionInit();
	}
	
	private void actionInit(){
		//ACTION FOR MODE BUTTONS
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == generalButton) {
					System.out.println("DA GENEREAL");
					modeString = "GENERAL";
				}
				else if(e.getSource() == simpleButton) {
					System.out.println("DA SIMP");
					modeString = "SIMPLE";
				}
			}
		};
		generalButton.addActionListener(buttonListener);
		simpleButton.addActionListener(buttonListener);
		
		//ACTION FOR ENTER BUTTON
		enterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = textField.getText();
				size = Integer.parseInt(text);
				if(size > 2 && !(modeString == "Not Selected")) {
					setGamePanel();
//					Validating a container means laying out its subcomponents.
//					Layout-related changes, such as setting the bounds of a component, or adding a component to the container, 
//					invalidate the container automatically
//					**Only works after one use**
					validate();
					textField.setEditable(false);
					enterButton.setEnabled(false);
					enterButton.removeMouseListener(this);
					generalButton.setEnabled(false);
					simpleButton.setEnabled(false);
					System.out.println(modeString);
				}
				
			}
		});
	}
	
	private void setGamePanel(){
		gameBoardCanvas = new GameBoardCanvas(); 
		//Change size
		CANVAS_WIDTH = CELL_SIZE * size;  
		CANVAS_HEIGHT = CELL_SIZE * size;
		//Add numbers to w/h to increase size of window
		gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
		//Resize for board
		pack(); 
	}
	
	class GameBoardCanvas extends JPanel {
		
		GameBoardCanvas(){

		}
		
		@Override
		public void paintComponent(Graphics g) { 
			super.paintComponent(g);   
			setBackground(Color.WHITE);
			drawGridLines(g);
		}
		
		private void drawGridLines(Graphics g){
			//Have to change these sizes too
			g.setColor(Color.LIGHT_GRAY);
			for (int row = 1; row < size; row++) {
				g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
						CANVAS_WIDTH-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
			}
			for (int col = 1; col < size; col++) {
				g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
						GRID_WIDTH, CANVAS_HEIGHT-1, GRID_WIDTH, GRID_WIDTH);
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
					GUI frame = new GUI(new Board(size));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}
