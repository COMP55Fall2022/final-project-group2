package group2.project;

import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import acm.graphics.GImage;
import acm.graphics.GOval;
import edu.pacific.comp55.starter.GraphicsPane;
import edu.pacific.comp55.starter.MainApplication;


public class Crow extends GraphicsPane implements ActionListener, KeyListener {
	private static final int START_Y = 675;
	private static final int START_X = 550;
	private MainApplication program;
	private GImage crowgamebackground;
	private GOval saaya;  //will change GOval to Gimage of cat later
	
	
	
	Timer crowtimer = new Timer(5, this);
	int x=15, y=0, velx=0, vely=0;
	
	
	public Crow(MainApplication app) {
		this.program = app;
		crowgamebackground = new GImage("7.png", 0, 0); 
		saaya = new GOval(START_X, START_Y, 100, 100);  //making start position static
		saaya.setFilled(true);
		saaya.setColor(Color.green);
	
		
		
	}

	
	//public void OutOfBounds() {
		//double rightarea =  crowgamebackground.getWidth()- saaya.getSize();
		
//}


	//public Crow() {
		//crowtimer.start();
		//addKeyListener(this);
		//setFocusable(true);
		//setFocusTraversalKeysEnabled(false);
	//}
	
	
	@Override
	public void showContents() {
		program.add(crowgamebackground);
		program.add(saaya);
		
		
		
	}

	@Override
	public void hideContents() {
		program.remove(crowgamebackground);
		
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		x = x + velx;
		y = y + vely;
	
		
	} 
	
	@Override
	public void keyPressed(KeyEvent e) {
		int c= e.getKeyCode();
		
		if (c == KeyEvent.VK_LEFT) {
			
			velx = -1;
			vely=0;
			saaya.move(-15, 0); 
	
		}
		
		
		if (c== KeyEvent.VK_UP) {
			velx =0;
			vely= -1;
			saaya.move(0, -1);
		}
		
		if (c == KeyEvent.VK_RIGHT) {
			velx = 1;
			vely =0;
			saaya.move(15, 0);
		}
		
		if (c == KeyEvent.VK_DOWN) {
			velx = 0;
			vely = 1;
			saaya.move(0, 1);
		}
		
		 
	}
	
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	

}
