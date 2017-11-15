package jeopardy;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

public class KeyListen {
	public static boolean isDone;
	
	private static int catSel = -1;
	private static int ptSel = -1;
	private static int npt = -1;
	private static int nct = -1;
	private static int team = -1;
	private static int num = 0;
	private static int numMult = 1;
	private static int wrong = 0;
	private static int mayLeave = 0;
	private static boolean ch = false;
	private static boolean canAns = false;
	private static boolean teamPrinted = false;
	private static boolean hasSelCat = false;
	private static String ques = null;
	private static Team teamSel = null;
	private static Team teamAns = null;
	private static Team teamMod = null;
	private static Team teamRed = new Team(Color.RED, "Red");
	private static Team teamYellow = new Team(Color.YELLOW, "Yellow");
	private static Team teamGreen = new Team(Color.GREEN, "Green");
	private static Team teamBlue = new Team(Color.CYAN, "Blue");
	private static Mode mode = Mode.RUN;
	private static List<Category> cat;
	private static Robot robot;
	private static int round = 1;
	
	public static void init() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		GamePanel.setTeams(teamRed, teamYellow, teamGreen, teamBlue);
	}
	
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
		
		// Number Pad Input Test
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
		case KeyEvent.VK_SUBTRACT: numMult = numMult * -1; ch = true; break;
		
		// cancel question selection
		case KeyEvent.VK_ESCAPE: {GamePanel.drawMainPanel(cat);exit();} break;
		
		// Happens when a team buzzes in
		case KeyEvent.VK_F9: {teamSel = teamRed;} break;
		case KeyEvent.VK_F10: {teamSel = teamYellow;} break;
		case KeyEvent.VK_F11: {teamSel = teamGreen;} break;
		case KeyEvent.VK_F12: {teamSel = teamBlue;} break;
		
		// Handle correct or wrong answer
		case KeyEvent.VK_BACK_SPACE: { // correct
			if(teamAns != null) {
				GamePanel.displayText(cat.get(nct).getQuestion(npt).getAnswer());
				teamAns.addScore(cat.get(nct).getQuestion(npt).getScore());
				mayLeave = 1;
			}
		} break;
		case KeyEvent.VK_BACK_SLASH: { // wrong
			if(teamAns != null && canAns == false) {
				GamePanel.displayText(ques);
				canAns = true;
				teamAns.setGuessed(true);
				teamAns.addScore(cat.get(nct).getQuestion(npt).getScore() * -1);
				wrong++;
				System.out.println(teamAns.getName() + " got it wrong " + wrong);
			}
		} break;
		
		// select team
		case KeyEvent.VK_F1: {teamMod = teamRed;} break;
		case KeyEvent.VK_F2: {teamMod = teamYellow;} break;
		case KeyEvent.VK_F3: {teamMod = teamGreen;} break;
		case KeyEvent.VK_F4: {teamMod = teamBlue;} break;
		
		case KeyEvent.VK_I: {
			mode = Mode.SCORE_SET;
			num = 0;
			numMult = 1;
		} break;
		case KeyEvent.VK_K: {
			mode = Mode.SCORE_ADD;
			numMult = 1;
			num = 0;
		} break;
		case KeyEvent.VK_ENTER: {
			if(e.getKeyLocation() == 4) {
				switch(mode) {
				case RUN: {} break;
				case SCORE_ADD: teamMod.addScore(num * numMult); break;
				case SCORE_SET: teamMod.setScore(num * numMult); break;
				default: {} break;
				}
				GamePanel.drawMainPanel(cat);
			} else if(e.getKeyLocation() == 1) {
				if(mayLeave == 1) {
					GamePanel.drawMainPanel(cat);
					exit();
					mayLeave = 0;
				}
			}
		} break;
		case KeyEvent.VK_B: {
			moveOn();
		} break;
		} // end of key listeners
		
		// TODO figure out how to do this properly
		// Bodge to prevent f10 from creating errors
		// Probibly need will be eliminated when i use F13-16
		// Presses and releases F10 to undo whatever F10 does
		try {
			if(e.getKeyCode() == KeyEvent.VK_F10) {
				robot.keyPress(KeyEvent.VK_F10);
				robot.keyRelease(KeyEvent.VK_F10);
			}
		} catch(NullPointerException ex) {
			System.err.println("NullPointerException at KeyListen.java:144,145");
		}
		doer();
	}
	
	private static void doer() {
		// Begin doers
		
		// Handle teams buzzing in
		if(teamSel != null && canAns == true && teamSel.hasGuessed() == false) {
			GamePanel.displayText(ques, teamSel.getColor());
			canAns = false;
			teamAns = teamSel;
			teamSel = null;
		}
		
		
		// End if everyone has guessed wrong
		if(wrong >= 4) {
			GamePanel.displayText(cat.get(nct).getQuestion(npt).getAnswer());
			mayLeave = 1;
			exit();
		}
		
		// Print number to console
		if(ch == true) {
			ch = false;
			System.out.println(num * numMult);
		}
		ch = false;
		
		// display question screen
		if(catSel != -1 && ptSel != -1 && hasSelCat == false) {
			if(cat.get(catSel).getQuestion(ptSel).isUsed() == false) {
				ques = cat.get(catSel).getQuestion(ptSel).getQuestion();
				GamePanel.displayText(ques);
				cat.get(catSel).getQuestion(ptSel).setIsUsed(true);
				npt = ptSel;
				nct = catSel;
				catSel = -1;
				canAns = true;
				hasSelCat = true;
			}
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
	
	private static void exit() {
		npt = -1;
		nct = -1;
		ptSel = -1;
		catSel = -1;
		teamRed.setGuessed(false);
		teamYellow.setGuessed(false);
		teamGreen.setGuessed(false);
		teamBlue.setGuessed(false);
		teamAns = null;
		ques = null;
		wrong = 0;
		canAns = false;
		isDone = true;
		for(int i = 0; i < cat.size(); i++) {
			if(cat.get(i).isDone() == false) {
				isDone = false;
				break;
			}
		}
		if(isDone == true) {
			moveOn();
		}
		hasSelCat = false;
	}
	
	private static void moveOn() {
		switch(round) {
			case 1: {
				Main.beginRoundTwo();
				round = 2;
			} break;
			case 2: {
				Main.beginRoundThree();
				round = 3;
				System.out.println("Round 3");
			} break;
			}
	}
	
	public static void buzzRed() { teamSel = teamRed; doer(); }
	public static void buzzYellow() { teamSel = teamYellow; doer(); }
	public static void buzzGreen() { teamSel = teamGreen; doer(); }
	public static void buzzBlue() { teamSel = teamBlue; doer(); }
}
