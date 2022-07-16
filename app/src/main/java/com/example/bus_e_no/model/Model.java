package com.example.bus_e_no.model;

/** Model Class to hold the data of the driver
 * @param
 */

public class Model {
    private String busRouteNumber;
    private String driverName;
    private int callIcon;

    public Model(String busRouteNumber, String driverName, int callIcon){
        this.busRouteNumber = busRouteNumber;
        this.driverName = driverName;
        this.callIcon = callIcon;
    }

    public Model(String busRouteNumber, String driverName){
        this.busRouteNumber = busRouteNumber;
        this.driverName = driverName;
    }

    public String getBusRouteNumber() {
        return busRouteNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public int getCallIcon() {
        return callIcon;
    }
}
