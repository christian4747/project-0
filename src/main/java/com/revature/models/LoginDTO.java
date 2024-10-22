package com.revature.models;

// What is a DTO?
// A data transfer object. It's meant to model some data that doesn't pertain to a DB table.
// Maybe we have login functionality that only takes username/password
// We want a user to be able to log in with ONLY their username/password instead of an entire Employee object
public class LoginDTO {

    // We just need this object to store employee_id and first_name to help with login
    private int spaceship_id;
    private String spaceship_name;

    // Boilerplate code
    public LoginDTO() {
    }

    public LoginDTO(int spaceship_id, String spaceship_name) {
        this.spaceship_id = spaceship_id;
        this.spaceship_name = spaceship_name;
    }

    public int getSpaceship_id() {
        return spaceship_id;
    }

    public void setSpaceship_id(int spaceship_id) {
        this.spaceship_id = spaceship_id;
    }

    public String getSpaceship_name() {
        return spaceship_name;
    }

    public void setSpaceship_name(String spaceship_name) {
        this.spaceship_name = spaceship_name;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "spaceship_id=" + spaceship_id +
                ", spaceship_name='" + spaceship_name + '\'' +
                '}';
    }
}
