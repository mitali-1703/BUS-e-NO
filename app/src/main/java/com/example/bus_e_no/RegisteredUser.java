package com.example.bus_e_no;

public class RegisteredUser {
    public String name;
    public String email;
    public String busRouteNo;

    public RegisteredUser(){}

    public RegisteredUser(String name, String email, String busRouteNo) {
        this.name = name;
        this.email = email;
        this.busRouteNo = busRouteNo;
    }
}
