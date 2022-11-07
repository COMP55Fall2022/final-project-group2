package edu.pacific.comp55.starter;
import java.awt.Color;

import javax.swing.JOptionPane;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

public class Instructions extends GraphicsProgram {
	private MainApplication program;
	private GLabel Instructions1;

	
	public Instructions(MainApplication app) {
		this.program = app;
		
		Instructions1 = new GLabel("Click on as many mice as possible in 30 seconds to win the game!", 150, 300);
		Instructions1.setFont("Arial-24");
		Instructions1.setColor(null);
		
	}

	
	//adds instructions to the screen 
	public void showInstructions() {
		program.add(Instructions1);
	}
	
	//removes instructions from the screen 
	public void hideInstructions() {
		program.remove(Instructions1);
	}
	
	public GLabel getinstructions1() {
		return Instructions1;
	}



	@Override
	public void run() {
		showInstructions();
		
	}
	

	
	
}
