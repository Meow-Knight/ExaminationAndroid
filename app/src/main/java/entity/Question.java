package entity;

import java.io.Serializable;

public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 531721818237891476L;
	private String content;
	private Integer examinationId;
	private Examination examination;
	
	public Question() {
	}
	
	public Question(String content, Integer examinationId) {
		super();
		this.content = content;
		this.examinationId = examinationId;
	}

	public Question(String content, Integer examinationId, Examination examination) {
		super();
		this.content = content;
		this.examinationId = examinationId;
		this.examination = examination;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}
	
	public Integer getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(Integer examinationId) {
		this.examinationId = examinationId;
	}

	@Override
	public String toString() {
		return "Question [content=" + content + ", examinationId=" + examinationId + ", examination=" + examination
				+ "]";
	}
	
}
