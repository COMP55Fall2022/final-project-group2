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

public class SetScene2 extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GImage scene2;
	
	//GImages needed for scene 2
	private GImage desertpath;
	private GImage desertpathtext;
	private GImage tumbleweed;
	private GImage crow;
	private GImage crowscreen;
	private GImage telePole;
	
	//Timers needed for scene 2
	private boolean crowtimerstart = false;
	Timer desertpathtimer = new Timer(40, this);
	Timer tumbleweedtimer = new Timer(40, this);
	Timer telePoletimer = new Timer(40, this);
	Timer crowtimer = new Timer(40, this);
	
	int dialogueCountdown = 0;
	
	public SetScene2(MainApplication app) {
		super();
		program = app;
		
		//For scene 2
		scene2 = new GImage("scene2.png", 0, 0);
		crow = new GImage("crow.png", 380, 30);
		tumbleweed = new GImage("tumbleweed.png", 800, 300);
		telePole = new GImage("telephonePole.png", 20, 100);
		desertpath = new GImage("desertpath.png", 0, 0);
		//Scaling all the GImages to the correct size
		desertpath.scale(0.5);
		crow.scale(0.5);
		tumbleweed.scale(0.1);
		telePole.scale(0.8);

	}
	
	
	
	// if we win the game we switch to scene2
	@Override
	public void showContents() {
		setScene2();
	}
	@Override
	public void hideContents() {
		program.remove(scene2);
		//program.remove();
	}

	public void setScene2() {
		program.add(scene2);
		program.add(crow);
		program.add(tumbleweed);
		program.add(telePole);
		program.add(desertpath);
	}
	
	// These functions check if the user is clicking on the interactable object and 
	//starts it's timer
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
	//	if (obj == roses && !suntimerstart) {
	//		rosesdialogue = new GImage("rosesdialogue.png", 65, 200);
	//		program.add(rosesdialogue);
	//		rosestimer.start();
	//	}
		
	//	if (obj == blueflower && !suntimerstart) {
	//		blueflowertext = new GImage("blueflowerdialogue.png", 670, 275);
	//		blueflowertext.scale(0.5);
	//		program.add(blueflowertext);
	//		blueflowertimer.start();
	//	}
		
		
	//	if (obj == sun) {
	//		sunscreen = new GImage("sunscreen.png", 350, 200);
	//		sunscreen.scale(0.5);
	//		program.add(sunscreen);
	//		suntimer.start();
	//		suntimerstart = true;
	//	}
		
		if (obj == desertpath && !crowtimerstart) {
			//desertpathtext = new GImage("desertpathtext.png, 300, 300");
		}
	
	}
	
	//These functions create and remove the dialogues that get prompted using their own timers
	@Override
	public void actionPerformed(ActionEvent g) {
		Object source = g.getSource();
	//	if (source == rosestimer) {
	//		dialogueCountdown++;
	//		System.out.println(dialogueCountdown);
	//		if (dialogueCountdown > 100) {
	//			program.remove(rosesdialogue);
	//			dialogueCountdown = 0;
	//			rosestimer.stop();
	//		}
	//	}
		
	//	if (source == blueflowertimer) {
	//		dialogueCountdown++;
			
	//		if (dialogueCountdown > 100) {
	//			program.remove(blueflowertext);
	//			dialogueCountdown = 0;
	//			blueflowertimer.stop();
	//		}
	//	}
		
	//	if (source == suntimer) {
			
	//	}
	}
}