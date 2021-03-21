package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.data.model.Task;
import com.example.demo.dto.TaskDTO;
import com.example.demo.service.TaskService;

@SpringBootTest
class TaskControllerUnitTest {

	@Autowired
	private TaskController taskController;
	
	@MockBean
	private TaskService taskService;
	
	private List<Task> tasks;
	private List<TaskDTO> taskDTOs;
	
	private Task validTask;
	private TaskDTO validTaskDTO;
	
	@BeforeEach
	public void init() {
		validTask = new Task(1, "Get this bread!");
		validTaskDTO = new TaskDTO(1, "Get this bread!");
		
		tasks = new ArrayList<Task>();
		taskDTOs = new ArrayList<TaskDTO>();
		
		tasks.add(validTask);
		taskDTOs.add(validTaskDTO);
	}
	
	@Test
	public void getAllTasksTest() {
		when(taskService.readAllTasks()).thenReturn(taskDTOs);
		
		ResponseEntity<List<TaskDTO>> response =
				new ResponseEntity<List<TaskDTO>>(taskDTOs, HttpStatus.OK);
		
		assertThat(response).isEqualTo(taskController.getAllTasks());
		
		verify(taskService, times(1)).readAllTasks();
	}
	
	@Test
	public void getTaskByIdTest() {
		when(taskService.readTaskById(Mockito.anyInt())).thenReturn(validTaskDTO);
		
		ResponseEntity<TaskDTO> response =
				new ResponseEntity<TaskDTO>(validTaskDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(taskController.getTaskById(validTask.getId()));
		
		verify(taskService, times(1)).readTaskById(Mockito.anyInt());
	}
	
	@Test
	public void getTaskByNameTest() {
		when(taskService.readTaskByName(Mockito.anyString())).thenReturn(validTaskDTO);
		
		ResponseEntity<TaskDTO> response =
				new ResponseEntity<TaskDTO>(validTaskDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(taskController.getTaskByName(validTask.getName()));
		
		verify(taskService, times(1)).readTaskByName(Mockito.anyString());
	}
	
	@Test
	public void createTaskTest() {
		when(taskService.createTask(Mockito.any(Task.class))).thenReturn(validTaskDTO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validTaskDTO.getId()));
		
		ResponseEntity<TaskDTO> response =
				new ResponseEntity<TaskDTO>(validTaskDTO, headers, HttpStatus.CREATED);
		
		assertThat(response).isEqualTo(taskController.createTask(validTask));
		
		verify(taskService, times(1)).createTask(Mockito.any(Task.class));
	}
	
	@Test
	public void updateTaskTest() {
		when(taskService.updateTask(Mockito.anyInt(), Mockito.any(Task.class))).thenReturn(validTaskDTO);
		
		ResponseEntity<TaskDTO> response =
				new ResponseEntity<TaskDTO>(validTaskDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(taskController.updateTask(validTask.getId(), validTask));
		
		verify(taskService, times(1)).updateTask(Mockito.anyInt(), Mockito.any(Task.class));
	}
	
	@Test
	public void deleteTaskTest() {
		when(taskService.deleteTask(Mockito.anyInt())).thenReturn(true);
		
		ResponseEntity<Boolean> response =
				new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
		assertThat(response).isEqualTo(taskController.deleteTask(validTask.getId()));
		
		verify(taskService, times(1)).deleteTask(Mockito.anyInt());
	}
	
}
