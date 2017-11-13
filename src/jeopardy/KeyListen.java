package jeopardy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JTextField;

public class KeyListen {
	
	static private int catSel = -1;
	static private int ptSel = -1;
	static private int npt = -1;
	private static int nct = -1;
	private static List<Category> cat;
	private static int num = 0;
	private static int dig = 1;
	private static boolean ch = false;
	
	public static void keyTyped(KeyEvent e) {}
	
	public static void keyReleased(KeyEvent e) {}
	
	public static void keyPressed(KeyEvent e) {
		
		
		switch(e.getKeyCode()) {
		// Select category
		case KeyEvent.VK_Q: catSel = 0; break;
		case KeyEvent.VK_W: catSel = 1; break;
		case KeyEvent.VK_E: catSel = 2; break;
		case KeyEvent.VK_R: catSel = 3; break;
		case KeyEvent.VK_T: catSel = 4; break;
		case KeyEvent.VK_Y: catSel = 5; break;

		// Select points
		case KeyEvent.VK_A: ptSel = 0; break;
		case KeyEvent.VK_S: ptSel = 1; break;
		case KeyEvent.VK_D: ptSel = 2; break;
		case KeyEvent.VK_F: ptSel = 3; break;
		case KeyEvent.VK_G: ptSel = 4; break;
		
		// Number Pad Imput Test
		case KeyEvent.VK_NUMPAD0: num = (num * 10) + 0; ch = true; break;
		case KeyEvent.VK_NUMPAD1: num = (num * 10) + 1; ch = true; break;
		case KeyEvent.VK_NUMPAD2: num = (num * 10) + 2; ch = true; break;
		case KeyEvent.VK_NUMPAD3: num = (num * 10) + 3; ch = true; break;
		case KeyEvent.VK_NUMPAD4: num = (num * 10) + 4; ch = true; break;
		case KeyEvent.VK_NUMPAD5: num = (num * 10) + 5; ch = true; break;
		case KeyEvent.VK_NUMPAD6: num = (num * 10) + 6; ch = true; break;
		case KeyEvent.VK_NUMPAD7: num = (num * 10) + 7; ch = true; break;
		case KeyEvent.VK_NUMPAD8: num = (num * 10) + 8; ch = true; break;
		case KeyEvent.VK_NUMPAD9: num = (num * 10) + 9; ch = true; break;
		
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
		
		if(ch == true) {
			ch = false;
			System.out.println(num);
		}
		ch = false;
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
