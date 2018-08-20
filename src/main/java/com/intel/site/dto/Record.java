package com.intel.site.dto;

public class Record implements Comparable<Record> {
    private final int id;
    private final String crew;
    private final float timeexit;
    private final String quest;

    public Record(int id, String crew, float timeexit, String quest) {
        this.id = id;
        this.crew = crew;
        this.timeexit = timeexit;
        this.quest = quest;
    }

    public int getId() {
        return id;
    }

    public String getCrew() {
        return crew;
    }

    public String getQuest() {
        return quest;
    }

    public float getTimeexit() {
        return timeexit;
    }

    @Override
    public int compareTo(Record o) {
        if (timeexit < o.timeexit) {
            return -1;
        }
        return 1;
    }
}
