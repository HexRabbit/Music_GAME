package endproject;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import javafx.embed.swing.JFXPanel;

import org.magiclen.magicaudioplayer.*;


public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	/*
	 * public static void main(String[] args) { Main frame = new Main();
	 * frame.setVisible(true); //Selection(); }
	 */

	// private JLabel l;
	public static long begin_time;
	public static LinkedList<MyLabel> l = new LinkedList<MyLabel>();
	public static JButton pause = new JButton("Pause");

	public static JLabel assess = new JLabel(); // record "Perfect", "Good"...
	public static JLabel score = new JLabel();
	public JLabel highest = new JLabel();
	public static JLabel combo = new JLabel("combo");
	

	public static JLabel accuracy = new JLabel("0.00%");
	public static double aa;
	public static double ab;
	public static JLabel accuracy_word = new JLabel("Accuracy:");
	
	public static int comboCount;
	public static int maxCombo = 0;
	public static int grade;
	public static int perfectCount = 0;
	public static int goodCount = 0;
	public static int badCount = 0;
	public static int missCount = 0;
	public static String now_play;
	public static AudioPlayer mp3;
	
	public static JLabel[] feedback = new JLabel[4];
	
	public JLabel background = new JLabel();
	
	public Main(String song) {
		now_play = song;
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		getContentPane().setBackground(Color.black);
		
		
		final JFXPanel fxPanel = new JFXPanel();

		pause.setActionCommand("pause");
		MyButtonListener buttonListener = new MyButtonListener();
		pause.addActionListener(buttonListener);
		pause.setFocusable(false);
		pause.setLocation(800, 50);
		pause.setOpaque(true);
		pause.setBackground(Color.black);
		pause.setSize(100, 30);
		add(pause);

		JButton back = new JButton("back");
		back.setActionCommand("back");
		back.addActionListener(buttonListener);
		back.setFocusable(false);
		back.setLocation(800, 80);
		back.setOpaque(true);
		back.setBackground(Color.black);
		back.setSize(100, 30);
		add(back);
		
		
		score.setOpaque(true);
		score.setForeground(Color.white);
		score.setBackground(Color.black);
		score.setLocation(750, 20);
		score.setSize(150, 30);
		score.setFont(new Font("New Romance", Font.BOLD, 32));
		
		add(score);
		Timer score_timer = new Timer();
		TimerTask check_score = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				score.setText(Integer.toString(grade));
			}

		};
		
		
		/*
		 * combo
		 * 
		 */
		
		combo.setOpaque(true);
		combo.setForeground(Color.white);
		combo.setBackground(Color.black);
		combo.setLocation(300,200);
		combo.setSize(200, 40);
		combo.setFont(new Font("New Romance", Font.BOLD, 32));

		add(combo);
		
		score_timer.schedule(check_score, 0, 50);
		

		accuracy_word.setOpaque(true);
		accuracy_word.setForeground(Color.white);
		accuracy_word.setBackground(Color.black);
		accuracy_word.setLocation(700, 220);
		accuracy_word.setSize(200, 30);
		accuracy_word.setFont(new Font("New Romance", Font.BOLD, 32));
		add(accuracy_word);
		
		accuracy.setOpaque(true);
		accuracy.setForeground(Color.white);
		accuracy.setBackground(Color.black);
		accuracy.setLocation(725, 250);
		accuracy.setSize(150, 30);
		accuracy.setFont(new Font("New Romance", Font.BOLD, 32));
		add(accuracy);


		/*
		 * to print out how performance you get
		 */
		assess.setLocation(330, 330);
		assess.setSize(120, 40);
		assess.setFont(new Font("New Romance", Font.BOLD, 32));
		assess.setOpaque(true);
		assess.setBackground(Color.black);
		assess.setForeground(Color.GREEN);
		assess.setAlignmentX(CENTER_ALIGNMENT);
		assess.setAlignmentY(CENTER_ALIGNMENT);
		add(assess);

		/*
		 * add background
		 */
		File infile = new File("src/endproject/line.png");
		Image image = null;
		try {
			image = ImageIO.read(infile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel line = new JLabel(new ImageIcon(image));
		line.setLocation(75, 0);
		line.setSize(600, 700);
		add(line);
		
		/*
		 * hide block < 75
		 */
		JLabel wall = new JLabel();
		wall.setLocation(0, 0);
		wall.setSize(800, 120);
		wall.setOpaque(true);
		wall.setBackground(Color.BLACK);
		add(wall);
		
		/*
		 * show the highest score should get in this game
		 * (should hide)
		 */
		
		highest.setLocation(750, 150);
		highest.setSize(150, 30);
		highest.setOpaque(true);
		highest.setBackground(Color.BLACK);
		highest.setForeground(Color.WHITE);
		highest.setFont(new Font("New Romance", Font.BOLD, 32));
		add(highest);

		/*
		 * feed back
		 * 
		 */
		File feed = new File("src/endproject/rec.png");
		Image image1 = null;
		try {
			image1 = ImageIO.read(feed);
			System.out.print(image1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < 4; i++) {
			feedback[i] = new JLabel(new ImageIcon(image1));
			feedback[i].setLocation(75 + 150*i, 575);
			feedback[i].setSize(150,35);
			feedback[i].setVisible(false);
			add(feedback[i]);
		}
		
		int high = 0;
		
		System.out.println(song);
		Song sng = SongReader.readFile("src/4K-beatmaps/" + song + "/" + song + ".osx");

		File songFile;
		songFile = new File("src/4K-beatmaps/" + song + "/audio.wav");
		mp3 = AudioPlayer.createPlayer(songFile);
		mp3.play();
		
		JButton re = new JButton();
		re.setActionCommand("Result");
		re.addActionListener(buttonListener);
		re.setFocusable(false);
		re.setLocation(800, 100);
		re.setOpaque(true);
		re.setBackground(Color.black);
		re.setSize(100, 30);
		add(re);
		Timer end_timer = new Timer();
		TimerTask result = new TimerTask() {
			public void run() {
				// TODO Auto-generated method stub
				re.doClick();
			}
		};
		
		end_timer.schedule(result,mp3.getAudioLength()/1000+5000);
		begin_time = System.currentTimeMillis(); //for Pause
		long start = System.currentTimeMillis();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < sng.track.get(i).size(); j++) {
				long end = System.currentTimeMillis();
				l.add(new MyLabel(i, sng.track.get(i).get(j).start, sng.track.get(i).get(j).end, this, end - start));
				
				if(sng.track.get(i).get(j).end == 0) {
					high+=200;
				} else {
					high += (sng.track.get(i).get(j).end - sng.track.get(i).get(j).start)/40*200; 
				}
			}
		}
		if(Remove.hidden == true) {
			high *= 1.5;
			JLabel hid_block = new JLabel();
			back.setFocusable(false);
			back.setLocation(75, 400);
			back.setBackground(Color.black);
			back.setSize(600, 200);
			add(back);
		}
		highest.setText(Integer.toString(high));
	}

	public void restart(String song) {
		
		aa=0;
		ab=0;
		
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		getContentPane().setBackground(Color.black);
		
		
		
		final JFXPanel fxPanel = new JFXPanel();

		pause.setActionCommand("pause");
		MyButtonListener buttonListener = new MyButtonListener();
		pause.addActionListener(buttonListener);
		pause.setFocusable(false);
		pause.setLocation(800, 50);
		pause.setOpaque(true);
		pause.setBackground(Color.black);
		pause.setSize(100, 30);
		add(pause);

		JButton back = new JButton("back");
		back.setActionCommand("back");
		back.addActionListener(buttonListener);
		back.setFocusable(false);
		back.setLocation(800, 80);
		back.setOpaque(true);
		back.setBackground(Color.black);
		back.setSize(100, 30);
		add(back);
		
		grade = 0;
		Timer score_timer = new Timer();
		TimerTask check_score = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				score.setText(Integer.toString(grade));
			}

		};

		score_timer.schedule(check_score, 0, 50);

		

		/*
		 * add background
		 */
		File infile = new File("src/endproject/line.png");
		Image image = null;
		try {
			image = ImageIO.read(infile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel line = new JLabel(new ImageIcon(image));
		line.setLocation(75, 0);
		line.setSize(600, 700);
		add(line);
		
		/*
		 * hide block < 75
		 */
		JLabel wall = new JLabel();
		wall.setLocation(0, 0);
		wall.setSize(800, 120);
		wall.setOpaque(true);
		wall.setBackground(Color.BLACK);
		add(wall);
		
		
		/*
		 * feed back
		 * 
		 */
		File feed = new File("src/endproject/rec.png");
		Image image1 = null;
		try {
			image1 = ImageIO.read(feed);
			System.out.print(image1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*for(int i = 0; i < 4; i++) {
			feedback[i] = new JLabel(new ImageIcon(image1));
			feedback[i].setLocation(75 + 150*i, 575);
			feedback[i].setSize(150,35);
			feedback[i].setVisible(false);
			add(feedback[i]);
		}*/
		
		int high = 0;
		
		System.out.println(song);
		Song sng = SongReader.readFile("src/4K-beatmaps/" + song + "/" + song + ".osx");

		File songFile;
		songFile = new File("src/4K-beatmaps/" + song + "/audio.wav");
		mp3 = AudioPlayer.createPlayer(songFile);
		mp3.play();
		
		while(l.isEmpty() == false) {
			l.getLast().move_timer.cancel();
			l.getLast().remove_timer.cancel();
			l.getLast().show_timer.cancel();
			l.getLast().move_timer.purge();
			l.getLast().remove_timer.purge();
			l.getLast().show_timer.purge();
			remove(l.getLast());
			removeKeyListener(l.getLast());
			l.remove(l.size()-1);
		}
		repaint();
		l.clear();
		
				
				
		begin_time = System.currentTimeMillis(); //for Pause
		long start = System.currentTimeMillis();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < sng.track.get(i).size(); j++) {
				long end = System.currentTimeMillis();
				l.add(new MyLabel(i, sng.track.get(i).get(j).start, sng.track.get(i).get(j).end, this, end - start));
				
				if(sng.track.get(i).get(j).end == 0) {
					high+=200;
				} else {
					high += (sng.track.get(i).get(j).end - sng.track.get(i).get(j).start)/40*200; 
				}
			}
		}
		highest.setText(Integer.toString(high));
	}
}