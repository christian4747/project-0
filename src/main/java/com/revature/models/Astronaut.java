package com.revature.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Astronaut {

    private int astronaut_id;
    private String astronaut_name;
    private double astronaut_work_speed;
    private double astronaut_combat_speed;

    private Spaceship astronaut_spaceship;

    private int astronaut_ship_fk;

    private double randomValueBetween0And2() {
        Random r = new Random();
        return roundTwoDecimalPoints(r.nextDouble() * 2);
    }

    private double roundTwoDecimalPoints(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public Astronaut() {
        Random r = new Random();
        this.astronaut_work_speed = randomValueBetween0And2();
        this.astronaut_combat_speed = randomValueBetween0And2();
    }

    public Astronaut(int astronaut_id, String astronaut_name, double astronaut_work_speed, double astronaut_combat_speed, Spaceship astronaut_spaceship) {
        this.astronaut_id = astronaut_id;
        this.astronaut_name = astronaut_name;
        this.astronaut_work_speed = astronaut_work_speed;
        this.astronaut_combat_speed = astronaut_combat_speed;
        this.astronaut_spaceship = astronaut_spaceship;
    }

    public int getAstronaut_id() {
        return astronaut_id;
    }

    public void setAstronaut_id(int astronaut_id) {
        this.astronaut_id = astronaut_id;
    }

    public String getAstronaut_name() {
        return astronaut_name;
    }

    public void setAstronaut_name(String astronaut_name) {
        this.astronaut_name = astronaut_name;
    }

    public double getAstronaut_work_speed() {
        return astronaut_work_speed;
    }

    public void setAstronaut_work_speed(double astronaut_work_speed) {
        this.astronaut_work_speed = astronaut_work_speed;
    }

    public double getAstronaut_combat_speed() {
        return astronaut_combat_speed;
    }

    public void setAstronaut_combat_speed(double astronaut_combat_speed) {
        this.astronaut_combat_speed = astronaut_combat_speed;
    }

    public Spaceship getAstronaut_spaceship() {
        return astronaut_spaceship;
    }

    public void setAstronaut_spaceship(Spaceship astronaut_spaceship) {
        this.astronaut_spaceship = astronaut_spaceship;
    }

    public int getAstronaut_ship_fk() {
        return astronaut_ship_fk;
    }

    public void setAstronaut_ship_fk(int astronaut_ship_fk) {
        this.astronaut_ship_fk = astronaut_ship_fk;
    }

    @Override
    public String toString() {
        return "Astronaut{" +
                "astronaut_id=" + astronaut_id +
                ", astronaut_name='" + astronaut_name + '\'' +
                ", astronaut_working_speed=" + astronaut_work_speed +
                ", astronaut_combat_speed=" + astronaut_combat_speed +
                ", astronaut_spaceship=" + astronaut_spaceship +
                ", astronaut_ship_fk=" + astronaut_ship_fk +
                '}';
    }
}
