package com.litvas.bank.controllers;

import com.litvas.bank.domain.Project;
import com.litvas.bank.domain.Worker;
import com.litvas.bank.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @GetMapping("/{projectId}")
    public List<Worker> getAllWorkerInProject(@PathVariable Long projectId) {
        return projectService.workersByProject(projectId);
    }

}
