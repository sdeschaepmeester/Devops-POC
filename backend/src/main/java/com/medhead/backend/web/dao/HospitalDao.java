package com.medhead.backend.web.dao;

import com.medhead.backend.model.Hospital;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public interface HospitalDao {
    List<Hospital> findAll();
    Hospital findById(int id);
    Hospital save(Hospital hospital);

}
