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

    @CrossOrigin(origins = "https://medhead-backend-d3ff39b61df1.herokuapp.com")
    @GetMapping("/Hospitals")
    public List<Hospital> getAllHospitals() {
        return hospitalDao.findAll();
    }

    @CrossOrigin(origins = "https://medhead-backend-d3ff39b61df1.herokuapp.com")
    @GetMapping(value = "/Hospitals/{id}")
    public Hospital getHospitalById(@PathVariable int id) {
        return hospitalDao.findById(id);
    }

    @CrossOrigin(origins = "https://medhead-backend-d3ff39b61df1.herokuapp.com")
    @PostMapping(value = "/Hospitals")
    public void addHospital(@RequestBody Hospital hospital) {
        hospitalDao.save(hospital);
    }
}