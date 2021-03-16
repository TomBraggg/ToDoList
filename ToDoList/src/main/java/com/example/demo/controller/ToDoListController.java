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

import com.example.demo.data.model.ToDoList;
import com.example.demo.dto.ToDoListDTO;
import com.example.demo.service.ToDoListService;

@RestController
@RequestMapping(path = "/todolist")
@CrossOrigin

public class ToDoListController {

	private ToDoListService toDoListService;
	
	@Autowired
	public ToDoListController(ToDoListService toDoListService) {
		this.toDoListService = toDoListService;
	}
	
	@GetMapping
	public ResponseEntity<List<ToDoListDTO>> getAllToDoLists() {
		List<ToDoListDTO> data = toDoListService.readAllToDoLists();
		return new ResponseEntity<List<ToDoListDTO>>(data, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ToDoListDTO> getToDoListById(@PathVariable("id") Integer id) {
		ToDoListDTO toDoList = toDoListService.readToDoListById(id);
		return new ResponseEntity<ToDoListDTO>(toDoList, HttpStatus.OK);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<ToDoListDTO> getToDoListByName(@PathVariable("name") String name) {
		ToDoListDTO toDoList = toDoListService.readToDoListByName(name);
		return new ResponseEntity<ToDoListDTO>(toDoList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ToDoListDTO> createToDoList(@Valid @RequestBody ToDoList toDoList) {
		ToDoListDTO newToDoList = toDoListService.createToDoList(toDoList);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(newToDoList.getId()));
		
		return new ResponseEntity<ToDoListDTO>(newToDoList, headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ToDoListDTO> updateToDoList(@PathVariable("id") int id, @RequestBody ToDoList toDoList) {
		ToDoListDTO updatedToDoList = toDoListService.updateToDoList(id, toDoList);
		return new ResponseEntity<ToDoListDTO>(updatedToDoList, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteDuck(@PathVariable("id") int id) {
		return new ResponseEntity<Boolean>(toDoListService.deleteToDoList(id), HttpStatus.OK);
	}
	
}
