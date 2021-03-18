package com.example.demo.data.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import nl.jqno.equalsverifier.EqualsVerifier;

public class ToDoListTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(ToDoList.class)
		.withPrefabValues(Task.class, new Task(1, "Bread"), new Task(1, "Shopping")).verify();
	}
	
	@Test
	public void testToString() {
		ToDoList testToDoList = new ToDoList(1, "Shopping");
		
		assertThat("ToDoList [id=1, name=Shopping, tasks=null]").isEqualTo(testToDoList.toString());
	}

}
