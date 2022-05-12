package com.group17.hifiprototype.dataclasses;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Session {


    private final String name;
    private final ZonedDateTime startTime,endTime;
    private final double latitude, longitude;
    private ArrayList<DataPoint> dataPoints;

    public Session(String name, ZonedDateTime startTime, ZonedDateTime endTime, double latitude, double longitude) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
