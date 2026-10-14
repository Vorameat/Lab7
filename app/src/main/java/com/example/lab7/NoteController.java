package com.example.lab7;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteController {
    private final NoteDao noteDao;
    private final ExecutorService executorService;

    public NoteController(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.noteDao = db.noteDao();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public interface NoteCallback<T> {
        void onResult(T result);
    }

    public void addNote(Note note, Runnable onComplete) {
        executorService.execute(() -> {
            NoteEntity entity = NoteMapper.toEntity(note);
            if (entity != null) {
                noteDao.insert(entity);
            }
            if (onComplete != null) {
                onComplete.run();
            }
        });
    }

    public void getAllNotes(NoteCallback<List<Note>> callback) {
        executorService.execute(() -> {
            List<NoteEntity> entities = noteDao.getAll();
            List<Note> notes = new ArrayList<>();
            for (NoteEntity entity : entities) {
                Note note = NoteMapper.fromEntity(entity);
                if (note != null) {
                    notes.add(note);
                }
            }
            if (callback != null) {
                callback.onResult(notes);
            }
        });
    }

    public void deleteNote(Note note, Runnable onComplete) {
        executorService.execute(() -> {
            NoteEntity entity = NoteMapper.toEntity(note);
            if (entity != null) {
                noteDao.delete(entity);
            }
            if (onComplete != null) {
                onComplete.run();
            }
        });
    }
}