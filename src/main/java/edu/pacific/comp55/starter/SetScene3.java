package edu.pacific.comp55.starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;

public class SetScene3 extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GImage scene3;
	
	//GImages needed for scene 3
	private GImage dog2;
	private GImage dog2text;
	private GImage barn;
	private GImage barntext;
	private GImage tree;
	private GImage treescreen;
	
	//Timers needed for scene 3
	private boolean treetimerstart = false;
	Timer barntimer = new Timer(40, this);
	Timer dog2timer = new Timer(40, this);
	Timer treetimer = new Timer(40, this);
	
	int dialogueCountdown = 0;
	
	public SetScene3(MainApplication app) {
		super();
		program = app;
		
		//For scene 3
		scene3 = new GImage("scene3.png", 0, 0);
		dog2 = new GImage("dog2.png", 100, 100);
		dog2text = new GImage("dog2text.png", 200, 100);
		barn = new GImage("barn.png", 100, 100);
		barntext = new GImage("barntext.png", 100, 100);

	}
	
	// if we win the game we switch to scene3
	@Override
	public void showContents() {
		setScene3();
	}
	@Override
	public void hideContents() {
		program.remove(scene3);
		//program.remove();
	}

	public void setScene3() {
		program.add(scene3);
		program.add(dog2);
		program.add(barn);
	}
	
	// These functions check if the user is clicking on the interactable object and 
	//starts it's timer
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
	
	}
	
	//These functions create and remove the dialogues that get prompted using their own timers
	@Override
	public void actionPerformed(ActionEvent g) {
		Object source = g.getSource();

	}
}