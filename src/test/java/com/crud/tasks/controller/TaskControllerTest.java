package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchTasks() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "Title1", "Content1"));
        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtoList);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Title1")))
                .andExpect(jsonPath("$[0].content", is("Content1")));
    }

    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        Task task = new Task(2L, "Title2", "Content2");
        TaskDto taskDto = new TaskDto(3L, "Title3", "Content3");
        when(service.getTask(anyLong())).thenReturn(task);
        when(taskMapper.mapToTaskDto(service.getTask(anyLong()))).thenReturn(taskDto);
        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.title", is("Title3")))
                .andExpect(jsonPath("$.content", is("Content3")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {

    }

    @Test
    public void shouldUpdateTask() throws Exception {

    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(1L, "Test task", "Test description");
        TaskDto taskDto = new TaskDto(1L, "Test Dto Task", "Test DTO description");
        when(service.saveTask(taskMapper.mapToTask(any(TaskDto.class)))).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}
