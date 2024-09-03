package com.resource.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "project-service")

public interface ProjectClient {

    @GetMapping("/api/project/{projectId}/exist")
    Boolean checkProjectExists(@PathVariable("projectId") Long projectId);

    }

