package endproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int column;
	public long b, e; //begin, end
	
	
	public MyLabel nextL;
	public Container parent;
	public Remove remove;
	public Show show;
	public Move move;
	Timer move_timer = new Timer();
	Timer show_timer = new Timer();
	Timer remove_timer = new Timer();
	public int block_size = 20;
	
	public MyLabel(int col, long begin, long end, Container c) {
		
		ImageIcon icon = new ImageIcon("src/endproject/block.png");
		setIcon(icon);
		
		
		if (end > 0) {  //if the label is long press
			//nextL = new MyLabel(col, begin + 200, end, c);
			block_size = (int)(end - begin)/2;
			
			//block_size = 500;
			//setSize(100, block_size);
		} /*else {
			
			setSize(100, 20);
		}*/
		
		setSize(100, block_size);
		setLocation(100 + col * 150, 95 - block_size);

		move = new Move(this, c);
		show = new Show(this, c); // show the label in JFrame
		remove = new Remove(this, c);
		
		
		if(begin < 1000) { //1000ms為block 從上面掉下來所需的時間
			begin = 1000;
			//setLocation(100 + col * 150, 75 - block_size);
		}
		
		show_timer.schedule(show, begin-1000);     //show the label when (begin-2500)
		move_timer.schedule(move, begin-1000, 40); //(575-75/20)*40 = (底-初始位置) / (每0.04秒往下20)
		remove_timer.schedule(remove, 0, 50);      //每0.05秒判斷一次是否要刪掉label
		
		
		c.addKeyListener(this); //add KeyListener to JFrame
		c.setFocusable(true);
		b = begin;
		e = end;
		column = col;
		parent = c;
		
		//System.out.println(b + " " + e);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if (e > 0) { // is long press
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
		} else if (getY() > 545 && getY() < 625 ) { // is single press
		
			switch(column) {
			case 0:
				if(arg0.getKeyChar() == 'd') {
					if(getY() < 555 || getY() >= 615) { //bad
						Main.assess.setText("Bad");
						Main.assess.setForeground(Color.red);
						Main.grade += 50;
					} else if(getY() < 575 || getY() >= 595) { //good
						Main.assess.setText("Good");
						Main.grade += 100;
						Main.assess.setForeground(Color.yellow);
					} else if(getY() >= 575 && getY() < 595) { //perfect
						Main.assess.setText("Perfect");
						Main.assess.setForeground(Color.GREEN);
						Main.grade += 200;
					}
					Container parent = getParent();
					parent.remove(this);
					parent.repaint();
					parent.removeKeyListener(this);
				}
				break;
			case 1:
				if(arg0.getKeyChar() == 'f' ) {
					if(getY() < 555 || getY() >= 615) { //bad
						Main.assess.setText("Bad");
						Main.assess.setForeground(Color.red);
						Main.grade += 50;
					} else if(getY() < 575 || getY() >= 595) { //good
						Main.assess.setText("Good");
						Main.grade += 100;
						Main.assess.setForeground(Color.yellow);
					} else if(getY() >= 575 && getY() < 595) { //perfect
						Main.assess.setText("Perfect");
						Main.assess.setForeground(Color.GREEN);
						Main.grade += 200;
					}
					Container parent = getParent();
					parent.remove(this);
					parent.repaint();
					parent.removeKeyListener(this);
				}
				break;
			case 2:
				if(arg0.getKeyChar() == 'j') {
					if(getY() < 555 || getY() >= 615) { //bad
						Main.assess.setText("Bad");
						Main.assess.setForeground(Color.red);
						Main.grade += 50;
					} else if(getY() < 575 || getY() >= 595) { //good
						Main.assess.setText("Good");
						Main.grade += 100;
						Main.assess.setForeground(Color.yellow);
					} else if(getY() >= 575 && getY() < 595) { //perfect
						Main.assess.setText("Perfect");
						Main.assess.setForeground(Color.GREEN);
						Main.grade += 200;
					}
					Container parent = getParent();
					parent.remove(this);
					parent.repaint();
					parent.removeKeyListener(this);
				}
				break;
			case 3:
				if(arg0.getKeyChar() == 'k') {
					if(getY() < 555 || getY() >= 615) { //bad
						Main.assess.setText("Bad");
						Main.assess.setForeground(Color.red);
						Main.grade += 50;
					} else if(getY() < 575 || getY() >= 595) { //good
						Main.assess.setText("Good");
						Main.grade += 100;
						Main.assess.setForeground(Color.yellow);
					} else if(getY() >= 575 && getY() < 595) { //perfect
						Main.assess.setText("Perfect");
						Main.assess.setForeground(Color.GREEN);
						Main.grade += 200;
					}
					Container parent = getParent();
					parent.remove(this);
					parent.repaint();
					parent.removeKeyListener(this);
				}
				break;
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getKeyChar()) {
		case 'd':
			remove.Ddown = false;
			break;
		case 'f':
			remove.Fdown = false;
			break;
		case 'j':
			remove.Jdown = false;
			break;
		case 'k':
			remove.Kdown = false;
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
}