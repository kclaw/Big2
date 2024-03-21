package IO.impl;

import IO.Question;

public class QuestionImpl extends Question {

	public QuestionImpl(int id, String question) {
		super(id, question);
	}

	@Override
	protected void echo() {
		System.out.println("Q==>"+ this.question);
	}

}
