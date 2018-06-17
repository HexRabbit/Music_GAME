package endproject;

import java.awt.Color;
import java.awt.Container;
import java.util.TimerTask;

public class Remove extends TimerTask {
	
	public static boolean hidden = false;
	public static int score_get = 50;
	
	
	private MyLabel label;
	private Container parent;
	public boolean Ddown, Fdown, Jdown, Kdown;

	

	public Remove(MyLabel l, Container c) {
		label = l;
		parent = c;

	}

	public void run() {
		
		if (hidden == true) {
			score_get = 75;
			if (!label.hold && label.getY() + label.block_size > 400) {
				label.setVisible(false);
			}
			if (!label.hold && label.getY() + label.block_size > 600) {
				label.setVisible(true);
			}
		}
		if (!label.hold && label.getY() + label.block_size > 700) {
			Main.assess.setText("Miss");
			Main.assess.setForeground(Color.pink);
			parent.remove(label);
			parent.repaint();
			parent.removeKeyListener(label);
			label.remove_timer.cancel();
			label.remove_timer.purge();
			Main.combo.setText("combo 0");
			Main.comboCount = 0;
			
			Main.aa+=4;
			int x = (int) (((Main.ab*100)/Main.aa)*100);
			double xx = x/100.0;
			Main.accuracy.setText(String.valueOf(xx)+"%");
			if(xx<60) {
				Main.accuracy.setForeground(Color.red);
			}
			else {
				Main.accuracy.setForeground(Color.green);
			}
			
		} else if (label.hold) {
			if ((label.column == 0 && Ddown == false) || (label.column == 1 && Fdown == false)
					|| (label.column == 2 && Jdown == false) || (label.column == 3 && Kdown == false)) {

				if (label.getY() < 555 || label.getY() >= 615) { // bad
					Main.assess.setText("Bad");
					Main.assess.setForeground(Color.red);
					Main.grade += label.block_size / 20 * score_get;
					Main.combo.setText("combo " + ++Main.comboCount);
					Main.ab+=1;
				} else if (label.getY() < 575 || label.getY() >= 595) { // good
					Main.assess.setText("Good");
					Main.assess.setForeground(Color.yellow);
					Main.grade += label.block_size / 20 * score_get * 2;
					Main.combo.setText("combo " + ++Main.comboCount);					
					Main.ab+=2;
				} else if (label.getY() >= 575 && label.getY() < 595) { // perfect
					Main.assess.setText("Perfect");
					Main.assess.setForeground(Color.GREEN);
					Main.grade += label.block_size / 20 * score_get * 4;
					Main.combo.setText("combo " + ++Main.comboCount);
					Main.ab+=4;
				}
				
				Main.aa+=4;
				int x = (int) (((Main.ab*100)/Main.aa)*100);
				double xx = x/100.0;
				Main.accuracy.setText(String.valueOf(xx)+"%");
				if(xx<60) {
					Main.accuracy.setForeground(Color.red);
				}
				else {
					Main.accuracy.setForeground(Color.green);
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
