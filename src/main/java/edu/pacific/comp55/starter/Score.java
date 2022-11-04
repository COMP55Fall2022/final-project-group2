package edu.pacific.comp55.starter;

import java.io.*;
import java.util.*;
import javax.swing.*;

//TODO Make a score that increments - maybe using JLabel
public class Score {
	JLabel scoreLabel = new JLabel("Score: 0");
	
	//TODO SetScore
	//public void setScore(int score) {}
	
	
	//TODO updateScore() - finish
	public void updateScore(int score) {
		score++;
		scoreLabel.setText("Score: 0");
	}
}
