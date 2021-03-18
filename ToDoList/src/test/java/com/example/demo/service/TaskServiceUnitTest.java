package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.data.model.Task;
import com.example.demo.data.repository.TaskRepository;
import com.example.demo.dto.TaskDTO;
import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.mappers.TaskMapper;

@SpringBootTest
public class TaskServiceUnitTest {

	@Autowired
	private TaskService taskService;

	@MockBean
	private TaskRepository taskRepository;

	@MockBean
	private TaskMapper taskMapper;

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
	public void readAllTasksTest() {
		when(taskRepository.findAll()).thenReturn(tasks);
		when(taskMapper.mapToDTO(Mockito.any(Task.class))).thenReturn(validTaskDTO);

		assertThat(taskDTOs).isEqualTo(taskService.readAllTasks());

		verify(taskRepository, times(1)).findAll();
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Task.class));
	}

	@Test
	public void readTaskByIdTest() {
		when(taskRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(validTask));
		when(taskMapper.mapToDTO(Mockito.any(Task.class))).thenReturn(validTaskDTO);

		assertThat(validTaskDTO).isEqualTo(taskService.readTaskById(validTask.getId()));

		verify(taskRepository, times(1)).findById(Mockito.anyInt());
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Task.class));
	}

	@Test
	public void readTaskByIdExceptionTest() {
		when(taskRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

		assertThrows(TaskNotFoundException.class, () -> taskService.readTaskById(validTask.getId()));

		verify(taskRepository, times(1)).findById(Mockito.anyInt());
	}

	@Test
	public void readTaskByNameTest() {
		when(taskRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(validTask));
		when(taskMapper.mapToDTO(Mockito.any(Task.class))).thenReturn(validTaskDTO);

		assertThat(validTaskDTO).isEqualTo(taskService.readTaskByName(validTask.getName()));

		verify(taskRepository, times(1)).findByName(Mockito.anyString());
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Task.class));
	}

	@Test
	public void readTaskByNameExceptionTest() {
		when(taskRepository.findByName(Mockito.anyString())).thenReturn(Optional.empty());

		assertThrows(TaskNotFoundException.class, () -> taskService.readTaskByName(validTask.getName()));

		verify(taskRepository, times(1)).findByName(Mockito.anyString());
	}

	@Test
	public void createTaskTest() {
		when(taskRepository.save(Mockito.any(Task.class))).thenReturn(validTask);
		when(taskMapper.mapToDTO(Mockito.any(Task.class))).thenReturn(validTaskDTO);

		assertThat(validTaskDTO).isEqualTo(taskService.createTask(validTask));

		verify(taskRepository, times(1)).save(Mockito.any(Task.class));
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Task.class));
	}

	@Test
	public void updateTaskTest() {
		Task updatedTask = new Task(2, "Countries");
		TaskDTO updatedTaskDTO = new TaskDTO(2, "Countries");

		when(taskRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(validTask));
		when(taskRepository.save(Mockito.any(Task.class))).thenReturn(updatedTask);
		when(taskMapper.mapToDTO(Mockito.any(Task.class))).thenReturn(updatedTaskDTO);

		TaskDTO testTaskDTO = taskService.updateTask(validTask.getId(), updatedTask);

		assertThat(updatedTaskDTO).isEqualTo(testTaskDTO);

		verify(taskRepository, times(1)).findById(Mockito.anyInt());
		verify(taskRepository, times(1)).save(Mockito.any(Task.class));
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Task.class));
	}

	@Test
	public void updateTaskExceptionTest() {
		Task updatedTask = new Task(2, "Countries");

		when(taskRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

		assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(validTask.getId(), updatedTask));

		verify(taskRepository, times(1)).findById(Mockito.anyInt());
	}

	@Test
	public void deleteTaskTest() {
		when(taskRepository.existsById(Mockito.anyInt())).thenReturn(true);

		assertThat(true).isEqualTo(taskService.deleteTask(1));

		verify(taskRepository, times(1)).existsById(Mockito.anyInt());
		verify(taskRepository, times(1)).deleteById(Mockito.anyInt());
	}

	@Test
	public void deleteTaskExceptionTest() {
		when(taskRepository.existsById(Mockito.anyInt())).thenReturn(false);

		assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask(1));

		verify(taskRepository, times(1)).existsById(Mockito.anyInt());
	}
}
