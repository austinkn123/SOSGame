package productSOSgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	public static JPanel contentPane = new JPanel();
	private JPanel initPanel = new JPanel();
	
	protected int CANVAS_WIDTH;
	protected int CANVAS_HEIGHT;
	public static int CELL_SIZE = 100; 
	
	private Board board;
	private GeneralGameBoard generalGame;
	private SimpleGameBoard simpleGame;
	
	private ButtonGroup modeGroup = new ButtonGroup();
	private ButtonGroup redPlayerGroup = new ButtonGroup();
	private ButtonGroup bluePlayerGroup = new ButtonGroup();
	private ButtonGroup bluePlayerTypeGroup = new ButtonGroup();
	private ButtonGroup redPlayerTypeGroup = new ButtonGroup();
	
	public JTextField sizeInput = new JTextField();
	
	
	protected static JPanel redPlayerPanel = new JPanel();
	protected static JPanel bluePlayerPanel = new JPanel();
	protected JLabel redPlayerLabel = new JLabel("  Red Player  ");
	protected JLabel bluePlayerLabel = new JLabel("  Blue Player  ");
	
	public JButton enterButton = new JButton("Enter");
	
	private final JRadioButton simpleButton = new JRadioButton("Simple ");
	private final JRadioButton generalButton = new JRadioButton("General");
	
	private final JRadioButton humanPlayerBlue = new JRadioButton("Human");
	private final JRadioButton cpuPlayerBlue = new JRadioButton("CPU");
	private final JRadioButton humanPlayerRed = new JRadioButton("Human");
	private final JRadioButton cpuPlayerRed = new JRadioButton("CPU");
	
	private final JRadioButton sPlayerRed = new JRadioButton("S");
	private final JRadioButton oPlayerRed = new JRadioButton("O");
	private final JRadioButton sPlayerBlue = new JRadioButton("S");
	private final JRadioButton oPlayerBlue = new JRadioButton("O");
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	private int size;
	private String modeString = "Not Selected";
	private char playerKeyRed = 'X';
	private char playerKeyBlue = 'X';
	private char cpuPlayerKeyRed = 'X';
	private char cpuPlayerKeyBlue = 'X';
	private char humanPlayerKeyRed = 'X';
	private char humanPlayerKeyBlue = 'X';


	/**
	 * Create the frame.
	 * @param board 
	 */
	public GUI(Board board) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.board = board;;
		setContentPane(new GeneralGameBoard(), new SimpleGameBoard());

		board.setSizeBoard(size);
		
		setTitle("SOS Game");
		setContentPane(contentPane);
		pack(); 
		setVisible(true);
		
	}
	
	private void setContentPane(GeneralGameBoard generalGame, SimpleGameBoard simpleGame){
		contentPane.setLayout(new BorderLayout());
		this.generalGame = generalGame;
		this.simpleGame = simpleGame;
		panel();
	}
	
	private void panel(){
		//SET INIT PANEL
		initPanel.setLayout(new GridBagLayout());
		
		
		contentPane.add(initPanel, BorderLayout.NORTH);
		contentPane.add(redPlayerPanel, BorderLayout.WEST);
		contentPane.add(bluePlayerPanel, BorderLayout.EAST);

		
		//TITLE LABEL
		JLabel titleLabel = new JLabel("SOS GAME");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		titleLabel.setBounds(10, 10, 195, 47);
		gbc.gridwidth = 7;
		gbc.gridx = 0;
		gbc.gridy = 0;
		initPanel.add(titleLabel, gbc);
		
		//SIZE LABEL
		JLabel sizeLabel = new JLabel("Enter Size(>2):");
		sizeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		sizeLabel.setBounds(10, 10, 195, 47);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		initPanel.add(sizeLabel, gbc);
		
		//TEXTFIELD SIZE
		sizeInput.setHorizontalAlignment(SwingConstants.CENTER);
		sizeInput.setText("");
		sizeInput.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sizeInput.setBounds(215, 17, 47, 33);
		sizeInput.setColumns(5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		initPanel.add(sizeInput, gbc);
		
		//ENTER BUTTON
		enterButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		enterButton.setBounds(272, 14, 92, 39);
		gbc.gridwidth = 7;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 5;
		initPanel.add(enterButton, gbc);
		
		//SIZE LABEL
		JLabel modeLabel = new JLabel("  Mode: ");
		modeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		modeLabel.setBounds(10, 10, 195, 47);
		gbc.gridwidth = 1;
		gbc.gridx = 4;
		gbc.gridy = 1;
		initPanel.add(modeLabel, gbc);
		
		//MODE BUTTONS AND PLAYER BUTTONS IN BUTTON GROUPS
		modeGroup.add(generalButton);
		modeGroup.add(simpleButton);
		redPlayerGroup.add(sPlayerRed);
		redPlayerGroup.add(oPlayerRed);
		bluePlayerGroup.add(sPlayerBlue);
		bluePlayerGroup.add(oPlayerBlue);
		
		redPlayerTypeGroup.add(humanPlayerRed);
		redPlayerTypeGroup.add(cpuPlayerRed);
		bluePlayerTypeGroup.add(humanPlayerBlue);
		bluePlayerTypeGroup.add(cpuPlayerBlue);
		
		//POSITIONING MODE BUTTONS
		gbc.gridx = 5;
		gbc.gridy = 1;
		initPanel.add(generalButton, gbc);
		gbc.gridx = 6;
		gbc.gridy = 1;
		initPanel.add(simpleButton, gbc);
		
//		POSITIONING HUMAN/CPU BUTTONS FOR RED/BLUE BUTTONS
		gbc.gridx = 0;
		gbc.gridy = 3;
		initPanel.add(humanPlayerRed, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		initPanel.add(cpuPlayerRed, gbc);
		gbc.gridx = 5;
		gbc.gridy = 3;
		initPanel.add(humanPlayerBlue, gbc);
		gbc.gridx = 6;
		gbc.gridy = 3;
		initPanel.add(cpuPlayerBlue, gbc);
		
		
		actionInit();
		
	}
	
	private void actionInit(){
		//TAKE IN SIZE INPUT
		sizeInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				//only accept numbers in text field
				if(!(Character.isDigit(c)) || (c==KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		}); 
		
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

		//ACTION FOR PLAYER BUTTONS 
		ActionListener buttonListenerPlayer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == sPlayerRed) {
					playerKeyRed = 'S';
				}
				if(e.getSource() == oPlayerRed) {
					playerKeyRed = 'O';
				}
				if(e.getSource() == sPlayerBlue) {
					playerKeyBlue = 'S';
				}
				if(e.getSource() == oPlayerBlue) {
					playerKeyBlue = 'O';
				}
				//Y for yes to identify if player is Human or CPU
				if(e.getSource() == humanPlayerRed) {
					humanPlayerKeyRed = 'R';
					cpuPlayerKeyRed = 'X';
				}
				if(e.getSource() == cpuPlayerRed) {
					cpuPlayerKeyRed = 'R';
				}
				if(e.getSource() == humanPlayerBlue) {
					humanPlayerKeyBlue = 'R';
					cpuPlayerKeyBlue = 'X';
				}
				if(e.getSource() == cpuPlayerBlue) {
					cpuPlayerKeyBlue = 'B';
				}
				
				
			}
		};
		
		sPlayerRed.addActionListener(buttonListenerPlayer);
		oPlayerRed.addActionListener(buttonListenerPlayer);
		sPlayerBlue.addActionListener(buttonListenerPlayer);
		oPlayerBlue.addActionListener(buttonListenerPlayer);
		
		humanPlayerRed.addActionListener(buttonListenerPlayer);
		cpuPlayerRed.addActionListener(buttonListenerPlayer);
		humanPlayerBlue.addActionListener(buttonListenerPlayer);
		cpuPlayerBlue.addActionListener(buttonListenerPlayer);
		
		
		
