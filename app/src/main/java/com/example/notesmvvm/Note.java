package com.example.notesmvvm;

public class Note {

    private String title;
    private String description;

    private int Id;
    public Note(int Id,String title, String description) {
        this.title = title;
        this.description = description;
        this.Id = Id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return Id;
    }


}
