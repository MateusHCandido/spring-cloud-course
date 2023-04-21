package com.mtzz.feignclients;

import com.mtzz.domain.EmployeeWorker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "hr-worker", url = "localhost:8001", path = "/workers")
public interface WorkerFeignClient
{
    @GetMapping(path = "/{workerId}")
    ResponseEntity<EmployeeWorker> findWorkerById(@PathVariable Long workerId);
}
