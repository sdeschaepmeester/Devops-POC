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

    /**
     * Retrieves a list of all hospitals.
     * @return a List of Hospital objects containing details of all hospitals.
     */
    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @GetMapping("/Hospitals")
    public List<Hospital> getAllHospitals() {
        return hospitalDao.findAll();
    }

    /**
     * Retrieves details of a hospital by its ID.
     * @param id the ID of the hospital to be retrieved
     * @return the Hospital object corresponding to the provided ID
     */
    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @GetMapping(value = "/Hospitals/{id}")
    public Hospital getHospitalById(@PathVariable int id) {
        return hospitalDao.findById(id);
    }

    /**
     * Adds a new hospital to the database.
     * @param hospital the Hospital object to be added
     */
    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @PostMapping(value = "/Hospitals")
    public void addHospital(@RequestBody Hospital hospital) {
        hospitalDao.save(hospital);
    }

    /**
     * Retrieves nearby hospitals based on location and specialty.
     * @param latitude the latitude of the current location
     * @param longitude the longitude of the current location
     * @param specialityId the ID of the required hospital specialty
     * @return the Hospital object closest to the given location with the specified specialty
     */
    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @GetMapping("/Hospitals/nearby")
    public List<Hospital> getHospitalsNearby(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam String specialityId) {
        return hospitalDao.getHospitalsNearby(latitude, longitude, specialityId);
    }
}