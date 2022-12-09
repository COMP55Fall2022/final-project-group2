package edu.pacific.comp55.starter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import acm.graphics.*;
import java.util.Timer;
import java.util.TimerTask;

public class TTTGraphics extends GraphicsPane implements ActionListener {
	private TicTacToe board;
	private MainApplication program;
	private GImage background;
	// The below coordinates are the location where the actual playable area is in
	// the background image.
	private static final int GAMEBOARD_LEFT = 180;
	private static final int GAMEBOARD_TOP = 100;
	private static final int GAMEBOARD_RIGHT = 1038;
	private static final int GAMEBOARD_BOTTOM = 956;
	// We store references to all of the GImage game pieces used so that we can
	// remove them later on.
	private ArrayList<GImage> pieces;
	private boolean messageShowing = false;
	private GLabel gameMessage;
	private GImage crowin;
	private GButton scene1;
	private GButton mainmenue;
	private GImage crowlost;
	private GButton tryagain;
	private GImage crowresult;

	public TTTGraphics(MainApplication app) {
		super();
		program = app;
		pieces = new ArrayList<>();
		background = new GImage("TTTBG1.png", 0, 0);
		board = new TicTacToe(3, 3);
		board.setupBoard();
		messageShowing = false;
		crowin = new GImage("crowin.png", 350, 200);
		scene1 = new GButton("Continue", 655, 500, 100, 100);
		scene1.setFillColor(Color.GREEN);
		crowin.scale(0.5);
		mainmenue = new GButton("Main Menu", 455, 500, 100, 100);
		mainmenue.setFillColor(Color.red);
		crowlost = new GImage("crowlost.png", 350, 200);
		crowlost.scale(0.5);
		tryagain = new GButton("Try Again", 655, 500, 100, 100);
		tryagain.setFillColor(Color.green);
	}

	private void drawGridLines() {
		GLine rowLine;
		GLine colLine;
		double y, x;
		for (int i = 0; i <= board.get_num_rows(); i++) {
			// rowLine = new GLine(0,i *
			// (MainApplication.WINDOW_WIDTH/3.4),MainApplication.WINDOW_WIDTH,i*(MainApplication.WINDOW_WIDTH/3.4));
			y = i * ((GAMEBOARD_BOTTOM - GAMEBOARD_TOP) / board.get_num_rows()) + GAMEBOARD_TOP;
			rowLine = new GLine(0, y, MainApplication.WINDOW_WIDTH, y);
			program.add(rowLine);
			rowLine.setVisible(false);
		}
		for (int j = 0; j <= board.get_num_cols(); j++) {
			// colLine = new
			// GLine(j*(MainApplication.WINDOW_HEIGHT/2.7),0,j*(MainApplication.WINDOW_HEIGHT/2.7),MainApplication.WINDOW_HEIGHT);
			x = j * ((GAMEBOARD_RIGHT - GAMEBOARD_LEFT) / board.get_num_cols()) + GAMEBOARD_LEFT;
			colLine = new GLine(x, 0, x, MainApplication.WINDOW_HEIGHT);
			program.add(colLine);
			colLine.setVisible(false);
		}
	}

	private void drawCat(double xPos, double yPos) {
		GImage piece = new GImage("cat.png", xPos, yPos);
		double offsetX = (((GAMEBOARD_RIGHT - GAMEBOARD_LEFT) / board.get_num_cols()) - piece.getWidth()) / 2.0;
		double offsetY = (((GAMEBOARD_BOTTOM - GAMEBOARD_TOP) / board.get_num_rows()) - piece.getHeight()) / 2.0;
		xPos += offsetX;
		yPos += offsetY;
		piece.setLocation(xPos, yPos);
		pieces.add(piece);
		program.add(piece);
	}

	private void drawDog(double xPos, double yPos) {
		GImage piece = new GImage("dog.png", xPos, yPos);
		double offsetX = (((GAMEBOARD_RIGHT - GAMEBOARD_LEFT) / board.get_num_cols()) - piece.getWidth()) / 2.0;
		double offsetY = (((GAMEBOARD_BOTTOM - GAMEBOARD_TOP) / board.get_num_rows()) - piece.getHeight()) / 2.0;
		xPos += offsetX;
		yPos += offsetY;
		piece.setLocation(xPos, yPos);
		pieces.add(piece);
		program.add(piece);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("ACTION PERFORMED");
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(background);
		drawGridLines();
		drawBoard();
	}

	private void drawBoard() {
		for (int i = 0; i < board.get_num_rows(); i++) {
			for (int j = 0; j < board.get_num_cols(); j++) {
				double y = i * ((GAMEBOARD_RIGHT - GAMEBOARD_LEFT) / board.get_num_cols()) + GAMEBOARD_TOP;
				double x = j * ((GAMEBOARD_BOTTOM - GAMEBOARD_TOP) / board.get_num_rows()) + GAMEBOARD_LEFT;
				if (board.board[i][j] == 'c') {
					drawCat(x, y);
				} else if (board.board[i][j] == 'd') {
					drawDog(x, y);
				}
			}
		}
	}

