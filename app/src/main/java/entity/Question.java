package entity;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 531721818237891476L;
	private Long id;
	private String content;
	private Long examinationId;
	private Examination examination;
	private List<Choice> choices;
	
	public Question() {
	}
	
	public Question(Long id, String content, Long examinationId) {
		super();
		this.id = id;
		this.content = content;
		this.examinationId = examinationId;
	}

	public Question(Long id, String content, Long examinationId, Examination examination) {
		super();
		this.id = id;
		this.content = content;
		this.examinationId = examinationId;
		this.examination = examination;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Long getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(Long examinationId) {
		this.examinationId = examinationId;
	}
	
	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", content=" + content + ", examinationId=" + examinationId + ", examination="
				+ examination + "]";
	}

}
