package entity;

import java.io.Serializable;

public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 531721818237891476L;
	private String content;
	private Integer answer;
	private String createdBy;
	
	public Question() {
	}

	public Question(String content, Integer answer, String createdBy) {
		this.content = content;
		this.answer = answer;
		this.createdBy = createdBy;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getAnswer() {
		return answer;
	}

	public void setAnswer(Integer answer) {
		this.answer = answer;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "Question [content=" + content + ", answer=" + answer + ", createdBy=" + createdBy + "]";
	}

}
