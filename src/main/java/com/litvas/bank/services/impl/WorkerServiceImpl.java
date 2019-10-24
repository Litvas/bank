package com.litvas.bank.services.impl;

import com.litvas.bank.domain.Project;
import com.litvas.bank.domain.Worker;
import com.litvas.bank.repositories.WorkerRepository;
import com.litvas.bank.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    @Override
    public Worker saveWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public void deleteWorker(Worker worker) {
        workerRepository.delete(worker);
    }

    @Override
    public Worker getOneWorker(Long id) {
        return workerRepository.getOne(id);
    }

    public Worker addWorkerToProject(Long workerId, Long projectId) {
        Worker worker = getOneWorker(workerId);
        worker.setCurrentProject(new Project());
        worker.getCurrentProject().setId(projectId);
        return saveWorker(worker);
    }

    @Override
    public Worker removeFromProject(Long workerId) {
        Worker worker = getOneWorker(workerId);
        worker.setCurrentProject(null);
        return saveWorker(worker);
    }

    @Override
    public Map<String, Worker> changeWorkersByProjects(Map<String, Long> workers) {
        Map<String, Worker> workerMap = new HashMap<>();
        Worker currentWorker = getOneWorker(workers.get("currentWorker"));
        Worker futureWorker = getOneWorker(workers.get("futureWorker"));
        futureWorker.setCurrentProject(new Project());
        futureWorker.getCurrentProject().setId(currentWorker.getCurrentProject().getId());
        currentWorker.setCurrentProject(null);
        saveWorker(futureWorker);
        saveWorker(currentWorker);
        workerMap.put("currentWorker", futureWorker);
        workerMap.put("pastWorker", currentWorker);
        return workerMap;
    }

}
