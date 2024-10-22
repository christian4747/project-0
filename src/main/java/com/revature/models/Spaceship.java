package com.revature.models;

public class Spaceship {

    private int spaceship_id;
    private String spaceship_name;
    private int spaceship_max_crew;

    public Spaceship() {
        this.spaceship_max_crew = 4;
    }

    public Spaceship(int spaceship_id, String spaceship_name, int spaceship_max_crew) {
        this.spaceship_id = spaceship_id;
        this.spaceship_name = spaceship_name;
        this.spaceship_max_crew = spaceship_max_crew;
    }

    // DB will generate spaceship_id
    public Spaceship(String spaceship_name, int spaceship_max_crew) {
        this.spaceship_name = spaceship_name;
        this.spaceship_max_crew = spaceship_max_crew;
    }

    // Spaceship without max crew (default 4)
    public Spaceship(String spaceship_name) {
        System.out.println("Construction is happening");
        this.spaceship_name = spaceship_name;
        this.spaceship_max_crew = 4;
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

    public int getSpaceship_max_crew() {
        return spaceship_max_crew;
    }

    public void setSpaceship_max_crew(int spaceship_max_crew) {
        this.spaceship_max_crew = spaceship_max_crew;
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "spaceship_id=" + spaceship_id +
                ", spaceship_name='" + spaceship_name + '\'' +
                ", spaceship_max_crew=" + spaceship_max_crew +
                '}';
    }
}
