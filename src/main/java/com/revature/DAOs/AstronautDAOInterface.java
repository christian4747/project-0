package com.revature.DAOs;

import com.revature.models.Astronaut;

import java.util.ArrayList;

public interface AstronautDAOInterface {

    ArrayList<Astronaut> getAllAstronauts();

    ArrayList<Astronaut> getHireableAstronauts();

    ArrayList<Astronaut> getAstronautsBySpaceshipId(int spaceshipId);

    int getAstronautsOnSpaceshipNum(int spaceshipId);

    Astronaut getAstronautById(int astronautId);

    Astronaut setAstronautShipIdFk(int astronautId, int spaceshipId);

    Astronaut insertAstronaut(Astronaut astronaut);

    Astronaut setAstronautShipIdFkNull(int astronautId);

    Astronaut deleteAstronautById(int astronautId);

}