//		RED PLAYER LABEL
		JLabel redPlayerLabel = new JLabel("  Red Player  ");
		redPlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		redPlayerLabel.setForeground(new Color(255, 0, 0));
		gbc.gridx = 0;
		gbc.gridy = 2;
		initPanel.add(redPlayerLabel, gbc);
		
//		PLACING RED PLAYER'S S/O BUTTONS
		gbc.gridx = 0;
		gbc.gridy = 4;
		initPanel.add(sPlayerRed, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		initPanel.add(oPlayerRed, gbc);
		
//		BLUE PLAYER LABEL
		JLabel bluePlayerLabel = new JLabel("  Blue Player  ");
		bluePlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bluePlayerLabel.setForeground(new Color(0, 0, 255));
		gbc.gridx = 5;
		gbc.gridy = 2;
		initPanel.add(bluePlayerLabel, gbc);
		
//		PLACING BLUE PLAYER'S S/O BUTTONS
		gbc.gridx = 5;
		gbc.gridy = 4;
		initPanel.add(sPlayerBlue, gbc);
		gbc.gridx = 6;
		gbc.gridy = 4;
		initPanel.add(oPlayerBlue, gbc);
		
		redPlayerLabel.setForeground(Color.RED);
		bluePlayerLabel.setForeground(Color.BLUE);
		
//		ACTION FOR ENTER BUTTON
		enterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = sizeInput.getText();
				size = Integer.parseInt(text);
				if((humanPlayerKeyRed != 'X' || cpuPlayerKeyRed != 'X') || (humanPlayerKeyBlue != 'X' || cpuPlayerKeyBlue != 'X')) {
					if(playerKeyRed != 'X' && playerKeyBlue != 'X') {
						System.out.println(humanPlayerKeyRed + "=HR ---" + cpuPlayerKeyRed + "=CR ---" + 
								humanPlayerKeyBlue  + "=HB ---" + cpuPlayerKeyBlue + "=CB");
//						PASS IN SIZE, MODE, PLAYER SYMBOL
						board.setCpuRedPlayer(cpuPlayerKeyRed);
						board.setCpuBluePlayer(cpuPlayerKeyBlue);
						System.out.println(board.getCpuRedPlayer() + "=CR ---" + 
								board.getCpuBluePlayer() + "=CB");
						board.setSize(size);
						board.setModeString(modeString);
						board.setRedPlayerKey(playerKeyRed);
						board.setBluePlayerKey(playerKeyBlue);
						
						if(board.setMode(modeString, size) != -1) {
							if(board.setMode(modeString, size) == 1) {
								generalGame.setModeString(modeString);
								generalGame.setSizeBoard(size);
							}
							else if (board.setMode(modeString, size) == 2) {
								simpleGame.setModeString(modeString);
								simpleGame.setSizeBoard(size);
							}
							else {
								board.setModeString(modeString);
								board.setSizeBoard(size);
							}
							
							setGamePanel( board, generalGame, simpleGame, size);
							
//					**Only works after one use**
							validate();
							sizeInput.setEditable(false);
							enterButton.setEnabled(false);
							enterButton.removeMouseListener(this);
							generalButton.setEnabled(false);
							simpleButton.setEnabled(false);
							sPlayerRed.setEnabled(false);
							oPlayerRed.setEnabled(false);
							sPlayerBlue.setEnabled(false);
							oPlayerBlue.setEnabled(false);
							
							humanPlayerBlue.setEnabled(false);
							cpuPlayerBlue.setEnabled(false);
							humanPlayerRed.setEnabled(false);
							cpuPlayerRed.setEnabled(false);
							System.out.println("MODE: " + modeString);
						}
					}
				}
				
				
			}
		});
	}
	
	public void setGamePanel(Board board, GeneralGameBoard generalGame, SimpleGameBoard simpleGame, int pSize) {
//		SCREEN BOARD SIZE
		CANVAS_WIDTH = 500;  
		CANVAS_HEIGHT = 500;
		CELL_SIZE = CANVAS_WIDTH / pSize;
		GameBoardCanvas gameBoardCanvas = new GameBoardCanvas(board, generalGame, simpleGame, pSize);
		gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
		pack(); 
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
