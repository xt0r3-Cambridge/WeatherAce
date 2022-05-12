package com.group17.hifiprototype.dataclasses;

import java.util.ArrayList;
import java.util.Date;

public class Session {


    private final String name;
    private final Date startTime,endTime;
    private ArrayList<DataPoint> dataPoints;

    public Session(String name, Date startTime, Date endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
