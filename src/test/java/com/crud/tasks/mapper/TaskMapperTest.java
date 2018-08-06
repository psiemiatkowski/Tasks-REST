package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskMapperTest {

    private TaskMapper taskMapper = new TaskMapper();

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "TaskDtoTitle1", "TaskDtoContent1");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals((Long) 1L, task.getId());
        Assert.assertEquals("TaskDtoTitle1", task.getTitle());
        Assert.assertEquals("TaskDtoContent1", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(2L, "TaskTitle2", "TaskContent2");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals((Long) 2L, taskDto.getId());
        Assert.assertEquals("TaskTitle2", taskDto.getTitle());
        Assert.assertEquals("TaskContent2", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(3L, "TaskTitle3", "TaskContent3"));
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        Assert.assertEquals(1, taskDtoList.size());
        Assert.assertEquals((Long)3L, taskDtoList.get(0).getId());
        Assert.assertEquals("TaskTitle3", taskDtoList.get(0).getTitle());
        Assert.assertEquals("TaskContent3", taskDtoList.get(0).getContent());
    }
}
