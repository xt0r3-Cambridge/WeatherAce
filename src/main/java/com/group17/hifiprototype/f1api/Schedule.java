package com.group17.hifiprototype.f1api;

import com.group17.hifiprototype.dataclasses.Race;
import com.group17.hifiprototype.dataclasses.Session;
import org.apache.commons.lang3.StringUtils;
import org.json.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Schedule {


    /**
     * Create a Race from a JSON returned by the F1 api
     *
     * @param jsonObject
     */
    public static Race createFromF1API(JSONObject jsonObject) {
        String name = jsonObject.getString("raceName");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd:HH:mm:ss");
        ZoneId utc = ZoneId.of("UTC");

        ZonedDateTime startTime = ZonedDateTime.of(LocalDateTime.parse(jsonObject.getJSONObject("FirstPractice").getString("date") + ":" +
                StringUtils.chop(jsonObject.getJSONObject("FirstPractice").getString("time")), formatter), utc);
        ZonedDateTime endTime = ZonedDateTime.of(LocalDateTime.parse(jsonObject.getString("date") + ":" +
                StringUtils.chop(jsonObject.getString("time")), formatter).plusHours(2), utc);
        JSONObject location = jsonObject.getJSONObject("Circuit").getJSONObject("Location");
        double latitude = Double.parseDouble(location.getString("lat"));
        double longitude = Double.parseDouble(location.getString("long"));

        ZonedDateTime mainTime = endTime.truncatedTo(ChronoUnit.DAYS).withHour(12);
        ArrayList<Session> sessions = new ArrayList<>(5);

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
        Race output = new Race(name,startTime,endTime,latitude,longitude);
        for (Session sess:sessions) {
            output.addSession(sess);
        }
        return output;
    }



    static ArrayList<Race> call() {

        try {
            String website = "http://ergast.com/api/f1/current.json";

            //System.out.println(combined);

            URL url = new URL(website);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                String jsonString = String.valueOf(informationString);

                JSONObject obj = new JSONObject(jsonString);

                JSONArray racesJson = obj.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");

                ArrayList<Race> races = new ArrayList<>(racesJson.length());

                for (int i = 0; i < racesJson.length(); i++) {
                    races.add(createFromF1API(racesJson.getJSONObject(i)));
                }
                return races;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public static void main(String[] args) {
        call();
    }
}