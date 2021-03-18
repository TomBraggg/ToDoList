package com.example.demo.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.data.model.ToDoList;
import com.example.demo.dto.ToDoListDTO;

@SpringBootTest
public class ToDoListMapperTest {

		@Autowired
		private ToDoListMapper toDoListMapper;
		
		private ToDoList validToDoList;
		private ToDoListDTO validToDoListDTO;
		
		@BeforeEach
		public void init() {
			validToDoList = new ToDoList(1, "Get this bread!");
			validToDoListDTO = new ToDoListDTO(1, "Get this bread!");
		}
		
		@Test
		public void mapToDTOTest() {
			assertThat(validToDoListDTO).isEqualTo(toDoListMapper.mapToDTO(validToDoList));
		}
		
		@Test
		public void mapToTaskTest() {
			assertThat(validToDoList).isEqualTo(toDoListMapper.mapToToDoList(validToDoListDTO));
		}
	
}
