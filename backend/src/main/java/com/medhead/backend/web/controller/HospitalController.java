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

    @GetMapping("/Hospitals")
    public List<Hospital> getAllHospitals() {
        return hospitalDao.findAll();
    }

    @GetMapping(value = "/Hospitals/{id}")
    public Hospital getHospitalById(@PathVariable int id) {
        return hospitalDao.findById(id);
    }

    @PostMapping(value = "/Hospitals")
    public void addHospital(@RequestBody Hospital hospital) {
        hospitalDao.save(hospital);
    }
}