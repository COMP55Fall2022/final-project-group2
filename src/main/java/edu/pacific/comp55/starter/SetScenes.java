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

public class SetScenes extends GraphicsPane implements ActionListener{
	private MainApplication program; 
	private GImage scene1;
	private GImage roses;
	private GLabel yes;
	Timer rosestimer = new Timer(40, this);
	int roseCountdown = 0;
	
	public SetScenes(MainApplication app) {
		super();
		program = app;
		scene1 = new GImage("scene1.png", 0, 0);
		roses = new GImage("roses.png", -165, 178);
		yes = new GLabel("This is not the main object, but keep trying!", 160, 170);
	
		
		
	}
	
	// if we win the game we switch to scene1
	
	@Override
	public void showContents() {
		program.add(scene1);
		program.add(roses);

		
	}

	@Override
	public void hideContents() {
		program.remove(scene1);
		program.remove(roses);
		
	}
	

	
	public void setscene1() {
		program.add(scene1);
		
	}
	
	
	
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == roses) {
			rosestimer.start();
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent g) {
		Object source = g.getSource();
		
		if (source == rosestimer) {
			roseCountdown++;
			System.out.println(roseCountdown);
			program.add(yes);
			  if (roseCountdown > 100) {
				 program.remove(yes);
				 rosestimer.stop();
			 }
		}
		
	}
	
	
	
	
}
