package com.resource.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "task-service")
public interface TaskClient {

    @GetMapping("api/task/{taskId}/exist")
    Boolean checkTaskExists(@PathVariable("taskId")Long taskId);
}
