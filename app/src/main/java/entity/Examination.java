package entity;

import java.io.Serializable;
import java.sql.Date;

public class Examination implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8638093418038654237L;

	// core field
	private Date startTime;
	private Date endTime;
	private String title;
	private String description;
	private Integer duration;
	private String createdByAccountID;
	
	// extension
	private String createdByAccountName;
	private Account createdBy;
	private Integer questionAmount;
	
	public Examination() {
	}

	public Examination(Date startTime, Date endTime, String title, String description, Integer duration,
			String createdByAccountID) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.createdByAccountID = createdByAccountID;
	}

	public Examination(Date startTime, Date endTime, String title, String description, Account createdBy,
			Integer duration, String createdByAccountID) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
		this.description = description;
		this.createdBy = createdBy;
		this.duration = duration;
		this.createdByAccountID = createdByAccountID;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Account createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public String getCreatedByAccountID() {
		return createdByAccountID;
	}

	public void setCreatedByAccountID(String createdByAccountID) {
		this.createdByAccountID = createdByAccountID;
	}
	
	public String getCreatedByAccountName() {
		return createdByAccountName;
	}

	public void setCreatedByAccountName(String createdByAccountName) {
		this.createdByAccountName = createdByAccountName;
	}
	
	public Integer getQuestionAmount() {
		return questionAmount;
	}

	public void setQuestionAmount(Integer questionAmount) {
		this.questionAmount = questionAmount;
	}

	@Override
	public String toString() {
		return "Examination [startTime=" + startTime + ", endTime=" + endTime + ", title=" + title + ", description="
				+ description + ", createdBy=" + createdBy + ", duration=" + duration + ", createdByAccountID="
				+ createdByAccountID + "]";
	}
}
