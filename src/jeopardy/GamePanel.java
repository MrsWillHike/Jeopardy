package jeopardy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel{
	
	static JFrame jf;
	
	public static void setGamePanel(JFrame j) {
		jf = j;
	}
	
	public static boolean drawMainPanel(List<Category> roundOne) {
		JPanel panel = new JPanel() {
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
        return true;
	}
	
	public static boolean displayText(String text) {
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
                int sh;
                int x;
                int y;
                if(sw > (double)getWidth() * (9d/10d)) {
                	int loc = text.length() / 2;
                	while(text.charAt(loc) != ' ') {
                		loc++;
                	}
                	String textA = text.substring(0,  loc++);
                	String textB = text.substring(loc, text.length());
                	
                	sh = g.getFontMetrics().getHeight();
                	
                	sw = g.getFontMetrics().stringWidth(textA);
            		x = ((getWidth() - sw) / 2);
            		y = ((getHeight() / 2) - (sh / 2));
            		g.setColor(Color.WHITE);
            		g.drawString(textA, x, y);
            		
            		sw = g.getFontMetrics().stringWidth(textB);
            		x = ((getWidth() - sw) / 2);
            		y = ((getHeight() / 2) + (sh / 2));
            		g.setColor(Color.WHITE);
            		g.drawString(textB, x, y);
                } else {
	                sh = g.getFontMetrics().getHeight();
	        		x = ((getWidth() - sw) / 2);
	        		y = ((getHeight() - sh) / 2);
	        		g.setColor(Color.WHITE);
	        		g.drawString(text, x, y);
                }
        	}
		};
		jf.add(panel);
        jf.validate();
        return true;
	}
}
