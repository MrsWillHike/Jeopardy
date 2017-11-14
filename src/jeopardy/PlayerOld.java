package jeopardy;

public class PlayerOld {
	
	private int score;
	private String name;
	
	public PlayerOld(String n) {
		name = n;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}
	
	public void setScore(int s) {
		score = s;
	}
	
	public void addPoints(int p) {
		score = score + p;
	}
}
