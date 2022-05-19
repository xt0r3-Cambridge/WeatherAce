package com.group17.hifiprototype.backend.dataclasses;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Weather information for a specific location, at a specific time.
 */
public class DataPoint implements Comparable<DataPoint> {
    private final double latitude, longitude;
    private final ZonedDateTime time;
    private double airTemperature;
    private Precipitation precipitation;
    private double groundTemperature;
    private double humidity;
    private double precipitationChance;
    private double windSpeed;
    private Direction windDirection;
    private double visibility;

    public DataPoint(double latitude, double longitude, ZonedDateTime time, double airTemperature,
                     Precipitation precipitation, double groundTemperature, double humidity,
                     double precipitationChance, double windSpeed, Direction windDirection, double visibility) {
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

    public DataPoint(double latitude, double longitude, ZonedDateTime time) {
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

    public ZonedDateTime getTime() {
        return time;
    }

    public double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public double getGroundTemperature() {
        return groundTemperature;
    }

    public void setGroundTemperature(double groundTemperature) {
        this.groundTemperature = groundTemperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPrecipitationChance() {
        return precipitationChance;
    }

    public void setPrecipitationChance(double precipitationChance) {
        this.precipitationChance = precipitationChance;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Direction getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Direction windDirection) {
        this.windDirection = windDirection;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Precipitation precipitation) {
        this.precipitation = precipitation;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    // Just for debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DP(");
        sb.append(time.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
        sb.append(", Temp: ").append(airTemperature);
        sb.append(", Rain Chance: ").append(precipitationChance);
        sb.append(")");
        return sb.toString();
    }

    public String prettyAirTemperature() {
        return (int) (airTemperature) + "°C";
    }

    public String prettyGroundTemperature() {
        return (int) (groundTemperature) + "°C";
    }

    public String prettyWind() {
        return (int) (windSpeed) + " m/s " + windDirection;
    }

    public String prettyTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return dtf.format(time);
    }

    public String prettyVisibility() {
        return String.format("%.2f", visibility) + " km";
    }

    public String prettyPrecipitation() {
        return (int) (precipitationChance * 100) + "%";
    }

    /**
     * Copies the weather data from another data point.
     *
     * @param other
     */
    public void copyDataFrom(DataPoint other) {
        this.airTemperature = other.airTemperature;
        this.precipitation = other.precipitation;
        this.groundTemperature = other.groundTemperature;
        this.humidity = other.humidity;
        this.precipitationChance = other.precipitationChance;
        this.windSpeed = other.windSpeed;
        this.windDirection = other.windDirection;
        this.visibility = other.visibility;
    }


    @Override
    public int compareTo(DataPoint o) {
        return time.compareTo(o.time);
    }
}
