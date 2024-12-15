package com.shet.taskmanager.service;

import com.shet.taskmanager.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {
    private final ArrayList<TaskEntity> tasks= new ArrayList<>();
    private int taskId=1;
    private final SimpleDateFormat deadlineFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity addTask(String title, String description, String deadLine) throws ParseException {
        TaskEntity task=new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadlineFormatter.parse(deadLine));
        task.setCompleted(false);
        tasks.add(task);
        taskId++;
        return task;
    }
    public ArrayList<TaskEntity> getTasks(){
        return tasks;
    }
    public TaskEntity getTaskById(int id){
        for(TaskEntity task: tasks){
            if(task.getId()==id){
                return task;
            }
        }
        return null;
    }

    public TaskEntity updateTask(int id, String description, String deadline, Boolean completed){
        TaskEntity task= getTaskById(id);
        if(description!=null)task.setDescription(description);
        if(deadline!=null)task.setDeadline(deadline);
        if(completed!=null)task.setCompleted(deadlineFormatter.parse(deadline));
        return task;
    }


}
