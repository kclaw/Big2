package IO;

import java.util.Objects;

import IO.impl.QuestionImpl;

public abstract class Question {
	
	protected int id;
	protected String question;
	
	public static Question of(int id, String question) {
		return new QuestionImpl(id, question);
	}
	
	public Question(int id, String question) {
		this.id = id;
		this.question = question;
		this.echo();
	}
	
	protected abstract void echo();
	
	public int id() {
		return this.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		return id == other.id;
	};
}
