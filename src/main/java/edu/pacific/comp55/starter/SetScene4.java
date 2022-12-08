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

public class SetScene4 extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GImage scene4;
	
	//Gimages needed for scene4
	private GImage dog3;
	private GImage dog3text;
	private GImage basket;
	private GImage basketscreen;
	private GImage mapGif;
	private AudioPlayer mapSound;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String SOUND_FILE = "Running On Leaves.mp3";
	//Timers needed for scene 4
	private boolean baskettimerstart = false;
	Timer dog3timer = new Timer(40, this);
	Timer baskettimer = new Timer(40, this);
	Timer gifTimer = new Timer(1000, this);
	int dialogueCountdown = 0;
	private int gifCounter = 0;
	public SetScene4(MainApplication app) {
		super();
		program = app;
		
		//For scene 4
		scene4 = new GImage("scene4.png", 0, 0);
		dog3 = new GImage("dog3.png", 200, 200);
		dog3text = new GImage("dog3text.png", 10, 10);
		basket = new GImage("basket.png", 0, 0);
		basketscreen = new GImage("basketscreen.png", 0 ,0);
		mapGif = new GImage("journey3.gif",0,0);
		mapSound = AudioPlayer.getInstance();
	}
	
	// if we win the game we switch to scene4
	@Override
	public void showContents() {
		setScene4();
	}
	@Override
	public void hideContents() {
		program.remove(scene4);
		//program.remove();
	}

	public void setScene4() {
		gifTimer.start();
		mapSound.playSound(MUSIC_FOLDER, SOUND_FILE);
		program.add(scene4);
		program.add(dog3);
		program.add(basket);
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
		
		if(source == gifTimer) {
			gifCounter++;
			System.out.println(gifCounter);
			if(gifCounter == 3) {
				program.remove(mapGif);
				mapSound.stopSound(MUSIC_FOLDER, SOUND_FILE);
				gifTimer.stop();
			}
		}
		
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