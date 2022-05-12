package com.group17.hifiprototype.weatherapi;

import org.json.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class weather {

    static void call(double x, double y) {
        DecimalFormat df = new DecimalFormat("#.####");

        DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("uuuu-MM-dd+HH:mm:ss.SSS");

        String fields = "temperature,precipitationProbability,precipitationType,humidity,windSpeed,windDirection,visibility";
        String startTime = dtf.format(java.time.LocalDateTime.now());
        String endTime = dtf.format(java.time.LocalDateTime.now().plusDays(5));

        startTime = startTime.replace("+", "T");
        startTime = startTime.replace(":", "%3A");
        endTime = endTime.replace("+", "T");
        endTime = endTime.replace(":", "%3A");

        String timesteps = "15m,1h,1d";
        String timezone = "Europe%2FLondon";
        String units = "metric";
        String location = df.format(x) + "," + df.format(y);

        String parameters = "&fields=" + fields + "&startTime=" + startTime + "Z&endTime=" + endTime + "Z&timesteps=" + timesteps + "&timezone=" + timezone + "&units=" + units + "&location=" + location;

        //System.out.println(parameters);

        try {
            String website = "https://api.tomorrow.io/v4/timelines";
            String apikey = "xaJtVMAcUEmoocLbBBvcFzC16AhkJKlu";
            String combined = website + "?apikey=" + apikey + parameters;

            //System.out.println(combined);

            URL url = new URL(combined);

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

                //System.out.println(informationString);

                String jsonString = String.valueOf(informationString);
                //JSONObject obj = new JSONObject(jsonString);
                //JSONArray dataObject = obj.getJSONArray("data");
                //JSONArray dataObject = obj.getJSONArray("consolidated_weather");
                //JSON simple library Setup with Maven is used to convert strings to JSON
                //JSONArray dataObject = new JSONArray(String.valueOf(informationString));

                //Get the first JSON object in the JSON array
                //System.out.println(dataObject.get(0));

                //JSONObject countryData = (JSONObject) dataObject.get(0);

                //System.out.println(countryData.get("visibility"));

                JSONObject obj = new JSONObject(jsonString);

                System.out.println(obj.get("data"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        call(40.758, -73.9855);
    }
}

