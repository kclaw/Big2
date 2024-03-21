package IO;

import java.util.Objects;

public abstract class Answer {

	protected int id;
	protected String answer;
	
	public Answer(int id, String answer) {
		this.answer = answer;
		this.id = id;
	}
	
	public abstract boolean accept();
	
	public void value(String value) {
		this.answer = value;
	}
	
	
	public String value() {
		return this.answer;
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
		Answer other = (Answer) obj;
		return id == other.id;
	}
}
