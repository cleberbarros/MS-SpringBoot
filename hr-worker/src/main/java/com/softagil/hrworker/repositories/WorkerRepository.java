package com.softagil.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softagil.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long>{

}
