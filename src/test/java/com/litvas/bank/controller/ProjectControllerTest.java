package com.litvas.bank.controller;

import com.litvas.bank.controllers.ProjectController;
import com.litvas.bank.domain.Project;
import com.litvas.bank.domain.Worker;
import com.litvas.bank.services.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProjectController.class)
public class ProjectControllerTest {

    @MockBean
    private ProjectService projectService;

    @Autowired
    private MockMvc mvc;

    private List<Project> projects = new ArrayList<>();
    private List<Worker> workers = new ArrayList<>();
    Project project = new Project();

    private final String projectAsJson = "{\n" +
            "        \"id\": 100,\n" +
            "        \"title\": \"someTitle\",\n" +
            "        \"description\": \"someDescription\",\n" +
            "        \"startDate\": \"2019-04-10\",\n" +
            "        \"deadline\": \"2019-12-10\"\n" +
            "}";

    @Before
    public void initTestData() {
        project.setId(100L);
        project.setTitle("someTitle");
        project.setDescription("someDescription");
        project.setStartDate(new GregorianCalendar(2019, Calendar.APRIL, 11).getTime());
        project.setDeadline(new GregorianCalendar(2019, Calendar.DECEMBER, 11).getTime());
        projects.add(project);

        Worker worker = new Worker();
        worker.setId(200L);
        worker.setFirstname("Inna");
        worker.setLastname("Petrova");
        worker.setSalary(1000L);
        worker.setPosition("qa");
        worker.setCurrentProject(project);
        workers.add(worker);
    }

    @Before
    public void setRule() {
        doReturn(projects).when(projectService).getAllProjects();
        doReturn(project).when(projectService).saveProject(any(Project.class));
        doReturn(workers).when(projectService).workersByProject(any(Long.class));
    }

    @Test
    public void getProjectsTest() throws Exception {
        mvc.perform(get("/api/projects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(100)))
                .andExpect(jsonPath("$[0].title", is("someTitle")))
                .andExpect(jsonPath("$[0].description", is("someDescription")))
                .andExpect(jsonPath("$[0].startDate", startsWith("2019-04-10")))
                .andExpect(jsonPath("$[0].deadline", startsWith("2019-12-10")));
    }

    @Test
    public void createProjectTest() throws Exception {
        mvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(projectAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(100)))
                .andExpect(jsonPath("title", is("someTitle")))
                .andExpect(jsonPath("description", is("someDescription")))
                .andExpect(jsonPath("startDate", startsWith("2019-04-10")))
                .andExpect(jsonPath("deadline", startsWith("2019-12-10")));
    }

    @Test
    public void getAllWorkerInProjectTest() throws Exception {
        mvc.perform(get("/api/projects/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(200)))
                .andExpect(jsonPath("$[0].firstname", is("Inna")))
                .andExpect(jsonPath("$[0].lastname", is("Petrova")))
                .andExpect(jsonPath("$[0].salary", is(1000)))
                .andExpect(jsonPath("$[0].position", is("qa")))
                .andExpect(jsonPath("$[0].currentProject.id", is(100)))
                .andExpect(jsonPath("$[0].currentProject.title", is("someTitle")))
                .andExpect(jsonPath("$[0].currentProject.description", is("someDescription")))
                .andExpect(jsonPath("$[0].currentProject.startDate", startsWith("2019-04-10")))
                .andExpect(jsonPath("$[0].currentProject.deadline", startsWith("2019-12-10")));
    }


}
