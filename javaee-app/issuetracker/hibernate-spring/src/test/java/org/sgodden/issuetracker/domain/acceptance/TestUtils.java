package org.sgodden.issuetracker.domain.acceptance;

import org.sgodden.issuetracker.domain.Issue;
import org.sgodden.issuetracker.domain.IssueRepository;

/**
 * Provides utility methods to test classes.
 * 
 * @author Simon Godden
 */
public class TestUtils {

    /**
     * Removes all {@link Issue} instances from the passed
     * repository.
     * 
     * @param rep the repository.
     */
    public static void removeAllIssues(IssueRepository rep) {
        for (Issue order : rep.findAll()) {
            rep.remove(order);
        }
    }
}
