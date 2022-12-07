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
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRectangle;
import acm.util.RandomGenerator;

public class Crow extends GraphicsPane implements ActionListener, KeyListener {
	private static final int JUMP_HEIGHT = -20;
	private static final int START_Y = 620;
	private static final int START_X = 550;
	private MainApplication program;
	private GImage crowgamebackground;
	private GImage saaya;
	private GImage crowlost;
	private GImage crowin;
	private GButton tryagain;
	private GButton mainmenue;
	private GButton scene1;
	int minutecount =0;
	//private GLabel livescounter;
	private int lives =3;
	public static final int WINDOW_WIDTH = 1220;
	public static final int WINDOW_HEIGHT = 1096;
	GLabel livescounter = new GLabel(String.valueOf(lives), 50, 50);
	private Crow newcrow = this;
	
	
	//For trash
	public static final int SIZE = 40;
	public static final int SPEED = 5;
	public static final int MS = 70;
	private static final int TRASHDIV = 50;
	private ArrayList<GOval> trash;
	private ArrayList<Integer> trashDiv;
	private ArrayList<Integer> trashDirection;
	private RandomGenerator trashGen;
	private int numTimes;
	Timer sec = new Timer(1000, this);
	Timer trashDown = new Timer(40, this);
	Timer crowtimerdown = new Timer(40, this);
	Timer crowtimerleft = new Timer(40, this);
	Timer crowtimeright = new Timer(40, this);
	Timer crowtimerup = new Timer(40, this);
	Timer gravitytimer = new Timer(40, this);
	Timer crowlosttimer = new Timer(40, this);
	int gravitymotion = JUMP_HEIGHT;
	int SAAYASIZE_Y = 126;
	int SAAYASIZE_X = 164;
	int jumpvelocity = -20;
	
	

	public Crow(MainApplication app) {
		this.program = app;
		crowgamebackground = new GImage("7.png", 0, 0);
		saaya = new GImage("cat.png", START_X, START_Y);
		
		//loostgame
		crowlost = new GImage("crowlost.png",350, 200);
		crowlost.scale(0.5);
		tryagain = new GButton("Try again", 655, 500, 100, 100);
		mainmenue = new GButton("Main menue", 455, 500, 100, 100);
		tryagain.setFillColor(Color.GREEN);
		mainmenue.setFillColor(Color.RED);
		
		//wingame
		crowin = new GImage("crowin.png", 350,200);
		scene1 = new GButton("Next scene", 655, 500, 100, 100);
		scene1.setFillColor(Color.GREEN);
		crowin.scale(0.5);

		
		//For trash
		System.out.println("startapp");
		trashGen = RandomGenerator.getInstance();
		trash = new ArrayList<GOval>();
		trashDiv = new ArrayList<Integer>();
		trashDirection = new ArrayList<Integer>();
		numTimes = 0;
		
		//livescounter
		
		livescounter.setColor(Color.black);
		
		
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
	
	public boolean isOutOfBoundsdown() {
		double downarea = 1096;

		if (saaya.getX() < downarea+SAAYASIZE_Y ) {
			return true;
		}
		return false;
	}

	//For trash
	private void addTrash() {
		GOval eTrash = makeTrash();
		Integer temp = (trashGen.nextBoolean() == true) ? 1 : -1;
		trashDirection.add(temp);
		trashDiv.add(trashGen.nextInt(TRASHDIV));
		trash.add(eTrash);
		program.add(eTrash);
		
	}
	
	
	
	
	

	public GOval makeTrash() {
		GOval temp = new GOval(trashGen.nextInt(WINDOW_WIDTH), 180 , SIZE, SIZE);
		temp.setColor(Color.GREEN);
		temp.setFilled(true);
		return temp;
	}
		
	private void trashMove() {
		for(int i = 0 ; i < trash.size() ; i++) {
			//Move Down
			trash.get(i).move(0, SPEED);
			
			//Move left/right
			if( trashDiv.get(i) == 0 ) {
				trashDirection.set(i, trashDirection.get(i) * -1);
				trashDiv.set(i, trashGen.nextInt(i, TRASHDIV));
			}
			trashDiv.set(i, trashDiv.get(i) - 1);
			trash.get(i).move(trashDirection.get(i) * SPEED, 0);
			
		}
	}
		
	public void showGameObjectContents() {
		program.add(crowgamebackground);
		program.add(saaya);
		program.add(livescounter);
		crowlosttimer.stop();
		trashDown.start();
		sec.start();
	}
		
	@Override
	public void showContents() {
		showGameObjectContents();
	}

	@Override
	public void hideContents() {
		program.remove(crowgamebackground);
		program.remove(saaya);
		program.remove(livescounter);
		trashDown.stop();
		removeWinloseScreen();
		
		for (int i=0; i<trash.size();i++) {
			program.remove(trash.get(i));
		}
	}
	
	


	@Override
	//this function uses key events and starts movement 
	public void actionPerformed(ActionEvent e) {
	
	System.out.println(minutecount);
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
			gravitymotion++;
			if (gravitymotion == 0) {
				crowtimerup.stop();
				gravitytimer.start();
			}
		
			saaya.move(0, gravitymotion);
		}
		
		if (source == gravitytimer) {
			gravitymotion++;
			if (gravitymotion == 20) {
				System.out.print(gravitymotion);
				gravitytimer.stop();
				gravitymotion = -20;
			}
			else {
			saaya.move(0, gravitymotion);
			}
			
		}
		
		if (source == crowtimerdown) {
			if(!isOutOfBoundsdown() && !isOutOfBoundsright() && !isOutOfBoundsleft()) {
			saaya.move(0, 5); }
		}
		
		if (source == trashDown) {
			//System.out.println("startTrashDown");
			numTimes++;
			//System.out.println(numTimes);
			if (numTimes % 60 == 0) {
				addTrash();
				addTrash();
				//System.out.println(numTimes);
			}
			trashMove();
		}
		
		if (source == crowlosttimer) {
			program.add(crowlost);
			program.add(tryagain);
			program.add(mainmenue);
			stopTimers();
			
		}
		
		if (source == sec) {
			minutecount++;
		}
		
	
		istrashtouchingsaaya();
		endgame();
		wongame();
		livescounter.setLabel(String.valueOf(lives));
		
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
		
		
		if (c == KeyEvent.VK_DOWN) {
			crowtimerdown.start();

		}
}
	
