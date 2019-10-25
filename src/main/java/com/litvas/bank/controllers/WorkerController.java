package com.litvas.bank.controllers;

import com.litvas.bank.domain.Worker;
import com.litvas.bank.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PutMapping("add_to_project")
    public Worker addWorkerToProject(@RequestBody Worker worker) {
        return workerService.addWorkerToProject(worker.getId(), worker.getCurrentProject().getId());
    }

    @PutMapping("remove_from_project")
    public Worker removeWorkerFromProject(@RequestBody Worker worker) {
        return workerService.removeWorkerFromProject(worker.getId());
    }

    @PutMapping("change_project")
    public Map<String, Worker> changeWorkersByProjects(@RequestBody Map<String, Long> workers) {
        return workerService.changeWorkersByProjects(workers);
    }

}
