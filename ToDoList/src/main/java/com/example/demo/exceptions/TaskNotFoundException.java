package com.example.demo.exceptions;

import javax.persistence.EntityNotFoundException;

public class TaskNotFoundException extends EntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6785470069967077474L;

	public TaskNotFoundException() {
		super();
	}
	
	public TaskNotFoundException(String message) {
		super(message);
	}
	
}
