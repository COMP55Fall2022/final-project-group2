package edu.pacific.comp55.starter;

import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.util.RandomGenerator;

public class Basket extends GraphicsPane implements ActionListener, KeyListener{
	
	public static final int WINDOW_WIDTH = 1220;
	public static final int WINDOW_HEIGHT = 1096;
	private MainApplication program;
	private GImage basketbackground;
	
	// apple movements
	public static final int SIZE = 40;
	public static final int SPEED = 10;
	public static final int MS = 70;
	private static final int APPLEDIV = 50;
	private ArrayList<GImage> apple;
	private ArrayList<Integer> appleDiv;
	private ArrayList<Integer> appleDirection;
	private RandomGenerator appleGen;
	private int numTimes;
	Timer appleDown = new Timer(40, this);
	
	// basket
	

	

	public Basket(MainApplication app) {
		this.program = app;
		basketbackground = new GImage("basketbackground.png", 0, 0);
		
		
		//apples
		appleGen = RandomGenerator.getInstance();
		apple = new ArrayList<GImage>();
		appleDiv = new ArrayList<Integer>();
		appleDirection = new ArrayList<Integer>();
		numTimes = 0;
	}

	//For apples
	private void addApples() {
		GImage eTrash = makeApple();
		Integer temp = (appleGen.nextBoolean() == true) ? 1 : -1;
		appleDirection.add(temp);
		appleDiv.add(appleGen.nextInt(APPLEDIV));
		apple.add(eTrash);
		program.add(eTrash);
		
	}
	
	
	

	public GImage makeApple() {
		GImage temp = new GImage("apple.png", appleGen.nextInt(WINDOW_WIDTH), 180);
		temp.scale(0.25);
		return temp;
	}
		
	private void appleMove() {
		for(int i = 0 ; i < apple.size() ; i++) {
			//Move Down
			apple.get(i).move(0, SPEED);
			
		}
	}


	
			
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// TODO Auto-generated method stub

		if (source == appleDown) {
			numTimes++;
			if (numTimes % 60 == 0) {
				addApples();


			}
			appleMove();
		}
	}

	@Override
	public void showContents() {
		program.add(basketbackground);
		appleDown.start();

	}

	@Override
	public void hideContents() {
		program.remove(basketbackground);
		
	}
	
	
	
	
	
	
	

}
