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
import com.example.demo.data.model.ToDoList;
import com.example.demo.data.repository.ToDoListRepository;
import com.example.demo.dto.ToDoListDTO;
import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.exceptions.ToDoListNotFoundException;
import com.example.demo.mappers.ToDoListMapper;

@SpringBootTest
class ToDoListServiceUnitTest {

	@Autowired
	private ToDoListService toDoListService;

	@MockBean
	private ToDoListRepository toDoListRepository;

	@MockBean
	private ToDoListMapper toDoListMapper;

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
	public void readAllToDoListsTest() {
		when(toDoListRepository.findAll()).thenReturn(toDoLists);
		when(toDoListMapper.mapToDTO(Mockito.any(ToDoList.class))).thenReturn(validToDoListDTO);

		assertThat(toDoListDTOs).isEqualTo(toDoListService.readAllToDoLists());

		verify(toDoListRepository, times(1)).findAll();
		verify(toDoListMapper, times(1)).mapToDTO(Mockito.any(ToDoList.class));
	}

	@Test
	public void readToDoListByIdTest() {
		when(toDoListRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(validToDoList));
		when(toDoListMapper.mapToDTO(Mockito.any(ToDoList.class))).thenReturn(validToDoListDTO);

		assertThat(validToDoListDTO).isEqualTo(toDoListService.readToDoListById(validToDoList.getId()));

		verify(toDoListRepository, times(1)).findById(Mockito.anyInt());
		verify(toDoListMapper, times(1)).mapToDTO(Mockito.any(ToDoList.class));
	}

	@Test
	public void readToDoListByIdExceptionTest() {
		when(toDoListRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

		assertThrows(ToDoListNotFoundException.class, () -> toDoListService.readToDoListById(validToDoList.getId()));

		verify(toDoListRepository, times(1)).findById(Mockito.anyInt());
	}

	@Test
	public void readToDoListByNameTest() {
		when(toDoListRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(validToDoList));
		when(toDoListMapper.mapToDTO(Mockito.any(ToDoList.class))).thenReturn(validToDoListDTO);

		assertThat(validToDoListDTO).isEqualTo(toDoListService.readToDoListByName(validToDoList.getName()));

		verify(toDoListRepository, times(1)).findByName(Mockito.anyString());
		verify(toDoListMapper, times(1)).mapToDTO(Mockito.any(ToDoList.class));
	}

	@Test
	public void readToDoListByNameExceptionTest() {
		when(toDoListRepository.findByName(Mockito.anyString())).thenReturn(Optional.empty());

		assertThrows(ToDoListNotFoundException.class,
				() -> toDoListService.readToDoListByName(validToDoList.getName()));

		verify(toDoListRepository, times(1)).findByName(Mockito.anyString());
	}

	@Test
	public void createToDoListTest() {
		when(toDoListRepository.save(Mockito.any(ToDoList.class))).thenReturn(validToDoList);
		when(toDoListMapper.mapToDTO(Mockito.any(ToDoList.class))).thenReturn(validToDoListDTO);

		assertThat(validToDoListDTO).isEqualTo(toDoListService.createToDoList(validToDoList));

		verify(toDoListRepository, times(1)).save(Mockito.any(ToDoList.class));
		verify(toDoListMapper, times(1)).mapToDTO(Mockito.any(ToDoList.class));
	}

	@Test
	public void updateToDoListTest() {
		ToDoList updatedToDoList = new ToDoList(2, "Countries");
		ToDoListDTO updatedToDoListDTO = new ToDoListDTO(2, "Countries");

		when(toDoListRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(validToDoList));
		when(toDoListRepository.save(Mockito.any(ToDoList.class))).thenReturn(updatedToDoList);
		when(toDoListMapper.mapToDTO(Mockito.any(ToDoList.class))).thenReturn(updatedToDoListDTO);

		ToDoListDTO testToDoListDTO = toDoListService.updateToDoList(validToDoList.getId(), updatedToDoList);

		assertThat(updatedToDoListDTO).isEqualTo(testToDoListDTO);

		verify(toDoListRepository, times(1)).findById(Mockito.anyInt());
		verify(toDoListRepository, times(1)).save(Mockito.any(ToDoList.class));
		verify(toDoListMapper, times(1)).mapToDTO(Mockito.any(ToDoList.class));
	}

	@Test
	public void updateToDoListExceptionTest() {
		ToDoList updatedToDoList = new ToDoList(2, "India");

		when(toDoListRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

		assertThrows(ToDoListNotFoundException.class,
				() -> toDoListService.updateToDoList(validToDoList.getId(), updatedToDoList));

		verify(toDoListRepository, times(1)).findById(Mockito.anyInt());
	}

	@Test
	public void deleteToDoListTest() {
		when(toDoListRepository.existsById(Mockito.anyInt())).thenReturn(true);

		assertThat(true).isEqualTo(toDoListService.deleteToDoList(1));

		verify(toDoListRepository, times(1)).existsById(Mockito.anyInt());
		verify(toDoListRepository, times(1)).deleteById(Mockito.anyInt());
	}
	
	@Test
	public void deleteTaskExceptionTest() {
		when(toDoListRepository.existsById(Mockito.anyInt())).thenReturn(false);

		assertThrows(ToDoListNotFoundException.class, () -> toDoListService.deleteToDoList(1));

		verify(toDoListRepository, times(1)).existsById(Mockito.anyInt());
	}

}
