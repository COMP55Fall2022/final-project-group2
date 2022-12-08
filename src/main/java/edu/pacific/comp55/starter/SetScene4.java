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
	private GImage saaya;
	private GImage dog3;
	private GImage dog3text;
	private GImage apple;
	private GImage appletext;
	private GImage basket;
	private GImage basketscreen;
	private GImage mapGif;
	private AudioPlayer mapSound;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String SOUND_FILE = "Running On Leaves.mp3";
	
	private GButton cont;
	private GButton exit;
	
	//Timers needed for scene 4
	private boolean baskettimerstart = false;
	Timer dog3timer = new Timer(40, this);
	Timer appletimer = new Timer(40, this);
	Timer baskettimer = new Timer(40, this);
	Timer gifTimer = new Timer(1000, this);
	int dialogueCountdown = 0;
	private int gifCounter = 0;
	
	public SetScene4(MainApplication app) {
		super();
		program = app;
		
		//For scene 4
		saaya = new GImage("cat.png", 200, 600);
		scene4 = new GImage("basketbackground.png", 0, 0);
		dog3 = new GImage("dog3.png", 700, 500);
		apple = new GImage("apple.png", 600, 700);
		basket = new GImage("basket.png", 400, 600);
		basketscreen = new GImage("basketscreen.png", 0 ,0);
		mapGif = new GImage("journey3.gif",0,0);
		mapSound = AudioPlayer.getInstance();

		dog3.scale(0.4);
		basket.scale(0.25);
		apple.scale(0.2);
		
		cont = new GButton("Continue", 655, 600, 100, 100);
		cont.setFillColor(Color.GREEN);
		exit = new GButton("Exit", 455, 600, 100, 100);
		exit.setFillColor(Color.RED);
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
		program.add(apple);
		program.add(saaya);
		program.add(mapGif);
	}
	
	// These functions check if the user is clicking on the interactable object and 
	//starts it's timer
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		
		if (obj == dog3 && !baskettimerstart) {
			dog3text = new GImage("dog3text.png", 380, 300);
			dog3text.scale(0.5);
			program.add(dog3text);
			dog3timer.start();
		}
		
		if (obj == apple && !baskettimerstart) {
			appletext = new GImage("appletext.png", 570, 500);
			appletext.scale(0.5);
			program.add(appletext);
			appletimer.start();
		}
		
		if (obj == basket) {
			basketscreen = new GImage("basketscreen.png", 350, 200);
			basketscreen.scale(0.5);
			program.add(basketscreen);
			baskettimer.start();
			baskettimerstart = true;
		}
		
		if (obj == cont) {
			removebuttons();
			program.switchToBasket();
		}
	
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
		
		if (source == dog3timer) {
			dialogueCountdown++;
			if (dialogueCountdown > 100) {
				program.remove(dog3text);
				dialogueCountdown = 0;
				dog3timer.stop();
			}
		}
		
		if (source == appletimer) {
			dialogueCountdown++;
			if (dialogueCountdown > 100) {
				program.remove(appletext);
				dialogueCountdown = 0;
				appletimer.stop();
			}
		}
		
		if (source == baskettimer) {
			baskettimer.stop();
			program.add(cont);
			program.add(exit);
		}
		
		if(source == gifTimer) {
			gifCounter++;
			System.out.println(gifCounter);
			if(gifCounter == 3) {
				program.remove(mapGif);
				gifTimer.stop();
			}
		}

	}
	
	public void removebuttons() {
		program.remove(cont);
		program.remove(exit);
	}
}