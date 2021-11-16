package entity;

import java.io.Serializable;

public class Choice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -812322956597375815L;
	private String content;
	private Boolean isAnswer;
	private Integer questionId;
	private Question question;
	
	public Choice() {
	}
	
	public Choice(String content, Boolean isAnswer, Integer questionId) {
		super();
		this.content = content;
		this.isAnswer = isAnswer;
		this.questionId = questionId;
	}

	public Choice(String content, Boolean isAnswer, Integer questionId, Question question) {
		super();
		this.content = content;
		this.isAnswer = isAnswer;
		this.questionId = questionId;
		this.question = question;
	}

	public Choice(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Boolean getIsAnswer() {
		return isAnswer;
	}

	public void setIsAnswer(Boolean isAnswer) {
		this.isAnswer = isAnswer;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "Choice [content=" + content + ", isAnswer=" + isAnswer + ", questionId=" + questionId + ", question="
				+ question + "]";
	}
	
}
