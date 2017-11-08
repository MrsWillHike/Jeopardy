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
}
