package com.shet.taskmanager.entity;

import lombok.Data;

import java.util.*;

@Data
public class TaskEntity {
    private int id;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;

}
