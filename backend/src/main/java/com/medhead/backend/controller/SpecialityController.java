package com.medhead.backend.controller;

import com.medhead.backend.model.Speciality;
import com.medhead.backend.service.SpecialityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/specialities")
public class SpecialityController {

    private final SpecialityService specialityService;

    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping
    public ResponseEntity<List<Speciality>> getAllSpecialities() {
        try {
            List<Speciality> specialities = specialityService.getAllSpecialities();
            return new ResponseEntity<>(specialities, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception and return an appropriate error response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

