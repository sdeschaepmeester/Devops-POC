package com.medhead.backend.model;

public class Speciality {
    private int id;
    private String speciality_group;
    private String speciality;

    public Speciality(int id, String speciality_group, String speciality) {
        this.id = id;
        this.speciality_group = speciality_group;
        this.speciality = speciality;
    }

    public int getId() {
        return id;
    }

    public String getSpeciality_group() {
        return speciality_group;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpeciality_group(String speciality_group) {
        this.speciality_group = speciality_group;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
