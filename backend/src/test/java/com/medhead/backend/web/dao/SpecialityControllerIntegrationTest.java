package com.medhead.backend.web.dao;

import com.medhead.backend.BackendApplication;
import com.medhead.backend.model.Speciality;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = BackendApplication.class)
@AutoConfigureMockMvc
public class SpecialityControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecialityDao specialityDao;

    @Test
    @WithMockUser
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