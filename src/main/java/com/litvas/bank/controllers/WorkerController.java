package com.litvas.bank.controllers;

import com.litvas.bank.domain.Worker;
import com.litvas.bank.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public List<Worker> getWorkers() {
        return workerService.getAllWorkers();
    }

    @PostMapping
    public Worker createWorker(@RequestBody Worker worker) {
        return workerService.saveWorker(worker);
    }

    @PutMapping
    public Worker updateWorker(@RequestBody Worker worker) {
        return workerService.saveWorker(worker);
    }

    @DeleteMapping
    public void deleteWorker(@RequestBody Worker worker) {
        workerService.deleteWorker(worker);
    }

}
