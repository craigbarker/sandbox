package org.sgodden.tom.model;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class CustomerOrderTest {

    public void testValidation() {
        CustomerOrder order = new CustomerOrder();
        Set<ConstraintViolation<CustomerOrder>> violations = getValidator().validate(order);
        assertEquals(violations.size(), 2, "Wrong number of violations");
    }
    
    private Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
    
}
