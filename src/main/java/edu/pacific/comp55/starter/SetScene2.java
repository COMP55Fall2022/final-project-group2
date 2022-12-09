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
	public static final String MUSIC_FOLDER = "sounds";
	private static final String SOUND_FILE = "Running On Leaves.mp3";
	
	//GImages needed for scene 2
	
	private GImage tumbleweed;
	private GImage tumbleweedtext;
	private GImage crow;
	private GImage crowscreen;
	private GImage telePole;
	private GImage telePoletext;
	private GButton cont;
	private GButton exit;
	private GImage map1gif;
	private AudioPlayer mapSound;
	
	
	//Timers needed for scene 2
	private boolean crowtimerstart = false;
	Timer tumbleweedtimer = new Timer(40, this);
	Timer telePoletimer = new Timer(40, this);
	Timer crowtimer = new Timer(40, this);
	Timer gifTimer = new Timer(1000, this);
	
	private int dialogueCountdown = 0;
	private int gifCounter = 0;
	
	public SetScene2(MainApplication app) {
		super();
		program = app;
		//For scene 2
		scene2 = new GImage("scene2 (1).png", 0, 0);
		crow = new GImage("crow.png", 450, 30);
		tumbleweed = new GImage("tumbleweed.png", 800, 400);
		telePole = new GImage("telephonePole.png", 20, 100);
		crow.scale(0.5);
		tumbleweed.scale(0.1);
		telePole.scale(0.8);
		
		cont = new GButton("Continue", 655, 600, 100, 100);
		cont.setFillColor(Color.GREEN);
		exit = new GButton("Main Menu", 455, 600, 100, 100);
		exit.setFillColor(Color.RED);
		map1gif = new GImage("journey1.gif", 0, 0);
		mapSound = AudioPlayer.getInstance();
	}
	
	
	
	// if we win the game we switch to scene2
	@Override
	public void showContents() {
		setScene2();
	}
	@Override
	public void hideContents() {
		program.remove(scene2);
		removebuttons();
		program.remove(crowscreen);
		program.remove(crow);
		program.remove(tumbleweed);
		program.remove(telePole);
	
	}

	public void setScene2() {
		gifTimer.start();
		mapSound.playSound(MUSIC_FOLDER, SOUND_FILE);
		program.add(scene2);
		program.add(crow);
		program.add(tumbleweed);
		program.add(telePole);
		program.add(map1gif);
	}
	
	// These functions check if the user is clicking on the interactable object and 
	//starts it's timer
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == tumbleweed && !crowtimerstart) {
			tumbleweedtext = new GImage("tumtext.png", 800, 200);
			tumbleweedtext.scale(0.5);
			program.add(tumbleweedtext);
			tumbleweedtimer.start();
		}
		
		if (obj == telePole && !crowtimerstart) {
			telePoletext = new GImage("telePoletext.png", 100, 100);
			telePoletext.scale(0.5);
			program.add(telePoletext);
			telePoletimer.start();
		}
		
		if (obj == crow) {
			crowscreen = new GImage("crowscreen.png", 350, 200);
			crowscreen.scale(0.5);
			program.add(crowscreen);
			crowtimer.start();
			crowtimerstart = true;
		}
		
		if (obj == cont) {
			removebuttons();
			program.switchToCrow();
		}
		
		if (obj == exit) {
			
			program.switchToMenu();
		}
	}
	
	//These functions create and remove the dialogues that get prompted using their own timers
	@Override
	public void actionPerformed(ActionEvent g) {
		Object source = g.getSource();
		
		//test
		if(source == gifTimer) {
			gifCounter++;
			System.out.println(gifCounter);
			if(gifCounter == 3) {
				program.remove(map1gif);
				mapSound.stopSound(MUSIC_FOLDER, SOUND_FILE);
				gifTimer.stop();
			}
		}
		
		if (source == tumbleweedtimer) {
			dialogueCountdown++;
			System.out.println(dialogueCountdown);
			if (dialogueCountdown > 100) {
				program.remove(tumbleweedtext);
				dialogueCountdown = 0;
				tumbleweedtimer.stop();
			}
		}
		
		if (source == telePoletimer) {
			dialogueCountdown++;
			
			if (dialogueCountdown > 100) {
				program.remove(telePoletext);
				dialogueCountdown = 0;
				telePoletimer.stop();
			}
		}
		
		if (source == crowtimer) {
			crowtimer.stop();
			program.add(cont);
			program.add(exit);
		}
	
	}
	
	public void removebuttons() {
		program.remove(cont);
		program.remove(exit);
	}
	
}