package jeopardy;

import java.awt.Color;

public class Team {
	private int score;
	private boolean didGuess;
	private Color color;
	private static int count;
	private int num;
	
	public Team() {
		score = 0;
		didGuess = false;
		color = Color.BLUE;
		num = ++count;
	}
	
	public Team(Color c) {
		score = 0;
		didGuess = false;
		color = c;
		num = ++count;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int s) {
		score = s;
	}
	
	public void addScore(int s) {
		score += s;
	}
	
	public boolean hasGuessed() {
		return didGuess;
	}
	
	public void setGuessed(boolean g) {
		didGuess = g;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getNum() {
		return num;
	}
}
