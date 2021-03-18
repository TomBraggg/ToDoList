package com.example.demo.data.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TaskTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Task.class)
		.withPrefabValues(Task.class, new Task(1, "Bread"), new Task(1, "Shopping")).verify();
	}
	
	@Test
	public void testToString() {
		Task testTask = new Task(1, "Shopping");
		
		assertThat("Task [id=1, name=Shopping, toDoList=null]").isEqualTo(testTask.toString());
	}

}
