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
		if (!label.hold && label.getY()+label.block_size > 700) {
			Main.assess.setText("Miss");
			Main.assess.setForeground(Color.pink);
			parent.remove(label);
			parent.repaint();
			parent.removeKeyListener(label);
			label.remove_timer.cancel();
			label.remove_timer.purge();
		}
		else if(label.hold) {
			if ((label.column == 0 && Ddown == false)
			 || (label.column == 1 && Fdown == false) 
			 || (label.column == 2 && Jdown == false) 
			 || (label.column == 3 && Kdown == false)) {
				
				
				if(label.getY() < 555 || label.getY() >= 615) { //bad
					Main.assess.setText("Bad");
					Main.assess.setForeground(Color.red);
					Main.grade += label.block_size/20*50;
				} else if(label.getY() < 575 || label.getY() >= 595) { //good
					Main.assess.setText("Good");
					Main.assess.setForeground(Color.yellow);
					Main.grade += label.block_size/20*100;
				} else if(label.getY() >= 575 && label.getY() < 595){ //perfect
					Main.assess.setText("Perfect");
					Main.assess.setForeground(Color.GREEN);
					Main.grade += label.block_size/20*200;
				}
				
				parent.remove(label);
				parent.repaint();
				parent.removeKeyListener(label);
				label.remove_timer.cancel();
				label.remove_timer.purge();
			} 
		}
	}
}
