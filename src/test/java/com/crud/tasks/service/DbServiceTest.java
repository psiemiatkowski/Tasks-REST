package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService service;

    @Mock
    private TaskRepository repository;

    @Test
    public void shouldGetAllTasks() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "TaskTitle1", "TaskContent1"));
        taskList.add(new Task(2L, "TaskTitle2", "TaskContent2"));
        when(repository.findAll()).thenReturn(taskList);
        //When
        List<Task> tasks = service.getAllTasks();
        //Then
        Assert.assertEquals(2, tasks.size());
        Assert.assertEquals("TaskContent2", taskList.get(1).getContent());
    }

    @Test
    public void shouldSaveTask() {
        //Given
        Task task1 = new Task(1L, "TaskTitle1", "TaskContent1");
        when(repository.save(any(Task.class))).thenReturn(task1);
        //When
        Task task = service.saveTask(task1);
        //Then
        Assert.assertEquals((Long)1L, task.getId());
        Assert.assertEquals("TaskTitle1", task.getTitle());
    }

    @Test
    public void shouldGetTask() {
        //Given
        Task task1 = new Task(1L, "TaskTitle1", "TaskContent1");
        when(repository.findOne(task1.getId())).thenReturn(task1);
        //When
        Task task = service.getTask(task1.getId());
        //Then
        Assert.assertEquals((Long)1L, task.getId());
        Assert.assertEquals("TaskContent1", task.getContent());
    }

    @Test
    public void shouldDeleteTask() {
        //Given
        Task task1 = new Task(1L, "TaskTitle1", "TaskContent1");
        //When
        service.deleteTask(task1.getId());
        //Then
        verify(repository, times(1)).delete(anyLong());
    }
}