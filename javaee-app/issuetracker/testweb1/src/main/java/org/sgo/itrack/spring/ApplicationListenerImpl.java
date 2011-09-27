package org.sgo.itrack.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sgodden.issuetracker.domain.Issue;
import org.sgodden.issuetracker.domain.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.TemplateModelException;

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
			WebApplicationContext wac = (WebApplicationContext) ((ContextRefreshedEvent) event)
			.getApplicationContext();
			
			FreeMarkerConfigurer fmc = wac
					.getBean(FreeMarkerConfigurer.class);
			try {
				fmc.getConfiguration().setSharedVariable("contextPath",	"/issueweb");
			} catch (TemplateModelException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void createIssues() {
		if (issueRepository.count() == 0) {
			issueRepository.persist(createIssue("I001", "Issue 1"));
			issueRepository.persist(createIssue("I002", "Issue 2"));
		}
	}
	
	private Issue createIssue(String issueNumber, String summary) {
		Issue issue = new Issue();
		issue.setIssueNumber(issueNumber);
		issue.setSummary(issueNumber);
		return issue;
	}

}
