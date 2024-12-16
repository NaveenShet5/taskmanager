package com.shet.taskmanager.service;

import com.shet.taskmanager.entity.NoteEntity;
import com.shet.taskmanager.entity.TaskEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class NotesService {
    private TaskService taskService;
    private HashMap<Integer, TaskNoteHolder> taskNoteHolders=new HashMap<>();
    class TaskNoteHolder{
        protected int noteId=1;
        protected ArrayList<NoteEntity> notes=new ArrayList<>();
    }

    public NotesService(TaskService taskService){
        this.taskService=taskService;
    }

    public List<NoteEntity> getNotesForTask(int taskId){
        TaskEntity task=taskService.getTaskById(taskId);
        if(task==null)return null;
        if(taskNoteHolders.get(taskId)==null){
            taskNoteHolders.put(taskId, new TaskNoteHolder());
        }
        return taskNoteHolders.get(taskId).notes;
    }

    public NoteEntity addNoteForTask(int taskId, String title, String body){
        TaskEntity task=taskService.getTaskById(taskId);
        if(task==null){
            return null;
        }
        if(taskNoteHolders.get(taskId)==null){
            taskNoteHolders.put(taskId, new TaskNoteHolder());
        }
        TaskNoteHolder taskNotesHolder=taskNoteHolders.get(taskId);
        NoteEntity note=new NoteEntity();
        note.setId(taskNotesHolder.noteId);
        note.setTitle(title);
        note.setBody(body);
        taskNotesHolder.notes.add(note);
        taskNotesHolder.noteId++;
        return note;
    }
}
