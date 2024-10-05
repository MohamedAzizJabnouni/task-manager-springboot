package com.example.demo.Services;

import com.example.demo.Models.Task;
import com.example.demo.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

}
