package com.litvas.bank.repositories;

import com.litvas.bank.domain.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    List<Worker> findAllByCurrentProjectId(Long currentProject);

}