	public void gravity(GImage obj) {
		obj.move(0, 60);
	
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		crowtimerleft.stop();
		crowtimeright.stop();
		//crowtimerup.stop();
		crowtimerdown.stop();
	}

	public boolean istrashtouchingsaaya() {
		
		GRectangle saayasbounds = saaya.getBounds();
		for (int i = 0; i < trash.size(); i++) {

			GRectangle trashbounds = trash.get(i).getBounds();
			if (saayasbounds.intersects(trashbounds)) {
				lives--;
				System.out.println("the number of lives left is:");
				System.out.println(lives);
			
				program.remove(trash.get(i));
				trash.remove(i);
				
			
			}

		}
		return false;

	}

	
	
	public void endgame() {
		if (lives == 0) {
			sec.stop();
			crowlosttimer.start();
			
		}
	}

	public void wongame() {
		if (lives > 0 && minutecount == 60) {
			stopTimers();
			minutecount = 0;
			program.add(crowin);
			program.add(mainmenue);
			program.add(scene1);
		}
	}

	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == tryagain) {
			crowlosttimer.stop();
			removeWinloseScreen();
			program.switchToCrow();
			//startTimers();
			lives = 3;
		}
		 if (obj == mainmenue){
			 lives = 3;
			 //removeWinloseScreen();
			 crowlosttimer.stop();
			 program.switchToMenu();
			 hideContents();
		}
		 
		 if (obj == scene1) {
			 lives =3;
			 program.switchToScene1();
		 }
		else {
			
		}
	}
	
	public void stopTimers() {
		crowtimerdown.stop();
		trashDown.stop();
		crowtimerdown.stop();
		crowtimerleft.stop();
		crowtimeright.stop();
		gravitytimer.stop();
		crowtimerup.stop();
	}
	
	public void startTimers() {
		trashDown.start();
		crowtimerdown.start();
		crowtimerleft.start();
		crowtimeright.start();
		crowtimerup.start();
		gravitytimer.start();
	}
	
	public void removeWinloseScreen() {
		program.remove(crowlost);
		program.remove(tryagain);
		program.remove(mainmenue);
		program.remove(crowin);
	}

}
