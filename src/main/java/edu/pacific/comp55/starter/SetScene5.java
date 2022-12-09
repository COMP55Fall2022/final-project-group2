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

public class SetScene5 extends GraphicsPane implements ActionListener {
	private MainApplication program;

	//GImages needed for scene 1
	private GButton exit;
	private GImage mapGif;
	private GImage homeGif1;
	private GImage homeGif2;
	private GImage homeGif3;
	private GImage homeGif4;
	private GImage homeGif5;
	private AudioPlayer mapSound;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String SOUND_FILE = "Running On Leaves.mp3";
	
	private Timer gifTimer = new Timer(1000, this);
	private Timer homeTimer = new Timer(1000, this);
	private int gifCounter = 0;

	
	public SetScene5(MainApplication app) {
		super();
		program = app;
		homeGif1 = new GImage("homePART1.gif",0,0);
		homeGif2 = new GImage("homePART2.gif",0,0);
		homeGif3 = new GImage("homePART3.gif",0,0);
		homeGif4 = new GImage("homePART4.gif",0,0);
		homeGif5 = new GImage("homePART5.gif",0,0);
		
		exit = new GButton("Main Menu", 525, 600, 100, 100);
		exit.setFillColor(Color.RED);
		
		mapGif = new GImage("journey4.gif",0,0);
		mapSound = AudioPlayer.getInstance();

		
	}
	
	
	
	// if we win the game we switch to scene1
	@Override
	public void showContents() {
		setScene5();
		gifTimer.start();
		mapSound.playSound(MUSIC_FOLDER, SOUND_FILE);
	}
	@Override
	public void hideContents() {
		program.remove(homeGif5);
		removebuttons();
	}
	public void setScene5() {
		program.add(mapGif);
		
	}
	
	// These functions check if the user is clicking on the interactable object and 
	//starts it's timer
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == exit) {
			hideContents();
			program.switchToMenu();
		}
	
	}
	
	//These functions create and remove the dialogues that get prompted using their own timers
	@Override
	public void actionPerformed(ActionEvent g) {
		Object source = g.getSource();
		
		if(source == gifTimer) {
			gifCounter++;
			System.out.println(gifCounter);
			if(gifCounter == 2) {
				mapSound.stopSound(MUSIC_FOLDER, SOUND_FILE);
				program.add(homeGif1);
			}
			if(gifCounter == 3) {
				program.remove(mapGif);
				gifTimer.stop();
				gifCounter = 0;
				System.out.println(gifCounter);
				homeTimer.start();
			}
		}
		
		if(source == homeTimer) {
			gifCounter++;
			System.out.println(gifCounter);
			if(gifCounter == 3) {program.add(homeGif2); }
			if(gifCounter == 4) {program.remove(homeGif1); }
			if(gifCounter == 7) {program.add(homeGif2); }
			if(gifCounter == 8) {program.remove(homeGif1); }
			if(gifCounter == 11) {program.add(homeGif3); }
			if(gifCounter == 12) {program.remove(homeGif2); }
			if(gifCounter == 15) {program.add(homeGif4); }
			if(gifCounter == 16) {program.remove(homeGif3); }
			if(gifCounter == 19) {program.add(homeGif5); }
			if(gifCounter == 20) {program.remove(homeGif4); }
			if(gifCounter == 23) {
				program.add(exit);
				homeTimer.stop();
			}
		}
	}
	
	public void removebuttons() {
		program.remove(exit);
	}
}
