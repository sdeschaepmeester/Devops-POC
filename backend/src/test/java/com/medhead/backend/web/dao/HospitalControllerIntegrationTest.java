package com.medhead.backend.web.dao;

import com.medhead.backend.model.Hospital;
import com.medhead.backend.model.Speciality;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class HospitalControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HospitalDao hospitalDao;

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testGetHospitalsNearby() throws Exception {
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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Hôpital Simone Veil"))
                .andExpect(jsonPath("$[1].name").value("Hôpital Vaugirard AP-HP"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testReserveHospital_Success() throws Exception {
        when(hospitalDao.reserveHospital(2)).thenReturn(true);

        mockMvc.perform(post("/Hospitals/{id}/reserve", 2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Reservation successful"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testReserveHospital_Failure() throws Exception {
        when(hospitalDao.reserveHospital(2)).thenReturn(false);

        mockMvc.perform(post("/Hospitals/{id}/reserve", 2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Could not reserve a bed: hospital not found or no available beds."));
    }
}