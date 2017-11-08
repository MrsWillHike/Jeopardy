package jeopardy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	
	public void parse(File f) {
		try {
			String line = "";
			int l = 0;
			String q = "";
			String a = "";
			Scanner s = new Scanner(f);
			name = s.nextLine();
			while(s.hasNextLine()) {
				line = s.nextLine();
				l = 1;
				q = "";
				a = "";
				while(line.charAt(l) != '"') {
					q = q + line.charAt(l);
					l++;
				}
				l++;
				while(line.charAt(l) != '"') {
					l++;
				}
				l++;
				while(line.charAt(l) != '"') {
					a = a + line.charAt(l);
					l++;
				}
				add(new Question(q, a));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
