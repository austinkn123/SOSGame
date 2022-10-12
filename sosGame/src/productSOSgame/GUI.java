package productSOSgame;

import java.awt.BasicStroke;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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

import productSOSgame.Board.Cell;
import productSOSgame.GeneralGameBoard.GameState;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
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
	private GeneralGameBoard generalGame = new GeneralGameBoard();
	private SimpleGameBoard simpleGame = new SimpleGameBoard();
	
	private JPanel initPanel = new JPanel();
	private JPanel modePanel = new JPanel();
	private JPanel redPlayerPanel = new JPanel();
	private JPanel bluePlayerPanel = new JPanel();
	private JLabel redPlayerLabel = new JLabel("  Red Player  ");
	private JLabel bluePlayerLabel = new JLabel("  Blue Player  ");
	private final JRadioButton sPlayerRed = new JRadioButton("S");
	private final JRadioButton oPlayerRed = new JRadioButton("O");
	private final JRadioButton sPlayerBlue = new JRadioButton("S");
	private final JRadioButton oPlayerBlue = new JRadioButton("O");
	private JLabel redPlayerPoints;
	private JLabel bluePlayerPoints;
	private JTextField textField = new JTextField();
	private JButton enterButton = new JButton("Enter");
	private Container contentPane = getContentPane();
	//PROBLEM WITH SIZE
	private int size;
	private final JRadioButton simpleButton = new JRadioButton("Simple Mode");
	private final JRadioButton generalButton = new JRadioButton("General Mode");
	private ButtonGroup modeGroup = new ButtonGroup();
	private ButtonGroup redPlayerGroup = new ButtonGroup();
	private ButtonGroup bluePlayerGroup = new ButtonGroup();
	private String modeString = "Not Selected";
	private char playerKeyRed = 'X';
	/**
	 * Create the frame.
	 */
	public GUI(Board board) {
		this.board = board;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new GeneralGameBoard(), new SimpleGameBoard());

		board.setSize(size);
		System.out.println(size);
		pack(); 
		setTitle("SOS Game");
		setVisible(true);  
	}
	
	public Board getBoard(){
		return board;
	}
	
	private void setContentPane(GeneralGameBoard generalGame, SimpleGameBoard simpleGame){
		contentPane.setLayout(new BorderLayout());
		this.generalGame = generalGame;
		this.simpleGame = simpleGame;
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
		
		
		//MODE BUTTONS and PLAYER BUTTONS
		modePanel.setLayout(new BorderLayout());
		initPanel.add(modePanel, BorderLayout.EAST);
		modeGroup.add(generalButton);
		modeGroup.add(simpleButton);
		redPlayerGroup.add(sPlayerRed);
		redPlayerGroup.add(oPlayerRed);
		bluePlayerGroup.add(sPlayerBlue);
		bluePlayerGroup.add(oPlayerBlue);
		modePanel.add(generalButton, BorderLayout.NORTH);
		modePanel.add(simpleButton, BorderLayout.CENTER);

		

		//SIZE LABEL
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
					modeString = "GENERAL";
				}
				else if(e.getSource() == simpleButton) {
					modeString = "SIMPLE";
				}
			}
		};
		generalButton.addActionListener(buttonListener);
		simpleButton.addActionListener(buttonListener);
		
		sPlayerBlue.setEnabled(false);
		oPlayerBlue.setEnabled(false);
		
		//ACTION FOR RED PLAYER BUTTONS 
		ActionListener buttonListenerRedPlayer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == sPlayerRed) {
					sPlayerBlue.setEnabled(true);
					oPlayerBlue.setEnabled(true);
					playerKeyRed = 'R';
					oPlayerBlue.doClick();
					sPlayerBlue.setEnabled(false);
					oPlayerBlue.setEnabled(false);
				}
				if(e.getSource() == oPlayerRed) {
					sPlayerBlue.setEnabled(true);
					oPlayerBlue.setEnabled(true);
					playerKeyRed = 'B';
					sPlayerBlue.doClick();
					sPlayerBlue.setEnabled(false);
					oPlayerBlue.setEnabled(false);
				}
			}
		};
		
		sPlayerRed.addActionListener(buttonListenerRedPlayer);
		oPlayerRed.addActionListener(buttonListenerRedPlayer);
		
		//Add Player Panels
		redPlayerPanel.setLayout(new BorderLayout());
		bluePlayerPanel.setLayout(new BorderLayout());
	
		redPlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		redPlayerLabel.setForeground(new Color(255, 0, 0));
		
		bluePlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bluePlayerLabel.setForeground(new Color(0, 0, 255));

		bluePlayerPanel.add(bluePlayerLabel, BorderLayout.NORTH);
		bluePlayerPanel.add(oPlayerBlue, BorderLayout.EAST);
		bluePlayerPanel.add(sPlayerBlue, BorderLayout.WEST);
		redPlayerPanel.add(redPlayerLabel, BorderLayout.NORTH);
		redPlayerPanel.add(oPlayerRed, BorderLayout.EAST);
		redPlayerPanel.add(sPlayerRed, BorderLayout.WEST);
		
		
		contentPane.add(redPlayerPanel, BorderLayout.WEST);
		contentPane.add(bluePlayerPanel, BorderLayout.EAST);
		
		
		//ACTION FOR ENTER BUTTON
		enterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = textField.getText();
				size = Integer.parseInt(text);
				if(playerKeyRed != 'X') {
					if(board.setMode(modeString, size) != -1) {
						if(board.setMode(modeString, size) == 1) {
							generalGame.setSizeGeneral(size);
						}
						else if (board.setMode(modeString, size) == 2) {
							simpleGame.setSize(size);
						}
						else {
							board.setSize(size);
						}
					setGamePanel();
//					**Only works after one use**
					validate();
					textField.setEditable(false);
					enterButton.setEnabled(false);
					enterButton.removeMouseListener(this);
					generalButton.setEnabled(false);
					simpleButton.setEnabled(false);
					sPlayerRed.setEnabled(false);
					oPlayerRed.setEnabled(false);
					System.out.println("MODE: " + modeString);
				}
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
		
		if(board.setMode(modeString, size) == 1) {
			redPlayerPoints = new JLabel(String.valueOf(generalGame.getPointRed()));
			bluePlayerPoints = new JLabel(String.valueOf(generalGame.getPointBlue()));
			redPlayerPoints.setFont(new Font("Tahoma", Font.PLAIN, 30));
			redPlayerPoints.setForeground(new Color(255, 0, 0));
			bluePlayerPoints.setFont(new Font("Tahoma", Font.PLAIN, 30));
			
			redPlayerPanel.add(redPlayerPoints, BorderLayout.CENTER);
			bluePlayerPanel.add(bluePlayerPoints, BorderLayout.CENTER);
		}
		
		//Resize for board
		pack(); 
	}
	
	class GameBoardCanvas extends JPanel {
		
		GameBoardCanvas(){
			
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {  
						int rowSelected = e.getY() / CELL_SIZE;
						int colSelected = e.getX() / CELL_SIZE;
						
						if(board.setMode(modeString, size) == 1) {
							generalGame.makeMoveInGeneralMode(rowSelected, colSelected, size, playerKeyRed);
						}
						else if (board.setMode(modeString, size) == 2) {
							simpleGame.makeMove(rowSelected, colSelected, size);
						}
					repaint(); 
				}
			});

		}
		
		@Override
		public void paintComponent(Graphics g) { 
			super.paintComponent(g);   
			setBackground(Color.WHITE);
			drawGridLines(g);
			drawBoard(g);
			playerStatus();
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
		
		private void drawBoard(Graphics g){
			Graphics2D g2d = (Graphics2D)g;
			g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); 
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					int x1 = col * CELL_SIZE + CELL_PADDING;
					int y1 = row * CELL_SIZE + CELL_PADDING;
					if(board.setMode(modeString, size) == 1) {
						if (generalGame.getCell(row,col, size) == Cell.RED_PLAYER) { //Red Player
							if(playerKeyRed == 'R') {
								g2d.setColor(Color.RED);
								g2d.setFont(new Font("TimesRoman", Font.PLAIN, SYMBOL_SIZE+20)); 
								g2d.drawString("S", x1+5, y1+65);
							}
							else{
								g2d.setColor(Color.RED);
								g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
							}
							
							
						} else if (generalGame.getCell(row,col, size) == Cell.BLUE_PLAYER) { //Blue Player
							if(playerKeyRed == 'B') {
								g2d.setColor(Color.BLUE);
								g2d.setFont(new Font("TimesRoman", Font.PLAIN, SYMBOL_SIZE+20)); 
								g2d.drawString("S", x1+5, y1+65);
							}
							else {
								g2d.setColor(Color.BLUE);
								g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
							}

						}
					}
					else if(board.setMode(modeString, size) == 2) {
						if (simpleGame.getCell(row,col, size) == Cell.RED_PLAYER) { //Red Player
							if(playerKeyRed == 'R') {
								g2d.setColor(Color.RED);
								g2d.setFont(new Font("TimesRoman", Font.PLAIN, SYMBOL_SIZE+20)); 
								g2d.drawString("S", x1+5, y1+65);
							}
							else{
								g2d.setColor(Color.RED);
								g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
							}

						} else if (simpleGame.getCell(row,col, size) == Cell.BLUE_PLAYER) { //Blue Player

							if(playerKeyRed == 'B') {
								g2d.setColor(Color.BLUE);
								g2d.setFont(new Font("TimesRoman", Font.PLAIN, SYMBOL_SIZE+20)); 
								g2d.drawString("S", x1+5, y1+65);
							}
							else {
								g2d.setColor(Color.BLUE);
								g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
							}
							

						}
					}
					
				}
			}
		}
		
		private void playerStatus(){
				if(board.setMode(modeString, size) == 1) {
					if (generalGame.getTurn() == 'R') {
						redPlayerLabel.setForeground(new Color(255, 0, 0));
						bluePlayerLabel.setForeground(new Color(0, 0, 0));
						redPlayerPoints.setForeground(new Color(255, 0, 0));
						bluePlayerPoints.setForeground(new Color(0, 0, 0));
					} 
					else{
						redPlayerLabel.setForeground(new Color(0, 0, 0));
						bluePlayerLabel.setForeground(new Color(0, 0, 255));
						redPlayerPoints.setForeground(new Color(0, 0, 0));
						bluePlayerPoints.setForeground(new Color(0, 0, 255));
					}
					
					if(generalGame.getGameState() == GameState.RED_SCORES) {
						generalGame.addPointRed();
						System.out.println(generalGame.getPointRed());
						redPlayerPoints.setText(String.valueOf(generalGame.getPointRed()));
						System.out.println(generalGame.getTurn());
					}
					
					if(generalGame.getGameState() == GameState.BLUE_SCORES) {
						generalGame.addPointBlue();
						System.out.println(generalGame.getPointBlue());
						bluePlayerPoints.setText(String.valueOf(generalGame.getPointBlue()));
					}
				}
				else if (board.setMode(modeString, size) == 2){
					if (simpleGame.getTurn() == 'R') {
						redPlayerLabel.setForeground(new Color(255, 0, 0));
						bluePlayerLabel.setForeground(new Color(0, 0, 0));

					} else {
						redPlayerLabel.setForeground(new Color(0, 0, 0));
						bluePlayerLabel.setForeground(new Color(0, 0, 255));

					}
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
					GUI frame = new GUI(new Board());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}
