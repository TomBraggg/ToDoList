package com.example.demo.dto;

import java.util.List;

public class ToDoListDTO {

	private int id;
	private String name;
	private List<TaskDTO> tasks;

	public ToDoListDTO() {

	}
	
	public ToDoListDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ToDoListDTO(int id, String name, List<TaskDTO> tasks) {
		super();
		this.id = id;
		this.name = name;
		this.tasks = tasks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TaskDTO> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskDTO> tasks) {
		this.tasks = tasks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDoListDTO other = (ToDoListDTO) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tasks == null) {
			if (other.tasks != null)
				return false;
		} else if (!tasks.equals(other.tasks))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ToDoListDTO [id=" + id + ", name=" + name + ", tasks=" + tasks + "]";
	}

}
