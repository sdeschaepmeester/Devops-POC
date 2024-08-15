package com.medhead.backend.web.dao;

import com.medhead.backend.model.Hospital;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HospitalDaoImpl implements HospitalDao {

    @Override
    public List<Hospital> findAll() {
        List<Hospital> hospitals = new ArrayList<>();
        String sql = "SELECT * FROM hospitals";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Hospital hospital = new Hospital(
                        rs.getInt("id"),
                        rs.getInt("available_beds"),
                        rs.getInt("latitude"),
                        rs.getInt("longitude"),
                        rs.getString("name")
                );
                hospitals.add(hospital);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hospitals;
    }

    @Override
    public Hospital findById(int id) {
        Hospital hospital = null;
        String sql = "SELECT * FROM hospitals WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                hospital = new Hospital(
                        rs.getInt("id"),
                        rs.getInt("available_beds"),
                        rs.getInt("latitude"),
                        rs.getInt("longitude"),
                        rs.getString("name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hospital;
    }

    @Override
    public Hospital save(Hospital hospital) {
        String sql = "INSERT INTO hospitals (availableBeds, longitude, latitude, name) VALUES (?, ?, ?, ?) RETURNING id";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(4, hospital.getName());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                hospital.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hospital;
    }
}