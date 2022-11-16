package edu.pacific.comp55.starter;

import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GLabel;

public class SetScenes extends GraphicsPane{
	private MainApplication program; 
	private GImage scene1;
	
	public SetScenes(MainApplication app) {
		super();
		program = app;
		scene1 = new GImage("scene1.png", 0, 0);
		
		
	}
	
	// if we win the game we switch to scene1
	
	@Override
	public void showContents() {
		program.add(scene1);
		
	}

	@Override
	public void hideContents() {
		program.remove(scene1);
		
	}
	

	
	
	

}
