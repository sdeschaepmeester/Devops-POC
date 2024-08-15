package com.medhead.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.medhead.backend.model.Speciality;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
}