package edu.pacific.comp55.starter;

import acm.graphics.GImage;

public class Rodent {
	private static int MAXID = 0;
	private int mouseId;
	private GImage mouseImg;
	
	
	
	// This sets the direction that the mouse is moving
	private double deltaX;
	private double deltaY;
	
	// This is the number of seconds that the mouse has been moving on it's path
	private double timeOnPath;
	
	// This sets the number of seconds that the mouse moves on it's path before changing directions
	private double timeToChangePath;
	
	private static final double MAX_SPEED = 600.0; // pixels per second
	private static final double MIN_SPEED = 75.0; // pixels per second
	
	public Rodent() {
		this.changeDirection();
		this.mouseId = MAXID + 1;
		this.timeToChangePath = 3.0; // default value. The mouse will change direction and speed every timeToChangePath seconds
		this.timeOnPath = 0.0;
	}
	
	public int getMouseId() {
		return this.mouseId;
	}

	public GImage getMouseImg() {
		return mouseImg;
	}

	public void setMouseImg(GImage mouseImg) {
		this.mouseImg = mouseImg;
	}

	public double getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}

	public double getTimeOnPath() {
		return timeOnPath;
	}

	public void setTimeOnPath(double timeOnPath) {
		if(this.timeOnPath >= this.timeToChangePath) {
			this.changeDirection();
		} else {
			this.timeOnPath = timeOnPath;
		}
	}
	
	public void changeDirection() {
		this.deltaX = ((double)(Math.random() * (MAX_SPEED - MIN_SPEED)) + MIN_SPEED) * (Math.random() >= 0.5 ? 1 : -1);
		this.deltaY = ((double)(Math.random() * (MAX_SPEED - MIN_SPEED)) + MIN_SPEED) * (Math.random() >= 0.5 ? 1 : -1);
		this.timeOnPath = 0;
	}
}
