package group2.project;
import javax.swing.*;
import java.util.Scanner;

public class TicTacToe {
     public char[][] board;
     private int rows;
     private int cols;
	 private boolean dogWin = false;
     
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

	public boolean checkForWin() {
		if(checkHorizontalWin()) {
			return checkHorizontalWin();
		}
		if(checkVerticalWin()) {
			return checkVerticalWin();
		}
		return false;
	}

	 public boolean checkHorizontalWin() {
		char array[] = new char[3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				array[j] = board[i][j];
			}
			if(array[0] == 'c' && array[1] == 'c' && array[2] == 'c') {
				System.out.println("You win!");
				return true;
			}
			else if(array[0] == 'd' && array[1] == 'd' && array[2] == 'd') {
				System.out.println("You lose!");
				setDogWin();
				break;
			}
		}
		return false;
	 }

	 public void setDogWin() {
		dogWin = true;
	 }
	 public boolean checkDogWin() {
		return dogWin;
	 }
	 public boolean checkVerticalWin() {
		char array[] = new char[3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				array[j] = board[j][i];
			}
			if(array[0] == 'c' && array[1] == 'c' && array[2] == 'c') {
				System.out.println("You win!");
				return true;
			}
			else if(array[0] == 'd' && array[1] == 'd' && array[2] == 'd') {
				System.out.println("You lose!");
				setDogWin();
				break;
			}
		}
		return false;
	 }

	 public void playGame(TicTacToe t) {
		t.setupBoard();
		t.printBoard();
		t.setupPlayers("Saaya", "Doggy");
		boolean win = false;
		while(!win) {
			win = checkForWin();
			if(checkDogWin() == true) {
				win = true;
				System.out.println("You Lose!");
			} 
			getPlayerMove();
			dogMove();
			
		}
	 }

	 public boolean checkDiagonalWin() {
        if((board[0][0] == 'c' && board[1][1] == 'c' && board[2][2] == 'c')|| (board[0][2] == 'c' && board[1][1] == 'c' && board[2][0] == 'c')) {
        	System.out.println("You win!");
        	return true;
        }else if((board[0][0] == 'd' && board[1][1] == 'd' && board[2][2] == 'd')|| (board[0][2] == 'd' && board[1][1] == 'd' && board[2][0] == 'd')) {
        	System.out.println("You lose!");
        }
        return false;
        
	 }
     
     public static void main(String[] args) {
    	 TicTacToe t = new TicTacToe(3,3);
    	 t.setupBoard();
    	 t.printBoard();
    	 t.setupPlayers("Saaya", "Doggy");
    	 
     }
     
}   


	


