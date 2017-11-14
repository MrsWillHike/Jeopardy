package jeopardy;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

public class KeyListen {
	
	private static int catSel = -1;
	private static int ptSel = -1;
	private static int npt = -1;
	private static int nct = -1;
	private static List<Category> cat;
	private static int num = 0;
	private static int dig = 1;
	private static boolean ch = false;
	private static int team = -1;
	private static boolean teamPrinted = false;
	private static String ques = null;
	private static Team teamSel = null;
	private static Team teamAns = null;
	private static boolean canAns = false;
	
	private static Team teamRed = new Team(Color.RED);
	private static Team teamYellow = new Team(Color.YELLOW);
	private static Team teamGreen = new Team(Color.GREEN);
	private static Team teamBlue = new Team(Color.CYAN);
	
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
		
		// cancel question selection
		case KeyEvent.VK_ESCAPE: {GamePanel.drawMainPanel(cat);npt = -1;nct = -1;ques = null;} break;
		
		// Handle right or wrong
		// should be redone
		case KeyEvent.VK_BACK_SPACE: { // correct
			GamePanel.drawMainPanel(cat);
			npt = -1;
			nct = -1;
			System.out.println("correct");
		} break;
		case KeyEvent.VK_BACK_SLASH: { // wrong
			if(nct != -1 && npt != -1) {
				GamePanel.displayText(ques);
				canAns = true;
				teamSel.setGuessed(true);
				System.out.println("wrong");
			}
		} break;
		
		// select team
		case KeyEvent.VK_F1: {team = 0; teamPrinted = true;} break;
		case KeyEvent.VK_F2: {team = 1; teamPrinted = true;} break;
		case KeyEvent.VK_F3: {team = 2; teamPrinted = true;} break;
		case KeyEvent.VK_F4: {team = 3; teamPrinted = true;} break;
		
		// Happens when a team buzzes in
		case KeyEvent.VK_F9: {teamSel = teamRed;} break;
		case KeyEvent.VK_F10: {teamSel = teamYellow;} break;
		case KeyEvent.VK_F11: {teamSel = teamGreen;} break;
		case KeyEvent.VK_F12: {teamSel = teamBlue;} break;
		
		} // end of key listeners

		// Begin doers
		
		// Handle teams buzzing in
		if(teamSel != null && canAns == true && teamSel.hasGuessed() == false) {
			GamePanel.displayText(ques, teamSel.getColor());
			canAns = false;
			teamAns = teamSel;
		}
		teamSel = null;
		
		// Print number to console
		if(ch == true) {
			ch = false;
			System.out.println(num);
		}
		ch = false;
		
		// display correct screen
		if(catSel != -1 && ptSel != -1 && canAns == false) {
			ques = cat.get(catSel).getQuestion(ptSel).getQuestion();
			GamePanel.displayText(ques);
			cat.get(catSel).getQuestion(ptSel).setIsUsed(true);
			npt = ptSel;
			nct = catSel;
			catSel = -1;
			canAns = true;
		}
		ptSel = -1;
		
		// Print selected team to console
		if(teamPrinted == true) {
			System.out.println("team " + team);
			teamPrinted = false;
		}
	}
	
	public static void setQuestions(List<Category> c) {
		cat = c;
	}
}
