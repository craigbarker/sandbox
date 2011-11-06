package org.sgodden.tom.persistence.listener;

import org.sgodden.tom.model.ValidationException;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.Set;
import java.util.logging.Logger;

public class ValidatorEntityListener {

    private static final Logger LOG = Logger.getLogger(ValidatorEntityListener.class.getName());

	private static Validator validator = Validation
			.buildDefaultValidatorFactory().getValidator();

	@PrePersist
	@PreUpdate
	public void validate(Serializable entity) {
		Set<ConstraintViolation<Serializable>> constraints = validator.validate(entity);
		if (constraints.size() > 0) {
//            if (LOG.isDebugEnabled()) {  TODO - logging config
                for (ConstraintViolation<Serializable> violation : constraints) {
                    LOG.severe(violation.toString());
                }
//            }
			throw new ValidationException(constraints);
		}
	}

}
