package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    @Override
    List<Task> findAll();

    @Override
    Task save(Task task);

    @Override
    Task findOne(Long id);

    @Override
    void delete(Long id);

    @Override
    long count();
}
