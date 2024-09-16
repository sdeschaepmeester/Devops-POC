package com.medhead.backend.web.dao;

import com.medhead.backend.model.Hospital;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HospitalDaoImpl implements HospitalDao {

    /**
     * Retrieves a list of all hospitals from the database.
     * @return a list of Hospital objects representing all hospitals in the database
     */
    @Override
    public List<Hospital> findAll() {
        List<Hospital> hospitals = new ArrayList<>();
        String sql = "SELECT * FROM hospitals";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Array specialitiesArray = rs.getArray("specialities_id");
                String[] specialities = specialitiesArray != null ? (String[]) specialitiesArray.getArray() : new String[0];

                Hospital hospital = new Hospital(
                        rs.getInt("id"),
                        rs.getInt("available_beds"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getString("name"),
                        specialities
                );
                hospitals.add(hospital);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hospitals;
    }

    /**
     * Finds a hospital by its unique ID.
     * @param id the ID of the hospital to find
     * @return the Hospital object with the specified ID, or null if not found
     */
    @Override
    public Hospital findById(int id) {
        Hospital hospital = null;
        String sql = "SELECT * FROM hospitals WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Array specialitiesArray = rs.getArray("specialities_id");
                String[] specialities = specialitiesArray != null ? (String[]) specialitiesArray.getArray() : new String[0];
                hospital = new Hospital(
                        rs.getInt("id"),
                        rs.getInt("available_beds"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getString("name"),
                        specialities
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hospital;
    }

    /**
     * Saves a new hospital to the database and returns the saved Hospital object with its generated ID.
     * @param hospital the Hospital object to save
     * @return the saved Hospital object with its generated ID
     */
    @Override
    public Hospital save(Hospital hospital) {
        String sql = "INSERT INTO hospitals (available_beds, longitude, latitude, name, specialities_id) VALUES (?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, hospital.getAvailable_beds());
            stmt.setDouble(2, hospital.getLongitude());
            stmt.setDouble(3, hospital.getLatitude());
            stmt.setString(4, hospital.getName());
            Array specialitiesArray = conn.createArrayOf("varchar", hospital.getSpecialities());
            stmt.setArray(5, specialitiesArray);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    hospital.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hospital;
    }

    /**
     * Retrieves nearby hospitals based on location and specialty.
     * @param latitude the latitude of the current location
     * @param longitude the longitude of the current location
     * @param specialityId the ID of the required hospital specialty
     * @return a list of the 4 nearest Hospital objects matching the specialty criteria
     */
    @Override
    public List<Hospital> getHospitalsNearby(double latitude, double longitude, String specialityId) {
        List<Hospital> nearestHospitals = new ArrayList<>();
        String sql = """
        SELECT hospital.id AS id,
               hospital.name AS name,
               hospital.latitude AS latitude,
               hospital.longitude AS longitude,
               hospital.available_beds AS available_beds,
               hospital.specialities_id AS specialities_id,
               6371 * 2 * ASIN(SQRT(
                   POWER(SIN((? - ABS(hospital.latitude)) * PI() / 180 / 2), 2) +
                   COS(? * PI() / 180) * COS(ABS(hospital.latitude) * PI() / 180) *
                   POWER(SIN((? - hospital.longitude) * PI() / 180 / 2), 2)
               )) AS distance
        FROM hospitals hospital
        WHERE hospital.available_beds > 0
        AND hospital.specialities_id @> ARRAY[?]::text[]
        ORDER BY distance
        LIMIT 4;
        """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, latitude);
            stmt.setDouble(2, longitude);
            stmt.setDouble(3, latitude);
            stmt.setString(4, specialityId);

            System.out.println("Executing SQL Query: " + stmt.toString());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Array sqlArray = rs.getArray("specialities_id");
                    String[] specialities = (String[]) sqlArray.getArray();

                    Hospital hospital = new Hospital(
                            rs.getInt("id"),
                            rs.getInt("available_beds"),
                            rs.getDouble("latitude"),
                            rs.getDouble("longitude"),
                            rs.getString("name"),
                            specialities
                    );
                    nearestHospitals.add(hospital);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nearestHospitals;
    }

}