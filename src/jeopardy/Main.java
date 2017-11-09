package jeopardy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main{
	
	public static void main(String args[]) {
		
		
		List<Category> roundOne = new ArrayList<Category>();
		String names[] = {"cooking", "fire", "firstaid", "knives", "scoutstuff", "water"};
		for(int i = 0; i < names.length; i++) {
			Category temp = new Category("");
			temp.parse(new File("catagories/" + names[i] + ".jep"), 200);
			roundOne.add(temp);
			
		}
		System.out.println(roundOne.toString());
		JFrame jf = new JFrame();
		jf.setTitle("Title");
		jf.setSize(1280, 720);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.addKeyListener(new KeyListener() {
			@Override public void keyTyped(KeyEvent e) {KeyListen.keyTyped(e);}
			@Override public void keyReleased(KeyEvent e) {KeyListen.keyReleased(e);}
			@Override public void keyPressed(KeyEvent e) {KeyListen.keyPressed(e);}
		});
		
		GamePanel gp = new GamePanel(jf);
		gp.drawMainPanel(roundOne);
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		displayText(roundOne.get(1).getQuestion(1).getQuestion(), jf);
	}
	
	public static boolean displayText(String text, JFrame jf) {
		JPanel panel = new JPanel() {
        	@Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0,  0,  getWidth(), getHeight());
                g.setColor(Color.BLUE);
                g.fillRect(10,  10, getWidth() - 20, getHeight() - 20);
                g.setColor(Color.BLACK);
                g.fillRect(20,  20, getWidth() - 40, getHeight() - 40);
                g.setColor(Color.BLUE);
                g.fillRect(25,  25, getWidth() - 50, getHeight() - 50);
                g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 60));
                int sw = g.getFontMetrics().stringWidth(text);
                int sh = g.getFontMetrics().getHeight();
        		int x = ((getWidth() - sw) / 2);
        		int y = ((getHeight() - sh) / 2);
        		g.setColor(Color.WHITE);
        		g.drawString(text, x, y);
        	}
		};
		jf.add(panel);
        jf.validate();
        return true;
	}
}
