package org.sgodden

import grails.test.*
import org.joda.time.*

class CustomerOrderTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraints() {
        CustomerOrder order = new CustomerOrder(orderNumber: "ASDASD")
        mockForConstraintsTests(CustomerOrder, [ order ])
        assertFalse order.validate()

        order.bookingDate = new LocalDate()
        println order.bookingDate
        assertTrue order.validate()
    }
}
