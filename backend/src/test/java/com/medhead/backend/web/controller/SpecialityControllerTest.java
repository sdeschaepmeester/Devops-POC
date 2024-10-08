package com.medhead.backend.web.controller;

import com.medhead.backend.model.Speciality;
import com.medhead.backend.web.dao.SpecialityDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class SpecialityControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SpecialityDao specialityDao;

    @InjectMocks
    private SpecialityController specialityController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(specialityController).build();
    }

    @Test
    public void testGetSpecialities() throws Exception {
        Speciality speciality1 = new Speciality(1, "Surgery group", "Cardiology");
        Speciality speciality2 = new Speciality(2, "Medical group", "Neurology");

        List<Speciality> mockSpecialities = Arrays.asList(speciality1, speciality2);

        when(specialityDao.findAll()).thenReturn(mockSpecialities);

        mockMvc.perform(get("/Specialities")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].speciality_group").value("Surgery group"))
                .andExpect(jsonPath("$[0].speciality").value("Cardiology"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].speciality_group").value("Medical group"))
                .andExpect(jsonPath("$[1].speciality").value("Neurology"));
    }

    @Test
    public void testGetSpecialityById() throws Exception {
        Speciality speciality = new Speciality(25, "General medicine group", "Dermatology");

        when(specialityDao.findById(25)).thenReturn(speciality);

        mockMvc.perform(get("/Specialities/{id}", 25)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.speciality_group").value("General medicine group"))
                .andExpect(jsonPath("$.speciality").value("Dermatology"));
    }
}