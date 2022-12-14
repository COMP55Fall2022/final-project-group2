package edu.pacific.comp55.starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class MenuPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GButton start;
	private GButton quit;
	private GImage background;
	//private GLabel title;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		start = new GButton("Start", 730, 650, 150, 100);
		quit = new GButton("Quit", 400, 650, 150, 100);
		start.setFillColor(Color.GREEN);
		quit.setFillColor(Color.RED);
		background = new GImage("2.png", 0, 0);
		//title = new GLabel("SAYA'S BIG ADVENTURE", 300, 300);
		//title.setFont("TimesRoman");
		//title.scale(5, 5);
		//title.setColor(Color.BLUE);
		displayGameObjectContents();
		
	}
	
	public void displayGameObjectContents() {
		program.add(background);
		//program.add(title);
		program.add(start);
		program.add(quit);
	}

	@Override
	public void showContents() {
//		program.add(background);
//		program.add(title);
//		program.add(rect);
//		program.add(rect2);

	}

	@Override
	public void hideContents() {
//		program.remove(rect);
//		program.remove(rect2);
//		program.remove(title);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == start) {
			//program.switchToTTT();
			//program.switchToMouse();
			program.switchToScene1();
		}
		else if (obj == quit){
			System.exit(0);
		}
		else {
			
		}
	}
	
}
