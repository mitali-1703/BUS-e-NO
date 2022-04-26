package com.example.bus_e_no;

public class User {
    public String name;
    public String busRoute;
    public String role;
    public String shift;
    public String course;

    public User(){

    }

    public User(String name, String busRoute, String role, String shift, String course) {
        this.name = name;
        this.busRoute = busRoute;
        this.role = role;
        this.shift = shift;
        this.course = course;
    }
}
