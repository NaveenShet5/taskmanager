package com.shet.taskmanager.controller;

import com.shet.taskmanager.dto.CreateNoteDTO;
import com.shet.taskmanager.dto.CreateNoteResponseDTO;
import com.shet.taskmanager.entity.NoteEntity;
import com.shet.taskmanager.service.NotesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {
    private NotesService notesService;

    public NotesController(NotesService notesService){
        this.notesService=notesService;
    }

    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId){
        var notes=notesService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNote(@PathVariable("taskId") Integer taskId, @RequestBody CreateNoteDTO body){
        var note=notesService.addNoteForTask(taskId, body.getTitle(), body.getBody());

        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId, note));
    }
}
