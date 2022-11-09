package edu.pacific.comp55.starter;
import javax.swing.*;


public class TicTacToe {
     public char[][] board;
     private int rows;
     private int cols;
     
     //private char xPlayer;
     //private char yPlayer;
     
     public TicTacToe(int rows, int cols) {
    	 this.rows = rows;
    	 this.cols = cols;
    	 this.board = new char[rows][cols];
     }
     public void setupBoard() {
    	 for (int i = 0; i < rows; i++) {
    		 for(int j = 0; j < cols; j++) {
    			 board[i][j] = 'e';
    			 
    		 }
    	 }
    	 
     }
     public void printBoard() {
    	 for(int i = 0; i < rows; i++) {
    		 
    		 for(int j = 0; j < cols; j++) {
    			 System.out.println(board[i][j]);
    		 }
    		 System.out.println("\n");
    	 }
     }
     public static void main(String[] args) {
    	 TicTacToe t = new TicTacToe(3,3);
    	 t.setupBoard();
    	 t.printBoard();
    	 
     }
     
}   


	


