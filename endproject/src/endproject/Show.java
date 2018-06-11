package endproject;

import java.awt.Container;
import java.util.TimerTask;

public class Show extends TimerTask{

	private MyLabel label;
	private Container parent;
	public Show(MyLabel l, Container c) {
		label = l;
		parent = c;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		parent.add(label);
	}

}
