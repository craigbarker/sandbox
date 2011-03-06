package org.sgodden.issuetracker.domain;

import java.io.Serializable;
import java.util.Set;

import javax.validation.ConstraintViolation;

@SuppressWarnings("serial")
public class ValidationException extends RuntimeException {

	private Set<ConstraintViolation<Serializable>> violations;

	public ValidationException(Set<ConstraintViolation<Serializable>> violations) {
		super();
		this.violations = violations;
	}

	public Set<ConstraintViolation<Serializable>> getViolations() {
		return violations;
	}

}
