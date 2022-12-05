package edu.pacific.comp55.starter;

import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRectangle;
import acm.util.RandomGenerator;

public class Basket extends GraphicsPane implements ActionListener, KeyListener {

	private static final int START_Y = 550;
	private static final int START_X = 500;
	public static final int WINDOW_WIDTH = 1220;
	public static final int WINDOW_HEIGHT = 1096;
	private MainApplication program;
	private GImage basketbackground;

	// Timer for game
	int minutecount = 0;
	Timer sec = new Timer(1000, this);
	Timer wongametimer = new Timer(40, this);
	Timer lostgametimer = new Timer(40, this);

	// apple movements
	public static final int SPEED = 12;
	private static final int APPLEDIV = 50;
	private ArrayList<GImage> apple;
	private ArrayList<Integer> appleDiv;
	private ArrayList<Integer> appleDirection;
	private RandomGenerator appleGen;
	private int numTimes;
	Timer appleDown = new Timer(40, this);

	// rottenapple
	private ArrayList<GImage> rottenapple;
	private ArrayList<Integer> rottenappleDiv;
	private ArrayList<Integer> rottenappleDirection;
	private RandomGenerator rottenappleGen;
	private int rottennumTimes;
	Timer rottenappleDown = new Timer(40, this);


	// basket
	private GImage basket;
	private double BASKET_X;
	
	Timer baskettimerleft = new Timer(40, this);
	Timer baskettimerright = new Timer(40, this);
	private GLine baskettop;

	// score
	private int applecount;
	GLabel applecounter = new GLabel(String.valueOf(applecount), 50, 50);

	// wongame
	private GImage crowin;
	private GButton mainmenue;
	private GButton scene1;
	private GImage crowlost;
	private GButton tryagain;

	public Basket(MainApplication app) {
		this.program = app;
		basketbackground = new GImage("basketbackground.png", 0, 0);

		// basket
		basket = new GImage("basket.png", START_X, START_Y);
		basket.scale(0.25);
		BASKET_X = basket.getWidth();
		baskettop = new GLine(510, 637, 644, 637);
		baskettop.setColor(Color.BLACK);
		// baskettop.setVisible(false);

		// apples
		appleGen = RandomGenerator.getInstance();
		apple = new ArrayList<GImage>();
		appleDiv = new ArrayList<Integer>();
		appleDirection = new ArrayList<Integer>();
		numTimes = 0;

		rottenappleGen = RandomGenerator.getInstance();
		rottenapple = new ArrayList<GImage>();
		rottenappleDiv = new ArrayList<Integer>();
		rottenappleDirection = new ArrayList<Integer>();
		rottennumTimes = 0;

		// score
		applecounter.setColor(Color.black);
		applecounter.scale(2);

		// wingame
		crowin = new GImage("crowin.png", 350, 200);
		scene1 = new GButton("Next scene", 655, 500, 100, 100);
		scene1.setFillColor(Color.GREEN);
		crowin.scale(0.5);
		mainmenue = new GButton("Main menue", 455, 500, 100, 100);
		mainmenue.setFillColor(Color.red);

		// lostgame
		crowlost = new GImage("crowlost.png", 350, 200);
		crowlost.scale(0.5);
		tryagain = new GButton("Try again", 655, 500, 100, 100);
		tryagain.setFillColor(Color.green);

	}

	// For apples
	private void addApples() {
		GImage eTrash = makeApple();
		Integer temp = (appleGen.nextBoolean() == true) ? 1 : -1;
		appleDirection.add(temp);
		appleDiv.add(appleGen.nextInt(APPLEDIV));
		apple.add(eTrash);
		program.add(eTrash);

	}

	public GImage makeApple() {
		GImage temp = new GImage("apple.png", appleGen.nextInt(WINDOW_WIDTH), 180);
		temp.scale(0.25);
		return temp;
	}

	private void appleMove() {
		for (int i = 0; i < apple.size(); i++) {
			// Move Down
			apple.get(i).move(0, SPEED);

		}
	}

	// for rotten apples

	private void addRottenApples() {
		GImage eTrash = makeRottenApple();
		Integer temp = (rottenappleGen.nextBoolean() == true) ? 1 : -1;
		rottenappleDirection.add(temp);
		rottenappleDiv.add(appleGen.nextInt(APPLEDIV));
		rottenapple.add(eTrash);
		program.add(eTrash);

	}

	public GImage makeRottenApple() {
		GImage temp = new GImage("rottenapple.png", rottenappleGen.nextInt(WINDOW_WIDTH), 180);
		temp.scale(0.2);
		return temp;
	}

