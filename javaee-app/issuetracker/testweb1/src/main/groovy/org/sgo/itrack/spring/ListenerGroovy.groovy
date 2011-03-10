package org.sgo.itrack.spring

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sgodden.issuetracker.domain.Issue;
import org.sgodden.issuetracker.domain.IssueRepository;

/**
 * Creates some issues using groovy's far easier than
 * java syntax.
 * @author Simon
 */
class ListenerGroovy {

	def createIssues(IssueRepository rep) {
		if (rep.count() == 0) {
			rep.persist([
						new Issue([
							issueNumber: "I001",
							summary: "Issue number 1"
						]),
						new Issue([
							issueNumber: "I002",
							summary: "Issue number 2"
						]),
						new Issue([
							issueNumber: "I003",
							summary: "Issue number 3"
						])
					] as Set<Issue>);
		}
	}
}
