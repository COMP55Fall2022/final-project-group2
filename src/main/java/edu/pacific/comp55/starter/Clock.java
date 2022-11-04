package edu.pacific.comp55.starter;
import javax.swing.*;

import acm.graphics.GLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Brendon Amino
public class Clock {
	private String s;
	private GLabel label;
	private int time;
	
	public void setClock(String s) {
		this.s = s;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	public String getClock() {
		return s;
	}
	
	public int getTime() {
		return time;
	}
	//Timer someTimerVar = new Timer(1000);
}
