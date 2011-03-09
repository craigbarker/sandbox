package org.sgodden.issuetracker.domain;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import org.sgodden.issuetracker.domain.acceptance.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import static org.testng.Assert.assertEquals;

/**
 * A basic stand-up test to see that persistence is working.
 * @author sgodden
 */
@Test(groups = "integration")
@ContextConfiguration(locations="/org/sgodden/issuetracker/domain/beans.xml")
public class IssueRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private IssueRepository rep;

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    public void testValidation() {
        Issue order = new Issue();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Issue>> violations = validator.validate(order);
        assertEquals(violations.size(), 2, "Wrong number of violations");
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testValidationException() {
        Issue order = new Issue();
        rep.persist(order);
    }

    public void testPersistIssue() {
        Issue order = new Issue();
        order.setSummary("ORD1");
        order.setIssueNumber("REF1");

        rep.persist(order);
        assertEquals(rep.count(), 1, "Wrong number of customer orders");
        TestUtils.removeAllIssues(rep);
        assertEquals(0, rep.count());
    }

}