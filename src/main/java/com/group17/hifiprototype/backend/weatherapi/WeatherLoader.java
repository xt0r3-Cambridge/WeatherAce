package com.group17.hifiprototype.backend.weatherapi;

import com.group17.hifiprototype.backend.dataclasses.DataPoint;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Interface between races and weather api
 */
public class WeatherLoader {
    private final double latitude, longitude;
    private final ZonedDateTime startTime, endTime;
    private ArrayList<DataPoint> dataPoints;
    private ZonedDateTime lastTimeUpdated;
    private boolean isLoaded;

    public WeatherLoader(double latitude, double longitude, ZonedDateTime startTime, ZonedDateTime endTime) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isLoaded = false;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public ZonedDateTime getLastTimeUpdated() throws UnsupportedOperationException {
        if (!isLoaded) throw new UnsupportedOperationException("Loader object not yet loaded");
        else return lastTimeUpdated;
    }

    /**
     * Calls the weather API to obtain its inner set of data points.
     *
     * @throws IOException
     */
    public void load() throws IOException {
        ZonedDateTime initialTime = ZonedDateTime.now().compareTo(startTime) >= 0 ? ZonedDateTime.now() : startTime;
        //API call
        dataPoints = weather.call(latitude, longitude, initialTime, endTime);

        dataPoints.sort(DataPoint::compareTo);
        isLoaded = true;
        lastTimeUpdated = ZonedDateTime.now();

    }

    /**
     * Populates data point with the closest match earlier than itself from the inner set.
     * Only call this after load() has been called.
     *
     * @param dataPoint
     */
    public void populate(DataPoint dataPoint) throws UnsupportedOperationException {
        if (!isLoaded) throw new UnsupportedOperationException();
        DataPoint closestDP = null;
        int i = 0;
        while (i < dataPoints.size() && dataPoints.get(i).compareTo(dataPoint) <= 0) {
            closestDP = dataPoints.get(i);
            i++;
        }
        assert closestDP != null;
        dataPoint.copyDataFrom(closestDP);
    }

}
