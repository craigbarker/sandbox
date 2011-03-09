package org.sgo.itrack.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sgodden.issuetracker.domain.Issue;
import org.sgodden.issuetracker.domain.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationListenerImpl implements
		ApplicationListener<ApplicationEvent> {

	private static final Log LOG = LogFactory
			.getLog(ApplicationListenerImpl.class);

	@Autowired
	private IssueRepository issueRepository;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent) {
			LOG.info("Context started!");
			createIssues();
		}
	}

	private void createIssues() {
		new ListenerGroovy().createIssues(issueRepository);
	}

}
