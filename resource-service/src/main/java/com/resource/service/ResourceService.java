package com.resource.service;

import com.resource.exception.ResourceNotFoundException;
import com.resource.exception.TaskNotFoundException;
import com.resource.model.Resource;
import com.resource.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    @Autowired
    private  RestTemplate restTemplate;

    @Autowired
    private  ResourceRepository resourceRepository;

    private static final String TASK_SERVICE_URL = "http://task-service/api/task";

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }



    public List<Resource> getAllResourcesByTaskId(Long taskId) {
        String url = TASK_SERVICE_URL + "/" + taskId + "/exist";
        Boolean existTask = restTemplate.getForObject(url, Boolean.class);

        if (Boolean.TRUE.equals(existTask)) {
            return resourceRepository.findByTaskId(taskId);
        } else {
            throw new ResourceNotFoundException(taskId);
        }
    }
    public Resource addResource(Resource resource) {
        String url = TASK_SERVICE_URL + "/" + resource.getTaskId() + "/exist";
        Boolean existTask = restTemplate.getForObject(url, Boolean.class);

        if (existTask != null && existTask) {
            return resourceRepository.save(resource);
        } else {
            throw new TaskNotFoundException("Task with ID " + resource.getTaskId() + " does not exist.");
        }
    }

    public Optional<Resource> getResourceById (Long id){
        return resourceRepository.findById(id);
    }

    public void deleteResource (Long id) {
        if (!resourceRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        resourceRepository.existsById(id);
    }

    public Resource updateResource(Long id, Resource resource) {
        Resource existingResource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        existingResource.setName(resource.getName());
        existingResource.setQuantity(resource.getQuantity());
        existingResource.setType(resource.getType());
        existingResource.setProvider(resource.getProvider());
        return resourceRepository.save(existingResource);
    }




}
