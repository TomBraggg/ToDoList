package com.example.demo.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ToDoListDTOTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(ToDoListDTO.class).verify();
	}
	
	@Test
	public void testToString() {
		ToDoListDTO testToDoListDTO = new ToDoListDTO(1, "Shopping");
		
		assertThat("ToDoListDTO [id=1, name=Shopping, tasks=null]").isEqualTo(testToDoListDTO.toString());
	}
	
}
