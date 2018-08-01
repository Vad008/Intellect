package com.intel.site.dto;

public class Sertificate {
    private final int id;
    private final String name;
    private final String phone;
    private final String email;
    private boolean done;

    public Sertificate(int id, String name, String phone, String email, boolean done) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.done = done;
    }

    public Sertificate(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
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
}
