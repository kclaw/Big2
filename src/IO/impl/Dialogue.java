package IO.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import IO.Answer;
import IO.Question;

public class Dialogue {
	static Map<Question, Answer> questionAnswerMap;
	static { 
		questionAnswerMap = new HashMap<>();
	}
	
	public static Answer ask(Question question, Retry retry) {
		Scanner sc = new Scanner(System.in);
		System.out.print("A==>");
		Answer answer = null;
		
		if(questionAnswerMap.containsKey(question)) {
			answer = questionAnswerMap.get(question);
			answer.value(sc.next());
		} else {
			if (question.id()==1) {
				answer = new AnswerImpl(question.id(), sc.next());
				questionAnswerMap.put(question, answer);
			}
		}
	
		for (int i=1;i<retry.times();i++) {
			if (answer.accept()) 
				break;
			else 
				answer.value(sc.next());
		}
		return answer;
	}

}
