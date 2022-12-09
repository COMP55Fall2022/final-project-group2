package edu.pacific.comp55.starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import acm.graphics.*;

import java.util.Timer;
import java.util.TimerTask;

public class MouseGame extends GraphicsPane implements ActionListener{
	//private ArrayList<GImage> mouseList;
	private ArrayList<Rodent> mouseList;
	private GImage mouse;
	private GImage background;
	private MainApplication program;
	private GLabel score;
	private GLabel timeRemaining;
	private int points;
	private Timer mouseMovement;
	private TimerTask task;
	private boolean isGameRunning;
	private long lastUpdatedTime;
	private double gameRunTime; // number of seconds that the game has been running
	private GButton startButton;
	private GImage crowin;
	private GButton scene1;
	private GButton mainmenue;
	private GImage crowlost;
	private GButton tryagain;
	private GLine rowLine;
	private GLine colLine;
	private GImage startBoard;
	private GImage startBoardInstructionsBackground;
	// The below coordinates are the location where the actual playable area is in the background image.
	private static final int GAMEBOARD_LEFT = 225;
	private static final int GAMEBOARD_TOP = 117;
	private static final int GAMEBOARD_RIGHT = 1050;
	private static final int GAMEBOARD_BOTTOM = 942;
	//Mouse scaling sizes
	private static final double[] scaleSizes = new double[]{0.75, 0.5, 0.25};
	
	
	
	private static final double MAX_GAME_TIME = 30; // number of seconds that the game runs for
	private static final int MAX_NUMBER_MICE = 10;
	
	public MouseGame(MainApplication app) {
		super();
		program = app;
		mouseList = new ArrayList<Rodent>();
		background = new GImage("mousebg.png", 0, 0);
		crowin = new GImage("crowin.png", 350, 200);
		scene1 = new GButton("Next scene", 655, 500, 100, 100);
		scene1.setFillColor(Color.GREEN);
		crowin.scale(0.5);
		mainmenue = new GButton("Main menue", 455, 500, 100, 100);
		mainmenue.setFillColor(Color.red);
		crowlost = new GImage("crowlost.png", 350, 200);
		crowlost.scale(0.5);
		tryagain = new GButton("Try again", 655, 500, 100, 100);
		tryagain.setFillColor(Color.green);
		score = new GLabel("Score: " + points, 200, 100);
		score.scale(5);
		score.setColor(Color.white);
		//trying to add instructions
		startBoard = new GImage("mouseinstructions.png",0,0);
		//startBoardInstructionsBackground = new GImage("mousebg.png",0 ,0);
		//startBoard.scale(1.1);
		
		isGameRunning = false;
		lastUpdatedTime = 0;
		gameRunTime = 0;
		timeRemaining = new GLabel("Time Remaining: " + this.getRemaingTimeAsString(), (GAMEBOARD_RIGHT - 550), 100);
		timeRemaining.scale(5);
		timeRemaining.setColor(Color.white);
		System.out.println("Game variables initialized");
		
	
	}
	
	// draws black lines around the gameboard just to help us make sure that we have the bounds correct. 
	// can be removed later on.
	private void drawGridLines() {
    	
    	double y, x;
    	for(int i = 0; i <= 1;i++) {
    		y = i*(GAMEBOARD_BOTTOM - GAMEBOARD_TOP) + GAMEBOARD_TOP;
    		rowLine = new GLine(0, y , MainApplication.WINDOW_WIDTH, y); 
        	program.add(rowLine);
        	rowLine.setVisible(false);
        }
        for(int j = 0; j <= 1;j++) {
        	x = j * (GAMEBOARD_RIGHT - GAMEBOARD_LEFT) + GAMEBOARD_LEFT;
        	colLine = new GLine(x, 0, x, MainApplication.WINDOW_HEIGHT);
        	program.add(colLine);
        	colLine.setVisible(false);
        }
    }
	
