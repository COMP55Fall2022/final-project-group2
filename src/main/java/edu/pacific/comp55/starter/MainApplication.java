package edu.pacific.comp55.starter;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 1220;
	public static final int WINDOW_HEIGHT = 1096;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "magicsound.mp3"};//comment

	private SomePane somePane;
	private MenuPane menu;
	private int count;
	private Crow crowGame;
	private SetScenes scene1;
	private SetScene2 scene2;
	private SetScene3 scene3;
	private SetScene4 scene4;
	private TTTGraphics tictactoe;
	private MouseGame mouse;
	private Basket basketGame;

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		System.out.println("Hello, world!");
		somePane = new SomePane(this);
		menu = new MenuPane(this);
		
		//scene1 = new SetScenes(this);
		tictactoe = new TTTGraphics(this);
		mouse = new MouseGame(this);
		setupInteractions();
		switchToMenu();
	}
	
	public void switchToCrow() {
		playRandomSound();
		crowGame = new Crow(this);
		count++;
		switchToScreen(crowGame);
	}
	
	public void switchToBasket() {
		playRandomSound();
		basketGame = new Basket(this);
		count++;
		switchToScreen(basketGame);
	}
	
	public void switchToScene1() {
		scene1 = new SetScenes(this);
		switchToScreen(scene1);
	}
	
	public void switchToScene2() {
		scene2 = new SetScene2(this);
		switchToScreen(scene2);
	}
	
	public void switchToScene3() {
		scene3 = new SetScene3(this);
		switchToScreen(scene3);
	}
	
	public void switchToScene4() {
		scene4 = new SetScene4(this);
		switchToScreen(scene4);
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
	
	public void switchToMouse() {
		playRandomSound();
		mouse = new MouseGame(this);
		count++;
		switchToScreen(mouse);
	}
	

	private void playRandomSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
	
	
	public static void main(String[] args) {
		new MainApplication().start();
	}
}
