package com.group17.hifiprototype.backend.dataclasses;

import com.group17.hifiprototype.backend.weatherapi.weather;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MockRaceFactory {
    /**
     * @return A fake race with sessions that have start and end times in the near future.
     * The session times are picked so that the race has one session for each supported weather data granularity
     */
    public static Race createMockRace() {
        ZonedDateTime currentTime = ZonedDateTime.now();
        ZonedDateTime roundedCurrentTime = ZonedDateTime.of(
            currentTime.getYear(),
            currentTime.getMonthValue(),
            currentTime.getDayOfMonth(),
            currentTime.getHour(),
            0,
            0,
            0,
            currentTime.getZone()
        ).plus(1, ChronoUnit.HOURS);

        ZonedDateTime[] sessionStartTimes = {
            roundedCurrentTime.plus(1, ChronoUnit.HOURS), // Minutely granularity
            roundedCurrentTime.plus(weather.API_MINUTE_UPDATE_LIMIT, ChronoUnit.HOURS), // Hourly granularity
            roundedCurrentTime.plus(weather.API_HOURLY_UPDATE_LIMIT, ChronoUnit.HOURS) // Daily granularity
        };

        // Random coordinates near Madrid
        double latitude = 40.289074448678086;
        double longitude = -3.6889418816413833;

        Function<ZonedDateTime, ZonedDateTime> getEndTime = st -> st.plus(3, ChronoUnit.HOURS);

        ArrayList<Session> sessions = Arrays.stream(sessionStartTimes).map(
            st -> new Session(
                "Example session",
                st,
                getEndTime.apply(st),
                latitude,
                longitude
            )
        ).collect(Collectors.toCollection(ArrayList::new)); // Why is Java so ugly?

        return new Race(
            "Example race",
            sessionStartTimes[0],
            getEndTime.apply(sessionStartTimes[sessionStartTimes.length - 1]),
            latitude,
            longitude,
            sessions
        );
    }
}
