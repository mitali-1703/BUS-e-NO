package com.example.bus_e_noadmin.model;

public class Model {
    private String busName;
    private String busRouteNumber;
    private int editIcon;
    private int deleteIcon;

    public Model(String busName, String busRouteNumber, int editIcon,int deleteIcon){
        this.busName = busName;
        this.busRouteNumber = busRouteNumber;
        this.editIcon = editIcon;
        this.deleteIcon = deleteIcon;
    }

    public String getBusName() {
        return busName;
    }

    public String getBusRouteNumber() {
        return busRouteNumber;
    }

    public int getEditIcon() {
        return editIcon;
    }

    public int getDeleteIcon() {
        return deleteIcon;
    }
}
