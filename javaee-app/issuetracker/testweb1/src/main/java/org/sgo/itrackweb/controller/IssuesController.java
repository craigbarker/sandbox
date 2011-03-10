package org.sgo.itrackweb.controller;

import org.sgodden.issuetracker.domain.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for listing issues.
 * @author Simon
 */
@Controller
public class IssuesController {
	
	@Autowired
	private IssueRepository rep;

    @RequestMapping("/issues")
    public ModelAndView listIssues() {
        return new ModelAndView("issues", "issues", rep.findAll());
    }

}
