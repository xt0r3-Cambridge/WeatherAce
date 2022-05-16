package com.group17.hifiprototype.dataclasses;

import com.group17.hifiprototype.weatherapi.WeatherLoader;
import com.group17.hifiprototype.weatherapi.weather;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

public class Race {
    private static final int HOUR_OF_MAIN_POINT = 14;

    private final String name;
    private final ZonedDateTime startTime, endTime;
    private final double latitude;
    private final double longitude;
    private final WeatherLoader weatherLoader;
    private ArrayList<Session> sessions;
    private boolean loaded;

    //Time used to display main weather info

    private DataPoint mainDataPoint;

    public Race(String name, ZonedDateTime startTime, ZonedDateTime endTime, double latitude, double longitude) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sessions = new ArrayList<>();
        this.weatherLoader = new WeatherLoader(latitude, longitude, startTime, endTime);
        this.loaded = false;
    }

    /**
     * Create a Race from a JSON returned by the F1 api
     *
     * @param jsonObject
     */
    public Race(JSONObject jsonObject) {
        name = jsonObject.getString("raceName");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd:HH:mm:ss");
        ZoneId utc = ZoneId.of("UTC");

        startTime = ZonedDateTime.of(LocalDateTime.parse(jsonObject.getJSONObject("FirstPractice").getString("date") + ":" +
                StringUtils.chop(jsonObject.getJSONObject("FirstPractice").getString("time")), formatter), utc);
        endTime = ZonedDateTime.of(LocalDateTime.parse(jsonObject.getString("date") + ":" +
                StringUtils.chop(jsonObject.getString("time")), formatter).plusHours(2), utc);
        JSONObject location = jsonObject.getJSONObject("Circuit").getJSONObject("Location");
        latitude = Double.parseDouble(location.getString("lat"));
        longitude = Double.parseDouble(location.getString("long"));

        ZonedDateTime mainTime = endTime.truncatedTo(ChronoUnit.DAYS).withHour(12);
        this.weatherLoader = new WeatherLoader(latitude, longitude, startTime, endTime);
        this.loaded = false;
        sessions = new ArrayList<>(5);

        if (jsonObject.has("ThirdPractice")) {
            LocalDateTime fp1 = LocalDateTime.parse(jsonObject.getJSONObject("FirstPractice").getString("date") +
                    ":" + StringUtils.chop(jsonObject.getJSONObject("FirstPractice").getString("time")), formatter);
            ZonedDateTime fp1Start = ZonedDateTime.of(fp1, utc);
            sessions.add(new Session("FP1", fp1Start, fp1Start.plus(1, ChronoUnit.HOURS), latitude, longitude));

            LocalDateTime fp2 = LocalDateTime.parse(jsonObject.getJSONObject("SecondPractice").getString("date") +
                    ":" + StringUtils.chop(jsonObject.getJSONObject("SecondPractice").getString("time")), formatter);
            ZonedDateTime fp2Start = ZonedDateTime.of(fp2, utc);
            sessions.add(new Session("FP2", fp2Start, fp2Start.plus(1, ChronoUnit.HOURS), latitude, longitude));

            LocalDateTime fp3 = LocalDateTime.parse(jsonObject.getJSONObject("ThirdPractice").getString("date") +
                    ":" + StringUtils.chop(jsonObject.getJSONObject("ThirdPractice").getString("time")), formatter);
            ZonedDateTime fp3Start = ZonedDateTime.of(fp3, utc);
            sessions.add(new Session("FP3", fp3Start, fp3Start.plus(1, ChronoUnit.HOURS), latitude, longitude));

            LocalDateTime qual = LocalDateTime.parse(jsonObject.getJSONObject("Qualifying").getString("date") +
                    ":" + StringUtils.chop(jsonObject.getJSONObject("Qualifying").getString("time")), formatter);
            ZonedDateTime qualStart = ZonedDateTime.of(qual, utc);
            sessions.add(new Session("Q", qualStart, qualStart.plus(1, ChronoUnit.HOURS), latitude, longitude));
        }

        if (jsonObject.has("Sprint")) {
            LocalDateTime fp1 = LocalDateTime.parse(jsonObject.getJSONObject("FirstPractice").getString("date") +
                    ":" + StringUtils.chop(jsonObject.getJSONObject("FirstPractice").getString("time")), formatter);
            ZonedDateTime fp1Start = ZonedDateTime.of(fp1, utc);
            sessions.add(new Session("FP1", fp1Start, fp1Start.plus(1, ChronoUnit.HOURS), latitude, longitude));

            LocalDateTime qual = LocalDateTime.parse(jsonObject.getJSONObject("Qualifying").getString("date") +
                    ":" + StringUtils.chop(jsonObject.getJSONObject("Qualifying").getString("time")), formatter);
            ZonedDateTime qualStart = ZonedDateTime.of(qual, utc);
            sessions.add(new Session("Q", qualStart, qualStart.plus(1, ChronoUnit.HOURS), latitude, longitude));

            LocalDateTime fp2 = LocalDateTime.parse(jsonObject.getJSONObject("SecondPractice").getString("date") +
                    ":" + StringUtils.chop(jsonObject.getJSONObject("SecondPractice").getString("time")), formatter);
            ZonedDateTime fp2Start = ZonedDateTime.of(fp2, utc);
            sessions.add(new Session("FP2", fp2Start, fp2Start.plus(1, ChronoUnit.HOURS), latitude, longitude));

            LocalDateTime sprint = LocalDateTime.parse(jsonObject.getJSONObject("Sprint").getString("date") +
                    ":" + StringUtils.chop(jsonObject.getJSONObject("Sprint").getString("time")), formatter);
            ZonedDateTime sprintStart = ZonedDateTime.of(sprint, utc);
            sessions.add(new Session("Sprint", sprintStart, sprintStart.plus(1, ChronoUnit.HOURS), latitude, longitude));
        }

        sessions.add(new Session("Race", endTime.minus(2, ChronoUnit.HOURS), endTime, latitude, longitude));

    }

    /**
     * @param jsonObject JSON Object encoding information about a race
     * @return a Race with the information provided in the JSON
     */
    public static Race LoadRace(JSONObject jsonObject) throws IllegalArgumentException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz");

            String name = jsonObject.getString("Name");
            Double latitude = jsonObject.getDouble("Latitude");
            Double longitude = jsonObject.getDouble("Longitude");
            ZonedDateTime start = ZonedDateTime.from(dtf.parse(jsonObject.getString("StartTime")));
            ZonedDateTime end = ZonedDateTime.from(dtf.parse(jsonObject.getString("EndTime")));

            Race outputRace = new Race(name, start, end, latitude, longitude);


            for (int i = 0; i < jsonObject.getJSONArray("Sessions").length(); i++) {

                JSONObject sessionJson = jsonObject.getJSONArray("Sessions").getJSONObject(i);
                String sessionName = sessionJson.getString("Name");
                ZonedDateTime sessionStart = ZonedDateTime.from(dtf.parse(sessionJson.getString("StartTime")));
                ZonedDateTime sessionEnd = ZonedDateTime.from(dtf.parse(sessionJson.getString("EndTime")));


                Session session = new Session(sessionName, sessionStart, sessionEnd, latitude, longitude);
                outputRace.addSession(session);

            }

            return outputRace;

        } catch (JSONException e) {
            throw new IllegalArgumentException(e.getCause());
        }
    }

    private void addSession(Session session) {
        sessions.add(session);
        sessions.sort(Session::compareTo);
    }

    public DataPoint getMainDataPoint() {
        return mainDataPoint;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":\n");
        sb.append("Time: ").append(startTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))).append(" - ");
        sb.append(endTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
        sb.append("\n");
        sb.append("Location: ").append(latitude).append(", ").append(longitude);
        sb.append("\n");
        sb.append("Sessions: ").append(sessions);
        return sb.toString();
    }

    public boolean isInitialised() {
        return loaded;
    }

    public ZonedDateTime timeLastUpdated() {
        return weatherLoader.getLastTimeUpdated();
    }

    /**
     * Downloads relevant weather data from the weather api and populates dynamically-created data points with it.
     */
    public void loadWeatherData() {
        try {
            weatherLoader.load();
            createDataPoints();
            fetchWeatherData();
            loaded = true;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * Creates data points for the race itself, and for all listed sessions. This does not download data or load data into the structure.
     */
    private void createDataPoints() {
        mainDataPoint = new DataPoint(latitude, longitude, endTime.truncatedTo(ChronoUnit.DAYS).withHour(HOUR_OF_MAIN_POINT));

        for (Session session : sessions) {
            Granularity granularity;
            long hoursFromPresent = Duration.between(ZonedDateTime.now(), session.getEndTime()).toHours();
            if (hoursFromPresent <= weather.API_MINUTE_UPDATE_LIMIT) granularity = Granularity.Minutes;
            else if (hoursFromPresent <= weather.API_HOURLY_UPDATE_LIMIT) granularity = Granularity.Hours;
            else granularity = Granularity.Days;

            session.createEmptyDataPoints(granularity);
        }
    }

    /**
     * Copies all data points with weather data from the weather loader.
     * This does not update the weather loader's information, or create any data point structure in the sessions.
     * Call loadWeatherData() to construct data points, download weather info, and fetch it.
     */
    private void fetchWeatherData() {
        if (!weatherLoader.isLoaded()) throw new RuntimeException("Attempted to copy data from empty WeatherLoader");
        weatherLoader.populate(mainDataPoint);
        for (Session session : sessions) {
            for (DataPoint dp : session.getDataPoints()) {
                weatherLoader.populate(dp);
            }
        }
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }
}