package com.group17.hifiprototype.dataclasses;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;

/**
 * An event session (what we think of as an individual race event).
 */
public class Session implements Comparable<Session> {


    private final String name;
    private final ZonedDateTime startTime, endTime;
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

    /**
     * Populates session with data points between its start and end time, at a specified granularity.
     * This creates empty data points, which need to be filled by a weather loader.
     * @param granularity
     */
    public void createEmptyDataPoints(Granularity granularity) {
        dataPoints = new ArrayList<>();
        int step = granularity.getStep();
        ZonedDateTime time = startTime;
        while (time.isBefore(endTime.plusSeconds(1))) {
            dataPoints.add(new DataPoint(latitude, longitude, time));
            time = time.plusMinutes(step);
        }
    }

    /**
     * This is for debugging purposes
     * @return
     */
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

    /**
     * @return List of all data points.
     */
    public List<DataPoint> getDataPoints() {
        return dataPoints;
    }

    /**
     * Sort by start time
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Session o) {
        return startTime.compareTo(o.startTime);
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }
}
