package edu.pacific.comp55.starter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import acm.graphics.GLabel;

//Brendon Amino
public class Clock implements ActionListener {
	private static final String clockTitle = "Time: ";
	ActionListener clock;
	
	private Timer timer;
	private String clockString;
	private GLabel label;
	private int time;
	
	public void createClock() { 
		timer = new Timer(getTime(), clock);
		label = new GLabel(clockTitle + clockString + getTime());
		
	}
	public void setClockText(String clockString) {
		this.clockString = clockString;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	public String getClockText() {
		return clockString;
	}
	
	public int getTime() {
		return time;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
		
}
