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

public class Crow extends GraphicsPane implements ActionListener, KeyListener {
	private static final int SAAYASIZE_Y = 100;
	private static final int SAAYASIZE_X = 100;
	private static final int START_Y = 675;
	private static final int START_X = 550;
	private MainApplication program;
	private GImage crowgamebackground;
	private GOval saaya; // will change GOval to Gimage of cat later
	public static final int WINDOW_WIDTH = 1220;
	public static final int WINDOW_HEIGHT = 1096;
	
	//For trash
	public static final int SIZE = 25;
	public static final int SPEED = 2;
	public static final int MS = 20;
	private ArrayList<GOval> trash;
	private RandomGenerator trashGen;
	private int numTimes;
	Timer trashDown = new Timer(50, this);

	Timer crowtimerleft = new Timer(50, this);
	Timer crowtimeright = new Timer(50, this);
	int x = 15, y = 0, velx = 0, vely = 0;

	public Crow(MainApplication app) {
		this.program = app;
		crowgamebackground = new GImage("7.png", 0, 0);
		saaya = new GOval(START_X, START_Y, SAAYASIZE_X, SAAYASIZE_Y); // making start position static
		saaya.setFilled(true);
		saaya.setColor(Color.green);
		
		//For trash
		trash = new ArrayList<GOval>();

	}

	public boolean isOutOfBoundsright() {
		double rightarea = WINDOW_WIDTH - SAAYASIZE_X;

		if (saaya.getX() > rightarea) {
			return true;
		}
		return false;
	}
	
	public boolean isOutOfBoundsleft() {
		double leftarea = 0;

		if (saaya.getX() < leftarea) {
			return true;
		}
		return false;
	}
	

	// public Crow() {
	// crowtimer.start();
	// addKeyListener(this);
	// setFocusable(true);
	// setFocusTraversalKeysEnabled(false);
	// }
	
	//For trash
	private void addTrash() {
		GOval e = makeTrash(trashGen.nextInt(0, WINDOW_WIDTH-SIZE/2));
		trash.add(e);
	}
	
	public GOval makeTrash(double x) {
		GOval temp = new GOval(WINDOW_HEIGHT-SIZE, y-SIZE/2, SIZE, SIZE);
		temp.setColor(Color.GREEN);
		temp.setFilled(true);
		return temp;
	}
	
	private void trashMove() {
		for(GOval e:trash) {
			e.move(0, trashGen.nextInt(-2, 2));
		}
	}

	@Override
	public void showContents() {
		program.add(crowgamebackground);
		program.add(saaya);
		//program.add(trash);
		
	}

	@Override
	public void hideContents() {
		program.remove(crowgamebackground);
		program.remove(saaya);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		x = x + velx;
		y = y + vely;
		if(!isOutOfBoundsleft()) {
			saaya.move(-15, 0);
		}
		else {
			crowtimerleft.stop();
		}
		//For trash
	
		System.out.print(x);
		x++;
	}

	
	
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();

		if (c == KeyEvent.VK_LEFT) {
			if (!isOutOfBoundsleft()) {
				crowtimerleft.start();
				velx = -1;
				vely = 0;
			
			}

		}

		if (c == KeyEvent.VK_UP) {
			velx = 0;
			vely = -1;
			saaya.move(0, -1);
		}

		if (c == KeyEvent.VK_RIGHT) {

			if (!isOutOfBoundsright()) {
				crowtimeright.start();
				velx = 1;
				vely = 0;
			
			}
		}


	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		crowtimerleft.stop();
		crowtimeright.stop();
	}

}
