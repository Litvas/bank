package com.litvas.bank.services;

import com.litvas.bank.domain.Project;
import com.litvas.bank.domain.Worker;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();

    Project saveProject(Project project);

}
