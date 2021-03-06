package jeopardy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel{
	
	private static JFrame jf;
	private static JPanel panel;
	
	private static Team teamRed;
	private static Team teamYellow;
	private static Team teamGreen;
	private static Team teamBlue;
	
	public static void setGamePanel(JFrame j) {
		jf = j;
	}
	
	public static void setTeams(Team red, Team yellow, Team green, Team blue) {
		teamRed = red;
		teamYellow = yellow;
		teamGreen = green;
		teamBlue = blue;
	}
	
	public static boolean drawMainPanel(List<Category> cat) {
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
                int bw = getWidth() / 7;
                int bh = getHeight() / 6;
                g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 20));
                for(int i = 0; i < cat.size(); i++) {
                	if(!cat.get(i).isDone()) {
	                	g.setColor(Color.RED);
	                	text = cat.get(i).getName();
	                	g.fillRect((getWidth() / 7) * i, 0, (getWidth() / 7) - 10, (getHeight() / 6) - 10);
	                	sw = g.getFontMetrics().stringWidth(text);
	            		sh = g.getFontMetrics().getHeight();
	            		x = (bw * i) + ((bw - sw) / 2);
	            		y = ((bh - sh) / 2);
	            		g.setColor(Color.WHITE);
	            		g.drawString(text, x, y);
	                	for(int j = 0; j < cat.get(i).size(); j++) {
	                		if(!cat.get(i).getQuestion(j).isUsed()) {
		                		g.setColor(Color.BLUE);
		                		g.fillRect((getWidth() / 7) * i, (getHeight() / 6) * (j + 1), (getWidth() / 7) - 10, (getHeight() / 6) - 10);
		                		text = cat.get(i).getQuestion(j).getScore() + "";
		                		sw = g.getFontMetrics().stringWidth(text);
		                		x = (bw * i) + ((bw - sw) / 2);
		                		y = (bh * (j + 1)) + ((bh - sh) / 2);
		                		g.setColor(Color.WHITE);
		                		g.drawString(text, x, y);
	                		}
	                	}
                	}
                }
                drawScore(g, teamRed, this, cat.size(), 0);
                drawScore(g, teamYellow, this, cat.size(), 1);
                drawScore(g, teamGreen, this, cat.size(), 2);
                drawScore(g, teamBlue, this, cat.size(), 3);
            }
        };
        jf.add(panel);
        jf.validate();
        return true;
	}
	
	private static void drawScore(Graphics g, Team t, JPanel p, int w, int j) {
		int bw = p.getWidth() / 7;
        int bh = p.getHeight() / 6;
        String text = t.getName();
        int sw = g.getFontMetrics().stringWidth(text);
        int sh = g.getFontMetrics().getHeight();
        int x = (bw * w) + ((bw - sw) / 2);
        int y = (bh * (j)) + ((bh - sh) / 2);
        g.setColor(t.getColor());
		g.drawString(text, x, y);
		y = y + sh;
		text = ((Integer) t.getScore()).toString();
        sw = g.getFontMetrics().stringWidth(text);
        x = (bw * w) + ((bw - sw) / 2);
        g.drawString(text,  x,  y);
	}
	
	public static boolean displayText(String text) {
		return displayText(text, Color.BLUE);
	}
	
	public static boolean displayText(String text, Color c) {
		if(text.equals(null)) {
			return false;
		}
		panel = new JPanel() {
        	@Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0,  0,  getWidth(), getHeight());
                g.setColor(c);
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
                String textA = "";
                String textB = "";
                boolean twoLine = false;
                if(text.contains("\n")) {
                	int loc = 0;
                	while(text.charAt(loc) != '\n') {
                		loc++;
                	}
                	textA = text.substring(0,  loc++);
                	textB = text.substring(loc, text.length());
                	twoLine = true;
                }
                if(sw > (double) getWidth() * (9d/10d)) {
                	int loc = text.length() / 2;
                	while(text.charAt(loc) != ' ') {
                		loc++;
                	}
                	textA = text.substring(0,  loc++);
                	textB = text.substring(loc, text.length());
                }
                if(twoLine == true) {
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
