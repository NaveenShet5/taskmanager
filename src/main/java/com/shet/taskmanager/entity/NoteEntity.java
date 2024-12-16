package com.shet.taskmanager.entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteEntity {

    private int id;
    private String title;
    private String body;
}
