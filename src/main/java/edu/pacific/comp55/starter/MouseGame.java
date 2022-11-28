package edu.pacific.comp55.starter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import acm.graphics.*;
import java.util.Timer;
import java.util.TimerTask;

public class MouseGame extends GraphicsPane implements ActionListener{
	private GImage mouse;
	private GImage background;
	private MainApplication program;
	
	public MouseGame(MainApplication app) {
		super();
		program = app;
		background = new GImage("mousebg.png", 0, 0);
	}
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(background);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("ACTION PERFORMED");
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(background);
	}
	
}
