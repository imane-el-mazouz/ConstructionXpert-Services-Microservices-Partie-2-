package com.project.service;

import com.project.dto.PersonDTO;
import com.project.exception.ProjectNotFoundException;
import com.project.feign.PersonClient;
import com.project.model.Project;
import com.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PersonClient personClient;

    public PersonDTO getPersonInfo(String token) {
        return personClient.getPersonInfo(token);
    }


    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }


    public Project saveProject(Project project){
        Project project1 = new Project();
        project1.setId(project.getId());
        project1.setName(project.getName());
        project1.setDescription(project.getDescription());
        project1.setStartDate(project.getStartDate());
        project1.setEndDate(project.getEndDate());
        project1.setBudget(project.getBudget());
        return projectRepository.save(project1);
    }


    public Optional<Project> getProjectById(Long id){
        return projectRepository.findById(id);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException("Project with id " + id + " doesn't exist");
        }
        projectRepository.deleteById(id);
    }

    public Project updateProject(Long id, Project updatedProject) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("No project found with this ID"));
        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        project.setStartDate(updatedProject.getStartDate());
        project.setEndDate(updatedProject.getEndDate());
        project.setBudget(updatedProject.getBudget());
        return projectRepository.save(project);
    }

    public Boolean existProject (Long id){
        return projectRepository.existsById(id);
    }

}
