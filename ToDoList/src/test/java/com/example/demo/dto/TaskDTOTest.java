package com.example.demo.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class TaskDTOTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(TaskDTO.class).verify();
	}
	
	@Test
	public void testToString() {
		TaskDTO testTaskDTO = new TaskDTO(1, "Shopping");
		
		assertThat("TaskDTO [id=1, name=Shopping]").isEqualTo(testTaskDTO.toString());
	}
	
}
