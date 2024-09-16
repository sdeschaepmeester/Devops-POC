package com.medhead.backend.model;

public class Hospital {
    private int id;
    private int available_beds;
    private double latitude;
    private double longitude;
    private String name;
    private String[] specialities;

    public Hospital(int id, int available_beds, double latitude, double longitude, String name, String[] specialities) {
        this.id = id;
        this.available_beds = available_beds;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.specialities = specialities;
    }

    public int getId() {
        return id;
    }

    public int getAvailable_beds() {
        return available_beds;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAvailable_beds(int available_beds) {
        this.available_beds = available_beds;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", nom='" + name + '\'' +
                ", beds=" + available_beds +
                '}';
    }

    public String[] getSpecialities() {
        return specialities;
    }

    public void setSpecialities(String[] specialities) {
        this.specialities = specialities;
    }
}
