package com.revature.controllers;

import com.revature.DAOs.AstronautDAO;
import com.revature.DAOs.SpaceshipDAO;
import com.revature.models.Astronaut;
import com.revature.models.Spaceship;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class AstronautController {

    AstronautDAO aDAO = new AstronautDAO();
    SpaceshipDAO sDAO = new SpaceshipDAO();

    public Handler getSpaceshipAstronauts = ctx -> {

        int spaceshipId = Integer.parseInt(ctx.pathParam("id"));

        if (sDAO.getSpaceshipById(spaceshipId) == null) {
            ctx.result("Spaceship with ID " + spaceshipId + " does not exist!");
            ctx.status(200);
        } else {
            ctx.json(aDAO.getAstronautsBySpaceshipId(spaceshipId));
            ctx.status(200);
        }

    };

    public Handler getCurrentSpaceshipAstronautCount = ctx -> {

        int spaceshipId = Integer.parseInt(ctx.pathParam("id"));

        int count = aDAO.getAstronautsOnSpaceshipNum(spaceshipId);

        ctx.json(count);
        ctx.status(200);
    };

    public Handler getAllAstronauts = ctx -> {
        ctx.json(aDAO.getAllAstronauts());
        ctx.status(200);
    };

    public Handler getAstronautById = ctx -> {
        int astronautId = Integer.parseInt(ctx.pathParam("id"));

        Astronaut a = aDAO.getAstronautById(astronautId);

        if (a == null) {
            ctx.result("Astronaut with ID " + astronautId + " not found!");
            ctx.status(404);
        } else {
            ctx.json(aDAO.getAstronautById(astronautId));
            ctx.status(200);
        }
    };

    public Handler getHireableAstronauts = ctx -> {
        ctx.json(aDAO.getHireableAstronauts());
        ctx.status(200);
    };

    public Handler hireAstronautOnSpaceshipId = ctx -> {
        int astronautId = Integer.parseInt(ctx.pathParam("astronaut_id"));
        int spaceshipId = Integer.parseInt(ctx.pathParam("spaceship_id"));

        Astronaut a = aDAO.getAstronautById(astronautId);
        Spaceship s = sDAO.getSpaceshipById(spaceshipId);
        int astronautsAboard = aDAO.getAstronautsOnSpaceshipNum(spaceshipId);

        // Astronaut is null
        if (a == null) {
            ctx.result("Given Astronaut ID " + astronautId + " does not exist!");
            ctx.status(400);
        // Spaceship is null
        } else if (s == null) {
            ctx.result("Given Spaceship ID " + spaceshipId + " does not exist!");
            ctx.status(400);
        // The Astronaut already belongs to a Spaceship
        } else if (a.getAstronaut_spaceship() != null) {
            ctx.result(astronautId + " is already hired to " + a.getAstronaut_spaceship().getSpaceship_name() + "!");
            ctx.status(400);
        // The Spaceship is full
        } else if (s.getSpaceship_max_crew() == astronautsAboard) {
            ctx.result(s.getSpaceship_name() + " is full!");
            ctx.status(400);
        } else {
            a = aDAO.setAstronautShipIdFk(astronautId, spaceshipId);
            ctx.json(a);
            ctx.status(200);
        }

    };

    public Handler insertAstronaut = ctx -> {
        Astronaut newAstronaut = ctx.bodyAsClass(Astronaut.class);

        ctx.json(aDAO.insertAstronaut(newAstronaut));
        ctx.status(200);
    };

    public Handler fireAstronautFromSpaceship = ctx -> {
        int astronautId = Integer.parseInt(ctx.pathParam("astronaut_id"));
        int spaceshipId = Integer.parseInt(ctx.pathParam("spaceship_id"));

        Astronaut a = aDAO.getAstronautById(astronautId);
        Spaceship s = sDAO.getSpaceshipById(spaceshipId);

        if (a == null) {
            ctx.result("Given Astronaut ID " + astronautId + " does not exist!");
            ctx.status(400);
            // Spaceship is null
        } else if (s == null) {
            ctx.result("Given Spaceship ID " + spaceshipId + " does not exist!");
            ctx.status(400);
            // The Astronaut already belongs to a Spaceship
        } else if (a.getAstronaut_spaceship() == null) {
            ctx.result("Given Astronaut ID " + astronautId + " does not work for a Spaceship!");
            ctx.status(400);
        } else if (a.getAstronaut_spaceship().getSpaceship_id() != s.getSpaceship_id()) {
            ctx.result(a.getAstronaut_name() + " was not found in Spaceship " + s.getSpaceship_name() + "!");
            ctx.status(400);
        } else {
            ctx.json(aDAO.setAstronautShipIdFkNull(astronautId));
            ctx.status(200);
        }
    };

    public Handler fireAllAstronautsFromSpaceship = ctx -> {
        int spaceshipId = Integer.parseInt(ctx.pathParam("id"));

        Spaceship s = sDAO.getSpaceshipById(spaceshipId);

        if (s == null) {
            ctx.result("Spaceship with ID " + spaceshipId + " does not exist!");
            ctx.status(400);
        } else {
            ArrayList<Astronaut> astronauts = aDAO.getAstronautsBySpaceshipId(spaceshipId);
            ArrayList<Astronaut> updatedList = new ArrayList<>();

            for (Astronaut a : astronauts) {
                updatedList.add(aDAO.setAstronautShipIdFkNull(a.getAstronaut_id()));
            }

            ctx.json(updatedList);
            ctx.status(200);

        }

    };

    public Handler deleteAstronaut = ctx -> {
        int astronautId = Integer.parseInt(ctx.pathParam("id"));

        Astronaut astronautToDelete = aDAO.getAstronautById(astronautId);

        if (astronautToDelete == null) {
            ctx.result("Astronaut with ID " + astronautId + " doesn't exist!");
            ctx.status(400);
        } else {
            ctx.json(aDAO.deleteAstronautById(astronautId));
            ctx.status(200);
        }
    };

}
