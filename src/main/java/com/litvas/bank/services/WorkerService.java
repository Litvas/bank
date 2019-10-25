package com.litvas.bank.services;

import com.litvas.bank.domain.Worker;

import java.util.List;
import java.util.Map;

public interface WorkerService {

    List<Worker> getAllWorkers();

    Worker saveWorker(Worker worker);

    void deleteWorker(Worker worker);

    Worker getOneWorker(Long id);

    Worker addWorkerToProject(Long workerId, Long projectId);

    Worker removeWorkerFromProject(Long workerId);

    Map<String, Worker> changeWorkersByProjects(Map<String, Long> workers);

}
