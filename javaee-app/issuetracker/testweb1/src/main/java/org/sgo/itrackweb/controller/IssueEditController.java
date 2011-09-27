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
public class IssueEditController {
	
	@Autowired
	private IssueService service;

    @RequestMapping("/app/editissue/{issueNumber}")
    public ModelAndView getIssue(@PathVariable String issueNumber) {
    	return new ModelAndView("viewissue", "issue", service.findByIssueNumber(issueNumber));
    }
    
    

}
