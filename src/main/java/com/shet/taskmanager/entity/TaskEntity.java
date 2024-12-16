package com.shet.taskmanager.entity;

import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TaskEntity {
    private int id;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;

}
