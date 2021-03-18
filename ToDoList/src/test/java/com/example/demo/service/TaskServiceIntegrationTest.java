package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.data.model.Task;
import com.example.demo.data.repository.TaskRepository;
import com.example.demo.dto.TaskDTO;
import com.example.demo.mappers.TaskMapper;

@SpringBootTest
public class TaskServiceIntegrationTest {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskMapper taskMapper;
	
	private List<Task> tasks;
	private List<TaskDTO> taskDTOs;
	
	private Task validTask;
	private TaskDTO validTaskDTO;

	@BeforeEach
	public void init() {
		validTask = new Task(1, "Get this bread!");

		tasks = new ArrayList<Task>();
		taskDTOs = new ArrayList<TaskDTO>();
		
		taskRepository.deleteAll();

		validTask = taskRepository.save(validTask);
		validTaskDTO = taskMapper.mapToDTO(validTask);
		
		tasks.add(validTask);
		taskDTOs.add(validTaskDTO);
	}
	
	@Test
	public void readAllTasksTest() {
		List<TaskDTO> tasksInDb = taskService.readAllTasks();
		assertThat(taskDTOs).isEqualTo(tasksInDb);
	}
	
	@Test
	public void readTaskByIdTest() {
		TaskDTO taskInDb = taskService.readTaskById(validTask.getId());
		assertThat(validTaskDTO).isEqualTo(taskInDb);
	}

	@Test
	public void readTaskByNameTest() {
		TaskDTO taskInDb = taskService.readTaskByName(validTask.getName());
		assertThat(validTaskDTO).isEqualTo(taskInDb);
	}

	@Test
	public void createTaskTest() {
		Task createdTask = new Task("Countries");
		TaskDTO newTaskInDb = taskService.createTask(createdTask);
		assertThat(taskMapper.mapToDTO(createdTask)).isEqualTo(newTaskInDb);
	}

	@Test
	public void updateTaskTest() {
		Task updatedTask = new Task(4, "Countries");
		TaskDTO updatedTaskInDb = taskService.updateTask(validTask.getId(), updatedTask);
		assertThat(taskMapper.mapToDTO(updatedTask)).isEqualTo(updatedTaskInDb);
	}

	@Test
	public void deleteTaskTest() {
		Boolean deleteVerifier = taskService.deleteTask(validTask.getId());
		assertThat(true).isEqualTo(deleteVerifier);
	}
	
}
