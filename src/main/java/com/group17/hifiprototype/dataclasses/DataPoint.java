package com.group17.hifiprototype.dataclasses;

import java.util.Date;

public class DataPoint {
    private final double latitude, longitude;
    private final Date time;
    private double airTemperature;
    private Precipitation precipitation;
    private double groundTemperature;
    private double humidity;
    private double precipitationChance;
    private double windSpeed;
    private double windDirection;
    private double visibility;

    public DataPoint(double latitude, double longitude, Date time, double airTemperature,
                     Precipitation precipitation, double groundTemperature, double humidity,
                     double precipitationChance, double windSpeed, double windDirection,double visibility) {
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
        this.visibility = visibility;
    }

    public DataPoint(double latitude, double longitude, Date time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
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

    public double getVisibility() {
        return visibility;
    }

    public void setAirTemperature(double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public void setPrecipitation(Precipitation precipitation) {
        this.precipitation = precipitation;
    }

    public void setGroundTemperature(double groundTemperature) {
        this.groundTemperature = groundTemperature;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setPrecipitationChance(double precipitationChance) {
        this.precipitationChance = precipitationChance;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }



}
