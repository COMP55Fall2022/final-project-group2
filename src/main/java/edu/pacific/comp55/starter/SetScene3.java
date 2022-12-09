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

	// GImages needed for scene 3
	private GImage dog2;
	private GImage dog2text;
	private GImage barn;
	private GImage barntext;
	private GImage tree;
	private GImage treescreen;
	private GImage mapGif;
	private GImage saaya;
	private AudioPlayer mapSound;
	private static final String MUSIC_FOLDER = "sounds";
	private static final String SOUND_FILE = "Running On Leaves.mp3";
	// Timers needed for scene 3
	private boolean treetimerstart = false;
	Timer barntimer = new Timer(40, this);
	Timer dog2timer = new Timer(40, this);
	Timer treetimer = new Timer(40, this);
	Timer gifTimer = new Timer(1000, this);
	private int dialogueCountdown = 0;
	private int dialogueCountdown2 = 0;
	private int gifCounter = 0;

	// buttons
	private GButton cont;
	private GButton exit;

	public SetScene3(MainApplication app) {
		super();
		program = app;
		
		// For scene 3
		saaya = new GImage("cat.png", 100, 750);
		scene3 = new GImage("scene3.png", 0, 0);
		dog2 = new GImage("dog2.png", 700, 750);
		barn = new GImage("barn.png", 67, 18);
		tree = new GImage("tree.png", 10, 400);
		mapGif = new GImage("journey2.gif", 0, 0);
		mapSound = AudioPlayer.getInstance();

		// buttons
		saaya.scale(2);
		scene3.scale(1.1);
		dog2.scale(0.4);
		saaya.scale(0.7);
		tree.scale(0.4);
		barn.scale(1.1);
		
		//buttons 
		cont = new GButton("Continue", 655, 550, 100, 100);
		cont.setFillColor(Color.GREEN);
		exit = new GButton("Main Menu", 455, 550, 100, 100);
		exit.setFillColor(Color.RED);
	}

	// if we win the game we switch to scene3
	@Override
	public void showContents() {
		setScene3();
	}

	@Override
	public void hideContents() {
		program.remove(scene3);
		program.remove(scene3);
		program.remove(dog2);
		program.remove(saaya);
		program.remove(exit);
		program.remove(cont);
		program.remove(treescreen);
		program.remove(mapGif);
	}

	public void setScene3() {
		gifTimer.start();
		mapSound.playSound(MUSIC_FOLDER, SOUND_FILE);
		program.add(scene3);
		program.add(barn);
		program.add(dog2);
		program.add(saaya);
		program.add(tree);
		program.add(mapGif);
	}

	// These functions check if the user is clicking on the interactable object and
	// starts it's timer
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());

		if (obj == dog2 && !treetimerstart) {
			dog2text = new GImage("dog2text.png", 350, 550);
			dog2text.scale(0.5);
			program.add(dog2text);
			dog2timer.start();
		}
		
		if (obj == barn && !treetimerstart) {
			barntext = new GImage("barntext.png", 200, 200);
			barntext.scale(0.5);
			program.add(barntext);
			barntimer.start();
		}
		
		if (obj == tree) {
			treescreen = new GImage("treescreen.png", 350, 200);
			treescreen.scale(0.5);
			program.add(treescreen);
			treetimer.start();
			treetimerstart = true;
		}
		
		if (obj == cont) {
			hideContents();
			program.switchToTTT();
		}
		if(obj == exit) {
			hideContents();
			program.switchToMenu();
		}

	}

	// These functions create and remove the dialogues that get prompted using their
	// own timers
	@Override
	public void actionPerformed(ActionEvent g) {
		Object source = g.getSource();

		if (source == gifTimer) {
			gifCounter++;
			System.out.println(gifCounter);
			if (gifCounter == 3) {
				program.remove(mapGif);
				mapSound.stopSound(MUSIC_FOLDER, SOUND_FILE);
				gifTimer.stop();
			}
		}

		if (source == dog2timer) {
			dialogueCountdown++;
			if (dialogueCountdown > 100) {
				program.remove(dog2text);
				dialogueCountdown = 0;
				dog2timer.stop();
			}
		}
		
		if (source == barntimer) {
			dialogueCountdown2++;
			if (dialogueCountdown2 > 100) {
				program.remove(barntext);
				dialogueCountdown2 = 0;
				barntimer.stop();
			}
		}
		
		if (source == treetimer) {
			dialogueCountdown++;
			treetimer.stop();
			program.add(cont);
			program.add(exit);
		}

	}

	public void removebuttons() {
		program.remove(cont);
		program.remove(exit);
	}

}