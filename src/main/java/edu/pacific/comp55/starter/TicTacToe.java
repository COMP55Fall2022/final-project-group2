package edu.pacific.comp55.starter;
import javax.swing.*;


public class TicTacToe {
     private char board[][] = new char[3][3];
     private char xPlayer;
     private char yPlayer;
     public void setupBoard() {
    	 for (int i = 0; i < 3; i++) {
    		 for(int j = 0; j < 3; j++) {
    			 board[i][j] = 'e';
    		 }
    	 }
    	 System.out.print(board);
     }
}
