package com.medhead.backend.web.dao;

import com.medhead.backend.model.Hospital;
import java.util.List;

public interface HospitalDao {
    List<Hospital> findAll();
    Hospital findById(int id);
    Hospital save(Hospital hospital);
    List<Hospital> getHospitalsNearby(double latitude, double longitude, String specialityId);
}
