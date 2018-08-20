package com.intel.site.dto;

public class Book {
    private final int id;
    private final String name;
    private final String phone;
    private final String email;
    private final String datequest;
    private final String quest;
    private final String messageuser;
    private boolean done;

    public Book(int id, String name, String phone, String email, String datequest, String quest, String messageuser, boolean done) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.datequest = datequest;
        this.quest = quest;
        this.messageuser = messageuser;
        this.done = done;
    }

    public Book(int id, String name, String phone, String email, String datequest, String quest, String messageuser) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.datequest = datequest;
        this.quest = quest;
        this.messageuser = messageuser;
        this.done = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isDone() {
        return done;
    }

    public String getDatequest() {
        return datequest;
    }

    public String getMessageuser() {
        return messageuser;
    }

    public String getQuest() {
        return quest;
    }
}
