package com.group17.hifiprototype.dataclasses;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;

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
        this.dataPoints = new ArrayList<>();
    }

    public void createDataPoints(Granularity granularity) {
        int step = granularity.getStep();
        ZonedDateTime time = startTime;
        while (time.isBefore(endTime)) {
            dataPoints.add(new DataPoint(latitude,longitude,time));
            time = time.plusMinutes(step);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(": ");
        sb.append(startTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
        sb.append(" - ");
        sb.append(endTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
        return sb.toString();
    }

    public List<DataPoint> getDataPoints() {
        return dataPoints;
    }

}
