package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.model.Task;
import com.example.demo.data.repository.TaskRepository;
import com.example.demo.dto.TaskDTO;
import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.mappers.TaskMapper;

@Service
public class TaskService {
	
	private TaskRepository taskRepository;
	private TaskMapper taskMapper;
	
	@Autowired
	public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
		this.taskRepository = taskRepository;
		this.taskMapper = taskMapper;
	}

	public List<TaskDTO> readAllTasks() {
		List<Task> tasks = taskRepository.findAll();
		List<TaskDTO> taskDTOs = new ArrayList<TaskDTO>();
		tasks.forEach(task -> taskDTOs.add(taskMapper.mapToDTO(task)));
		return taskDTOs;
	}

	public TaskDTO readTaskById(int id) {
		Optional<Task> task = taskRepository.findById(id);
		if (task.isPresent()) {
			return taskMapper.mapToDTO(task.get());
		} else {
			throw new TaskNotFoundException("Task not found!");
		}
	}

	public TaskDTO readTaskByName(String name) {
		Optional<Task> task = taskRepository.findByName(name);
		if (task.isPresent()) {
			return taskMapper.mapToDTO(task.get());
		} else {
			throw new TaskNotFoundException("Task not found!");
		}
	}

	public TaskDTO createTask(@Valid Task task) {
		Task newTask = taskRepository.save(task);
		return taskMapper.mapToDTO(newTask);
	}

	public TaskDTO updateTask(int id, Task task) {
		Optional<Task> taskInDbOpt = taskRepository.findById(id);
		Task taskInDb;
		
		if (taskInDbOpt.isPresent()) {
			taskInDb = taskInDbOpt.get();
		} else {
			throw new TaskNotFoundException("Task not found!");
		}
		
		taskInDb.setName(task.getName());
		
		Task updatedTask = taskRepository.save(taskInDb);
		return taskMapper.mapToDTO(updatedTask);
	}
	
	public Boolean deleteTask(int id) {
		if (taskRepository.existsById(id)) {
			taskRepository.deleteById(id);
			return true;
		} else {
			throw new TaskNotFoundException("Task not found!");
		}
	}
	
}
