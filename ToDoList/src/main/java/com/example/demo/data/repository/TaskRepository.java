package com.example.demo.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.data.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	Optional<Task> findByName(String name);

}
