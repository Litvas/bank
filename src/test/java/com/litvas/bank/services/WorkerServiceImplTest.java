package com.litvas.bank.services;

import com.litvas.bank.domain.Project;
import com.litvas.bank.domain.Worker;
import com.litvas.bank.repositories.WorkerRepository;
import com.litvas.bank.services.impl.WorkerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class WorkerServiceImplTest {

    @InjectMocks
    private WorkerServiceImpl testedEntry;

    @Mock
    private WorkerRepository workerRepository;

    @Mock
    private List<Worker> workers;

    private Worker currentWorker = new Worker();
    private Worker futureWorker = new Worker();

    @Before
    public void setRules() {
        doReturn(workers).when(workerRepository).findAll();
        doReturn(currentWorker).when(workerRepository).getOne(1L);
        doReturn(futureWorker).when(workerRepository).getOne(2L);
        doReturn(currentWorker).when(workerRepository).save(currentWorker);
    }

    @Before
    public void initWorker() {
        currentWorker.setId(1L);
        currentWorker.setCurrentProject(new Project());
        currentWorker.getCurrentProject().setId(1L);

        futureWorker.setId(2L);
        futureWorker.setCurrentProject(new Project());
        futureWorker.getCurrentProject().setId(1L);
    }

    @Test
    public void getAllWorkersTest() {
        assertThat(testedEntry.getAllWorkers()).isSameAs(workers);
    }

    @Test
    public void getOneWorkerTest() {
        assertThat(testedEntry.getOneWorker(1L)).isSameAs(currentWorker);
    }

    @Test
    public void saveWorkerTest() {
        assertThat(testedEntry.saveWorker(currentWorker)).isSameAs(currentWorker);
    }

    @Test
    public void deleteWorkerTest() {
        testedEntry.deleteWorker(currentWorker);
        verify(workerRepository).delete(currentWorker);
    }

    @Test
    public void addWorkerToProjectTest() {
        Long projectId = 2L;
        currentWorker.getCurrentProject().setId(projectId);
        assertThat(testedEntry.addWorkerToProject(currentWorker.getId(), projectId)).isSameAs(currentWorker);
    }

    @Test
    public void removeWorkerFromProjectTest() {
        currentWorker.setCurrentProject(null);
        assertThat(testedEntry.removeWorkerFromProject(currentWorker.getId())).isSameAs(currentWorker);
    }

    @Test
    public void changeWorkersByProjectsTest() {
        Map<String, Long> workersMap = new HashMap<>();
        workersMap.put("currentWorker", 1L);
        workersMap.put("futureWorker", 2L);
        Map<String, Worker> result = new HashMap<>();
        futureWorker.setCurrentProject(currentWorker.getCurrentProject());
        currentWorker.setCurrentProject(null);
        result.put("currentWorker", futureWorker);
        result.put("pastWorker", currentWorker);
        assertThat(testedEntry.changeWorkersByProjects(workersMap)).isEqualTo(result);
    }
}
