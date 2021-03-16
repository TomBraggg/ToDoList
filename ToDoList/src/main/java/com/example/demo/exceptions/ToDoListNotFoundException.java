package com.example.demo.exceptions;

import javax.persistence.EntityNotFoundException;

public class ToDoListNotFoundException extends EntityNotFoundException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8986185065006160947L;

	public ToDoListNotFoundException() {
		super();
	}
	
	public ToDoListNotFoundException(String message) {
		super(message);
	}
	
}
