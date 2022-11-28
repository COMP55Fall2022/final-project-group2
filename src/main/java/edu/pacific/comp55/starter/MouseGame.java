package edu.pacific.comp55.starter;


import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import acm.graphics.*;
import acm.program.GraphicsProgram;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.random;

public class MouseGame extends GraphicsPane implements ActionListener{
	private ArrayList<GImage> mouseList;
	private GImage mouse;
	private GImage background;
	private MainApplication program;
	// The below coordinates are the location where the actual playable area is in the background image.
	private static final int GAMEBOARD_LEFT = 180;
	private static final int GAMEBOARD_TOP = 100;
	private static final int GAMEBOARD_RIGHT = 1038;
	private static final int GAMEBOARD_BOTTOM = 956;
	//Mouse scaling sizes
	private static final double[] scaleSizes = new double[]{0.85, 0.5, 0.25};
	
	
	public MouseGame(MainApplication app) {
		super();
		program = app;
		background = new GImage("mousebg.png", 0, 0);
		
	}
	
	
	private void addMouse() {
		int x = (int) (Math.random() * (GAMEBOARD_LEFT - GAMEBOARD_RIGHT) + GAMEBOARD_BOTTOM);
		int y = (int) (Math.random() * (GAMEBOARD_TOP - GAMEBOARD_BOTTOM) + GAMEBOARD_BOTTOM);
		int mouseScaleSize = (int) (Math.random() * 3); //Scales the mouse down since the original image is too big
		
		mouse = new GImage("genericMouse.png", x, y);
		mouse.scale(scaleSizes[mouseScaleSize]);
		
		mouseList.add(mouse);
		program.add(mouse);
	}
	
	//Checks if the any mice are overlapping and moves them appropriately
	private void checkForOverlap(int x, int y) {
		
	}
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(background);
		for(int i = 0; i < 5; i++) {
			addMouse();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("ACTION PERFORMED");
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GPoint point = new GPoint(e.getX(), e.getY());
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		for(int i = 0; i < mouseList.size(); i++) {
			program.remove(mouseList.get(i));
		}
		program.remove(background);
	}
	
}
