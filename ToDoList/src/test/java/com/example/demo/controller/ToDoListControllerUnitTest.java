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

import com.example.demo.data.model.ToDoList;
import com.example.demo.dto.ToDoListDTO;
import com.example.demo.service.ToDoListService;

@SpringBootTest
public class ToDoListControllerUnitTest {
	
	@Autowired
	private ToDoListController toDoListController;
	
	@MockBean
	private ToDoListService toDoListService;
	
	private List<ToDoList> toDoLists;
	private List<ToDoListDTO> toDoListDTOs;
	
	private ToDoList validToDoList;
	private ToDoListDTO validToDoListDTO;

	@BeforeEach
	public void init() {
		validToDoList = new ToDoList(1, "Shopping List");
		validToDoListDTO = new ToDoListDTO(1, "Shopping List");
		
		toDoLists = new ArrayList<ToDoList>();
		toDoListDTOs = new ArrayList<ToDoListDTO>();
		
		toDoLists.add(validToDoList);
		toDoListDTOs.add(validToDoListDTO);
	}
	
	@Test
	public void getAllToDoListsTest() {
		when(toDoListService.readAllToDoLists()).thenReturn(toDoListDTOs);
		
		ResponseEntity<List<ToDoListDTO>> response =
				new ResponseEntity<List<ToDoListDTO>>(toDoListDTOs, HttpStatus.OK);
		
		assertThat(response).isEqualTo(toDoListController.getAllToDoLists());
		
		verify(toDoListService, times(1)).readAllToDoLists();
	}
	
	@Test
	public void getToDoListByIdTest() {
		when(toDoListService.readToDoListById(Mockito.anyInt())).thenReturn(validToDoListDTO);
		
		ResponseEntity<ToDoListDTO> response =
				new ResponseEntity<ToDoListDTO>(validToDoListDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(toDoListController.getToDoListById(validToDoList.getId()));
		
		verify(toDoListService, times(1)).readToDoListById(Mockito.anyInt());
	}
	
	@Test
	public void getToDoListByNameTest() {
		when(toDoListService.readToDoListByName(Mockito.anyString())).thenReturn(validToDoListDTO);
		
		ResponseEntity<ToDoListDTO> response =
				new ResponseEntity<ToDoListDTO>(validToDoListDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(toDoListController.getToDoListByName(validToDoList.getName()));
		
		verify(toDoListService, times(1)).readToDoListByName(Mockito.anyString());
	}
	
	@Test
	public void createToDoListTest() {
		when(toDoListService.createToDoList(Mockito.any(ToDoList.class))).thenReturn(validToDoListDTO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validToDoListDTO.getId()));
		
		ResponseEntity<ToDoListDTO> response =
				new ResponseEntity<ToDoListDTO>(validToDoListDTO, headers, HttpStatus.CREATED);
		
		assertThat(response).isEqualTo(toDoListController.createToDoList(validToDoList));
		
		verify(toDoListService, times(1)).createToDoList(Mockito.any(ToDoList.class));
	}
	
	@Test
	public void updateToDoListTest() {
		when(toDoListService.updateToDoList(Mockito.anyInt(), Mockito.any(ToDoList.class))).thenReturn(validToDoListDTO);
		
		ResponseEntity<ToDoListDTO> response =
				new ResponseEntity<ToDoListDTO>(validToDoListDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(toDoListController.updateToDoList(validToDoList.getId(), validToDoList));
		
		verify(toDoListService, times(1)).updateToDoList(Mockito.anyInt(), Mockito.any(ToDoList.class));
	}
	
	@Test
	public void deleteToDoListTest() {
		when(toDoListService.deleteToDoList(Mockito.anyInt())).thenReturn(true);
		
		ResponseEntity<Boolean> response =
				new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
		assertThat(response).isEqualTo(toDoListController.deleteToDoList(validToDoList.getId()));
		
		verify(toDoListService, times(1)).deleteToDoList(Mockito.anyInt());
	}
	
}
