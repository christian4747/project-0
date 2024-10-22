package com.revature.DAOs;

import com.revature.models.Astronaut;
import com.revature.models.Spaceship;

import java.util.ArrayList;

public interface SpaceshipDAOInterface {

    ArrayList<Spaceship> getAllSpaceships();

    Spaceship getSpaceshipById(int id);

    String updateSpaceshipName(int spaceshipId, String newName);

    Spaceship insertSpaceship(Spaceship spaceship);

    Spaceship deleteSpaceshipById(int spaceshipId);

    Spaceship getSpaceshipByName(String name);

}
