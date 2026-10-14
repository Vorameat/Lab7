package com.example.lab7;

import java.util.Date;

public class TextNote extends Note {
    private String content;

    public TextNote(String title, Date createdDate, String content) {
        this.title = title;
        this.createdDate = createdDate;
        this.content = content;
    }

    public String getTextContent() {
        return content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String getSummary() {
        return "Title: " + title + "\nContent: " + content;
    }
}