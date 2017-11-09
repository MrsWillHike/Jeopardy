package jeopardy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
        
		JPanel panel;
		
        panel = new JPanel() {
        	@Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0,  0,  getWidth(), getHeight());
                g.setColor(Color.BLUE);
                String text = "";
                int sw;
                int sh = g.getFontMetrics().getHeight();
                int x;
                int y;
                int bw;
                int bh;
                g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 20));
                for(int i = 0; i < roundOne.size(); i++) {
                	g.setColor(Color.RED);
                	text = roundOne.get(i).getName();
                	g.fillRect((getWidth() / 6) * i, 0, (getWidth() / 6) - 10, (getHeight() / 6) - 10);
                	sw = g.getFontMetrics().stringWidth(text);
            		sh = g.getFontMetrics().getHeight();
            		bw = getWidth() / 6;
            		x = (bw * i) + ((bw - sw) / 2);
            		y = getHeight() / 6;
            		y = ((y - sh) / 2);
            		g.setColor(Color.WHITE);
            		g.drawString(text, x, y);
                	for(int j = 0; j < roundOne.get(i).size(); j++) {
                		g.setColor(Color.BLUE);
                		g.fillRect((getWidth() / 6) * i, (getHeight() / 6) * (j + 1), (getWidth() / 6) - 10, (getHeight() / 6) - 10);
                		text = roundOne.get(i).getQuestion(j).getScore() + "";
                		sw = g.getFontMetrics().stringWidth(text);
                		bw = getWidth() / 6;
                		x = (bw * i) + ((bw - sw) / 2);
                		bh = getHeight() / 6;
                		y = (bh * (j + 1)) + ((bh - sh) / 2);
                		g.setColor(Color.WHITE);
                		g.drawString(text, x, y);
                	}
                }
            }
        };
        jf.add(panel);
        jf.validate();
        
//        try {
//			Thread.sleep(5000);
//			System.exit(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
