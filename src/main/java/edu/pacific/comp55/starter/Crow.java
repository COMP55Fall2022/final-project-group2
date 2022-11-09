package edu.pacific.comp55.starter;

import acm.graphics.GImage;

public class Crow extends GraphicsPane {
	private MainApplication program;
	private GImage crowgamebackground;
	
	
	
	
	
	public Crow(MainApplication app) {
		this.program = app;
		crowgamebackground = new GImage("7.png", 0, 0);
	}
	
	
	
	
	@Override
	public void showContents() {
		program.add(crowgamebackground);
		
		
		
	}

	@Override
	public void hideContents() {
		program.remove(crowgamebackground);
		
	} 
	

}
