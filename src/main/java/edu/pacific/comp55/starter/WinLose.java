package edu.pacific.comp55.starter;

import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import acm.program.*;

//Sagar Mathur
public class WinLose {
    private boolean win;
    
    private GLabel winLabel;
    private GLabel loseLabel;
    public void setWin(boolean w) {
    	w = win;
    }
    public boolean checkForWin() {
    	
    	return true;
    }
    public boolean checkForLose() {
    	return false;
    }
    public void setWinLabel() {
    	winLabel = new GLabel("YOU WIN!!!", 50, 50);
    	
    }
    public void setLoseLabel() {
    	loseLabel = new GLabel("YOU LOSE!!!",50,50);
    	
    }
    
}
