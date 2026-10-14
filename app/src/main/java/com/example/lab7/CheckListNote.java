package com.example.lab7;

import java.util.Date;
import java.util.List;

public class CheckListNote extends Note {
    private List<String> items;

    public CheckListNote(String title, Date createdDate, List<String> items) {
        this.title = title;
        this.createdDate = createdDate;
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String getSummary() {
        String itemsText = (items != null) ? String.join(", ", items) : "";
        return "Title: " + title + "\nItems: " + itemsText;
    }
}