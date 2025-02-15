package com.shet.taskmanager.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateTaskDTO {
    String title;
    String description;
    String deadline;
}
