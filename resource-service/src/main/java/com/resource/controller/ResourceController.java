package com.resource.controller;

import com.resource.exception.ResourceNotFoundException;
import com.resource.model.Resource;
import com.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService ;

    @GetMapping
    public ResponseEntity<List<Resource>> getAllResources(){
        List<Resource> resources = resourceService.getAllResources();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Resource>> getResourcesByTaskId(@PathVariable Long taskId ){
        List<Resource> resources = resourceService.getAllResourcesByTaskId(taskId);
        return ResponseEntity.ok(resources);
    }

    @PostMapping("/add")
    public ResponseEntity<Resource> saveResource(@RequestBody Resource resource){
        resourceService.addResource(resource);
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id ){
        Resource resource = resourceService.getResourceById(id) .orElseThrow(() -> new ResourceNotFoundException(id));
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResource (@PathVariable Long id){
        resourceService.deleteResource(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Resource> updateResource(@PathVariable Long id, @RequestBody Resource resource) {
        Resource updatedResource = resourceService.updateResource(id, resource);
        return ResponseEntity.ok(updatedResource);
    }




}
