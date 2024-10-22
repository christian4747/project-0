package com.revature.controllers;

import com.revature.DAOs.AuthDAO;
import com.revature.models.LoginDTO;
import com.revature.models.Spaceship;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;

public class AuthController {

    AuthDAO aDAO = new AuthDAO();

    // HttpSession object to store user info after successful log in
    // This object will be initialized upon successful login, letting a user access the app
    public static HttpSession ses;

    // POST request (we're sending a username and password)
    public Handler loginHandler = ctx -> {

        // Extract the Request body as a LoginDTO object
        LoginDTO lDTO = ctx.bodyAsClass(LoginDTO.class);

        // Use the LoginDTO data to send to the DAO and try to log in
        Spaceship loggedInSpaceship = aDAO.login(lDTO.getSpaceship_id(), lDTO.getSpaceship_name());

        if (loggedInSpaceship != null) {
            // Create a session object
            ses = ctx.req().getSession();

            // Store user info in the Session with the setAttribute() method
            ses.setAttribute("spaceship_name", loggedInSpaceship.getSpaceship_name());
            ses.setAttribute("spaceship_max_crew", loggedInSpaceship.getSpaceship_max_crew());
            // NOTE: We could have stored any kind of info in the Session like this

            // Foreshadowing for P1:
            // Each Employee will have a role of employee or manager
            // Managers will be able to do things that Employees can't
            // Their identity as a manager or employee will be stored in their session after login
            // Which will let us control what functionalities they can and can't access

            // Accessing stored session data

            // Send a success message
            ctx.status(200).result("Login successful! Welcome, " + ses.getAttribute("spaceship_name") + ".");

        } else {
            // If log in fails, a good status code is 401 - Unauthorized
            ctx.status(401);
            ctx.result("Invalid spaceship_id or spaceship_name! Please try again.");
        }


    };



}
