package com.medhead.backend.web.controller;
import com.medhead.backend.web.dao.HospitalDao;
import com.medhead.backend.model.Hospital;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HospitalController {

    private final HospitalDao hospitalDao;

    public HospitalController(HospitalDao hospitalDao){
        this.hospitalDao = hospitalDao;
    }

    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @GetMapping("/Hospitals")
    public List<Hospital> getAllHospitals() {
        return hospitalDao.findAll();
    }

    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @GetMapping(value = "/Hospitals/{id}")
    public Hospital getHospitalById(@PathVariable int id) {
        return hospitalDao.findById(id);
    }

    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @PostMapping(value = "/Hospitals")
    public void addHospital(@RequestBody Hospital hospital) {
        hospitalDao.save(hospital);
    }

    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @GetMapping("/Hospitals/nearby")
    public Hospital getHospitalsNearby(@RequestBody double latitude, double longitude, String specialityId) {
        return hospitalDao.getHospitalsNearby(latitude, longitude, specialityId);
    }
}