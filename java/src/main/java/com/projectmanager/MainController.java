package com.projectmanager;

import com.projectmanager.response.ProjectOutput;
import com.projectmanager.response.ProjectViewResponse;
import com.projectmanager.response.TaskOutput;
import com.projectmanager.response.UserViewResponse;
import com.projectmanager.entity.Employee;
import com.projectmanager.repository.ProjectRepository;
import com.projectmanager.repository.TaskRepository;
import com.projectmanager.repository.EmployeeRepository;
import com.projectmanager.entity.Task;
import com.projectmanager.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

@Controller
public class MainController {

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private TaskRepository taskRepository;

  Logger logger = LoggerFactory.getLogger(MainController.class);

  //Get path to json files from resources folder
  Resource projectResource = new ClassPathResource("projects.json");
  Resource taskResource = new ClassPathResource("tasks.json");
  Resource userResource = new ClassPathResource("users.json");

  //Populate database with sample data
  @RequestMapping("/populate")
  public @ResponseBody String addNew () {

    logger.info("Attempting to add projects");
    Gson gson = new Gson();
    JsonReader reader;

    Type projectType = new TypeToken<Collection<Project>>(){}.getType();
    
    //For each of the 3 json files, the program loads a list of objects from the file and then saves those objects to the repository

    try {
      InputStream pFile = projectResource.getInputStream();
      reader = new JsonReader(new InputStreamReader(pFile));
      List<Project> projects = gson.fromJson(reader, projectType);
      projects.forEach((p) -> {
        projectRepository.save(p);
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

    Type userType = new TypeToken<Collection<Employee>>(){}.getType();
    try {
      InputStream uFile = userResource.getInputStream();
      reader = new JsonReader(new InputStreamReader(uFile));
      List<Employee> users = gson.fromJson(reader, userType);
      users.forEach((u) -> {
        employeeRepository.save(u);
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

    Type taskType = new TypeToken<Collection<Task>>(){}.getType();
    try {
      InputStream tFile = taskResource.getInputStream();
      reader = new JsonReader(new InputStreamReader(tFile));
      List<Task> tasks = gson.fromJson(reader, taskType);
      tasks.forEach((t) -> {
        taskRepository.save(t);
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return "Saved";
  }

  //Returns a list of all employees. Used for the home page.
  @RequestMapping("/employees")
  public @ResponseBody Iterable<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  //Returns a list of all the projects associated with a user, as well as that user's name.
  @RequestMapping("/users/{userId}")
  public @ResponseBody UserViewResponse getProjectsForUser(@PathVariable int userId) {
    UserViewResponse uvr = new UserViewResponse();
    List<Project> projects = projectRepository.getProject(userId);
    projects.forEach((p) -> {
      ProjectOutput newProject = new ProjectOutput();
      int projectId = p.getId();

      List<String> memberNames = new ArrayList<String>();
      employeeRepository.getMemberIDs(projectId).forEach((m) -> {
        memberNames.add(employeeRepository.findById(m).get().getName());
      });
      newProject.setMembers(memberNames);
      newProject.setId(projectId);
      newProject.setURL("/project/" + projectId);
      newProject.setName(p.getName());
      newProject.setTotal(projectRepository.getEstimate(projectId));
      uvr.addProject(newProject);
    });
    uvr.setName(employeeRepository.findById(userId).get().getName());
    return uvr;
  }

  //Returns a list of all the tasks associated with a project, as well as the project's name and the total estimated time for the project.
  @RequestMapping("/projects/{projectId}")
  public @ResponseBody ProjectViewResponse getTasksForProject(@PathVariable int projectId) {
    ProjectViewResponse pvr = new ProjectViewResponse();
    List<Task> tasks = taskRepository.getTasks(projectId);

    tasks.forEach((t) -> {
      TaskOutput newTask = new TaskOutput();
      newTask.setName(t.getName());
      newTask.setTime(t.getTime());
      newTask.setEmployee(employeeRepository.findById(t.getUserId()).get().getName());
      pvr.addTask(newTask);
    });
    pvr.setTime(projectRepository.getEstimate(projectId));
    pvr.setName(projectRepository.findById(projectId).get().getName());

    return pvr;
  }

}
