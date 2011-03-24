package org.sgodden.itrack.service;

import java.util.List;

import org.sgodden.issuetracker.domain.Issue;

/**
 * Provides service methods related to listing of {@link Issue}s.
 * @author Simon
 */
public interface IssueListService {
	
	/**
	 * Returns all issues by issue id descending.
	 * @return all issues.
	 */
	public List<IssueVO> listIssuesByIssueIdDescending();

}
