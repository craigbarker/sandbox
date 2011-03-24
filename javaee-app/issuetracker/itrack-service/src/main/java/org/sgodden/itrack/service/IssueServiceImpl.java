package org.sgodden.itrack.service;

import org.dozer.DozerBeanMapper;
import org.sgodden.issuetracker.domain.Issue;
import org.sgodden.issuetracker.domain.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueServiceImpl implements IssueService {

	@Autowired
	private IssueRepository repository;
	@Autowired
	private DozerBeanMapper mapper;
	
	public IssueVO findByIssueNumber(String issueId) {
		Issue issue = repository.findByIssueNumber(issueId);
		return mapper.map(issue, IssueVO.class);
	}

}
