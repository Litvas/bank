package com.litvas.bank.services;

import com.litvas.bank.domain.Project;
import com.litvas.bank.domain.Worker;
import com.litvas.bank.repositories.ProjectRepository;
import com.litvas.bank.repositories.WorkerRepository;
import com.litvas.bank.services.impl.ProjectServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceImplTest {

    @InjectMocks
    private ProjectServiceImpl testedEntry;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private WorkerRepository workerRepository;

    @Mock
    private List<Project> projects;

    @Mock
    List<Worker> workers;

    private Project project = new Project();

    @Before
    public void setRules() {
        doReturn(projects).when(projectRepository).findAll();
        doReturn(project).when(projectRepository).save(project);
        doReturn(workers).when(workerRepository).findAllByCurrentProjectId(1L);
    }

    @Test
    public void getAllProjectTest() {
        assertThat(testedEntry.getAllProjects()).isSameAs(projects);
    }

    @Test
    public void saveProjectTest() {
        assertThat(testedEntry.saveProject(project)).isSameAs(project);
    }

    @Test
    public void workersByProjectTest() {
        assertThat(testedEntry.workersByProject(1L)).isSameAs(workers);
    }

}