	private void rottenappleMove() {
		for (int i = 0; i < rottenapple.size(); i++) {
			// Move Down
			rottenapple.get(i).move(0, SPEED);

		}
	}

	public boolean isOutOfBoundsright() {
		double rightarea = WINDOW_WIDTH - BASKET_X;

		if (basket.getX() > rightarea) {
			return true;
		}
		return false;
	}

	public boolean isOutOfBoundsleft() {
		double leftarea = 0;

		if (basket.getX() < leftarea) {
			return true;
		}
		return false;
	}

//	public boolean appleoverlap() {
//		if (rottenapplebounds.intersects(applebounds)) {
//			return true;
//		}
//		return false;
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		// basket movements

		if (source == baskettimerleft) {
			if (!isOutOfBoundsleft()) {
				basket.move(-25, 0);
				baskettop.move(-25, 0);
			}
		}

		if (source == baskettimerright) {
			if (!isOutOfBoundsright()) {
				basket.move(25, 0);
				baskettop.move(25, 0);
			}
		}

		// apple movements

		if (source == appleDown) {
			numTimes++;
			if (numTimes % 30 == 0) {
				addApples();

			}
			appleMove();
		}

		if (source == rottenappleDown) {
			rottennumTimes++;
			if (rottennumTimes % 30 == 0) {
				addRottenApples();
				addRottenApples();

			}

			rottenappleMove();

		}

		if (source == sec) {
			minutecount++;
		}

		if (source == wongametimer) {
			program.add(crowin);
			program.add(scene1);
			program.add(mainmenue);
			stoptimers();
		}

		if (source == lostgametimer) {
			program.add(crowlost);
			program.add(tryagain);
			program.add(mainmenue);
			stoptimers();
		}

		isappletouchingbasket();
		isrottenappletouchingbasket();
		wingame();
		endgame();
		applecounter.setLabel(String.valueOf(applecount));
	}

	@Override
	public void showContents() {
		program.add(basketbackground);
		program.add(basket);
		program.add(baskettop);
		appleDown.start();
		rottenappleDown.start();
		program.add(applecounter);
		sec.start();

	}

	@Override
	public void hideContents() {
		program.remove(basketbackground);
		program.remove(basket);
		appleDown.stop();
		rottenappleDown.stop();
	}

	public boolean isappletouchingbasket() {

		GRectangle basketbounds = baskettop.getBounds();
		for (int i = 0; i < apple.size(); i++) {

			// GRectangle rotteapplebounds = rottenapple.get(i).getBounds();

			GRectangle applebounds = apple.get(i).getBounds();
			if (basketbounds.intersects(applebounds)) {
				applecount++;

				program.remove(apple.get(i));
				apple.remove(i);

			}

		}
		return false;

	}

	public boolean isrottenappletouchingbasket() {

		GRectangle basketbounds = baskettop.getBounds();
		for (int i = 0; i < rottenapple.size(); i++) {

			GRectangle rottenapplebounds = rottenapple.get(i).getBounds();

			if (basketbounds.intersects(rottenapplebounds)) {
				applecount--;

				program.remove(rottenapple.get(i));
				rottenapple.remove(i);

			}

		}
		return false;

	}
	
	//basket movements

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();

		if (c == KeyEvent.VK_LEFT) {
			baskettimerleft.start();

		}

		if (c == KeyEvent.VK_RIGHT) {
			baskettimerright.start();

		}

	}

	public void keyReleased(KeyEvent e) {
		baskettimerleft.stop();
		baskettimerright.stop();

	}

	public void stoptimers() {
		appleDown.stop();
		rottenappleDown.stop();
		baskettimerleft.stop();
		baskettimerright.stop();
	}
	

	
	//starting and ending game
	public void wingame() {
		if (applecount >= 1 && minutecount == 20) {
			System.out.println("You won the game");

			wongametimer.start();
		}

	}

	public void endgame() {
		if (applecount <= 5 && minutecount == 20) {
			lostgametimer.start();
		}

		if (applecount < 0) {
			applecount = 0;
		}

	}
	
	
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == tryagain) {
			lostgametimer.stop();
		
			this.program.switchToBasket();
		}
		 if (obj == mainmenue){
			
			 lostgametimer.stop();
			 wongametimer.stop();
			 program.switchToMenu();
			 
		}
		 
		 if (obj == scene1) {
			 wongametimer.stop();
			 this.program.switchToScene1();
		 }
		else {
			
		}
	}
	

}
