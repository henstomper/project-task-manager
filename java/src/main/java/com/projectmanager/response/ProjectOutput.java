package com.projectmanager.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Formats Projects used in HTTP responses

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectOutput {
    List<String> members;
    int id;
    String name;
    long total;
    String URL;
}
