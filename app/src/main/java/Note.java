package com.example.lab7;

import java.util.Date;

public abstract class Note {
    public String title;
    public Date createdDate;

    public abstract String getSummary();
}