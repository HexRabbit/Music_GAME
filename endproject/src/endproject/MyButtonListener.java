package endproject;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class MyButtonListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if( arg0.getActionCommand() == "pause" && Main.pause.getText() == "Pause") {
			new Pause();
			Main.pause.setText("Resume");
			
			
				
		} else if(arg0.getActionCommand() == "pause" && Main.pause.getText() == "Resume") {
			new Resume();
			Main.pause.setText("Pause");
			Main.pause.setFocusable(false);
			
		} else if(arg0.getActionCommand() == "play") {
			
			JButton button = new JButton();
			button = (JButton) arg0.getSource();
			if(Selection.frame1 == null)
				Selection.frame1 = new Main(button.getText());
			else
				Selection.frame1.restart(button.getText());
			Selection.frame1.setVisible(true);
			Selection.frame.setVisible(false);
			
			Selection.mp3.close();
		} else if(arg0.getActionCommand() == "back") {
			Main.mp3.close();
			Selection.frame.setVisible(true);
			//Selection.mp3.play();
			Selection.frame1.dispose();
			//Selection.frame1 = null;
			
		} else if(arg0.getActionCommand() == "Result") {
			
			System.out.println("hi");
			
			Selection.frame2 = new Result(Selection.frame1.maxCombo,Selection.frame1.perfectCount,Selection.frame1.goodCount,Selection.frame1.badCount,Selection.frame1.missCount,Selection.frame1.grade,Selection.frame1.now_play);
			Selection.frame2.setVisible(true);
			Selection.frame1.dispose();
		}
	}

	
}
