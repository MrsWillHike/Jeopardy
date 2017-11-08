package jeopardy;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{
	
	public static void main(String args[]) {
		List<Category> roundOne = new ArrayList<Category>();
		Category tempCat = new Category("fakename");
		tempCat.add(new Question("Question", "Answer", 100));
		//roundOne.add(
		//this.
		JFrame jf = new JFrame();
		jf.setTitle("Title");
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		jf.setUndecorated(true);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		JPanel panel;
		
        panel = new JPanel() {
        	@Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                for(int i = 0; i < 6; i++) {
                	for(int j = 0; j < 6; j++) {
                		if(j == 0) {
                			g.setColor(Color.RED);
                		} else {
                			g.setColor(Color.BLUE);
                		}
                		g.fillRect((getWidth() / 6) * i, (getHeight() / 6) * j, (getWidth() / 6) - 10, (getHeight() / 6) - 10);
                		int x = getWidth() / 6;
                		x = (x * i) + (int)(1d/8d) * x;
                		int y = getHeight() / 6;
                		y = (y * j) - (int)(2d/8d) * y;
                		g.setColor(Color.GREEN);
                		g.drawString("String", x, y);
                	}
                }
            }
        };
        jf.add(panel);
        jf.validate();
	}
}
