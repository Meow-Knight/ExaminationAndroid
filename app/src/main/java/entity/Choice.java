package entity;

import java.io.Serializable;

public class Choice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -812322956597375815L;
	private String content;
	private Integer questionId;
	
	public Choice() {
	}

	public Choice(String content, Integer questionId) {
		this.content = content;
		this.questionId = questionId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "Choice [content=" + content + ", questionId=" + questionId + "]";
	}
}
