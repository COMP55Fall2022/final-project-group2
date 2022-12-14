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
	private static final String MUSIC_FOLDER = "sounds";
	private static final String SOUND_FILES[] = {"Rustling Leaves.mp3", "shortPileOfLeaves.mp3", "Congrats.mp3"};
	private static final String SONG_FILE = "Carefree - Kevin MacLeod.mp3";
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
	private AudioPlayer effects;
	
	//Timers needed for scene 1
	private boolean suntimerstart = false;
	private Timer rosestimer = new Timer(100, this);
	private Timer blueflowertimer = new Timer(100, this);
	private Timer suntimer = new Timer(100, this);
	private Timer clickprompttimer = new Timer(100, this);
	
	private int dialogueCountdown1 = 0;
	private int dialogueCountdown2 = 0;
	private int dialogueCountdown3 = 0;
	
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
		effects = AudioPlayer.getInstance();
		
	}
	
	
	
	// if we win the game we switch to scene1
	@Override
	public void showContents() {
		setScene1();
		effects.playSound(MUSIC_FOLDER, SONG_FILE);
		clickprompttimer.start();
	}
	@Override
	public void hideContents() {
		program.remove(scene1);
		program.remove(roses);
		program.remove(blueflower);
		program.remove(sun);
		program.remove(clickprompt);
		removebuttons();
		clickprompttimer.stop();
		effects.stopSound(MUSIC_FOLDER, SONG_FILE);
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
		
		if(obj == clickprompt || obj != clickprompt) {
			clickprompttimer.stop();
			program.remove(clickprompt);
		}
		if (obj == roses && !suntimerstart) {
			rosesdialogue = new GImage("rosesdialogue.png", 65, 200);
			program.add(rosesdialogue);
			effects.playSound(MUSIC_FOLDER, SOUND_FILES[0]);
			rosestimer.start();
		}
		
		if (obj == blueflower && !suntimerstart) {
			blueflowertext = new GImage("blueflowerdialogue.png", 670, 275);
			blueflowertext.scale(0.5);
			program.add(blueflowertext);
			effects.playSound(MUSIC_FOLDER, SOUND_FILES[1]);
			blueflowertimer.start();
		}
		
		
		if (obj == sun) {
			sunscreen = new GImage("sunscreen.png", 350, 150);
			sunscreen.scale(0.5);
			program.add(sunscreen);
			effects.playSound(MUSIC_FOLDER, SOUND_FILES[2]);
			suntimer.start();
			suntimerstart = true;
		}
		
		if (obj == cont) {
			hideContents();
			program.switchToMouse();
		}
		
		if (obj == exit) {
			removebuttons();
			hideContents();
			program.switchToMenu();
		}
	
	}
	
	//These functions create and remove the dialogues that get prompted using their own timers
	@Override
	public void actionPerformed(ActionEvent g) {
		Object source = g.getSource();
		
		
		if (source == rosestimer) {
			dialogueCountdown1++;
			System.out.println(dialogueCountdown1);
			if (dialogueCountdown1 > 50) {
				program.remove(rosesdialogue);
				dialogueCountdown1 = 0;
				rosestimer.stop();
			}
		}
		
		if (source == clickprompttimer) {
			program.add(clickprompt);
			dialogueCountdown2++;
			System.out.println(dialogueCountdown2);
			if (dialogueCountdown2 > 50) {
				program.remove(clickprompt);
				dialogueCountdown2 = 0;
				clickprompttimer.stop();
			}
		}
		
		if (source == blueflowertimer) {
			dialogueCountdown3++;
			
			if (dialogueCountdown3 > 50) {
				program.remove(blueflowertext);
				dialogueCountdown3 = 0;
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
