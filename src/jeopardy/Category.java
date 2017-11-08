package jeopardy;

import java.util.ArrayList;
import java.util.List;

public class Category {
	private String name;
	private List<Question> questions = new ArrayList<Question>();
	
	public Category(String n) {
		name = n;
	}
	
	public void add(Question q) {
		questions.add(q);
	}
	
	public Question getQuestion(int i) {
		return questions.get(i);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	@Override
	public String toString() {
		String out = "[\"";
		boolean f = false;
		for(int i = 0; i < questions.size(); i++) {
			if(f) {
				out = out + "> ";
			} else {
				f = true;
			}
			out = out + questions.get(i).toString();
		}
		out = out + "]";
		return out;
	}
}
