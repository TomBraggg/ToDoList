package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.data.model.Task;
import com.example.demo.data.model.ToDoList;
import com.example.demo.data.repository.ToDoListRepository;
import com.example.demo.dto.ToDoListDTO;
import com.example.demo.mappers.ToDoListMapper;

@SpringBootTest
public class ToDoListServiceIntegrationTest {

	@Autowired
	private ToDoListService toDoListService;
	
	@Autowired
	private ToDoListRepository toDoListRepository;
	
	@Autowired
	private ToDoListMapper toDoListMapper;
	
	private List<Task> tasks = new ArrayList<>();
	private List<ToDoList> toDoLists;
	private List<ToDoListDTO> toDoListDTOs;
	
	private ToDoList validToDoList;
	private ToDoListDTO validToDoListDTO;

	@BeforeEach
	public void init() {
		validToDoList = new ToDoList(1, "Shopping", tasks);

		toDoLists = new ArrayList<ToDoList>();
		toDoListDTOs = new ArrayList<ToDoListDTO>();
		
		toDoListRepository.deleteAll();

		validToDoList =  toDoListRepository.save(validToDoList);
		validToDoListDTO = toDoListMapper.mapToDTO(validToDoList);
		
		toDoLists.add(validToDoList);
		toDoListDTOs.add(validToDoListDTO);
	}
	
	@Test
	public void readAllToDoListsTest() {
		List<ToDoListDTO> toDoListsInDb = toDoListService.readAllToDoLists();
		assertThat(toDoListDTOs).isEqualTo(toDoListsInDb);
	}
	
	@Test
	public void readToDoListByIdTest() {
		ToDoListDTO toDoListInDb = toDoListService.readToDoListById(validToDoList.getId());
		assertThat(validToDoListDTO).isEqualTo(toDoListInDb);
	}

	@Test
	public void readToDoListByNameTest() {
		ToDoListDTO toDoListInDb = toDoListService.readToDoListByName(validToDoList.getName());
		assertThat(validToDoListDTO).isEqualTo(toDoListInDb);
	}

	@Test
	public void createToDoListTest() {
		ToDoList createdToDoList = new ToDoList("Countries");
		ToDoListDTO newToDoListInDb = toDoListService.createToDoList(createdToDoList);
		assertThat(toDoListMapper.mapToDTO(createdToDoList)).isEqualTo(newToDoListInDb);
	}

	@Test
	public void updateToDoListTest() {
		ToDoList updatedToDoList = new ToDoList(7, "Countries", tasks);
		ToDoListDTO updatedToDoListInDb = toDoListService.updateToDoList(validToDoList.getId(), updatedToDoList);
		assertThat(toDoListMapper.mapToDTO(updatedToDoList)).isEqualTo(updatedToDoListInDb);
	}

	@Test
	public void deleteToDoListTest() {
		Boolean deleteVerifier = toDoListService.deleteToDoList(validToDoList.getId());
		assertThat(true).isEqualTo(deleteVerifier);
	}
	
}
