package com.revature;

import com.revature.controllers.AstronautController;
import com.revature.controllers.AuthController;
import com.revature.controllers.SpaceshipController;
import io.javalin.Javalin;
import io.javalin.http.UnauthorizedResponse;

public class Launcher {

    public static void main(String[] args) {

        var app = Javalin.create().start(7000);

        SpaceshipController sc = new SpaceshipController();
        AstronautController ac = new AstronautController();
        AuthController auc = new AuthController();

        app.before("/spaceships", ctx -> {
            if (AuthController.ses == null) {
                throw new UnauthorizedResponse("You must login before doing this!");
            }
        });

        app.before("/spaceships/*", ctx -> {
            if (AuthController.ses == null) {
                throw new UnauthorizedResponse("You must login before doing this!");
            }
        });

        app.before("/astronauts", ctx -> {
            if (AuthController.ses == null) {
                throw new UnauthorizedResponse("You must login before doing this!");
            }
        });

        app.before("/astronauts/*", ctx -> {
            if (AuthController.ses == null) {
                throw new UnauthorizedResponse("You must login before doing this!");
            }
        });

        app.exception(UnauthorizedResponse.class, (e, ctx) -> {
            ctx.status(401);
            ctx.result(e.getMessage());
        });

        // Spaceship Login
        app.post("/auth", auc.loginHandler);

        // Get all spaceships
        app.get("/spaceships", sc.getAllSpaceships);

        // Get all astronauts
        app.get("/astronauts", ac.getAllAstronauts);

        // Get all astronauts that can be hired
        app.get("/astronauts/hireable", ac.getHireableAstronauts);

        // Get an Astronaut by ID
        app.get("/astronauts/{id}", ac.getAstronautById);

        // Get a spaceship by ID
        app.get("/spaceships/{id}", sc.getSpaceshipById);

        // Get a spaceship's astronauts
        app.get("/astronauts/spaceships/{id}", ac.getSpaceshipAstronauts);

        // Get the number of astronauts on a spaceship
        app.get("/astronauts/spaceships/{id}/count", ac.getCurrentSpaceshipAstronautCount);

        // Insert a new spaceship
        app.post("/spaceships", sc.insertSpaceship);

        // Insert a new astronaut
        app.post("/astronauts", ac.insertAstronaut);

        // Hire an astronaut onto a spaceship
        app.patch("/spaceships/{spaceship_id}/astronauts/{astronaut_id}", ac.hireAstronautOnSpaceshipId);

        // Fire an astronaut
        app.patch("/astronauts/{astronaut_id}/spaceships/{spaceship_id}", ac.fireAstronautFromSpaceship);

        // Fire all astronauts from a spaceship
        app.put("/spaceships/{id}/fire-all-astronauts", ac.fireAllAstronautsFromSpaceship);

        // Delete a spaceship
        app.delete("/spaceships/{id}", sc.deleteSpaceship);

        // Delete an astronaut
        app.delete("/astronauts/{id}", ac.deleteAstronaut);

    }

}
