package edu.pacific.comp55.starter;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import acm.graphics.GImage;
import acm.graphics.GOval;

import acm.util.RandomGenerator;
import java.util.ArrayList;

public class TTTGraphics extends TicTacToe, GraphicsPane implements ActionListener{
	private TicTacToe board = new TicTacToe(3, 3);
	private MainApplication program;
	private GImage background;
	private GImage playerMove;
	private GImage dogMove;
	
	 public TTTGraphics(int rows, int cols) {
		super(rows, cols);
		// TODO Auto-generated constructor stub
	}
	
	public TTTGraphics(MainApplication app) {
		program = app;
		
	}
	 public static final int PROGRAM_WIDTH = 500;
	 public static final int PROGRAM_HEIGHT = 500;
	 
}
