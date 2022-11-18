package edu.pacific.comp55.starter;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 1220;
	public static final int WINDOW_HEIGHT = 1096;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "magicsound.mp3"};

	private SomePane somePane;
	private MenuPane menu;
	private int count;
	private Crow crowGame;
	private SetScenes scene1;
	private TTTGraphics tictactoe;

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		System.out.println("Hello, world!");
		somePane = new SomePane(this);
		menu = new MenuPane(this);
		crowGame = new Crow(this);
		scene1 = new SetScenes(this);
		tictactoe = new TTTGraphics(this);
		setupInteractions();
		switchToMenu();
	}
	
	public void switchToCrow() {
		playRandomSound();
		count++;
		switchToScreen(crowGame);
	}
	
	public void switchToScene1() {
		switchToScreen(scene1);
	}

	public void switchToMenu() {
		playRandomSound();
		count++;
		switchToScreen(menu);
	}

	public void switchToSome() {
		playRandomSound();
		switchToScreen(somePane);
	}
	
	public void switchToTTT() {
		playRandomSound();
		count++;
		switchToScreen(tictactoe);
	}

	private void playRandomSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
	
	
	public static void main(String[] args) {
		new MainApplication().start();
	}
}
