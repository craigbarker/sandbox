package org.sgodden.itrack.service;

/**
 * Provides services related to the Issue entity.
 * @author Simon
 */
public interface IssueService {
	
	/**
	 * Finds an issue by its issue number.
	 * @param issueNumber the issue number.
	 * @return the issue.
	 */
	public IssueVO findByIssueNumber(String issueNumber);

}
