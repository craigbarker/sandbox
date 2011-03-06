package org.sgodden.issuetracker.domain;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Tests for the Issue class.
 * @author g106ahe
 */
@Test(groups="unit")
public class IssueTest {

    /**
     * Simple test of getters and setters.
     */
    public void testGettersAndSetters() {
        Issue instance = new Issue();

        assertNull(instance.getId());

        instance.setCustomerReference("C");
        assertEquals("C", instance.getCustomerReference());

        instance.setOrderNumber("O");
        assertEquals("O", instance.getOrderNumber());
    }

}