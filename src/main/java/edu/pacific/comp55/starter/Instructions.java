package edu.pacific.comp55.starter;
import javax.swing.JOptionPane;

import acm.graphics.GImage;

public class Instructions extends GraphicsProgram {
	private MainApplication program;
	private GParagraph Instructions1;
	private GParagraph Instructions2;
	private GParagraph Instructions3; 
	private GParagraph Instructions4;
       
	
	//public void minigame1instructions() {
	//removeObjects();
	//OptionPane.showMessageDialog(null, "Instuctions1", "Play game1", JOptionPane.INFORMATION_MESSAGE);
		
		
	public Instructions(MainApplication app, MiniGameType type) {
		this.program = app;
		
		Instructions1 = new GParagraph("Click on as many mice as possible in 30 seconds to win the game!", 150, 300);
		Instructions1.setFont("Arial-24");
		

		Instructions2 = new GParagraph("Move Saaya left and right to protect her from the crow", 150, 300);
		Instructions2.setFont("Arial-24");
		
		Instructions3 = new GParagraph("Are you ready to play Tic Tac Toe with Doug?", 150, 300);
		Instructions3.setFont("Arial-24");
		
		
		Instructions4 = new GParagraph("Move left and right to collect as many apples as you can before you reach home ", 150, 300);
		Instructions4.setFont("Arial-24");
	}

	
	
	//adds instructions to the screen 
	public void showInstructions() {
		
	}
	
	//removes instructions from the screen 
	public void hideInstructions() {
		
	}
	
	public GParagraph getinstructions1() {
		return Instructions1;
	}
	
	public GParagraph getinstrutions2() {
		return Instructions2;
	}
	
	public GParagraph getinstrutions3() {
		return Instructions3;
	}
	
	public GParagraph getinstrutions4() {
		return Instructions4;
	}
	
	
	
	
}