	private void startGame() {
		// This is the main game loop
		System.out.println("Starting Game");
		lastUpdatedTime = System.nanoTime();
		isGameRunning = true;
		mouseMovement = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				onGameTimerEvent();
			}
		};
		// this sets up a timer to run at about 60 frames per second. This allows us to update objects on the screen at that rate.
		mouseMovement.scheduleAtFixedRate(task, 0, (1000/60));
	}
	private String getRemaingTimeAsString() {
		double remainingTime = MAX_GAME_TIME - gameRunTime;
		long HH = (long)remainingTime / 3600;
		long MM = ((long)remainingTime % 3600) / 60;
		long SS = (long)remainingTime % 60;
		if(HH > 0) {
			return(String.format("%02d:%02d:%02d", HH, MM, SS));
		} else {
			return(String.format("%02d:%02d", MM, SS));
		}
	}
	
	private void onGameTimerEvent() {
		double elapsedTimeInSeconds;
		long currentTime;
		double dX, dY;
		if(isGameRunning) {
			// This is the number of seconds that have passed since the last time we updated the screen
			currentTime = System.nanoTime();
			elapsedTimeInSeconds = ((double)(currentTime - lastUpdatedTime)) / (double)1_000_000_000; // converts nanoseconds to seconds
			gameRunTime += elapsedTimeInSeconds;
			timeRemaining.setLabel("Time Remaining: " + this.getRemaingTimeAsString());
			if(gameRunTime >= MAX_GAME_TIME) {
				isGameRunning = false;
				return;
			}
			double modX, modY;
			ArrayList<String> inGameBoard;
			for(Rodent gm:mouseList) {
				if(this.checkForCollision(gm.getMouseImg(), gm.getMouseId())) {
					gm.changeDirection();
				} 
				gm.setTimeOnPath(gm.getTimeOnPath() + elapsedTimeInSeconds);
				dX = ((double)gm.getDeltaX() * elapsedTimeInSeconds);
				dY = ((double)gm.getDeltaY() * elapsedTimeInSeconds);
				gm.getMouseImg().move(dX, dY);
				// If we need to change direction due to going outside of the game board, then we need to pop the mouse back on the gameboard
				// and make sure it's going in a direction away from the wall
				inGameBoard = this.checkGameboardCollision(gm.getMouseImg());
				if(inGameBoard.size() > 0) {
					modX = gm.getMouseImg().getX();
					modY = gm.getMouseImg().getY();
					if(inGameBoard.contains("left")) {
						modX = GAMEBOARD_LEFT + 10;
						if(gm.getDeltaX() < 0) {
							gm.setDeltaX(-1.0 * gm.getDeltaX());
						}
					}
					if(inGameBoard.contains("right")) {
						modX = GAMEBOARD_RIGHT - gm.getMouseImg().getWidth() - 10;
						if(gm.getDeltaX() > 0) {
							gm.setDeltaX(-1.0 * gm.getDeltaX());
						}
					}
					if(inGameBoard.contains("top")) {
						modY = GAMEBOARD_TOP + 10;
						if(gm.getDeltaY() < 0) {
							gm.setDeltaY(-1.0 * gm.getDeltaY());
						}
					}
					if(inGameBoard.contains("bottom")) {
						modY = GAMEBOARD_BOTTOM - gm.getMouseImg().getHeight() - 10;
						if(gm.getDeltaY() > 0) {
							gm.setDeltaY(-1.0 * gm.getDeltaY());
						}
					}
					gm.getMouseImg().setLocation(modX, modY);
				}
			}
			lastUpdatedTime = currentTime;
		} else {
			System.out.println("cancelling timer....");
			this.handleGameOver();
		}
	}
	
	private void handleGameOver() {
		System.out.println("GAME IS OVER. Take Next Action....");
		this.isGameRunning = false;
		mouseMovement.cancel();
		if(wingame() == true) {
			program.add(crowin);
			program.add(scene1);
			program.add(mainmenue);
			
		}else {
			program.add(crowlost);
			program.add(tryagain);
			program.add(mainmenue);
			
		}
		
	}
	
	private void addMouse() {
		int x = (int) (Math.random() * (GAMEBOARD_RIGHT - GAMEBOARD_LEFT) + GAMEBOARD_LEFT);
		int y = (int) (Math.random() * (GAMEBOARD_BOTTOM - GAMEBOARD_TOP) + GAMEBOARD_TOP);
		mouse = new GImage("genericMouse.png");
		int scale = this.getScaleMouseNum();
		mouse.scale(scaleSizes[scale]);
		mouse.setLocation(x,y);
		int x1, y1;
		while(checkForCollision(mouse, -1)) {
			x1 = (int) (Math.random() * (GAMEBOARD_RIGHT - GAMEBOARD_LEFT) + GAMEBOARD_LEFT);
			y1 = (int) (Math.random() * (GAMEBOARD_BOTTOM - GAMEBOARD_TOP) + GAMEBOARD_TOP);
			mouse.setLocation(x1, y1);
		} 
		Rodent gm = new Rodent();
		gm.setMouseImg(mouse);
		mouseList.add(gm);
		program.add(gm.getMouseImg());
	}
	
	//Scales the mouse down since the original image is too big. 
	//Chances for a default size is 50%, scaled down half is 30%, and scaled down to a quarter is 20% 
	private int getScaleMouseNum() {
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
		return scaleNum;
	}
	
	// This function checks if the mouse has collided with any other mice AND 
	// also checks to make sure the mouse is within the game board
	private boolean checkForCollision(GImage m, int mouseId) {
		GRectangle mouseBounds = m.getBounds();
		GRectangle miceListBounds;
		for(Rodent gm:mouseList) {
			// Make sure we are not comparing this mouse with itself!
			if(mouseId != gm.getMouseId()) {
				miceListBounds = gm.getMouseImg().getBounds();
				if (mouseBounds.intersects(miceListBounds)) {
					return true;
				}
			}
		}
		if(!this.checkGameboardCollision(m).isEmpty()) {
			return true;
		}
		return false;
	}
	
	// Checks if we are still within the gameboard
	private ArrayList<String> checkGameboardCollision(GImage m) {
		ArrayList<String> ret = new ArrayList<String>();
		GRectangle mouseBounds = m.getBounds();
		if(mouseBounds.getX() <= GAMEBOARD_LEFT) {
			ret.add("left");
		}
		if((mouseBounds.getX()+mouseBounds.getWidth()) >= GAMEBOARD_RIGHT) {
			ret.add("right");
		}
		if(mouseBounds.getY() <= GAMEBOARD_TOP) {
			ret.add("top");
		}
		if((mouseBounds.getY()+mouseBounds.getHeight()) >= GAMEBOARD_BOTTOM) {
			ret.add("bottom");
		}
		return(ret);
	}	
	
	private void addScore(int s) {
		points += s;
	}
	
	private void displayStartButton() {
		startButton = new GButton("START", 600, 625, 100, 100);
		startButton.setFillColor(Color.GREEN);
		//program.add(startBoardInstructionsBackground);
		program.add(startBoard);
		program.add(startButton);
	}
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(background);
		program.add(score);
		program.add(timeRemaining);
		drawGridLines();
		for(int i = 0; i < MAX_NUMBER_MICE; i++) {
			addMouse();
		}
		this.displayStartButton();
		System.out.println("showContents Completed");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(!isGameRunning) {
			GObject obj = program.getElementAt(e.getX(), e.getY());
			if(obj != null) {
				if(obj == startButton) {
					program.remove(startButton);
					program.remove(startBoard);
					//program.remove(startBoardInstructionsBackground);
					this.startGame();
					return;
				}
				if(obj == tryagain) {
					program.switchToMouse();
					
				}
				if(obj == mainmenue) {
					program.switchToMenu();
					program.remove(crowlost);
					program.remove(tryagain);
					program.remove(mainmenue);
					program.remove(crowin);
					program.remove(scene1);
					program.remove(score);
					program.remove(timeRemaining);
					
				}
				if(obj == scene1) {
					program.switchToScene2();
				}
			}
		}
		GImage g;
		GObject mouseObj = program.getElementAt(e.getX(), e.getY());
		if(mouseObj != null) {
			for(Rodent gm : mouseList) {
				g = gm.getMouseImg();
				if(mouseObj == g) {
					if(g.getWidth() == 231.75) {
						addScore(5);
						score.setLabel("Score: " + points);
						addMouse();
					}
					else if(g.getWidth() == 154.5) {
						addScore(10);
						score.setLabel("Score: " + points);
						addMouse();
					}
					else {
						addScore(15);
						score.setLabel("Score: " + points);
						addMouse();
					}
					program.remove(g);
					mouseList.remove(gm);
					if(this.wingame()) {
						this.handleGameOver();
					}
					break;
				}
			}
		}
		
	}
	
	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		for(int i = 0; i < mouseList.size(); i++) {
			program.remove(crowlost);
			program.remove(tryagain);
			program.remove(mainmenue);
			program.remove(crowin);
			program.remove(scene1);
			program.remove(score);
			program.remove(timeRemaining);
			program.remove(mouseList.get(i).getMouseImg());
		}
		mouseList.clear();
		program.remove(background);
		program.remove(rowLine);
		program.remove(colLine);
		
	}
	
	public boolean wingame() {
		if(points >= 100){
			return true;
		} else {
			return false;
		}
	}
	
	
	
}
