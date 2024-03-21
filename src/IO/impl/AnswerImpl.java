package IO.impl;

import IO.Answer;

public class AnswerImpl extends Answer{
	


	public AnswerImpl(int id, String answer) {
		super(id, answer);
	}

	public boolean accept() {
		return answer.equals("p") || answer.equals("d");
	}
}
