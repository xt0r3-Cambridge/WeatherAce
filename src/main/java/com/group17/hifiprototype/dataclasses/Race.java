package com.group17.hifiprototype.dataclasses;

import org.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Race {

    private final String name;
    private final ZonedDateTime startTime, endTime;
    private ArrayList<Session> sessions;
    private final double latitude;
    private final double longitude;

    public Race(JSONObject jsonObject) {
        name = jsonObject.getString("raceName");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd:HH:mm:ss");
        ZoneId utc = ZoneId.of("UTC");

        startTime = ZonedDateTime.of(LocalDateTime.parse(jsonObject.getJSONObject("FirstPractice").getString("date")+":"+
                StringUtils.chop(jsonObject.getJSONObject("FirstPractice").getString("time")), formatter), utc);
        endTime = ZonedDateTime.of(LocalDateTime.parse(jsonObject.getString("date")+":"+
                StringUtils.chop(jsonObject.getString("time")), formatter).plusHours(2), utc);
        JSONObject location = jsonObject.getJSONObject("Circuit").getJSONObject("Location");
        latitude = Double.parseDouble(location.getString("lat"));
        longitude = Double.parseDouble(location.getString("long"));

        sessions = new ArrayList<>(5);

        if (jsonObject.has("ThirdPractice")){
            LocalDateTime fp1 = LocalDateTime.parse(jsonObject.getJSONObject("FirstPractice").getString("date") +
                    ":"+ StringUtils.chop(jsonObject.getJSONObject("FirstPractice").getString("time")), formatter);
            ZonedDateTime fp1Start = ZonedDateTime.of(fp1, utc);
            sessions.add(new Session("FP1", fp1Start, fp1Start.plus(1, ChronoUnit.HOURS), latitude, longitude));

            LocalDateTime fp2 = LocalDateTime.parse(jsonObject.getJSONObject("SecondPractice").getString("date") +
                    ":"+ StringUtils.chop(jsonObject.getJSONObject("SecondPractice").getString("time")), formatter);
            ZonedDateTime fp2Start = ZonedDateTime.of(fp2, utc);
            sessions.add(new Session("FP2", fp2Start, fp2Start.plus(1, ChronoUnit.HOURS), latitude, longitude));

            LocalDateTime fp3 = LocalDateTime.parse(jsonObject.getJSONObject("ThirdPractice").getString("date") +
                    ":"+ StringUtils.chop(jsonObject.getJSONObject("ThirdPractice").getString("time")), formatter);
            ZonedDateTime fp3Start = ZonedDateTime.of(fp3, utc);
            sessions.add(new Session("FP3", fp3Start, fp3Start.plus(1, ChronoUnit.HOURS), latitude, longitude));

            LocalDateTime qual = LocalDateTime.parse(jsonObject.getJSONObject("Qualifying").getString("date") +
                    ":"+ StringUtils.chop(jsonObject.getJSONObject("Qualifying").getString("time")), formatter);
            ZonedDateTime qualStart = ZonedDateTime.of(qual, utc);
            sessions.add(new Session("Q", qualStart, qualStart.plus(1, ChronoUnit.HOURS), latitude, longitude));
        }

        if (jsonObject.has("Sprint")){
            LocalDateTime fp1 = LocalDateTime.parse(jsonObject.getJSONObject("FirstPractice").getString("date") +
                    ":"+ StringUtils.chop(jsonObject.getJSONObject("FirstPractice").getString("time")), formatter);
            ZonedDateTime fp1Start = ZonedDateTime.of(fp1, utc);
            sessions.add(new Session("FP1", fp1Start, fp1Start.plus(1, ChronoUnit.HOURS), latitude, longitude));

            LocalDateTime qual = LocalDateTime.parse(jsonObject.getJSONObject("Qualifying").getString("date") +
                    ":"+ StringUtils.chop(jsonObject.getJSONObject("Qualifying").getString("time")), formatter);
            ZonedDateTime qualStart = ZonedDateTime.of(qual, utc);
            sessions.add(new Session("Q", qualStart, qualStart.plus(1, ChronoUnit.HOURS), latitude, longitude));

            LocalDateTime fp2 = LocalDateTime.parse(jsonObject.getJSONObject("SecondPractice").getString("date") +
                    ":"+ StringUtils.chop(jsonObject.getJSONObject("SecondPractice").getString("time")), formatter);
            ZonedDateTime fp2Start = ZonedDateTime.of(fp2, utc);
            sessions.add(new Session("FP2", fp2Start, fp2Start.plus(1, ChronoUnit.HOURS), latitude, longitude));

            LocalDateTime sprint = LocalDateTime.parse(jsonObject.getJSONObject("Sprint").getString("date") +
                    ":"+ StringUtils.chop(jsonObject.getJSONObject("Sprint").getString("time")), formatter);
            ZonedDateTime sprintStart = ZonedDateTime.of(sprint, utc);
            sessions.add(new Session("Sprint", sprintStart, sprintStart.plus(1, ChronoUnit.HOURS), latitude, longitude));
        }

        sessions.add(new Session("Race", endTime.minus(2, ChronoUnit.HOURS), endTime, latitude, longitude));

    }
}