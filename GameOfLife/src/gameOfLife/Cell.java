package gameOfLife;

import java.awt.Point;
import java.util.ArrayList;

public class Cell {
	private boolean isAlive, aliveNext = false;
	private Point location, boardSize;
	private ArrayList<Point> neighbors = new ArrayList<Point>(9);
	private Cell[][] board;
	
	public Cell(Cell[][] board, Point location, Boolean isAlive) {
		this.isAlive = isAlive;
		this.location = location;
		this.board = board;
		this.boardSize = new Point(board.length, board[0].length);
		populateNeighborsList();
	}
	
	private void populateNeighborsList() {
		// Populate list of all possible neighbors 
		for(int i = -1; i<= 1; i++) {
			for(int j = -1; j<= 1; j++) {
				neighbors.add(new Point(location.x + i,location.y + j));
			}
		}
		
		// The cell doesn't count itself as a neighbor
		neighbors.remove(location); 
		
		
		// Remove invalid neighbors
		ArrayList<Point> removed = new ArrayList<Point>(0);
		
		for(Point p : neighbors) {
			if(p.x < 0 || p.x >= boardSize.x || p.y < 0 || p.y >= boardSize.y) {
				removed.add(p);
			}
		}
		neighbors.removeAll(removed);				
	}

	public boolean willLive() {
		return aliveNext;
	}
	
	public void setAlive() {
		isAlive = true;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public int countNeighbors() {
		if(location.equals(new Point(2, 2)))
			System.out.println();
		int sum = 0;
		for(Point p : neighbors) {
			if(board[p.x][p.y].isAlive) {
				sum++;				
			}
		}
		return sum;
	}
	
	/*
	 * 	Live /w 2 or 3 live neighbors -> live
	 *  Dead /w 3 live neighbors -> live
	 *  Else -> dead
	 */
	public void setNextState() {
		int liveNeighbors = countNeighbors();
		
		if(liveNeighbors == 3) 
			aliveNext = true;
		else if(isAlive && liveNeighbors == 2) 
			aliveNext = true;
		else 
			aliveNext = false;
	}
	
	public void update() {
		isAlive = aliveNext;
	}
	
}
