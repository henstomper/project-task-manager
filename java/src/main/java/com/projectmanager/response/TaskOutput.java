package com.projectmanager.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Formats Tasks used in HTTP responses

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskOutput {
    Long time;
    String name;
    String employee;
}
