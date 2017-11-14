package jeopardy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

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
		GamePanel.setGamePanel(jf);
		jf.setTitle("Title");
		jf.setSize(1280, 720);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.addKeyListener(new KeyListener() {
			@Override public void keyTyped(KeyEvent e) {KeyListen.keyTyped(e);}
			@Override public void keyReleased(KeyEvent e) {KeyListen.keyReleased(e);}
			@Override public void keyPressed(KeyEvent e) {KeyListen.keyPressed(e);}
		});
		GamePanel.drawMainPanel(roundOne);		
	}
	
	public static void beginRoundTwo() {
		List<Category> roundTwo = new ArrayList<Category>();
		String namesTwo[] = {"fire", "water", "knives", "cooking", "firstaid", "scoutstuff"};
		for(int i = 0; i < namesTwo.length; i++) {
			Category temp = new Category("");
			temp.parse(new File("catagories/" + namesTwo[i] + ".jep"), 400);
			roundTwo.add(temp);
		}
		System.out.println(roundTwo.toString());
		GamePanel.drawMainPanel(roundTwo);
		KeyListen.isDone = false;
		KeyListen.setQuestions(roundTwo);
	}
}
