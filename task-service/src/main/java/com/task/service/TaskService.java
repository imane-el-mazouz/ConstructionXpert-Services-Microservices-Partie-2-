

package com.task.service;

import com.task.exception.ProjectNotFoundException;
import com.task.exception.TaskNotFoundException;
import com.task.model.Task;
import com.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private final RestTemplate restTemplate;

    private static final String PROJECT_SERVICE_URL = "http://project-service/api/project";

    public Task addTask(Task task) {
        String url = PROJECT_SERVICE_URL + "/" + task.getProjectId() + "/exist";
        Boolean existProject = restTemplate.getForObject(url, Boolean.class);

        if (existProject != null && existProject) {
            return taskRepository.save(task);
        } else {
            throw new ProjectNotFoundException("Project with ID " + task.getProjectId() + " does not exist.");
        }
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    public List<Task> getTasksByProjectId(Long projectId) throws TaskNotFoundException {
        Boolean existProject = restTemplate.getForObject(PROJECT_SERVICE_URL + "/" + projectId + "/exist", Boolean.class);
        if (Boolean.TRUE.equals(existProject)) {
            return taskRepository.findByProjectId(projectId);
        }
        throw new TaskNotFoundException(projectId);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    public Task updateTask (Long id , Task task){
        Task task1 = taskRepository.findById(id) .orElseThrow(() -> new TaskNotFoundException(id));
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