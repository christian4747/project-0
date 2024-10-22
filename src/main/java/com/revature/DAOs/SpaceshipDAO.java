package com.revature.DAOs;

import com.revature.models.Astronaut;
import com.revature.models.Spaceship;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class SpaceshipDAO implements SpaceshipDAOInterface {

    @Override
    public ArrayList<Spaceship> getAllSpaceships() {
        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM spaceships";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<Spaceship> spaceships = new ArrayList<>();

            while (rs.next()) {
                Spaceship newSpaceship = new Spaceship(
                        rs.getInt("spaceship_id"),
                        rs.getString("spaceship_name"),
                        rs.getInt("spaceship_max_crew")
                );

                spaceships.add(newSpaceship);

            }

            return spaceships;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Spaceship getSpaceshipById(int id) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM spaceships WHERE spaceship_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

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

    @Override
    public String updateSpaceshipName(int shipId, String newName) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE spaceships SET spaceship_name = ? WHERE spaceship_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newName);
            ps.setInt(2, shipId);

            ps.executeUpdate();

            return newName;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public Spaceship insertSpaceship(Spaceship spaceship) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO spaceships(spaceship_name, spaceship_max_crew) VALUES (?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, spaceship.getSpaceship_name());
            ps.setInt(2, spaceship.getSpaceship_max_crew());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if (keys.next()) {
                int newId = keys.getInt(1);
                spaceship.setSpaceship_id(newId);
            }

            return spaceship;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Spaceship deleteSpaceshipById(int spaceshipId) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            Spaceship deletedSpaceship = getSpaceshipById(spaceshipId);

            String sql = "DELETE FROM spaceships WHERE spaceship_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, spaceshipId);

            ps.executeUpdate();

            return deletedSpaceship;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Spaceship getSpaceshipByName(String name) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM spaceships WHERE spaceship_name = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Spaceship(
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
