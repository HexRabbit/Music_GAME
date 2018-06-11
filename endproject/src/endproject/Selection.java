package endproject;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.magiclen.magicaudioplayer.AudioPlayer;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Selection extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static File folder1 = new File("src/4K-beatmaps");
	static String[] list1 = folder1.list(new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return !name.equals(".DS_Store");
        }
    });
	LinkedList<JButton> button = new LinkedList<JButton>();
	JPanel[] panel = new JPanel[list1.length];
	Image image = null;
	File imagefile;
	JLabel BG;

	private static int m;
    static Main frame1;
    static Selection frame;
    
    public static File songFile = new File("src/4K-beatmaps/" + list1[m] + "/audio.wav");
    public static AudioPlayer mp3 = AudioPlayer.createPlayer(songFile);
    
	public static void main(String[] args) {

		frame = new Selection();
		frame.setVisible(true);
		
		//frame1 = new Main();
		//frame1.setVisible(true);
	}

	

	public Selection() {

		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		for (int i = 0; i < 5; ++i) {
			panel[i] = new JPanel();
			panel[i].setSize(300, 30);

		}

		panel[0].setLocation(500, 50);
		panel[1].setLocation(450, 80);
		panel[2].setLocation(400, 110);
		panel[3].setLocation(450, 140);
		panel[4].setLocation(500, 170);
		setLayout(null);

		for (int i = 0; i < list1.length; ++i) {
			button.add(new JButton(list1[i]));
			// button.get(i).addActionListener(this);
			button.get(i).setPreferredSize(new Dimension(300, 30));
			button.get(i).setFocusable(false);
			//System.out.println(button.get(i));
			MyButtonListener play = new MyButtonListener();
			button.get(i).addActionListener(play);
			button.get(i).setActionCommand("play");
			
		}

		m = list1.length / 2;

		//System.out.println(m);

		panel[0].add(button.get(m - 2));
		add(panel[0]);
		panel[1].add(button.get(m - 1));
		add(panel[1]);
		panel[2].add(button.get(m));
		add(panel[2]);
		panel[3].add(button.get(m + 1));
		add(panel[3]);
		panel[4].add(button.get(m + 2));
		add(panel[4]);

		imagefile = new File("src/4K-beatmaps/" + list1[m] + "/BG.jpg");
	
		try {
			image = ImageIO.read(imagefile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		BG = new JLabel(new ImageIcon(image));
		//BG.setSize(192,108);
		BG.setSize(192*2, 108*2);
		BG.setLocation(0, 100);
		add(BG);

		addKeyListener(this);
		setVisible(true);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int temp = m;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
		//	m = (m >= list1.length /*- 3*/) ? list1.length /*- 3*/ : m + 1;
			m = (m >= list1.length-1 /*- 3*/) ? list1.length-1 /*- 3*/ : m + 1;
			System.out.println(m + "D");
			if(mp3.isPlaying()) {
				mp3.close();
			}
			File songFile1;
			
			songFile1 = new File("src/4K-beatmaps/" + list1[m] + "/audio.wav");
			mp3 = AudioPlayer.createPlayer(songFile1);
			mp3.play();
			button.get(m).setFocusable(true);

			break;
		case KeyEvent.VK_UP:
			m = (m == 0) ? 0 : m - 1;
			System.out.println(m + "U");
			if(mp3.isPlaying()) {
				mp3.close();
			}
			File songFile2;
			
			songFile2 = new File("src/4K-beatmaps/" + list1[m] + "/audio.wav");
			mp3 = AudioPlayer.createPlayer(songFile2);
			mp3.play();
			//button.get(m).setFocusable(true);
			break;

		}

		for (int i = 0; i < 5; ++i) {

			panel[i].removeAll();
			panel[i].revalidate();
			panel[i].repaint();

		}

		File imagefile = new File("src/4k-beatmaps/" + list1[m] + "/BG.jpg");
		try {
			image = ImageIO.read(imagefile);
		} catch (IOException gg) {
			gg.printStackTrace();
		}
		
		
		
		BG.setIcon(new ImageIcon(image));
		
		BG.setSize(192*2,108*2);
		BG.setLocation(0,100);
		this.repaint();
		
		if (m == button.size() - 1) {
			for (int i = 0; i < 3; ++i) {
				panel[i].add(button.get(m - 2 + i));
				add(panel[i]);
				panel[i].revalidate();
				panel[i].repaint();
			}
		} else if (m == button.size() - 2) {
			for (int i = 0; i < 4; ++i) {
				panel[i].add(button.get(m - 2 + i));
				add(panel[i]);
				panel[i].revalidate();
				panel[i].repaint();
			}
		} else if (m == 2) {

			for (int i = 0; i < 5; ++i) {

				panel[i].add(button.get(i));
				add(panel[i]);
				panel[i].revalidate();
				panel[i].repaint();
			}

		} else if (m == 1) {
			for (int i = 1; i < 5; ++i) {

				panel[i].add(button.get(i - 1));
				add(panel[i]);
				panel[i].revalidate();
				panel[i].repaint();
			}
		} else if (m == 0) {
			for (int i = 2; i < 5; ++i) {

				panel[i].add(button.get(i - 2));
				add(panel[i]);
				panel[i].revalidate();
				panel[i].repaint();
			}
		} else {
			for (int i = 0; i < 5; ++i) {

				panel[i].add(button.get(m - 2 + i));
				add(panel[i]);
				panel[i].revalidate();
				panel[i].repaint();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}