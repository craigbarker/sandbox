package org.sgodden.itrack.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.sgodden.issuetracker.domain.Issue;
import org.sgodden.issuetracker.domain.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of {@link IssueListService}.
 * @author Simon
 */
@Service
public class IssueListServiceImpl implements IssueListService {

	@Autowired
	private IssueRepository repository;
	@Autowired
	private DozerBeanMapper mapper;
	
	public IssueListServiceImpl() {
		super();
	}
	
	/**
	 * Constructor for unit testing.
	 * @param repository the repository to use.
	 * @param mapper the mapper to use.
	 */
	public IssueListServiceImpl(
			IssueRepository repository,
			DozerBeanMapper mapper) {
		this();
		this.repository = repository;
		this.mapper = mapper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	public List<IssueVO> listIssuesByIssueIdDescending() {
		List<Issue> issues = repository.findAll();
		List<IssueVO> ret = new ArrayList<IssueVO>();
		for (Issue issue : issues) {
			ret.add(mapper.map(issue, IssueVO.class));
		}
		return ret;
	}

}
