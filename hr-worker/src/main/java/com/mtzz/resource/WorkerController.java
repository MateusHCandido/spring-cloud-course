package com.mtzz.resource;

import com.mtzz.domain.EmployeeWorker;
import com.mtzz.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/workers")
public class WorkerController {

    @Autowired
    private WorkerRepository workerRepository;


    @GetMapping
    public ResponseEntity<List<EmployeeWorker>> listAllWorkers()
    {
        List<EmployeeWorker> employeeWorkerList = workerRepository.findAll();
        return ResponseEntity.ok(employeeWorkerList);
    }

    @GetMapping(path = "/{workerId}")
    public ResponseEntity<EmployeeWorker> findWorkerById(@PathVariable Long workerId)
    {
        EmployeeWorker localizedEmployeeWorker = workerRepository.findById(workerId).get();
        return ResponseEntity.ok(localizedEmployeeWorker);
    }
}