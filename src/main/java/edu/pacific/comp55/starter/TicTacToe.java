package edu.pacific.comp55.starter;

import javax.swing.*;
import java.util.Scanner;

public class TicTacToe {
	public char[][] board;
	private int rows;
	private int cols;
	private boolean dogWin = false;
	private boolean youWin = false;
	public int squareCount = 0;
	public int dogMoveX = 0;
	public int dogMoveY = 0;
	public int playerMoveX = 0;
	public int playerMoveY = 0;
	private String xPlayer;
	private String yPlayer;

	public TicTacToe(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.board = new char[rows][cols];
	}

	public void setupBoard() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = 'b';

			}
		}
		checkForTie();
	}

//     public void testBoard() {
//    	 for (int i = 0; i < rows; i++) {
//    		 for(int j = 0; j < cols; j++) {
//    			 board[i][j] = 'x';
//    			 
//    		 }
//    	 }
//     }

	public void setupPlayers(String xPlayer, String yPlayer) {
		this.xPlayer = xPlayer;
		this.yPlayer = yPlayer;
	}

	public void printBoard() {
		for (int i = 0; i < rows; i++) {

			for (int j = 0; j < cols; j++) {
				System.out.print(board[i][j]);
			}
			System.out.print("\n");
		}
	}

	public void dogMove() {
		boolean start = true;
		while (start) {
			int a = (int) (Math.random() * 3);
			int b = (int) (Math.random() * 3);
			if (board[a][b] == 'b') {
				board[a][b] = 'd';
				dogMoveX = a;
				dogMoveY = b;
				start = false;
			}
		}
		printBoard();
	}

	// Grabs an input from user
	public void getPlayerMove() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Input row move: ");
		int x = sc.nextInt();
		System.out.print("Input col move: ");
		int y = sc.nextInt();
		if (board[x][y] != 'b') {
			System.out.println("Illegal move! Try again...");
			getPlayerMove();
		} else {
			System.out.println("Executing move...");
			board[x][y] = 'c';
			// System.out.println("-------------------------");
			printBoard();
			System.out.println("-------------------------");
		}
	}

	public boolean checkForWin() {
		return checkHorizontalWin() || checkVerticalWin() || checkDiagonalWin();
	}

	public boolean checkHorizontalWin() {
		char array[][] = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				array[i][j] = board[i][j];
				if ((array[i][0] == 'c') && (array[i][1] == 'c') && (array[i][2] == 'c')) {
					System.out.println("You win!");
					youWin = true;
					return true;
				} else if ((array[i][0] == 'd') && (array[i][1] == 'd') && (array[i][2] == 'd')) {
					System.out.println("You lose!");
					setDogWin();
					return true;
				}
			}
			
		}
		return false;
	}

	public void setDogWin() {
		dogWin = true;
	}

	public boolean getDogWin() {
		return dogWin;
	}

	public int get_num_rows() {
		return rows;
	}

	public int get_num_cols() {
		return cols;
	}
	
	public boolean getYouWin() {
		return youWin;
	}

	public boolean checkVerticalWin() {
		char array[][] = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				array[i][j] = board[i][j];

				if (array[0][j] == 'c' && array[1][j] == 'c' && array[2][j] == 'c') {
					System.out.println("You win!");
					youWin = true;
					return true;
				} else if (array[0][j] == 'd' && array[1][j] == 'd' && array[2][j] == 'd') {
					System.out.println("You lose!");
					setDogWin();
					return true;
				}
			}
		}
		return false;
	}

	public void playGame() {
		setupBoard();
		printBoard();
		setupPlayers("Saaya", "Doggy");
		boolean win = false;
		while (!win) {
//			testBoard();
//			printBoard();
			getPlayerMove();
			// checkForTie();
			if (checkForWin()) {
				win = true;
				System.out.println("You Win!");
				break;
			}

			dogMove();
			if (getDogWin() == true) {
				win = true;
				System.out.println("You Lose!");
				break;
			}
			if (checkForTie()) {
				win = false;
				System.out.println("It's a tie");
				break;
			}
			System.out.println("End of if statement");

		}
	}

	public boolean checkDiagonalWin() {
		if ((board[0][0] == 'c' && board[1][1] == 'c' && board[2][2] == 'c')
				|| (board[0][2] == 'c' && board[1][1] == 'c' && board[2][0] == 'c')) {
			System.out.println("You win!");
			youWin = true;
			return true;
		} else if ((board[0][0] == 'd' && board[1][1] == 'd' && board[2][2] == 'd')
				|| (board[2][0] == 'd' && board[1][1] == 'd' && board[0][2] == 'd')) {
			System.out.println("You lose!");
			return true;
		}
		return false;

	}

	public boolean checkForTie() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j] != 'b') {
					squareCount++;			
				}
			}
		}
		if (squareCount == 9) {
			return true;
		}
		else
		{
			squareCount = 0;
			return false;
		}
	}

	public static void main(String[] args) {
		TicTacToe t = new TicTacToe(3, 3);
		t.playGame();
	}

}
