package com.medhead.backend.web.dao;
import com.medhead.backend.model.Speciality;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public interface SpecialityDao {
    List<Speciality> findAll();
    Speciality findById(int id);
}