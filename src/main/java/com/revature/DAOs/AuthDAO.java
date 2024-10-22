package com.revature.DAOs;

import com.revature.models.Spaceship;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {

    public Spaceship login(int spaceship_id, String spaceship_name) {

        // Open a connection
        try (Connection conn = ConnectionUtil.getConnection()) {

            // Create our SQL String
            String sql = "SELECT * FROM spaceships WHERE spaceship_id = ? AND spaceship_name = ?";

            // PreparedStatement and fill in the blanks
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, spaceship_id);
            ps.setString(2, spaceship_name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Spaceship(
                        rs.getInt("spaceship_id"),
                        rs.getString("spaceship_name"),
                        rs.getInt("spaceship_max_crew")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
