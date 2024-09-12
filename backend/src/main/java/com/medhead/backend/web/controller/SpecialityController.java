package com.medhead.backend.web.controller;
import com.medhead.backend.web.dao.SpecialityDao;
import com.medhead.backend.model.Speciality;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpecialityController {

    private final SpecialityDao specialityDao;

    public SpecialityController(SpecialityDao specialityDao){
        this.specialityDao = specialityDao;
    }

    /**
     * Retrieves all the possible specialities of hospitals.
     * @return the list of Specialities.
     */
    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @GetMapping("/Specialities")
    public List<Speciality> getAllSpecialities() {
        return specialityDao.findAll();
    }

    /**
     * Retrieves details of a speciality by its ID.
     * @param id the ID of the speciality to be retrieved
     * @return the Speciality object corresponding to the provided ID
     */
    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @GetMapping(value = "/Specialities/{id}")
    public Speciality getSpecialityById(@PathVariable int id) {
        return specialityDao.findById(id);
    }

}