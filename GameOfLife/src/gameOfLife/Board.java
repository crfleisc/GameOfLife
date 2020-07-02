package gameOfLife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel{
	private static final long serialVersionUID = 1L;
	private Cell[][] board;
	private int width, height;
	private final static int CELL_SIZE = 10;
	private final Color[] COLOURS = {Color.BLACK, Color.BLUE, Color.CYAN,
			Color.GREEN, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
	private final Color COLOUR = getRandomColor();
	
	public Board(int width, int height, int rate) {
		this.width = width;
		this.height = height;
		board = new Cell[width][height];
		populateBoard(rate);
	}
	
	/*
	 * Seeds the board with random living cells at a given rate
	 * 
	 * @param rate
	 * 			probability of rate/100 that a cell lives
	 */
	private void populateBoard(int rate) {
		if(rate < 0 || rate > 100) {
			throw new InvalidParameterException("Rate must be between 0-100");
		}
		
		boolean isAlive;
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				isAlive = seedRandomly(rate);
				board[i][j] = new Cell(board, new Point(i, j), isAlive);
			}
		}
	}
	
	private boolean seedRandomly(int rate) {
		if(rate > Math.random() * 100) 
			return true;
		return false;
	}
	
	/*
	 * Determines next state of board then updates it
	 */
	private void next() {
		for(Cell[] col : board) {
			for(Cell c : col) {
				c.setNextState();
			}
		}
		for(Cell[] col2 : board) {
			for(Cell c : col2) {
				c.update();
			}
		}

	}

	private void handInitBoard() {
//		board[2][2].setAlive();
//		board[2][3].setAlive();
//		board[2][4].setAlive();

//		board[4][3].setAlive();
//		board[4][4].setAlive();
//		board[4][5].setAlive();
//		board[3][4].setAlive();
		
//		board[0][4].setAlive();
//		board[1][4].setAlive();
//		board[2][4].setAlive();
//		board[6][4].setAlive();
//		board[7][4].setAlive();
//		board[8][4].setAlive();
//		board[4][0].setAlive();
//		board[4][1].setAlive();
//		board[4][2].setAlive();
//		board[4][6].setAlive();
//		board[4][7].setAlive();
//		board[4][8].setAlive();
		
		
//		// Box
//		board[1][1].setAlive();
//		board[1][2].setAlive();
//		board[2][1].setAlive();
//		board[2][2].setAlive();
		
//		// Box
//		board[0][0].setAlive();
//		board[0][1].setAlive();
//		board[1][0].setAlive();
//		board[1][1].setAlive();
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board[i][j].isAlive()) {
                    //g.setColor(getRandomColor());
                    g.setColor(COLOUR);
                	g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }
    
	private Color getRandomColor() {
		return COLOURS[(int) Math.floor(Math.random() * COLOURS.length)];
	}

	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int boardWide = screenSize.width / CELL_SIZE;
		int boardHeight = screenSize.height / CELL_SIZE;
		int RATE = 15;
		
		Board b = new Board(boardHeight, boardWide, RATE);

		JFrame frame = new JFrame();
        frame.getContentPane().add(b);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
        new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	b.next();
            	b.repaint();
            }
        }).start();
		
	}

}
