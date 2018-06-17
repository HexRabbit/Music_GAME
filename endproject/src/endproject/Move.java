package endproject;

import java.awt.Color;
import java.awt.Container;
import java.util.TimerTask;

public class Move extends TimerTask{
	
	private MyLabel label;
	private Container parent;
	public Move(MyLabel l, Container c) {
		label = l;
		parent = c;
				
	}
	
	public void run() {
		label.setLocation(label.getX(), label.getY()+1);
		if(label.getY() > 700) {
			label.move_timer.cancel();
			label.move_timer.purge();
		}
	}
}
