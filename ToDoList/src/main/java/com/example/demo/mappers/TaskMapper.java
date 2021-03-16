package com.example.demo.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.data.model.Task;
import com.example.demo.dto.TaskDTO;

@Component
public class TaskMapper {

	private ModelMapper modelMapper;
	
	@Autowired
	public TaskMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public TaskDTO mapToDTO(Task task) {
		return this.modelMapper.map(task, TaskDTO.class);
	}
	
	public Task mapToTask(Task taskDTO) {
		return this.modelMapper.map(taskDTO, Task.class);
	}
	
}
