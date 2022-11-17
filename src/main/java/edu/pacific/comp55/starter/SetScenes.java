package edu.pacific.comp55.starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;

public class SetScenes extends GraphicsPane{
	private MainApplication program; 
	private GImage scene1;
	private GImage roses;
	private GLabel yes;
	
	public SetScenes(MainApplication app) {
		super();
		program = app;
		scene1 = new GImage("scene1.png", 0, 0);
		roses = new GImage("roses.png", -165, 178);
		yes = new GLabel("You found it", 160, 170);
	
		
		
	}
	
	// if we win the game we switch to scene1
	
	@Override
	public void showContents() {
		program.add(scene1);
		program.add(roses);

		
	}

	@Override
	public void hideContents() {
		program.remove(scene1);
		program.remove(roses);
		
	}
	

	
	public void setscene1() {
		program.add(scene1);
		
	}
	
	public void addrosetext() {
		program.add(yes);
	}
	
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == roses) {
			addrosetext();
		
		}
	}
	
	
	
	
}
