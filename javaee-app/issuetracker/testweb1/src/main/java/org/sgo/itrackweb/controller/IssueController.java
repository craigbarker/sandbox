package org.sgo.itrackweb.controller;

import org.sgodden.itrack.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for listing issues.
 * @author Simon
 */
@Controller
@RequestMapping("/issue")
public class IssueController {
	
	@Autowired
	private IssueService service;

    @RequestMapping("/{issueNumber}")
    public ModelAndView getIssue(@PathVariable String issueNumber) {
    	return new ModelAndView("issue", "issue", service.findByIssueNumber(issueNumber));
    }

}
