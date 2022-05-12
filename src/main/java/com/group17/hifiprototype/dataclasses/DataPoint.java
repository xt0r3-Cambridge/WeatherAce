package com.group17.hifiprototype.dataclasses;

import java.util.Date;

public class DataPoint {
    private final double latitude, longitude;
    private final Date time;
    private final double airTemperature;
    private final Precipitation precipitation;
    private double groundTemperature;
    private double humidity;
    private double precipitationChance;
    private double windSpeed;
    private double windDirection;

    public DataPoint(double latitude, double longitude, Date time, double airTemperature,
                     Precipitation precipitation, double groundTemperature, double humidity,
                     double precipitationChance, double windSpeed, double windDirection) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.airTemperature = airTemperature;
        this.precipitation = precipitation;
        this.groundTemperature = groundTemperature;
        this.humidity = humidity;
        this.precipitationChance = precipitationChance;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Date getTime() {
        return time;
    }

    public double getAirTemperature() {
        return airTemperature;
    }

    public double getGroundTemperature() {
        return groundTemperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPrecipitationChance() {
        return precipitationChance;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }


}
