package com.example.demo.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.data.model.Task;
import com.example.demo.dto.TaskDTO;

@SpringBootTest
class TaskMapperTest {
	
	@Autowired
	private TaskMapper taskMapper;
	
	private Task validTask;
	private TaskDTO validTaskDTO;
	
	@BeforeEach
	public void init() {
		validTask = new Task(1, "Get this bread!");
		validTaskDTO = new TaskDTO(1, "Get this bread!");
	}
	
	@Test
	public void mapToDTOTest() {
		assertThat(validTaskDTO).isEqualTo(taskMapper.mapToDTO(validTask));
	}
	
	@Test
	public void mapToTaskTest() {
		assertThat(validTask).isEqualTo(taskMapper.mapToTask(validTaskDTO));
	}
	
}
