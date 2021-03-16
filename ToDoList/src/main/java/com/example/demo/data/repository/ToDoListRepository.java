package com.example.demo.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.data.model.ToDoList;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Integer>{

	Optional<ToDoList> findByName(String name);

}
