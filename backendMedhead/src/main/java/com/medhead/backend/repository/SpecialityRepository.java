package com.medhead.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medhead.backend.model.Speciality;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
}