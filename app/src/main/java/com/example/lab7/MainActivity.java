package com.example.lab7;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etTitle, etContent;
    private TextView tvNotesList;
    private NoteController noteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        tvNotesList = findViewById(R.id.tvNotesList);

        noteController = new NoteController(this);

        Button btnAddTextNote = findViewById(R.id.btnAddTextNote);
        Button btnAddChecklist = findViewById(R.id.btnAddChecklist);

        btnAddTextNote.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();

            if (!title.isEmpty()) {
                TextNote textNote = new TextNote(title, new Date(), content);
                noteController.addNote(textNote, this::loadNotes);
                clearInputs();
            } else {
                Toast.makeText(this, "กรุณากรอกหัวข้อ", Toast.LENGTH_SHORT).show();
            }
        });

        btnAddChecklist.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();

            if (!title.isEmpty()) {
                List<String> items = Arrays.asList(content.split(","));
                CheckListNote checkListNote = new CheckListNote(title, new Date(), items);
                noteController.addNote(checkListNote, this::loadNotes);
                clearInputs();
            } else {
                Toast.makeText(this, "กรุณากรอกหัวข้อ", Toast.LENGTH_SHORT).show();
            }
        });

        loadNotes();
    }

    private void loadNotes() {
        noteController.getAllNotes(notes -> {
            StringBuilder sb = new StringBuilder();
            for (Note note : notes) {
                sb.append(note.getSummary()).append("\n-------------------\n");
            }
            runOnUiThread(() -> tvNotesList.setText(sb.toString()));
        });
    }

    private void clearInputs() {
        runOnUiThread(() -> {
            etTitle.setText("");
            etContent.setText("");
        });
    }
}