package com.litvas.bank.services;

import com.litvas.bank.domain.Worker;

import java.util.List;

public interface WorkerService {

    List<Worker> getAllWorkers();

    Worker saveWorker(Worker worker);

    void deleteWorker(Worker worker);

}
