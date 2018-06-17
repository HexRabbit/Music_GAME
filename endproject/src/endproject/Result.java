package endproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Result extends JFrame{
	
	
	
	public Result(int MaxCombo,int Perfect,int Good,int Bad,int Miss,int Score,String song)
	{
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Image image = null;
		
		JLabel BG = new JLabel();
		File imagefile = new File("src/4k-beatmaps/" + song + "/BG.jpg");
		try {
			image = ImageIO.read(imagefile);
		} catch (IOException gg) {
			gg.printStackTrace();
		}
		
		image = image.getScaledInstance(192*2, 108*2, image.SCALE_DEFAULT);
		
		BG.setIcon(new ImageIcon(image));
		
		BG.setSize(192*2,108*2);
		BG.setLocation(450,200);
		add(BG);
		JPanel panel = new JPanel();
		
		JLabel combo = new JLabel(   "MAX COMBO");
		JLabel score = new JLabel(   "SCORE");
		JLabel perfect = new JLabel( "PERFECT");
		JLabel good = new JLabel(    "GOOD");
		JLabel bad = new JLabel(     "BAD");
		JLabel miss = new JLabel(    "MISS");
		combo.setSize(250,300);
		score.setSize(250,300);
		perfect.setSize(250,300);
		good.setSize(250,300);
		bad.setSize(250,300);
		miss.setSize(250,300);
		
		
		combo.setFont(new Font("New Romance", Font.BOLD, 32));
		score.setFont(new Font("New Romance", Font.BOLD, 32));
		perfect.setFont(new Font("New Romance", Font.BOLD, 32));
		good.setFont(new Font("New Romance", Font.BOLD, 32));
		bad.setFont(new Font("New Romance", Font.BOLD, 32));
		miss.setFont(new Font("New Romance", Font.BOLD, 32));
		
		combo.setLocation(100, 0);		
		score.setLocation(100, 75);	
		perfect.setLocation(100, 150);	
		good.setLocation(100, 225);	
		bad.setLocation(100,300);	
		miss.setLocation(100,375);	
		
		add(combo);
		add(score);
		add(perfect);
		add(good);
		add(bad);
		add(miss);
		
		JLabel combo1 = new JLabel(Integer.toString(MaxCombo), JLabel.RIGHT);
		JLabel score1 = new JLabel(Integer.toString(Score), JLabel.RIGHT);
		JLabel perfect1 = new JLabel( Integer.toString(Perfect), JLabel.RIGHT);
		JLabel good1 = new JLabel(Integer.toString(Good), JLabel.RIGHT);
		JLabel bad1 = new JLabel(Integer.toString(Bad), JLabel.RIGHT);
		JLabel miss1 = new JLabel(Integer.toString(Miss), JLabel.RIGHT);
		combo1.setSize(75,300);
		score1.setSize(175,300);
		perfect1.setSize(75,300);
		good1.setSize(75,300);
		bad1.setSize(75,300);
		miss1.setSize(75,300);
		
		
		combo1.setFont(new Font("New Romance", Font.BOLD, 32));
		score1.setFont(new Font("New Romance", Font.BOLD, 32));
		perfect1.setFont(new Font("New Romance", Font.BOLD, 32));
		good1.setFont(new Font("New Romance", Font.BOLD, 32));
		bad1.setFont(new Font("New Romance", Font.BOLD, 32));
		miss1.setFont(new Font("New Romance", Font.BOLD, 32));
		
		combo1.setLocation(350, 0);		
		score1.setLocation(250, 75);	
		perfect1.setLocation(350, 150);	
		good1.setLocation(350, 225);	
		bad1.setLocation(350,300);	
		miss1.setLocation(350,375);	
		
		
		/*//combo1.setHorizontalAlignment();	
		score1.setAlignmentX(RIGHT_ALIGNMENT);
		perfect1.setAlignmentX(RIGHT_ALIGNMENT);
		good1.setAlignmentX(RIGHT_ALIGNMENT);
		bad1.setAlignmentX(RIGHT_ALIGNMENT);
		miss1.setAlignmentX(RIGHT_ALIGNMENT);*/
		
		
		add(combo1);
		add(score1);
		add(perfect1);
		add(good1);
		add(bad1);
		add(miss1);
		
		setLayout(null);
		MyButtonListener buttonListener = new MyButtonListener();
		JButton back = new JButton("back");
		back.setActionCommand("back");
		back.addActionListener(buttonListener);
		back.setFocusable(false);
		back.setLocation(500, 80);
		back.setOpaque(true);
		back.setBackground(Color.black);
		back.setSize(100, 30);
		add(back);
		
	
	}
	
	
}
