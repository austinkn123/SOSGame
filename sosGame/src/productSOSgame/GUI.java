package productSOSgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.border.EmptyBorder;

import productSOSgame.Board.Cell;

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
	
	JTextField sizeInput = new JTextField();
	JButton enterButton = new JButton("Enter");
	
	private JPanel redPlayerPanel = new JPanel();
	private JPanel bluePlayerPanel = new JPanel();
	protected JLabel redPlayerLabel = new JLabel("  Red Player  ");
	protected JLabel bluePlayerLabel = new JLabel("  Blue Player  ");
	
	private final JRadioButton simpleButton = new JRadioButton("Simple Mode");
	private final JRadioButton generalButton = new JRadioButton("General Mode");
	private final JRadioButton sPlayerRed = new JRadioButton("S");
	private final JRadioButton oPlayerRed = new JRadioButton("O");
	private final JRadioButton sPlayerBlue = new JRadioButton("S");
	private final JRadioButton oPlayerBlue = new JRadioButton("O");
	protected JLabel redPlayerPoints;
	protected JLabel bluePlayerPoints;
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	int size;
	String modeString = "Not Selected";
	char playerKeyRed = 'X';
	char playerKeyBlue = 'X';



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
		
		//TITLE LABEL
		JLabel titleLabel = new JLabel("SOS GAME");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		titleLabel.setBounds(10, 10, 195, 47);
		gbc.gridwidth = 6;
		gbc.gridx = 0;
		gbc.gridy = 0;
		initPanel.add(titleLabel, gbc);
		
		//SIZE LABEL
		JLabel sizeLabel = new JLabel("Enter Size(>2):");
		sizeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
		gbc.gridwidth = 6;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 3;
		initPanel.add(enterButton, gbc);
		
		//SIZE LABEL
		JLabel modeLabel = new JLabel("  Choose a Mode: ");
		modeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		modeLabel.setBounds(10, 10, 195, 47);
		gbc.gridwidth = 1;
		gbc.gridx = 3;
		gbc.gridy = 1;
		initPanel.add(modeLabel, gbc);
		
		//MODE BUTTONS and PLAYER BUTTONS
		modeGroup.add(generalButton);
		modeGroup.add(simpleButton);
		redPlayerGroup.add(sPlayerRed);
		redPlayerGroup.add(oPlayerRed);
		bluePlayerGroup.add(sPlayerBlue);
		bluePlayerGroup.add(oPlayerBlue);
		gbc.gridx = 4;
		gbc.gridy = 1;
		initPanel.add(generalButton, gbc);
		gbc.gridx = 5;
		gbc.gridy = 1;
		initPanel.add(simpleButton, gbc);
		
		contentPane.add(redPlayerPanel, BorderLayout.WEST);
		contentPane.add(bluePlayerPanel, BorderLayout.EAST);
		
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

		//ACTION FOR RED PLAYER BUTTONS 
		ActionListener buttonListenerRedPlayer = new ActionListener() {
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
			}
		};
		
		sPlayerRed.addActionListener(buttonListenerRedPlayer);
		oPlayerRed.addActionListener(buttonListenerRedPlayer);
		sPlayerBlue.addActionListener(buttonListenerRedPlayer);
		oPlayerBlue.addActionListener(buttonListenerRedPlayer);
		
		//RED PLAYER LABEL
		JLabel redPlayerLabel = new JLabel("  Red Player  ");
		redPlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		redPlayerLabel.setForeground(new Color(255, 0, 0));
		gbc.gridx = 0;
		gbc.gridy = 2;
		initPanel.add(redPlayerLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		initPanel.add(sPlayerRed, gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		initPanel.add(oPlayerRed, gbc);
		
		//BLUE PLAYER LABEL
		JLabel bluePlayerLabel = new JLabel("  Blue Player  ");
		bluePlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bluePlayerLabel.setForeground(new Color(0, 0, 255));
		gbc.gridx = 3;
		gbc.gridy = 2;
		initPanel.add(bluePlayerLabel, gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 2;
		initPanel.add(sPlayerBlue, gbc);
		gbc.gridx = 5;
		gbc.gridy = 2;
		initPanel.add(oPlayerBlue, gbc);
		
		//ACTION FOR ENTER BUTTON
		enterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = sizeInput.getText();
				size = Integer.parseInt(text);
				if(playerKeyRed != 'X' && playerKeyBlue != 'X') {
					board.setSize(size);
					board.setModeString(modeString);
					board.setRedPlayerKey(playerKeyRed);
					board.setBluePlayerKey(playerKeyBlue);
					if(board.setMode(modeString, size) != -1) {
						if(board.setMode(modeString, size) == 1) {
							generalGame.setSizeBoard(size);
						}
						else if (board.setMode(modeString, size) == 2) {
							simpleGame.setSizeBoard(size);
						}
						else {
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
					System.out.println("MODE: " + modeString);
				}
				}
				
			}
		});
	}
	
	public void setGamePanel(Board board, GeneralGameBoard generalGame, SimpleGameBoard simpleGame, int pSize) {
		CANVAS_WIDTH = 600;  
		CANVAS_HEIGHT = 600;
		CELL_SIZE = CANVAS_WIDTH / pSize;
		GameBoardCanvas gameBoardCanvas = new GameBoardCanvas(board, generalGame, simpleGame, pSize);
		gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
		pack(); 
	}
	
	//For testing GUI
	public void nonEmptyBoard(GeneralGameBoard generalGame, SimpleGameBoard simpleGame, int pSize) {
		CANVAS_WIDTH = 600;  
		CANVAS_HEIGHT = 600;
		CELL_SIZE = CANVAS_WIDTH / pSize;
		GameBoardCanvas gameBoardCanvas = new GameBoardCanvas(board, generalGame, simpleGame, pSize);
		gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT)); 
//		gameBoardCanvas.makeMoveonBoard(generalGame, simpleGame, 1, 1, pSize, playerKeyRed, playerKeyBlue, "GENERAL");
//		gameBoardCanvas.makeMoveonBoard(generalGame, simpleGame, 0, 0, pSize, playerKeyRed, playerKeyBlue, "GENERAL");
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
