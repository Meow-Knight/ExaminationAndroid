package entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class History implements Serializable {

	private static final long serialVersionUID = 5943336356469956079L;

	private Long id;
	private Integer correctAnswerAmount;
	private Integer totalQuestionAmount;
	private String accountId;
	private Long examinationId;
	private Timestamp createdAt;
	
	private Account account;
	private Examination examination;
	
	public History() {
	}

	public History(Long id, Integer correctAnswerAmount, Integer totalQuestionAmount, String accountId, Long examinationId, Timestamp createdAt) {
		super();
		this.id = id;
		this.correctAnswerAmount = correctAnswerAmount;
		this.totalQuestionAmount = totalQuestionAmount;
		this.accountId = accountId;
		this.examinationId = examinationId;
		this.createdAt = createdAt;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCorrectAnswerAmount() {
		return correctAnswerAmount;
	}

	public void setCorrectAnswerAmount(Integer correctAnswerAmount) {
		this.correctAnswerAmount = correctAnswerAmount;
	}

	public Integer getTotalQuestionAmount() {
		return totalQuestionAmount;
	}

	public void setTotalQuestionAmount(Integer totalQuestionAmount) {
		this.totalQuestionAmount = totalQuestionAmount;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Long getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(Long examinationId) {
		this.examinationId = examinationId;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", correctAnswerAmount=" + correctAnswerAmount + ", totalQuestionAmount="
				+ totalQuestionAmount + ", accountId=" + accountId + ", examinationId=" + examinationId + ", createdAt="
				+ createdAt + "]";
	}

}
