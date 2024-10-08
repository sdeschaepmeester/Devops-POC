package com.medhead.backend.web.controller;

import com.medhead.backend.model.Hospital;
import com.medhead.backend.model.Speciality;
import com.medhead.backend.web.dao.HospitalDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class HospitalControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HospitalDao hospitalDao;

    @InjectMocks
    private HospitalController hospitalController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(hospitalController).build();
    }

    @Test
    public void testGetHospitalsNearby() throws Exception {
        // Simulate hospitals
        Speciality speciality = new Speciality(25, "General medicine group", "Dermatology");
        Hospital hospital1 = new Hospital(2, 15, 49.001890151438, 2.27061330799479, "Hôpital Simone Veil", Arrays.asList(speciality.getSpeciality()).toArray(new String[0]));
        Hospital hospital2 = new Hospital(9, 15, 48.8370544694846, 2.29436158376076, "Hôpital Vaugirard AP-HP", Arrays.asList(speciality.getSpeciality()).toArray(new String[0]));

        List<Hospital> mockHospitals = Arrays.asList(hospital1, hospital2);

        when(hospitalDao.getHospitalsNearby(48.8566, 2.3522, "speciality1")).thenReturn(mockHospitals);

        mockMvc.perform(get("/Hospitals/nearby")
                        .param("latitude", "48.8566")
                        .param("longitude", "2.3522")
                        .param("specialityId", "speciality1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Vérifier que la réponse est OK (200)
                .andExpect(jsonPath("$[0].name").value("Hôpital Simone Veil"))
                .andExpect(jsonPath("$[1].name").value("Hôpital Vaugirard AP-HP"));
    }

    @Test
    public void testGetHospitalList() throws Exception {
        Speciality speciality = new Speciality(25, "General medicine group", "Dermatology");
        Hospital hospital1 = new Hospital(2, 15, 49.001890151438, 2.27061330799479, "Hôpital Simone Veil", Arrays.asList(speciality.getSpeciality()).toArray(new String[0]));
        Hospital hospital2 = new Hospital(9, 15, 48.8370544694846, 2.29436158376076, "Hôpital Vaugirard AP-HP", Arrays.asList(speciality.getSpeciality()).toArray(new String[0]));

        List<Hospital> mockHospitals = Arrays.asList(hospital1, hospital2);

        when(hospitalDao.findAll()).thenReturn(mockHospitals);

        mockMvc.perform(get("/Hospitals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Hôpital Simone Veil"))
                .andExpect(jsonPath("$[1].name").value("Hôpital Vaugirard AP-HP"));
    }

    @Test
    public void testGetHospitalById() throws Exception {
        Speciality speciality = new Speciality(25, "General medicine group", "Dermatology");

        Hospital hospital = new Hospital(2, 15, 49.001890151438, 2.27061330799479, "Hôpital Simone Veil", Arrays.asList(speciality.getSpeciality()).toArray(new String[0]));
        when(hospitalDao.findById(2)).thenReturn(hospital);

        mockMvc.perform(get("/Hospitals/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hôpital Simone Veil"))
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    public void testReserveHospital_Success() throws Exception {
        // Successful reservation
        when(hospitalDao.reserveHospital(2)).thenReturn(true);

        mockMvc.perform(post("/Hospitals/{id}/reserve", 2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testReserveHospital_Failure() throws Exception {
        // Failed reservation
        when(hospitalDao.reserveHospital(-99)).thenReturn(false);

        mockMvc.perform(post("/Hospitals/{id}/reserve", 2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
