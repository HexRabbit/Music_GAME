package endproject;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JButton;


public class MyButtonListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		/*if( arg0.getActionCommand() == "pause") {
			JButton button = new JButton();
			button = (JButton) arg0.getSource();
			if(button.getText() == "Pause") {
				new Pause();
			
				button.setText("Resume");
				arg0.setSource(button);
			} else if(button.getText() == "Resume") {
				new Resume();
				button.setText("Pause");
				button.setFocusable(false);
				arg0.setSource(button);
			}
			
				
		} else*/ if(arg0.getActionCommand() == "play") {
			
			JButton button = new JButton();
			button = (JButton) arg0.getSource();
			//if(Selection.frame1 == null)
				Selection.frame1 = new Main(button.getText());
			//else
			//	Selection.frame1.restart(button.getText());
			Selection.frame1.setVisible(true);
			Selection.frame.setVisible(false);
			
			Selection.mp3.close();
		} else if(arg0.getActionCommand() == "back") {
			Selection.frame1.mp3.close();
			Selection.frame.setVisible(true);
			//Selection.mp3.play();
			System.out.println("size: "+ Selection.frame1.l.size());
			while(Selection.frame1.l.size() > 0) {
				
				Selection.frame1.l.getLast().move_timer.cancel();
				Selection.frame1.l.getLast().remove_timer.cancel();
				Selection.frame1.l.getLast().show_timer.cancel();
				Selection.frame1.l.getLast().move_timer.purge();
				Selection.frame1.l.getLast().remove_timer.purge();
				Selection.frame1.l.getLast().show_timer.purge();
				Selection.frame1.l.getLast().move_timer = null;
				Selection.frame1.l.getLast().remove_timer= null;
				Selection.frame1.l.getLast().show_timer=null;
				
				Selection.frame1.l.getLast().removeAll();
				//Selection.frame1.l.getLast().validate();
				Selection.frame1.remove(Selection.frame1.l.getLast());
				Selection.frame1.removeKeyListener(Selection.frame1.l.getLast());
				Selection.frame1.l.removeLast();
				
				//System.gc();
			}
			
			Selection.frame1.l.clear();
			System.out.println("size: "+ Selection.frame1.l.size());
			Selection.frame1.start_timer.cancel();
			Selection.frame1.start_timer.purge();
			Selection.frame1.start_timer = null;
			Selection.frame1.score_timer.cancel();
			Selection.frame1.score_timer.purge();
			Selection.frame1.score_timer = null;
			Selection.frame1.end_timer.cancel();
			Selection.frame1.end_timer.purge();
			Selection.frame1.end_timer = null;
			
			Selection.frame1.dispose();
			Selection.frame1.removeNotify();
			//Selection.frame1.setVisible(false);
			Selection.frame1.removeAll();
			Selection.frame1.validate();
			Selection.frame1 = null;
			System.gc();
			
			
		} else if(arg0.getActionCommand() == "Result") {
			
			System.out.println("hi");
			
			Selection.frame2 = new Result(Selection.frame1.maxCombo,Selection.frame1.perfectCount,Selection.frame1.goodCount,Selection.frame1.badCount,Selection.frame1.missCount,Selection.frame1.grade,Selection.frame1.now_play);
			Selection.frame2.setVisible(true);
			Selection.frame1.dispose();
		}
	}

	
}