	private void showMessage(String theMsg, boolean autoHide) {
		gameMessage = new GLabel(theMsg);

		gameMessage.setFont(new Font("Serif", Font.BOLD, 32));
		gameMessage.setColor(Color.red);

		// Center the message in the area above the gameboard
		double xPos = (MainApplication.WINDOW_WIDTH - gameMessage.getWidth()) / 2.0;
		double yPos = (GAMEBOARD_TOP - gameMessage.getHeight()) / 2.0;
		gameMessage.setLocation(xPos, yPos);
		program.add(gameMessage);
		messageShowing = true;
		if (autoHide) {
			Timer myTime = new Timer("messageTimer");
			myTime.schedule(new TimerTask() {
				@Override
				public void run() {
					removeMessage();
				}
			}, 2000);
		}
	}

	private void removeMessage() {
		program.remove(gameMessage);
		this.messageShowing = false;
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		for (GImage p : pieces) {
			program.remove(p);
		}
		if (this.messageShowing) {
			this.removeMessage();
		}
		
		program.remove(crowresult);
		//program.remove(tryagain);
		//tryagain = null;
		
		
		program.remove(mainmenue);
		program.remove(scene1);
	}

	private int calculate_row_from_mouse(int xPos) {
		int row = (xPos - GAMEBOARD_LEFT) / (((GAMEBOARD_RIGHT - GAMEBOARD_LEFT) / board.get_num_cols()));
		return row;
	}

	private int calculate_col_from_mouse(int yPos) {
		int col = (yPos - GAMEBOARD_TOP) / (((GAMEBOARD_BOTTOM - GAMEBOARD_TOP) / board.get_num_rows()));
		return col;
	}

	private void displayResetButton() {
		//tryagain = new GButton("Try again", 655, 500, 100, 100);
		//tryagain.setFillColor(Color.GREEN);
		program.add(tryagain);
	}

	private void resetGame() {
		this.hideContents();
		this.removeMessage();
		board = new TicTacToe(3, 3);
		board.setupBoard();
	}

	private void handleWin() {
		System.out.println("handling win...");
		if (board.getYouWin()) {
			crowresult = crowin;
			System.out.println("You Won!!!");
			this.showMessage("You Won!!!", false);
			program.add(crowresult);
			program.add(scene1);
			program.add(mainmenue);
			//this.exitGame();
		} else if (board.getDogWin()) {
			crowresult = crowlost;
			System.out.println("You lost the game to the dog!!!");
			this.showMessage("You lost the game to the dog!!!", false);
			program.add(crowresult);
			program.add(mainmenue);
			this.offerToReplay();
		}
		// END THE GAME HERE. TODO: Add button to exit or restart the game
	}

	private void handleTie() {
		System.out.println("Determined it is a tie!!!");
		this.showMessage("It's a Tie Game!!!", false);
		this.offerToReplay();
	}

	private void doDogMove() {
		board.dogMove();
		this.drawBoard();
		if (board.checkForWin()) {
			System.out.println("checked for win...true");
			this.handleWin();
		} else {
			System.out.println("checked for win...false");
			if (board.checkForTie()) {
				this.handleTie();
				System.out.println("Tie in doDogMove");
			}
		}
	}

	private void exitGame() {
		System.out.println("need to add code to move on to next mini-game?");
		program.switchToScene4();
	}
	

	private void offerToReplay() {
		System.out.println("need to add code to add a 'click to restart' button, that restarts the game.");
		
		this.displayResetButton();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		// Handle any button that are outside of the gameboard here
		GObject obj = program.getElementAt(x, y);
		if (obj != null) {
			if (obj == tryagain) {
				System.out.println("reset button pressed");
				program.remove(tryagain);
				program.remove(mainmenue);
				program.remove(crowlost);
				this.resetGame();
				return;
			}
			
			if(obj == mainmenue) {
				hideContents();
				
				program.switchToMenu();
				
			}
			
			if(obj == scene1) {
				this.exitGame();
			}
		}

		// return if the game is over
		if (board.checkForWin()) {
			return;
		} else if (board.checkForTie()) {
			this.handleTie();
			System.out.println("Tie in mousePressed");
		}

		// Now check the row and col on the gameboard. Return if the calculated row and
		// col is outside of the gameboard allowed values
		int row = calculate_row_from_mouse(y);
		int col = calculate_col_from_mouse(x);
		boolean inGameBoard = false;

		if (row >= 0 && row < board.get_num_rows()) {
			if (col >= 0 && col < board.get_num_cols()) {
				inGameBoard = true;
			}
		}
		if (!inGameBoard) {
			return;
		}

		if (board.board[row][col] == 'b') {
			board.board[row][col] = 'c';
		} else {
			System.out.println("Illegal Move");
			showMessage("Illegal Move!!!", true);
			return;
		}
		this.drawBoard();
		if (board.checkForWin()) {
			this.handleWin();
		} else {
			if (board.checkForTie()) {
				this.handleTie();
				System.out.println("Tie in drawBoard");
			}
			Timer timer = new Timer("dogmove");
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					doDogMove();
				}
			}, 1000L);
		}
	}
}
