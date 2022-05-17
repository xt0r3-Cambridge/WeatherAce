package com.group17.hifiprototype.f1api;

import com.group17.hifiprototype.dataclasses.Race;
import org.json.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Schedule {

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
                    races.add(Race.createFromF1API(racesJson.getJSONObject(i)));
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