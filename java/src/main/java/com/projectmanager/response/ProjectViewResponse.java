package com.projectmanager.response;

import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

//Formats HTTP responses for the Project view

@Data
@AllArgsConstructor
@Builder
public class ProjectViewResponse {
    
    public ProjectViewResponse() {
        this.name = "";
        this.tasks = new ArrayList<>();
    }

    public List<TaskOutput> tasks;

    public int time;

    public String name;

    public void addTask(TaskOutput p) {
        tasks.add(p);
    }

}
