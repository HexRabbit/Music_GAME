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
		
		/*if (hidden == true) {
			score_get = 75;
			if (!label.hold && label.getY() + label.block_size > 400) {
				label.setVisible(false);
			}
			if (!label.hold && label.getY() + label.block_size > 600) {
				label.setVisible(true);
			}
		}*/
		if (!label.hold && label.getY() + label.block_size > 700) {
			Selection.frame1.assess.setText("Miss");
			Selection.frame1.assess.setForeground(Color.pink);
			parent.remove(label);
			parent.repaint();
			++Selection.frame1.missCount;
			parent.removeKeyListener(label);
			label.remove_timer.cancel();
			label.remove_timer.purge();
			Selection.frame1.combo.setText("combo 0");
			Selection.frame1.comboCount = 0;
			
			Selection.frame1.aa+=4;
			int x = (int) (((Selection.frame1.ab*100)/Selection.frame1.aa)*100);
			double xx = x/100.0;
			Selection.frame1.accuracy.setText(String.valueOf(xx)+"%");
			if(xx<60) {
				Selection.frame1.accuracy.setForeground(Color.red);
			}
			else {
				Selection.frame1.accuracy.setForeground(Color.green);
			}
			
		} else if (label.hold) {
			if ((label.column == 0 && Ddown == false) || (label.column == 1 && Fdown == false)
					|| (label.column == 2 && Jdown == false) || (label.column == 3 && Kdown == false)) {

				if (label.getY() < 555 || label.getY() >= 615) { // bad
					Selection.frame1.assess.setText("Bad");
					Selection.frame1.assess.setForeground(Color.red);
					Selection.frame1.grade += label.block_size / 20 * score_get;
					Selection.frame1.combo.setText("combo " + ++Selection.frame1.comboCount);
					Selection.frame1.badCount++;
					Selection.frame1.ab+=1;
				} else if (label.getY() < 575 || label.getY() >= 595) { // good
					Selection.frame1.assess.setText("Good");
					Selection.frame1.assess.setForeground(Color.yellow);
					Selection.frame1.grade += label.block_size / 20 * score_get * 2;
					Selection.frame1.combo.setText("combo " + ++Selection.frame1.comboCount);
					Selection.frame1.goodCount++;
					Selection.frame1.ab+=2;
				} else if (label.getY() >= 575 && label.getY() < 595) { // perfect
					Selection.frame1.assess.setText("Perfect");
					Selection.frame1.assess.setForeground(Color.GREEN);
					Selection.frame1.grade += label.block_size / 20 * score_get * 4;
					Selection.frame1.combo.setText("combo " + ++Selection.frame1.comboCount);
					Selection.frame1.perfectCount++;
					Selection.frame1.ab+=4;
				}
				
				if(Selection.frame1.comboCount >= Selection.frame1.maxCombo)
					Selection.frame1.maxCombo = Selection.frame1.comboCount;
				
				Selection.frame1.aa+=4;
				int x = (int) (((Selection.frame1.ab*100)/Selection.frame1.aa)*100);
				double xx = x/100.0;
				Selection.frame1.accuracy.setText(String.valueOf(xx)+"%");
				if(xx<60) {
					Selection.frame1.accuracy.setForeground(Color.red);
				}
				else {
					Selection.frame1.accuracy.setForeground(Color.green);
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
