package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.model.Task;
import com.example.demo.dto.TaskDTO;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping(path = "/task")
@CrossOrigin
public class TaskController {
	
	private TaskService taskService;
	
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@GetMapping
	public ResponseEntity<List<TaskDTO>> getAllTasks() {
		List<TaskDTO> data = taskService.readAllTasks();
		return new ResponseEntity<List<TaskDTO>>(data, HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskDTO> getTaskById(@PathVariable("id") int id) {
		TaskDTO task = taskService.readTaskById(id);
		return new ResponseEntity<TaskDTO>(task, HttpStatus.OK);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<TaskDTO> getTaskByName(@PathVariable("name") String name) {
		TaskDTO task = taskService.readTaskByName(name);
		return new ResponseEntity<TaskDTO>(task, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody Task task) {
		TaskDTO newTask = taskService.createTask(task);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(newTask.getId()));
		
		return new ResponseEntity<TaskDTO>(newTask, headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") int id, @RequestBody Task task) {
		TaskDTO updatedTask = taskService.updateTask(id, task);
		return new ResponseEntity<TaskDTO>(updatedTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteTask(@PathVariable("id") int id) {
		return new ResponseEntity<Boolean>(taskService.deleteTask(id), HttpStatus.OK);
	}
	
}
