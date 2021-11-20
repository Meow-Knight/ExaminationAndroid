package entity;

import java.io.Serializable;

public class Choice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -812322956597375815L;
	private Long id;
	private String content;
	private Boolean isAnswer;
	private Long questionId;
	private Question question;
	
	public Choice() {
	}
	
	public Choice(Long id, String content, Boolean isAnswer, Long questionId) {
		super();
		this.id = id;
		this.content = content;
		this.isAnswer = isAnswer;
		this.questionId = questionId;
	}

	public Choice(Long id, String content, Boolean isAnswer, Long questionId, Question question) {
		super();
		this.id = id;
		this.content = content;
		this.isAnswer = isAnswer;
		this.questionId = questionId;
		this.question = question;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "Choice [id=" + id + ", content=" + content + ", isAnswer=" + isAnswer + ", questionId=" + questionId
				+ ", question=" + question + "]";
	}

}
