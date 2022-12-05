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

public class SetScenes extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GImage scene1;
	private GImage scene2;
	private GImage scene3;
	private GImage scene4;
	//private GImage endscene;
	
	//GImages needed for scene 1
	private GImage roses;
	private GImage rosesdialogue;
	private GImage blueflower;
	private GImage blueflowertext;
	private GImage sun;
	private GImage sunscreen;
	
	//GImages needed for scene 2
	private GImage desertpath;
	private GImage desertpathtext;
	private GImage tumbleweed;
	private GImage crow;
	private GImage crowscreen;
	
	//GImages needed for scene3
	private GImage dog2;
	private GImage dog2text;
	private GImage barn;
	private GImage barntext;
	private GImage tree;
	private GImage treescreen;
	
	//Gimages needed for scene4
	private GImage dog3;
	private GImage dog3text;
	private GImage basket;
	private GImage basketscreen;
	
	//Timers needed for scene 1
	private boolean suntimerstart = false;
	Timer rosestimer = new Timer(40, this);
	Timer blueflowertimer = new Timer(40, this);
	Timer suntimer = new Timer(40, this);
	
	//Timers needed for scene 2
	private boolean crowtimerstart = false;
	Timer desertpathtimer = new Timer(40, this);
	Timer crowtimer = new Timer(40, this);
	
	//Timers needed for scene 3
	
	//Timers needed for scene 4
	
	int dialogueCountdown = 0;
	

	public SetScenes(MainApplication app) {
		super();
		program = app;
		scene1 = new GImage("scene1.png", 0, 0);
		roses = new GImage("roses.png", -165, 178);
		blueflower = new GImage("blueflower.png", 795, 425);
		sun = new GImage("sun.png", 400, -20);
		sun.scale(0.15);
		
		//For scene 2
		scene2 = new GImage("scene2.png", 0, 0);
		crow = new GImage("crow.png", 50, 50);
		tumbleweed = new GImage("tumbleweed.png", 100, 100);
		
		
		//For scene 3
		scene3 = new GImage("scene3.png", 0, 0);
		dog2 = new GImage("dog2.png", 100, 100);
		dog2text = new GImage("dog2text.png", 200, 100);
		
		
		//For scene 4
		scene4 = new GImage("scene4.png", 0, 0);
		dog3 = new GImage("dog3.png", 200, 200);
		dog3text = new GImage("dog3text.png", 10, 10);
		basket = new GImage("basket.png", 0, 0);
		basketscreen = new GImage("basketscreen.png", 0 ,0);
	}
	
	
	

	// if we win the game we switch to scene1

	@Override
	public void showContents() {
		program.add(scene1);
		program.add(roses);
		program.add(blueflower);
		program.add(sun);
		
		//scene 2
		program.add(scene2);

	}

	@Override
	public void hideContents() {
		program.remove(scene1);
		program.remove(roses);

	}

	public void setscene1() {
		program.add(scene1);

	}

	public void setscene2() {
		program.add(scene2);
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
			sunscreen = new GImage("sunscreen.png", 350, 200);
			sunscreen.scale(0.5);
			program.add(sunscreen);
			suntimer.start();
			suntimerstart = true;
		}
		
		if (obj == desertpath && !crowtimerstart) {
			//desertpathtext = new GImage("desertpathtext.png, 300, 300");
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
		
		if (source == blueflowertimer) {
			dialogueCountdown++;
			
			if (dialogueCountdown > 100) {
				program.remove(blueflowertext);
				dialogueCountdown = 0;
				blueflowertimer.stop();
			}
		}
		
		if (source == suntimer) {
			
		}

	}

}
