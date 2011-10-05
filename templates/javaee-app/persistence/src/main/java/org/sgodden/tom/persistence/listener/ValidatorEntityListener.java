package org.sgodden.tom.persistence.listener;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.sgodden.tom.model.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidatorEntityListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(org.sgodden.tom.model.listener.ValidatorEntityListener.class);

	private static Validator validator = Validation
			.buildDefaultValidatorFactory().getValidator();

	@PrePersist
	@PreUpdate
	public void validate(Serializable entity) {
		Set<ConstraintViolation<Serializable>> constraints = validator.validate(entity);
		if (constraints.size() > 0) {
			throw new ValidationException(constraints);
		}
	}

}
