package edu.pacific.comp55.starter;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import acm.graphics.*;
import acm.util.RandomGenerator;
import java.util.ArrayList;
import acm.util.*;
import acm.program.*;
public class TTTGraphics extends GraphicsPane implements ActionListener{
	public static final int PROGRAM_WIDTH = 1220;
	public static final int PROGRAM_HEIGHT = 1096;
	private TicTacToe board;
	private MainApplication program;
	private GImage background;
	private GRect playerMove;
	private GOval dogMove;
	private GLine rowLine;
	private GLine colLine;
	
	public TTTGraphics(MainApplication app) {
		super();
		program = app;
		background = new GImage("TTTBG1.png", 0, 0);
		board = new TicTacToe(3, 3);
	}
	
	
    private void drawGridLines() {
    	for(int i = 1; i <= 2;i++) {
        	rowLine = new GLine(0,i * (PROGRAM_WIDTH/3.4),PROGRAM_WIDTH,i*(PROGRAM_WIDTH/3.4));
        	program.add(rowLine);
        }
        for(int j = 1; j <=2;j++) {
        	colLine = new GLine(j*(PROGRAM_HEIGHT/2.7),0,j*(PROGRAM_HEIGHT/2.7),PROGRAM_HEIGHT);
        	program.add(colLine);
        }
    }
    
    private void drawDogMove() {
    	int x = board.getDogX();
    	int y = board.getDogY();
    	dogMove = new GOval((PROGRAM_WIDTH/2.4) + 15,(PROGRAM_WIDTH/2.7) + 15,150,150);
    	dogMove.setColor(Color.red);
    	program.add(dogMove);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(background);
		drawGridLines();
		drawDogMove();
		System.out.println("Lines on");	
	}


	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int x1 = x/400;
		int y1 = y/350;
		
		System.out.println(x1 + " + " + y1);
	}
	
//	public static void main(String[] args) {
//	    new MainApplication().start();
//		System.out.println("start app");
//	}
//	
	 
}
