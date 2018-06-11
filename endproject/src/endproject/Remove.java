package endproject;

import java.awt.Color;
import java.awt.Container;
import java.util.TimerTask;

public class Remove extends TimerTask{
	
	private MyLabel label; 
	private Container parent;
	public boolean Ddown, Fdown, Jdown, Kdown;
	
	public Remove(MyLabel l, Container c) {
		label = l;
		parent = c;
		
	}
	
	public void run() {
		if(label.getY() + label.block_size > 600 && label.getY() < 635) {
			if (label.column == 0 && Ddown == false) {
				// Container parent = getParent();
				/*if(label.getY() < 555 || label.getY() >= 615) { //bad
					Main.assess.setText("Bad");
				} else if(label.getY() < 575 || label.getY() >= 595) { //good
					Main.assess.setText("Good");
				} else if(label.getY() >= 575 && label.getY() < 595) { //perfect
					Main.assess.setText("Perfect");
				}*/
				//Main.grade += 20;
				Main.assess.setText("Miss");
				Main.assess.setForeground(Color.pink);
				parent.remove(label);
				parent.repaint();
				parent.removeKeyListener(label);
			} else if (label.column == 1 && Fdown == false) {
				// Container parent = getParent();
				//System.out.println("F is down");
				//Main.grade += 20;
				Main.assess.setText("Miss");
				Main.assess.setForeground(Color.pink);
				parent.remove(label);
				parent.repaint();
				parent.removeKeyListener(label);
			} else if (label.column == 2 && Jdown == false) {
				// Container parent = getParent();
				//Main.grade += 20;
				Main.assess.setText("Miss");
				Main.assess.setForeground(Color.pink);
				parent.remove(label);
				parent.repaint();
				parent.removeKeyListener(label);
			} else if (label.column == 3 && Kdown == false) {
				// Container parent = getParent();
				//Main.grade += 20;
				Main.assess.setText("Miss");
				Main.assess.setForeground(Color.pink);
				parent.remove(label);
				parent.repaint();
				parent.removeKeyListener(label);
			}
		} else if(label.getY() > 635 && label.getY() < 700) {
			if (label.column == 0 && Ddown == true) {
				
				if(label.getY() > 670 && label.getY() <= 700) { //bad
					Main.assess.setText("Bad");
					Main.assess.setForeground(Color.red);
					Main.grade += label.block_size/20*50;
				} else if(label.getY() > 650 && label.getY() <= 670) { //good
					Main.assess.setText("Good");
					Main.assess.setForeground(Color.yellow);
					Main.grade += label.block_size/20*100;
				} else if(label.getY() > 635 && label.getY() <= 650){ //perfect
					Main.assess.setText("Perfect");
					Main.assess.setForeground(Color.GREEN);
					Main.grade += label.block_size/20*200;
				}
				
				parent.remove(label);
				parent.repaint();
				parent.removeKeyListener(label);
			} else if (label.column == 1 && Fdown == true) {
				
				if(label.getY() > 670 && label.getY() <= 700) { //bad
					Main.assess.setText("Bad");
					Main.assess.setForeground(Color.red);
					Main.grade += label.block_size/20*50;
				} else if(label.getY() > 650 && label.getY() <= 670) { //good
					Main.assess.setText("Good");
					Main.assess.setForeground(Color.yellow);
					Main.grade += label.block_size/20*100;
				} else if(label.getY() > 635 && label.getY() <= 650){ //perfect
					Main.assess.setText("Perfect");
					Main.assess.setForeground(Color.GREEN);
					Main.grade += label.block_size/20*200;
				}
				
				parent.remove(label);
				parent.repaint();
				parent.removeKeyListener(label);
			} else if (label.column == 2 && Jdown == true) {

				if(label.getY() > 670 && label.getY() <= 700) { //bad
					Main.assess.setText("Bad");
					Main.assess.setForeground(Color.red);
					Main.grade += label.block_size/20*50;
				} else if(label.getY() > 650 && label.getY() <= 670) { //good
					Main.assess.setText("Good");
					Main.assess.setForeground(Color.yellow);
					Main.grade += label.block_size/20*100;
				} else if(label.getY() > 635 && label.getY() <= 650){ //perfect
					Main.assess.setText("Perfect");
					Main.assess.setForeground(Color.GREEN);
					Main.grade += label.block_size/20*200;
				}
				
				parent.remove(label);
				parent.repaint();
				parent.removeKeyListener(label);
			} else if (label.column == 3 && Kdown == true) {

				if(label.getY() > 670 && label.getY() <= 700) { //bad
					Main.assess.setText("Bad");
					Main.assess.setForeground(Color.red);
					Main.grade += label.block_size/20*50;
				} else if(label.getY() > 650 && label.getY() <= 670) { //good
					Main.assess.setText("Good");
					Main.assess.setForeground(Color.yellow);
					Main.grade += label.block_size/20*100;
				} else if(label.getY() > 635 && label.getY() <= 650){ //perfect
					Main.assess.setText("Perfect");
					Main.assess.setForeground(Color.GREEN);
					Main.grade += label.block_size/20*200;
				}
				
				parent.remove(label);
				parent.repaint();
				parent.removeKeyListener(label);
			}
		}
	}
}
