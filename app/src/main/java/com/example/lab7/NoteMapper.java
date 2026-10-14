package com.example.lab7;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class NoteMapper {

    static Gson gson = new Gson();

    public static NoteEntity toEntity(Note note) {
        if (note instanceof TextNote) {
            return new NoteEntity(note.title, "text", null, ((TextNote) note).getTextContent(), note.createdDate);
        } else if (note instanceof CheckListNote) {
            String jsonItems = gson.toJson(((CheckListNote) note).getItems());
            return new NoteEntity(note.title, "checklist", jsonItems, null, note.createdDate);
        }
        return null;
    }

    public static Note fromEntity(NoteEntity entity) {
        if (entity.type.equals("text")) {
            return new TextNote(entity.title, entity.createdDate, entity.content);
        } else if (entity.type.equals("checklist")) {
            List<String> items = gson.fromJson(entity.checklistItemsJson, new TypeToken<List<String>>() {}.getType());
            return new CheckListNote(entity.title, entity.createdDate, items);
        }
        return null;
    }
}