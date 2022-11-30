package edu.pacific.comp55.starter;


import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import acm.graphics.*;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.random;

public class MouseGame extends GraphicsPane implements ActionListener{
	private ArrayList<GImage> mouseList;
	private GImage mouse;
	private GImage background;
	private MainApplication program;
	private GLabel score; 
	private int points;
	
	// The below coordinates are the location where the actual playable area is in the background image.
	private static final int GAMEBOARD_LEFT = 175;
	private static final int GAMEBOARD_TOP = 100;
	private static final int GAMEBOARD_RIGHT = 975;
	private static final int GAMEBOARD_BOTTOM = 842;
	//Mouse scaling sizes
	private static final double[] scaleSizes = new double[]{0.75, 0.5, 0.25};

	
	public MouseGame(MainApplication app) {
		super();
		program = app;
		mouseList = new ArrayList<GImage>();
		background = new GImage("mousebg.png", 0, 0);
		score = new GLabel("Score: " + points, 275, 100);
		score.scale(5);
		score.setColor(Color.white);
	}
	
	
	private void addMouse() {
		int x = (int) (Math.random() * (GAMEBOARD_LEFT - GAMEBOARD_RIGHT) + GAMEBOARD_RIGHT);
		int y = (int) (Math.random() * (GAMEBOARD_BOTTOM - GAMEBOARD_TOP) + GAMEBOARD_TOP);
		mouse = new GImage("genericMouse.png", x, y);
		scaleMouse(mouse);
		
		
		if(!checkForOverlap(mouse)) {
			boolean temp = checkForOverlap(mouse);
			while(!temp) {
				int x1 = (int) (Math.random() * (GAMEBOARD_LEFT - GAMEBOARD_RIGHT) + GAMEBOARD_RIGHT);
				int y1 = (int) (Math.random() * (GAMEBOARD_BOTTOM - GAMEBOARD_TOP) + GAMEBOARD_TOP);
				mouse.setLocation(x1, y1);
				temp = checkForOverlap(mouse);
			}
		}
		mouseList.add(mouse);
		program.add(mouse);
	}
	
	//Scales the mouse down since the original image is too big. 
	//Chances for a default size is 50%, scaled down half is 30%, and scaled down to a quarter is 20% 
	private void scaleMouse(GImage m) {
		int mouseScaleSize = (int) (Math.random() * 10); 
		int scaleNum = 0;
		if(mouseScaleSize >= 5) {
			scaleNum = 0;
		}
		else if(mouseScaleSize < 5 && mouseScaleSize >= 2) {
			scaleNum = 1;
		}
		else {
			scaleNum = 2;
		}
		mouse.scale(scaleSizes[scaleNum]);
		
	}
	
	//Checks if the any mice are overlapping and returns false if overlapped.
	private boolean checkForOverlap(GImage m) {
		GRectangle mouseBounds = m.getBounds();
		for (int i = 0; i < mouseList.size(); i++) {
			GRectangle miceListBounds = mouseList.get(i).getBounds();

			if (mouseBounds.intersects(miceListBounds)) {
				System.out.println("INTERSECTION OCCURED!");
				return false;
			}
		}
		return true;
	}
	
	private void addScore(int s) {
		points += s;
	}
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(background);
		program.add(score);
		for(int i = 0; i < 10; i++) {
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
		GObject mouseObj = program.getElementAt(e.getX(), e.getY());
		if(mouseObj != null) {
			for(GImage g : mouseList) {
				if(mouseObj == g) {
					if(g.getWidth() == mouse.getWidth() * 0.75) {
						addScore(5);
						score.setLabel("Score: " + points);
					}
					else if(g.getWidth() == mouse.getWidth() * 0.5) {
						addScore(10);
						score.setLabel("Score: " + points);
					}
					else {
						addScore(15);
						score.setLabel("Score: " + points);
					}
					program.remove(g);
					mouseList.remove(g);
					System.out.println(mouseList.size());
					break;
				}
			}
		}
		
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
