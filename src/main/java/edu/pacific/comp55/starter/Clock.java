package edu.pacific.comp55.starter;
import java.awt.event.ActionEvent;
import acm.graphics.*;
import acm.program.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import acm.graphics.GLabel;

//Brendon Amino
public class Clock extends GraphicsProgram implements ActionListener {
	private static final String clockTitle = "Time: ";
	private static int timerTime = 1000;
	
	private Timer timer = new Timer(timerTime, this);;
	private String clockString;
	private GLabel label;
	private int time;
	
	public Clock() { 
		time = 0;
		clockString = null;
	}
	
	public void createClock() { 
		new Clock().start();
	}
	public void setClockText(String clockString) {
		this.clockString = clockString;
	}
	public void setTime(int t) {
		time = t;
	}
	
	public void moveLabel(int x, int y) {
		label.setLocation(x, y);
	}

	public String getClockText() {
		return clockString;
	}
	
	public int getTime() {
		return time;
	}
	
	private boolean checkIfClockStringNull() {
		if(clockString == null) {
			return false;
		}
		return true;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!checkIfClockStringNull()) {
			label.setLabel(clockTitle + getTime());
		}
		else {
			label.setLabel(clockString + getTime());
		}
		time--;
		if(time == 0) {
			timer.stop();
		}
	}
	public void init() {
		setSize(800, 600);
		requestFocus();
	}
	@Override
	public void run() {
		if(!checkIfClockStringNull()) {
			label = new GLabel(clockTitle + getTime(), 0, 100);
		}
		else {
			label = new GLabel(clockString + getTime(), 0, 100);
		}
		add(label);
		timer.start();
	}
	
	public static void main(String[] args) {
		Clock clock = new Clock();
		clock.setTime(25);
		clock.start();
	}
		
}
