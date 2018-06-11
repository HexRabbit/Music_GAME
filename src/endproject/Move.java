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
		label.setLocation(label.getX(), label.getY()+20);
		
		if(label.getY() > 670 && label.isValid()) {
			
				Main.assess.setText("Miss");
				Main.assess.setForeground(Color.pink);
				parent.remove(label);
				parent.repaint();
				
		}
	}
}
