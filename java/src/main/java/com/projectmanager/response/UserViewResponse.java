package com.projectmanager.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

//Formats HTTP responses for the User view

@Data
@AllArgsConstructor
@Builder
public class UserViewResponse {

    public UserViewResponse() {
        this.name = "";
        this.projects = new ArrayList<>();
    }

    List<ProjectOutput> projects;

    String name;

    public void addProject(ProjectOutput p) {
        projects.add(p);
    }

}
