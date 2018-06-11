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
			
			Main frame1 = new Main(button.getText());
			frame1.setVisible(true);
			Selection.frame.setVisible(false);
			
			Selection.mp3.close();
		}
	}

	
}
