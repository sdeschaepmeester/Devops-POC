package com.medhead.backend.service;

import org.springframework.beans.factory.annotation.Autowired; import
org.springframework.stereotype.Service; import
com.medhead.backend.model.Speciality; import
com.medhead.backend.repository.SpecialityRepository;

import java.util.List;

@Service public class SpecialityService {

@Autowired private SpecialityRepository specialityRepository;

public List<Speciality> getAllSpecialities() { return
specialityRepository.findAll(); } }
 