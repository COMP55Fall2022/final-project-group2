package edu.pacific.comp55.starter;

import java.io.*;
import java.util.*;
import javax.swing.*;

//Displays the players score
public class Score {
	JLabel scoreLabel;
	
	public void setScore(int score) {
		scoreLabel = new JLabel("Score: 0");
	}
	
	
	public void updateScore(int score) {
		score++;
		scoreLabel.setText("Score: 0");
	}
}
