package com.litvas.bank.services.impl;

import com.litvas.bank.domain.Project;
import com.litvas.bank.domain.Worker;
import com.litvas.bank.repositories.ProjectRepository;
import com.litvas.bank.repositories.WorkerRepository;
import com.litvas.bank.services.ProjectService;
import com.litvas.bank.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Worker> workersByProject(Long projectId) {
        return workerRepository.findAllByCurrentProjectId(projectId);
    }

}
