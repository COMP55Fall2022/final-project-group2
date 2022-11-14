package edu.pacific.comp55.starter;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import acm.graphics.GImage;
import acm.graphics.GLine;
import acm.graphics.GOval;

import acm.util.RandomGenerator;
import java.util.ArrayList;

public class TTTGraphics extends GraphicsPane implements ActionListener{
	private TicTacToe board;
	private MainApplication program;
	private GImage background;
	private GImage playerMove;
	private GImage dogMove;
	private GLine rowLine;
	private GLine colLine;
	
	public TTTGraphics(MainApplication app) {
		super();
		
		this.program = app;
		board = new TicTacToe(3, 3);
		
	}
	
	
	 public static final int PROGRAM_WIDTH = 500;
	 public static final int PROGRAM_HEIGHT = 500;
	 
    private void drawGridLines() {
    	for(int i = 1; i <= 3-1;i++) {
        	rowLine = new GLine(0,i * (PROGRAM_WIDTH/3),PROGRAM_WIDTH,i*(PROGRAM_WIDTH/3));
        	program.add(rowLine);
        }
        for(int j = 1; j <=3;j++) {
        	colLine = new GLine(j*(PROGRAM_HEIGHT/3),0,j*(PROGRAM_HEIGHT/3),PROGRAM_HEIGHT);
        	program.add(colLine);
        }
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void run() {
		drawGridLines();
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		
	}
	 
}
