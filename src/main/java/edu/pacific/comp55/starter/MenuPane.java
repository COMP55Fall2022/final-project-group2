package edu.pacific.comp55.starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GButton rect;
	private GImage background;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		rect = new GButton("Start", 730, 650, 150, 100);
		rect.setFillColor(Color.GREEN);
		background = new GImage("2.png", 0, 0);
	}

	@Override
	public void showContents() {
		program.add(background);
		program.add(rect);
	}

	@Override
	public void hideContents() {
		program.remove(rect);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == rect) {
			program.switchToSome();
		}
	}
}
