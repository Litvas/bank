package com.litvas.bank.services.impl;

import com.litvas.bank.domain.Worker;
import com.litvas.bank.repositories.WorkerRepository;
import com.litvas.bank.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
