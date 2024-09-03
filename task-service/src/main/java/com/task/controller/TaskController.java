package com.task.controller;

import com.task.exception.TaskNotFoundException;
import com.task.model.Task;
import com.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("all")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.addTask(task));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable Long projectId) throws TaskNotFoundException {
        return ResponseEntity.ok(taskService.getTasksByProjectId(projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id  , @RequestBody Task task){
        taskService.updateTask(id ,task);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/{id}/exist")
    public ResponseEntity<Boolean> existTask (@PathVariable Long id ){
        boolean exist = taskService.existTask(id);
        return ResponseEntity.ok(exist);
    }
}
