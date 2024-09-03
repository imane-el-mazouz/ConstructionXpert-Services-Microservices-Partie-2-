

package com.task.service;

import com.task.exception.ProjectNotFoundException;
import com.task.exception.TaskNotFoundException;
import com.task.feign.ProjectClient;
import com.task.model.Task;
import com.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectClient projectClient;


    public Task addTask(Task task) {
        Boolean existProject = projectClient.checkProjectExists(task.getProjectId());

        if(existProject != null && existProject) {
            return taskRepository.save(task);
        } else {
            throw new ProjectNotFoundException("Project with ID " + task.getProjectId() + " does not exist.");
        }


    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    public List<Task> getTasksByProjectId(Long projectId) throws TaskNotFoundException {
        Boolean existProject = projectClient.checkProjectExists(projectId);
        if (Boolean.TRUE.equals(existProject)) {
            return taskRepository.findByProjectId(projectId);
        }

        throw new TaskNotFoundException("Project with ID " + projectId + " does not exist.");
    }


    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id) throws TaskNotFoundException {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("task not found with this id");
        }
        taskRepository.deleteById(id);
    }

    public Task updateTask (Long id , Task task) throws TaskNotFoundException {
        Task task1 = taskRepository.findById(id) .orElseThrow(() -> new TaskNotFoundException("task not found"));
        task1.setDescription(task.getDescription());
        task1.setStartDate(task.getStartDate());
        task1.setEndDate(task.getEndDate());
        task1.setStatus(task.getStatus());
        return taskRepository.save(task1);
    }

    public Boolean existTask (Long id){
        return taskRepository.existsById(id);
    }



}