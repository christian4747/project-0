package com.revature.DAOs;

import com.revature.models.Astronaut;
import com.revature.models.Spaceship;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class AstronautDAO implements AstronautDAOInterface {
    @Override
    public ArrayList<Astronaut> getAllAstronauts() {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM astronauts";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<Astronaut> astronauts = new ArrayList<>();

            SpaceshipDAO sDAO = new SpaceshipDAO();

            while (rs.next()) {
                Astronaut newAstronaut = new Astronaut(
                        rs.getInt("astronaut_id"),
                        rs.getString("astronaut_name"),
                        rs.getDouble("astronaut_work_speed"),
                        rs.getDouble("astronaut_combat_speed"),
                        null
                );

                int spaceship_id_fk = rs.getInt("astronaut_spaceship_fk");

                Spaceship newSpaceship = sDAO.getSpaceshipById(spaceship_id_fk);

                newAstronaut.setAstronaut_spaceship(newSpaceship);

                astronauts.add(newAstronaut);

            }

            return astronauts;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public ArrayList<Astronaut> getHireableAstronauts() {
        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM astronauts WHERE astronaut_spaceship_fk IS NULL";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<Astronaut> astronauts = new ArrayList<>();

            SpaceshipDAO sDAO = new SpaceshipDAO();

            while (rs.next()) {
                Astronaut newAstronaut = new Astronaut(
                        rs.getInt("astronaut_id"),
                        rs.getString("astronaut_name"),
                        rs.getDouble("astronaut_work_speed"),
                        rs.getDouble("astronaut_combat_speed"),
                        null
                );

                int spaceship_id_fk = rs.getInt("astronaut_spaceship_fk");

                Spaceship newSpaceship = sDAO.getSpaceshipById(spaceship_id_fk);

                newAstronaut.setAstronaut_spaceship(newSpaceship);

                astronauts.add(newAstronaut);

            }

            return astronauts;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public ArrayList<Astronaut> getAstronautsBySpaceshipId(int spaceshipId) {
        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM astronauts WHERE astronaut_spaceship_fk = ?";

            PreparedStatement s = conn.prepareStatement(sql);
            s.setInt(1, spaceshipId);

            ResultSet rs = s.executeQuery();

            ArrayList<Astronaut> astronauts = new ArrayList<>();

            SpaceshipDAO sDAO = new SpaceshipDAO();

            while (rs.next()) {
                Astronaut newAstronaut = new Astronaut(
                        rs.getInt("astronaut_id"),
                        rs.getString("astronaut_name"),
                        rs.getDouble("astronaut_work_speed"),
                        rs.getDouble("astronaut_combat_speed"),
                        null
                );

                int spaceship_id_fk = rs.getInt("astronaut_spaceship_fk");

                Spaceship newSpaceship = sDAO.getSpaceshipById(spaceship_id_fk);

                newAstronaut.setAstronaut_spaceship(newSpaceship);

                astronauts.add(newAstronaut);

            }

            return astronauts;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getAstronautsOnSpaceshipNum(int spaceshipId) {
        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT COUNT(*) FROM astronauts WHERE astronaut_spaceship_fk = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, spaceshipId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Astronaut getAstronautById(int astronautId) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM astronauts WHERE astronaut_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, astronautId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Astronaut newAstronaut = new Astronaut(
                        rs.getInt("astronaut_id"),
                        rs.getString("astronaut_name"),
                        rs.getDouble("astronaut_work_speed"),
                        rs.getDouble("astronaut_combat_speed"),
                        null
                );

                int astronaut_spaceship_fk = rs.getInt("astronaut_spaceship_fk");
                if (astronaut_spaceship_fk != 0) {
                    SpaceshipDAO sDAO = new SpaceshipDAO();
                    newAstronaut.setAstronaut_spaceship(sDAO.getSpaceshipById(astronaut_spaceship_fk));
                }

                return newAstronaut;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Astronaut setAstronautShipIdFk(int astronautId, int spaceshipId) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            Astronaut a = getAstronautById(astronautId);
            SpaceshipDAO sDAO = new SpaceshipDAO();

            Spaceship s = sDAO.getSpaceshipById(spaceshipId);

            String sql = "UPDATE astronauts SET astronaut_spaceship_fk = ? WHERE astronaut_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, spaceshipId);
            ps.setInt(2, astronautId);

            ps.executeUpdate();

            a.setAstronaut_spaceship(s);
            a.setAstronaut_ship_fk(spaceshipId);

            return a;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Astronaut insertAstronaut(Astronaut astronaut) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO astronauts(astronaut_name, astronaut_work_speed, astronaut_combat_speed) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, astronaut.getAstronaut_name());
            ps.setDouble(2, astronaut.getAstronaut_work_speed());
            ps.setDouble(3, astronaut.getAstronaut_combat_speed());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if (keys.next()) {
                int newId = keys.getInt(1);
                astronaut.setAstronaut_id(newId);
            }

            return astronaut;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Astronaut setAstronautShipIdFkNull(int astronautId) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            Astronaut a = getAstronautById(astronautId);

            String sql = "UPDATE astronauts SET astronaut_spaceship_fk = NULL WHERE astronaut_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, astronautId);

            ps.executeUpdate();

            a.setAstronaut_spaceship(null);
            a.setAstronaut_ship_fk(0);

            return a;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Astronaut deleteAstronautById(int astronautId) {
        try (Connection conn = ConnectionUtil.getConnection()) {

            Astronaut deletedAstronaut = getAstronautById(astronautId);

            String sql = "DELETE FROM astronauts WHERE astronaut_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, astronautId);

            ps.executeUpdate();

            return deletedAstronaut;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
