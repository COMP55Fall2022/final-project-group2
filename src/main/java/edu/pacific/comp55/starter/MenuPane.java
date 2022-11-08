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
	private GButton rect;
	private GImage background;
	private GLabel title;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		rect = new GButton("Next", 200, 200, 200, 200);
		rect.setFillColor(Color.RED);
		background = new GImage("2.png", 0, 0);
		title = new GLabel("SAYA'S BIG ADVENTURE", 300, 300);
		title.setFont("TimesRoman");
		title.scale(5, 5);
		title.setColor(Color.BLUE);
		
	}

	@Override
	public void showContents() {
		//program.add(rect);
		program.add(background);
		program.add(title);
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
