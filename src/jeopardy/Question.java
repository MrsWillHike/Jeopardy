package jeopardy;

public class Question {

	private String question;
	private String answer;
	private int points;
	boolean isUsed;
	
	public Question(String q, String a, int p) {
		question = q;
		answer = a;
		points = p;
		
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public int getScore() {
		return points;
	}
	
	public boolean getIsUsed() {
		return isUsed;
	}
	
	public void setIsUsed(boolean u) {
		isUsed = u;
	}
}
