package org.sgo.itrackweb.controller;

import org.sgodden.itrack.service.IssueListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for listing issues.
 * @author Simon
 */
@Controller
public class IssueListController {
	
	@Autowired
	private IssueListService service;

    @RequestMapping("/issues")
    public ModelAndView listIssues() {
        return new ModelAndView("issues", "issues", service.listIssuesByIssueIdDescending());
    }

}
