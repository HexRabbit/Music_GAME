package endproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class MyLabel extends JLabel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int column;
	public long b, e; // begin, end

	public Container parent;
	public Remove remove;
	public Show show;
	public Move move;
	Timer move_timer = new Timer();
	Timer show_timer = new Timer();
	Timer remove_timer = new Timer();
	public int block_size = 20;
	boolean hold = false;

	public MyLabel(int col, long begin, long end, Container c, long time_elapsed) {

		ImageIcon icon = new ImageIcon("src/endproject/block.png");
		setIcon(icon);

		if (end > 0) { // if the label is long press

			block_size = (int) (end - begin) / 2;
		}

		setSize(100, block_size);
		setLocation(100 + col * 150, 95 - block_size);

		move = new Move(this, c);
		show = new Show(this, c); // show the label in JFrame
		remove = new Remove(this, c);

		begin = begin - time_elapsed;
		if (begin < 1000) { // 1000ms�block 敺�������������
			begin = 1000;
		}

		// System.out.println(time_elapsed);
		show_timer.schedule(show, begin - 1000); // show the label when (begin-1000)
		move_timer.scheduleAtFixedRate(move, begin - 1000, 2); // (575-75)*2 = (摨�-����蔭) / (瘥�2ms敺�銝�1)
		remove_timer.scheduleAtFixedRate(remove, begin - 1000, 2); // 瘥�2ms��銝�甈⊥�閬��abel

		c.addKeyListener(this); // add KeyListener to JFrame
		c.setFocusable(true);
		b = begin;
		e = end;
		column = col;
		parent = c;

		// System.out.println(b + " " + e);
	}

	@Override

	public void keyPressed(KeyEvent arg0) {
			
		// TODO Auto-generated method stub
		switch (arg0.getKeyChar()) {
		case 'd':
			Main.feedback[0].setVisible(true);
			break;
		case 'f':
			Main.feedback[1].setVisible(true);
			break;
		case 'j':
			Main.feedback[2].setVisible(true);
			break;
		case 'k':
			Main.feedback[3].setVisible(true);
			break;
		}
		if (e > 0 && getY() + block_size > 555 && getY() + block_size < 615) { // is long press
			hold = true;
			switch (arg0.getKeyChar()) {
			case 'd':
				remove.Ddown = true;
				break;
			case 'f':
				remove.Fdown = true;
				break;
			case 'j':
				remove.Jdown = true;
				break;
			case 'k':
				remove.Kdown = true;
				break;
			}
		} else if (e == 0 && getY() > 545 && getY() < 635) { // is single press
			// e == 0 will help avoid long press step into this if-condition
			char key = arg0.getKeyChar();
			if ((column == 0 && key == 'd') || (column == 1 && key == 'f') || (column == 2 && key == 'j')
					|| (column == 3 && key == 'k')) {
				if (getY() < 555 || getY() >= 615) { // bad
					Main.assess.setText("Bad");
					Main.assess.setForeground(Color.red);
					Main.grade += Remove.score_get;
					Main.combo.setText("combo " + ++Main.comboCount);
					Main.ab+=1;
				} else if (getY() < 575 || getY() >= 595) { // good
					Main.assess.setText("Good");
					Main.grade += Remove.score_get * 2;
					Main.assess.setForeground(Color.yellow);
					Main.combo.setText("combo " + ++Main.comboCount);
					Main.ab+=2;
				} else if (getY() >= 575 && getY() < 595) { // perfect
					Main.assess.setText("Perfect");
					Main.assess.setForeground(Color.GREEN);
					Main.grade += Remove.score_get * 4;
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
				parent.remove(this);
				parent.repaint();
				parent.removeKeyListener(this);
				remove_timer.cancel();
				remove_timer.purge();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getKeyChar()) {
		case 'd':
			remove.Ddown = false;
			Main.feedback[0].setVisible(false);
			break;
		case 'f':
			remove.Fdown = false;
			Main.feedback[1].setVisible(false);
			break;
		case 'j':
			remove.Jdown = false;
			Main.feedback[2].setVisible(false);
			break;
		case 'k':
			remove.Kdown = false;
			Main.feedback[3].setVisible(false);
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	
}