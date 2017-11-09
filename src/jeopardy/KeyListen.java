package jeopardy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JTextField;

public class KeyListen {
	
	static private int catSel = -1;
	static private int ptSel = -1;
	static private int npt = -1;
	static private int nct = -1;
	private static List<Category> cat;
	
	public static void keyTyped(KeyEvent e) {}
	
	public static void keyReleased(KeyEvent e) {}
	
	public static void keyPressed(KeyEvent e) {
		int num = 0;
		int dig = 0;
		
		switch(e.getKeyCode()) {
		// Select category
		case KeyEvent.VK_Q: catSel = 0; break;
		case 'w': catSel = 1; break;
		case 'e': catSel = 2; break;
		case 'r': catSel = 3; break;
		case 't': catSel = 4; break;
		case 'y': catSel = 5; break;

		// Select points
		case KeyEvent.VK_A: ptSel = 0; break;
		case 's': ptSel = 1; break;
		case 'd': ptSel = 2; break;
		case 'f': ptSel = 3; break;
		case 'g': ptSel = 4; break;
		
		// Number Pad Imput Test
		case KeyEvent.VK_NUMPAD0: dig = dig * 10; break;
		case KeyEvent.VK_NUMPAD1: dig = dig * 10; break;
		// Handle right or wrong
		case KeyEvent.VK_ESCAPE: {
			GamePanel.drawMainPanel(cat);
			npt = -1;
			nct = -1;
		} break;
		case KeyEvent.VK_ENTER: {
			GamePanel.drawMainPanel(cat);
			npt = -1;
			nct = -1;
		} break;
		case KeyEvent.VK_BACK_SPACE: {
			if(nct != -1 && npt != -1) {
				GamePanel.displayText(cat.get(nct).getQuestion(npt).getAnswer());
				npt = -1;
				nct = -1;
				
			}
		}
		}
		
		// display correct screen
		if(catSel != -1 && ptSel != -1) {
			GamePanel.displayText(cat.get(catSel).getQuestion(ptSel).getQuestion());
			npt = ptSel;
			nct = catSel;
			catSel = -1;
		}
		ptSel = -1;
	}
	
	public static void setQuestions(List<Category> c) {
		cat = c;
	}
}
