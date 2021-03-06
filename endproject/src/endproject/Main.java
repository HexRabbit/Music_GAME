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

//import javafx.embed.swing.JFXPanel;

import org.magiclen.magicaudioplayer.*;


public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	/*
	 * public static void main(String[] args) { Main frame = new Main();
	 * frame.setVisible(true); //Selection(); }
	 */

	
	
	
	

	public JLabel assess;// = new JLabel(); // record "Perfect", "Good"...
	
	public JLabel highest;// = new JLabel();
	public JLabel combo;// = new JLabel("combo");
	public JLabel maxText;

	public JLabel accuracy;// = new JLabel("0.00%");
	public double aa;
	public double ab;
	public JLabel accuracy_word;// = new JLabel("Accuracy:");
	
	public int comboCount;
	public int maxCombo = 0;
	public int grade;
	public int perfectCount = 0;
	public int goodCount = 0;
	public int badCount = 0;
	public int missCount = 0;
	public int playwait = 3000;
	public String now_play;
	public AudioPlayer mp3;
	
	public LinkedList<MyLabel> l;// = new LinkedList<MyLabel>();
	
	//public JLabel background;// = new JLabel();
	public Timer score_timer;
	public Timer start_timer;
	public Timer end_timer;
	public Main(String song) {
		now_play = song;
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		getContentPane().setBackground(Color.black);
		
		
		//final JFXPanel fxPanel = new JFXPanel();
		
		JButton pause = new JButton("Pause");
		//pause.setActionCommand("pause");
		
		
		pause.setFocusable(false);
		pause.setLocation(740, 580);
		pause.setOpaque(true);
		pause.setBackground(Color.gray);
		pause.setSize(100, 30);
		add(pause);

		JButton back = new JButton("back");
		back.setActionCommand("back");
		MyButtonListener buttonListener = new MyButtonListener();
		back.addActionListener(buttonListener);
		back.setFocusable(false);
		back.setLocation(740, 550);
		back.setOpaque(true);
		back.setBackground(Color.gray);
		back.setSize(100, 30);
		add(back);
		
		JLabel score = new JLabel();
		score.setOpaque(true);
		score.setForeground(Color.white);
		score.setBackground(Color.black);
		score.setLocation(700, 70);
		score.setSize(150, 30);
		score.setFont(new Font("New Romance", Font.BOLD, 32));
		add(score);
		
		/* update score */
		score_timer = new Timer();
		TimerTask check_score = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				score.setText(String.format("%08d", grade));
			}

		};
		score_timer.schedule(check_score, 0, 50);
		
		
		/*
		 * combo
		 * 
		 */
		combo = new JLabel("combo 0", JLabel.CENTER);
		combo.setForeground(Color.white);
		combo.setLocation(300,200);
		combo.setSize(200, 40);
		combo.setFont(new Font("New Romance", Font.BOLD, 32));

		add(combo);
		
		
		
		accuracy_word = new JLabel("Accuracy:");
		accuracy_word.setOpaque(true);
		accuracy_word.setForeground(Color.white);
		accuracy_word.setBackground(Color.black);
		accuracy_word.setLocation(700, 220);
		accuracy_word.setSize(200, 30);
		accuracy_word.setFont(new Font("New Romance", Font.BOLD, 32));
		add(accuracy_word);
		
		accuracy = new JLabel("0.00%");
		accuracy.setOpaque(true);
		accuracy.setForeground(Color.white);
		accuracy.setBackground(Color.black);
		accuracy.setLocation(750, 260);
		accuracy.setSize(150, 30);
		accuracy.setFont(new Font("New Romance", Font.BOLD, 32));
		add(accuracy);


		/*
		 * to print out how performance you get
		 */
		assess = new JLabel(" ", JLabel.CENTER); 
		assess.setLocation(330, 330);
		assess.setSize(120, 40);
		assess.setFont(new Font("New Romance", Font.BOLD, 32));
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
		wall.setSize(700, 120);
		wall.setOpaque(true);
		wall.setBackground(Color.BLACK);
		add(wall);
		
		/*
		 * show the highest score should get in this game
		 * (should hide)
		 */
		highest = new JLabel();
		highest.setLocation(750, 460);
		highest.setSize(150, 30);
		highest.setForeground(Color.WHITE);
		highest.setFont(new Font("New Romance", Font.BOLD, 32));
		add(highest);
		
		maxText = new JLabel("Max:");
		maxText.setForeground(Color.white);
		maxText.setLocation(700, 420);
		maxText.setSize(200, 30);
		maxText.setFont(new Font("New Romance", Font.BOLD, 32));
		add(maxText);
		
		
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
		
		JLabel[] feedback = new JLabel[4];
		for(int i = 0; i < 4; i++) {
			feedback[i] = new JLabel(new ImageIcon(image1));
			feedback[i].setLocation(75 + 150*i, 575);
			feedback[i].setSize(150,35);
			feedback[i].setVisible(false);
			add(feedback[i]);
		}
		
		int high = 0;
		
		/* for testing purpose */
		JButton re = new JButton("Result");
		re.setActionCommand("Result");
		re.addActionListener(buttonListener);
		re.setFocusable(false);
		re.setLocation(740, 610);
		re.setOpaque(true);
		re.setBackground(Color.gray);
		re.setSize(100, 30);
		add(re);
		
		
		/* Load song */
		System.out.println(song);
		Song sng = SongReader.readFile("src/4K-beatmaps/" + song + "/" + song + ".osx");
		File songFile;
		songFile = new File("src/4K-beatmaps/" + song + "/audio.wav");
		mp3 = AudioPlayer.createPlayer(songFile);
		
		/* Add start & end timer to decrease latency */
		start_timer = new Timer();
		TimerTask playStart = new TimerTask() {
			public void run() {
				// TODO Auto-generated method stub
				mp3.play();
			}
		};
		end_timer = new Timer();
		TimerTask result = new TimerTask() {
			public void run() {
				// TODO Auto-generated method stub
				re.doClick();
			}
		};
		
		start_timer.schedule(playStart, playwait);
		
		end_timer.schedule(result,mp3.getAudioLength()/1000 + 1500 + playwait);
		
		
		final long start = System.currentTimeMillis();
		l = new LinkedList<MyLabel>();
		
		pause.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  if(pause.getText() == "Pause") {
						new Pause(start, l);
					
						pause.setText("Resume");
						
					} else if(pause.getText() == "Resume") {
						new Resume(start, l);
						pause.setText("Pause");
						pause.setFocusable(false);
					}
				  } 
				} );
		
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < sng.track.get(i).size(); j++) {
				long end = System.currentTimeMillis();
				System.out.println(end - start);
				l.add(new MyLabel(i, sng.track.get(i).get(j).start, sng.track.get(i).get(j).end, this, end - start - playwait));
				
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
			hid_block.setFocusable(false);
			hid_block.setLocation(75, 400);
			hid_block.setOpaque(true);
			hid_block.setBackground(Color.black);
			hid_block.setSize(600, 180);
			add(hid_block);
		}
		highest.setText(Integer.toString(high));
		
		/* Add key click sound */
		this.addKeyListener(new MyKeyListener(feedback));
	}

}