package edu.pacific.comp55.starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class SetScenes extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GImage scene1;

	//GImages needed for scene 1
	private GImage roses;
	private GImage clickprompt;
	private GImage rosesdialogue;
	private GImage blueflower;
	private GImage blueflowertext;
	private GImage sun;
	private GImage sunscreen;
	private GButton cont;
	private GButton exit;
	
	
	//Timers needed for scene 1
	private boolean suntimerstart = false;
	private Timer rosestimer = new Timer(40, this);
	private Timer blueflowertimer = new Timer(40, this);
	private Timer suntimer = new Timer(40, this);
	private Timer clickprompttimer = new Timer(40, this);
	
	private int dialogueCountdown = 0;
	
	public SetScenes(MainApplication app) {
		super();
		program = app;
		scene1 = new GImage("scene1.png", 0, 0);
		roses = new GImage("roses.png", -165, 178);
		blueflower = new GImage("blueflower.png", 795, 425);
		sun = new GImage("sun.png", 400, -20);
		sun.scale(0.15);
		
		cont = new GButton("Continue", 655, 500, 100, 100);
		cont.setFillColor(Color.GREEN);
		exit = new GButton("Main Menu", 455, 500, 100, 100);
		exit.setFillColor(Color.RED);
		
		clickprompt = new GImage("clickprompt.png", 200, 240);
		clickprompt.scale(0.75);

		
	}
	
	
	
	// if we win the game we switch to scene1
	@Override
	public void showContents() {
		setScene1();
		clickprompttimer.start();
	}
	@Override
	public void hideContents() {
		program.remove(scene1);
		program.remove(roses);
		removebuttons();
		program.remove(sunscreen);
	}
	public void setScene1() {
		program.add(scene1);
		program.add(roses);
		program.add(blueflower);
		program.add(sun);
	}
	
	// These functions check if the user is clicking on the interactable object and 
	//starts it's timer
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == roses && !suntimerstart) {
			rosesdialogue = new GImage("rosesdialogue.png", 65, 200);
			program.add(rosesdialogue);
			rosestimer.start();
		}
		
		if (obj == blueflower && !suntimerstart) {
			blueflowertext = new GImage("blueflowerdialogue.png", 670, 275);
			blueflowertext.scale(0.5);
			program.add(blueflowertext);
			blueflowertimer.start();
		}
		
		
		if (obj == sun) {
			sunscreen = new GImage("sunscreen.png", 350, 150);
			sunscreen.scale(0.5);
			program.add(sunscreen);
			suntimer.start();
			suntimerstart = true;
		}
		
		if (obj == cont) {
			removebuttons();
			program.switchToMouse();
		}
		
		if (obj == exit) {
			program.switchToMenu();
		}
	
	}
	
	//These functions create and remove the dialogues that get prompted using their own timers
	@Override
	public void actionPerformed(ActionEvent g) {
		Object source = g.getSource();
		
		
		if (source == rosestimer) {
			dialogueCountdown++;
			System.out.println(dialogueCountdown);
			if (dialogueCountdown > 100) {
				program.remove(rosesdialogue);
				dialogueCountdown = 0;
				rosestimer.stop();
			}
		}
		
		if (source == clickprompttimer) {
			program.add(clickprompt);
			dialogueCountdown++;
			System.out.println(dialogueCountdown);
			if (dialogueCountdown > 80) {
				program.remove(clickprompt);
				dialogueCountdown = 0;
				clickprompttimer.stop();
			}
		}
		
		if (source == blueflowertimer) {
			dialogueCountdown++;
			
			if (dialogueCountdown > 100) {
				program.remove(blueflowertext);
				dialogueCountdown = 0;
				blueflowertimer.stop();
			}
		}
		
		if (source == suntimer) {
			suntimer.stop();
			program.add(cont);
			program.add(exit);
		}
	}
	
	public void removebuttons() {
		program.remove(cont);
		program.remove(exit);
	}
}
