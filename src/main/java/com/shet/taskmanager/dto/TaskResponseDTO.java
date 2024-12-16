package com.shet.taskmanager.dto;

import com.shet.taskmanager.entity.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskResponseDTO {
    private int id;
    private String title;
    private String description;
    private Date deadline;
    private Boolean completed;
    private List<NoteEntity> notes;

}
