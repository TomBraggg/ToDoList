package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/list")
@CrossOrigin

public class ToDoListController {

	@GetMapping
	public ResponseEntity<String> getAllLists(){
		
		return new ResponseEntity<String>("lol", HttpStatus.OK);
		
	}
	
}
