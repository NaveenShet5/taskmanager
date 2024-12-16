package com.shet.taskmanager.controller;

import com.shet.taskmanager.dto.CreateTaskDTO;
import com.shet.taskmanager.dto.ErrorResponseDTO;
import com.shet.taskmanager.dto.TaskResponseDTO;
import com.shet.taskmanager.dto.UpdateTaskDTO;
import com.shet.taskmanager.entity.TaskEntity;
import com.shet.taskmanager.service.NotesService;
import com.shet.taskmanager.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private  final NotesService notesService;
    private ModelMapper modelMapper = new ModelMapper();


    public TaskController(TaskService taskService, NotesService notesService) {
        this.taskService = taskService;
        this.notesService = notesService;
    }

    @GetMapping("")
    public  ResponseEntity<List<TaskEntity>>getTask(){
        var tasks=taskService.getTasks();
        return  ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Integer id){
        var task=taskService.getTaskById(id);
        var notes=notesService.getNotesForTask(id);
        if(task==null){
            return ResponseEntity.notFound().build();
        }
        var taskResponse=modelMapper.map(task, TaskResponseDTO.class);
        taskResponse.setNotes(notes);
        return ResponseEntity.ok(taskResponse);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws  ParseException{
        var task=taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());

        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO>handleError(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Date Format"));
        }
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body) throws  ParseException{
        var task=taskService.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());
        if(task==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }
}
