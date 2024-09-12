
package com.medhead.backend.web.dao;

import com.medhead.backend.model.Speciality;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SpecialityDaoImpl implements SpecialityDao {

    /**
     * Retrieves a list of all specialities from the database.
     * @return a list of Speciality objects representing all specialities in the system
     */
    @Override
    public List<Speciality> findAll() {
        List<Speciality> specialities = new ArrayList<>();
        String sql = "SELECT * FROM specialities";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Speciality speciality = new Speciality(
                        rs.getInt("id"),
                        rs.getString("speciality_group"),
                        rs.getString("speciality")
                        );
                specialities.add(speciality);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return specialities;
    }

    /**
     * Retrieves a speciality by its unique ID from the database.
     * @param id the unique identifier of the speciality
     * @return the Speciality object if found, or null if not found
     */
    @Override
    public Speciality findById(int id) {
        Speciality speciality = null;
        String sql = "SELECT * FROM specialities WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                speciality = new Speciality(
                        rs.getInt("id"),
                        rs.getString("speciality_group"),
                        rs.getString("speciality")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return speciality;
    }

}