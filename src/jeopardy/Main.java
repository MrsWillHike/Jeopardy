package jeopardy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{
	
	public static void main(String args[]) {
		List<Category> catagories = new ArrayList<Category>();
		String names[] = {"scout"};
		for(int i = 0; i < names.length; i++) {
			Category temp = new Category("");
			temp.parse(new File("catagories/" + names[i] + ".jep"));
			System.out.println(catagories.toString());
		}
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
                g.setColor(Color.BLUE);
                String text = "";
                for(int i = 0; i < catagories.size(); i++) {
                	for(int j = 0; j < 6; j++) {
                		if(j == 0) {
                			g.setColor(Color.RED);
                			text = catagories.get(i).getName();
                		} else {
                			g.setColor(Color.BLUE);
                			text = catagories.get(i).getQuestion(j).getScore() + "";
                		}
                		g.fillRect((getWidth() / 6) * i, (getHeight() / 6) * j, (getWidth() / 6) - 10, (getHeight() / 6) - 10);
                		int x = getWidth() / 6;
                		g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 20));
                		int sw = g.getFontMetrics().stringWidth(text);
                		int sh = g.getFontMetrics().getHeight();
                		x = (x * i) + ((x - sw) / 2);
                		int y = getHeight() / 6;
                		y = (y * j) + ((y - sh) / 2);
                		if(j == 0) {
                			g.setColor(Color.BLACK);
                		} else {
                			g.setColor(Color.WHITE);
                		}
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
