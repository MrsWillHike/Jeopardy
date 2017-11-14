package jeopardy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MenuBar;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Main{
	
	public static void main(String args[]) {
		List<Category> roundOne = new ArrayList<Category>();
		KeyListen.setQuestions(roundOne);
		KeyListen.init();
		
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
		
		GamePanel.setGamePanel(jf);
		
		
		//GamePanel.displayText("Jeopardy");
		//long del = System.currentTimeMillis() + 5000; while(del > System.currentTimeMillis()) {}
		
		GamePanel.drawMainPanel(roundOne);
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//GamePanel.displayText(roundOne.get(1).getQuestion(1).getQuestion());
	}
}
