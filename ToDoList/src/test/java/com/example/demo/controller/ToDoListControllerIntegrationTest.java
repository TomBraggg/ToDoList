package com.example.demo.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.data.model.Task;
import com.example.demo.data.model.ToDoList;
import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.ToDoListDTO;
import com.example.demo.mappers.ToDoListMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql", "classpath:test-data.sql" },
	executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class ToDoListControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Task validTask = new Task(1, "bread");
	private TaskDTO validTaskDTO = new TaskDTO(1, "bread");
	
	private List<Task> validTasks = List.of(validTask);
	private List<TaskDTO> validTaskDTOs = List.of(validTaskDTO);

	private ToDoListDTO validToDoListDTO = new ToDoListDTO(1, "shopping", validTaskDTOs);
	
	private List<ToDoListDTO> validToDoListDTOs = List.of(validToDoListDTO);
	
	@Test
	public void getAllToDoListsTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/todolist");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(validToDoListDTOs));
				
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	
	@Test
	public void getToDoListByIdTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/todolist/1");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(validToDoListDTO));
				
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	
	@Test
	public void getToDoListByNameTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/todolist/name/shopping");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(validToDoListDTO));
				
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	
	@Test
	public void createToDoListTest() throws Exception {
		ToDoList toDoListToSave = new ToDoList("Countries");
		ToDoListDTO expectedToDoList = new ToDoListDTO(2, "Countries");
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/todolist");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(toDoListToSave));
		
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedToDoList));
		
		ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location", "2");
		
		mvc.perform(mockRequest)
			.andExpect(statusMatcher)
			.andExpect(contentMatcher)
			.andExpect(headerMatcher);
	}
	
	@Test
	public void updateToDoListTest() throws Exception {
		ToDoList updatedToDoList = new ToDoList("Countries", validTasks);
		ToDoListDTO expectedToDoList = new ToDoListDTO(1, "Countries", validTaskDTOs);
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/todolist/1");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(updatedToDoList));
		
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedToDoList));
				
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	
	@Test
	public void deleteToDoListTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/todolist/1");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");
				
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	
}
