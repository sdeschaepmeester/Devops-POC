package com.medhead.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "specialities")
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specialityGroup;
    private String speciality;

    // Constructeur sans argument requis par JPA
    public Speciality() {}

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialityGroup() {
        return specialityGroup;
    }

    public void setSpecialityGroup(String specialityGroup) {
        this.specialityGroup = specialityGroup;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", specialityGroup='" + specialityGroup + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }
}
