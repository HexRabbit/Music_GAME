package endproject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import org.magiclen.magicaudioplayer.AudioPlayer;

public class MyKeyListener extends KeyAdapter {
	
	Map<Character, Boolean> pressed;
	Map<Character, AudioPlayer> clickSound;
	File clickSoundFile;
	JLabel[] feedback;
	
	MyKeyListener(JLabel[] feedback) {
		clickSoundFile = new File("src/res/clicksound.wav");
		this.feedback = new JLabel[4];
		
		pressed = new HashMap<Character, Boolean>();
		clickSound = new HashMap<Character, AudioPlayer>();
		
		pressed.put('d', false);
		pressed.put('f', false);
		pressed.put('j', false);
		pressed.put('k', false);
		
		clickSound.put('d', AudioPlayer.createPlayer(clickSoundFile));
		clickSound.put('f', AudioPlayer.createPlayer(clickSoundFile));
		clickSound.put('j', AudioPlayer.createPlayer(clickSoundFile));
		clickSound.put('k', AudioPlayer.createPlayer(clickSoundFile));
		
		this.feedback = feedback;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		switch (key) {
		case 'd':
			feedback[0].setVisible(true);
			break;
		case 'f':
			feedback[1].setVisible(true);
			break;
		case 'j':
			feedback[2].setVisible(true);
			break;
		case 'k':
			feedback[3].setVisible(true);
			break;
		}
		if (pressed.containsKey(key) && pressed.get(key) == false) {
			clickSound.get(key).setVolume(20);
			clickSound.get(key).play();
			pressed.put(key, true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		switch (key) {
		case 'd':
			feedback[0].setVisible(false);
			break;
		case 'f':
			feedback[1].setVisible(false);
			break;
		case 'j':
			feedback[2].setVisible(false);
			break;
		case 'k':
			feedback[3].setVisible(false);
			break;
		}
		if (pressed.containsKey(key) && pressed.get(key) == true) {
			pressed.put(key, false);

		}
	}
}
