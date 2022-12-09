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
	public static final String MUSIC_FOLDER = "sounds";
	private static final String SOUND_FILE = "Running On Leaves.mp3";
	// Timers needed for scene 3
	private boolean treetimerstart = false;
	Timer barntimer = new Timer(40, this);
	Timer dog2timer = new Timer(40, this);
	Timer treetimer = new Timer(40, this);
	Timer gifTimer = new Timer(1000, this);
	int dialogueCountdown = 0;
	int gifCounter = 0;

	// buttons
	private GButton cont;
	private GButton exit;

	public SetScene3(MainApplication app) {
		super();
		program = app;


		// For scene 3
		scene3 = new GImage("scene3bgimage.png", 0, 0);
		dog2 = new GImage("dog2.png", 800, 650);
		dog2.scale(0.5);

		
		
		dog2text = new GImage("dog2text.png", 200, 100);
		barn = new GImage("barn.png", 100, 100);
		barntext = new GImage("barntext.png", 100, 100);
		mapGif = new GImage("journey2.gif", 0, 0);
		mapSound = AudioPlayer.getInstance();

		// cat
		saaya = new GImage("cat.png", 600, 650);

		// buttons
		cont = new GButton("Continue", 655, 500, 100, 100);
		cont.setFillColor(Color.GREEN);
		exit = new GButton("Exit", 455, 500, 100, 100);
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
		program.add(dog2);
		program.add(saaya);
		program.add(barn);
		program.add(mapGif);
	}

	// These functions check if the user is clicking on the interactable object and
	// starts it's timer
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());

		if (obj == dog2) {
			treescreen = new GImage("sunscreen.png", 350, 200);
			treescreen.scale(0.5);
			program.add(treescreen);
			dog2timer.start();

		}

		if (obj == cont) {
			removebuttons();
			program.switchToTTT();
		}

		if (obj == exit) {
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
			dog2timer.stop();
			program.add(cont);
			program.add(exit);
		}

	}

	public void removebuttons() {
		program.remove(cont);
		program.remove(exit);
	}

}