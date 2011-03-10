package org.sgodden.itrack.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.sgodden.issuetracker.domain.Issue;
import org.sgodden.issuetracker.domain.IssueRepository;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static org.easymock.EasyMock.*;

/**
 * This test is broken.
 * @author Simon
 */
@Test
public class IssueListServiceTest {

	private IssueListService cut;
	private IssueRepository repository;
	private DozerBeanMapper mapper = new DozerBeanMapper();

	@BeforeTest
	public void setUp() {
		repository = createMock(IssueRepository.class);
		cut = new IssueListServiceImpl(repository, mapper);
	}

	public void testListIssues() {
		Long[] issueIds = new Long[]{1l, 2l, 3l};
		//Long[] issueIds = new Long[]{3l, 2l, 1l};
		List<Issue> issues = new ArrayList<Issue>();
		for (Long issueId : issueIds) {
			Issue issue = new Issue();
			issue.setId(issueId);
		}
		expect(repository.findAll())
			.andReturn(issues);
		replay(repository);
		List<IssueVO> issueVOs = cut.listIssuesByIssueIdDescending();
		// should arrive back in the reverse sequence
		for (int i = 0; i < issues.size(); i++) {
			assertEquals(issueVOs.get(i).getId(), issueIds[issueIds.length - i]);
		}
		//fail("broken");
	}
}
