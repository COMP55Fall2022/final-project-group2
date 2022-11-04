package edu.pacific.comp55.starter;
import javax.swing.*;

import acm.graphics.GLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Brendon Amino
public class Clock {
	private static final string clockTitle = "Time: ";
	ActionListener clock = new ActionListener();
	
	private Timer timer;
	private String clockString;
	private GLabel label;
	private int time;
	//private Integer t;
	
	public void createClock() { 
		timer = new Timer(getTime(), this);
	}
	public void setClockText(String s) {
		this.s = s;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	public String getClockText() {
		return s;
	}
	
	public int getTime() {
		return time;
	}
		
	//Timer someTimerVar = new Timer(1000);
}
