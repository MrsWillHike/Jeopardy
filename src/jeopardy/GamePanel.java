package jeopardy;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	Graphics myGraphics;
	GamePanel() {
	}
	
	public void setGraphics(Graphics g) {
		myGraphics = g;
		paintComponent(getGraphics());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g = myGraphics;
	}
}
