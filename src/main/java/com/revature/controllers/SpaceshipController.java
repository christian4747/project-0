package com.revature.controllers;

import com.revature.DAOs.AstronautDAO;
import com.revature.DAOs.SpaceshipDAO;
import com.revature.models.Astronaut;
import com.revature.models.Spaceship;
import io.javalin.http.Handler;

public class SpaceshipController {

    SpaceshipDAO sDAO = new SpaceshipDAO();

    public Handler getSpaceshipById = ctx -> {
        int spaceshipId = Integer.parseInt(ctx.pathParam("id"));

        Spaceship s = sDAO.getSpaceshipById(spaceshipId);

        if (s == null) {
            ctx.result("Spaceship with ID " + spaceshipId + " not found!");
            ctx.status(404);
        } else {
            ctx.json(s);
            ctx.status(200);
        }
    };

    public Handler getAllSpaceships = ctx -> {
        ctx.json(sDAO.getAllSpaceships());
        ctx.status(200);
    };

    public Handler insertSpaceship = ctx -> {
        Spaceship newSpaceship = ctx.bodyAsClass(Spaceship.class);

        Spaceship existingSpaceship = sDAO.getSpaceshipByName(newSpaceship.getSpaceship_name());
        if (existingSpaceship != null) {
            ctx.result("A Spaceship with the name " + newSpaceship.getSpaceship_name() + " already exists!");
            ctx.status(400);
        } else {
            ctx.json(sDAO.insertSpaceship(newSpaceship));
            ctx.status(200);
        }
    };

    public Handler deleteSpaceship = ctx -> {
        int spaceshipId = Integer.parseInt(ctx.pathParam("id"));

        Spaceship spaceshipToDelete = sDAO.getSpaceshipById(spaceshipId);

        if (spaceshipToDelete == null) {
            ctx.result("Spaceship with ID " + spaceshipId + " doesn't exist!");
            ctx.status(400);
        } else {
            ctx.json(sDAO.deleteSpaceshipById(spaceshipId));
            ctx.status(200);
        }
    };

}
