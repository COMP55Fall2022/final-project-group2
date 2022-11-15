package edu.pacific.comp55.starter;

import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.util.RandomGenerator;

public class Crow extends GraphicsPane implements ActionListener, KeyListener {
	private static final int JUMP_HEIGHT = 30;
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
	public static final int SIZE = 10;
	public static final int SPEED = 2;
	public static final int MS = 60;
	private ArrayList<GOval> trash;
	private RandomGenerator trashGen;
	private int numTimes;
	Timer trashDown = new Timer(50, this);

	Timer crowtimerleft = new Timer(40, this);
	Timer crowtimeright = new Timer(40, this);
	Timer crowtimerup = new Timer(40, this);
	Timer gravitytimer = new Timer(40, this);
	int gravitymotion = JUMP_HEIGHT;
	

	public Crow(MainApplication app) {
		this.program = app;
		crowgamebackground = new GImage("7.png", 0, 0);
		saaya = new GOval(START_X, START_Y, SAAYASIZE_X, SAAYASIZE_Y); // making start position static
		saaya.setFilled(true);
		saaya.setColor(Color.green);
		
		//For trash
		trash = new ArrayList<GOval>();
		numTimes = 0;

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
		add(e);
	}
		
	private void add(GOval e) {
		// TODO Auto-generated method stub
			
	}

	public GOval makeTrash(double x) {
		GOval temp = new GOval(WINDOW_HEIGHT-SIZE, x-SIZE/2, SIZE, SIZE);
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

	}

	@Override
	public void hideContents() {
		program.remove(crowgamebackground);

	}

	@Override
	//this function uses key events and starts movement 
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == crowtimerleft) {
			if(!isOutOfBoundsleft()) {
			saaya.move(-10, 0); }
		}
		
		
		if (source == crowtimeright) {
			if(!isOutOfBoundsright()) {
			saaya.move(10, 0); }
		}
		
		
		if (source == crowtimerup) {
			gravitymotion--;
			if (gravitymotion == 0) {
				crowtimerup.stop();
				gravitytimer.start();
			}
			//gravitytimer.start();
			saaya.move(0, -5);
		}
		
		if (source == gravitytimer) {
			gravitymotion++;
			if (gravitymotion == JUMP_HEIGHT) {
				gravitytimer.stop();
			}
			saaya.move(0, 5);
		}
	}

	
	
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();

		if (c == KeyEvent.VK_LEFT) {
				crowtimerleft.start();

		}

		if (c == KeyEvent.VK_UP && !crowtimerup.isRunning() && !gravitytimer.isRunning()) {
			
			crowtimerup.start();
			
		}

		if (c == KeyEvent.VK_RIGHT) {
			crowtimeright.start();

		}
}
	
	public void gravity(GObject obj) {
		obj.move(0, 60);
	
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		crowtimerleft.stop();
		crowtimeright.stop();
		//crowtimerup.stop();
	}

}


