package com.project.controller;

import com.project.exception.ProjectNotFoundException;
import com.project.model.Project;
import com.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService ;

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }


    @PostMapping("/add")
    public ResponseEntity <Project> saveProject(@RequestBody Project project){
        Project savedProject = projectService.saveProject(project);
        return ResponseEntity.ok(savedProject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + id + " not found"));
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        Project project = projectService.getProjectById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with id " + id + " doesn't exist"));
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
        Project updatedProject = projectService.updateProject(id, project);
        return ResponseEntity.ok(updatedProject);
    }


    @GetMapping("/{id}/exist")
    public ResponseEntity <Boolean> existProject ( @PathVariable Long id ){
        boolean exist = projectService.existProject(id);
        return ResponseEntity.ok(exist);
    }


}
