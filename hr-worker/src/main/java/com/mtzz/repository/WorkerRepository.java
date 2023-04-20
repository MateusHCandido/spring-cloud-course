package com.mtzz.repository;

import com.mtzz.domain.EmployeeWorker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<EmployeeWorker, Long>{}
