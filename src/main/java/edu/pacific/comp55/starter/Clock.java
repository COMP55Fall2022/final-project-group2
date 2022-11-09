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
	
	public void createClock() { 
		
		add(label);
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
		label.setLabel(clockTitle + getTime());
		time--;
		if(time == 0) {
			timer.stop();
		}
	}
	public void init() {
		setSize(600, 800);
		requestFocus();
	}
	@Override
	public void run() {
		label = new GLabel(clockTitle + getTime(), 0, 100);
		// TODO Auto-generated method stub
		setTime(60);
		createClock();
		add(label);
		timer.start();
		
		
	}
	public static void main(String[] args) {
		new Clock().start();
	}
		
}
