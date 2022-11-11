package group2.project;
import javax.swing.*;
import java.util.Scanner;

public class TicTacToe {
     public char[][] board;
     private int rows;
     private int cols;
     
     private String xPlayer;
     private String yPlayer;
     
     public TicTacToe(int rows, int cols) {
    	 this.rows = rows;
    	 this.cols = cols;
    	 this.board = new char[rows][cols];
     }
     
     public void setupBoard() {
    	 for (int i = 0; i < rows; i++) {
    		 for(int j = 0; j < cols; j++) {
    			 board[i][j] = 'b';
    			 
    		 }
    	 }
     }
     
     public void setupPlayers(String xPlayer, String yPlayer) {
    	 this.xPlayer = xPlayer;
    	 this.yPlayer = yPlayer;
     }
     
     public void printBoard() {
    	 for(int i = 0; i < rows; i++) {
    		 
    		 for(int j = 0; j < cols; j++) {
    			 System.out.print(board[i][j]);
    		 }
    		 System.out.print("\n");
    	 }
     }
     
     public void dogMove() {
		boolean start = true;
		while(start) {
			int a = (int) (Math.random() * 3);
			int b = (int) (Math.random() * 3);
			if(board[a][b] == 'b') {
				board[a][b] = 'd';
				start = false;
			}
		}
	 }

	 public void getPlayerMove() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Input row move: ");
		int x = sc.nextInt();
		System.out.print("Input col move: ");
		int y = sc.nextInt();
		if(board[x][y] != 'b') {
			System.out.println("Illegal move! Try again...");
			getPlayerMove();
		}
		else {
			System.out.println("Executing move...");
			board[x][y] = 'c';
			printBoard();
		}
	 }

	public void checkForWin() {
		
	}
     
     public static void main(String[] args) {
    	 TicTacToe t = new TicTacToe(3,3);
    	 t.setupBoard();
    	 t.printBoard();
    	 t.setupPlayers("Saaya", "Doggy");
    	 
     }
     
}   


	


