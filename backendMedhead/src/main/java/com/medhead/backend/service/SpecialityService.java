package com.medhead.backend.service;

import org.springframework.stereotype.Service;
import com.medhead.backend.model.Speciality;
import com.medhead.backend.repository.SpecialityRepository;

import java.util.List;

@Service
public class SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    public List<Speciality> getAllSpecialities() {
        try {
            return specialityRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch specialities", e);
        }
    }
}
