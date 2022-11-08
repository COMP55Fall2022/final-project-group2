package edu.pacific.comp55.starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GButton rect;
	private GButton rect2;
	private GImage background;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		rect = new GButton("Start", 730, 650, 150, 100);
		rect2 = new GButton("Quit", 400, 650, 150, 100);
		rect.setFillColor(Color.GREEN);
		rect2.setFillColor(Color.RED);
		background = new GImage("2.png", 0, 0);
	}

	@Override
	public void showContents() {
		program.add(background);
		program.add(rect);
		program.add(rect2);
	}

	@Override
	public void hideContents() {
		program.remove(rect);
		program.remove(rect2);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == rect) {
			program.switchToSome();
		}
		else if (obj == rect2){
			System.exit(0);
		}
		else {
			
		}
	}
	
}
