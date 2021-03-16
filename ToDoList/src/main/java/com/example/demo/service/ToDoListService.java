package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.model.ToDoList;
import com.example.demo.data.repository.ToDoListRepository;
import com.example.demo.dto.ToDoListDTO;
import com.example.demo.exceptions.ToDoListNotFoundException;
import com.example.demo.mappers.ToDoListMapper;

@Service
public class ToDoListService {
	
	private ToDoListRepository toDoListRepository;
	private ToDoListMapper toDoListMapper;

	@Autowired
	public ToDoListService(ToDoListRepository toDoListRepository, ToDoListMapper toDoListMapper) {
		this.toDoListRepository = toDoListRepository;
		this.toDoListMapper = toDoListMapper;
	}
	
	public List<ToDoListDTO> readAllToDoLists() {
		List<ToDoList> toDoLists = toDoListRepository.findAll();
		List<ToDoListDTO> toDoListDTOs = new ArrayList<ToDoListDTO>();
		toDoLists.forEach(toDoList -> toDoListDTOs.add(toDoListMapper.mapToDTO(toDoList)));
		return toDoListDTOs;
	}

	public ToDoListDTO readToDoListById(Integer id) {
		Optional<ToDoList> toDoList = toDoListRepository.findById(id);
		if (toDoList.isPresent()) {
			return toDoListMapper.mapToDTO(toDoList.get());
		} else {
			throw new ToDoListNotFoundException("To do list not found!");
		}
	}

	public ToDoListDTO readToDoListByName(String name) {
		Optional<ToDoList> toDoList = toDoListRepository.findByName(name);
		if (toDoList.isPresent()) {
			return toDoListMapper.mapToDTO(toDoList.get());
		} else {
			throw new ToDoListNotFoundException("To do list not found!");
		}
	}

	public ToDoListDTO createToDoList(@Valid ToDoList toDoList) {
		ToDoList newToDoList = toDoListRepository.save(toDoList);
		return toDoListMapper.mapToDTO(newToDoList);
	}

	public ToDoListDTO updateToDoList(int id, ToDoList toDoList) {
		Optional<ToDoList> toDoListInDbOpt = toDoListRepository.findById(id);
		ToDoList toDoListInDb;
		
		if (toDoListInDbOpt.isPresent()) {
			toDoListInDb = toDoListInDbOpt.get();
		} else {
			throw new ToDoListNotFoundException("To do list not found!");
		}
		
		toDoListInDb.setName(toDoList.getName());
		
		ToDoList updatedToDoList = toDoListRepository.save(toDoListInDb);
		return toDoListMapper.mapToDTO(updatedToDoList);
	}

	public Boolean deleteToDoList(int id) {
		
		if (toDoListRepository.existsById(id)) {
			toDoListRepository.deleteById(id);
			return true;
		} else {
			throw new ToDoListNotFoundException("To do list not found!");
		}
	}

}
