package org.sgodden.itrack.service;

/**
 * Value object for issue.
 * @author Simon
 */
public class IssueVO {
	
	private Long id;
	private String issueNumber;
	private String summary;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

}
