package com.example.lab7;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "notes")
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String title;

    @NonNull
    public String type; // "text" หรือ "checklist"

    public String checklistItemsJson;
    public String content;

    public Date createdDate;

    public NoteEntity(@NonNull String title, @NonNull String type, String checklistItemsJson, String content, Date createdDate) {
        this.title = title;
        this.type = type;
        this.checklistItemsJson = checklistItemsJson;
        this.content = content;
        this.createdDate = createdDate;
    }
}