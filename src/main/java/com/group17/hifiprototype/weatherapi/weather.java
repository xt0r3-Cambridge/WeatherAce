package com.group17.hifiprototype.weatherapi;

import com.group17.hifiprototype.dataclasses.DataPoint;
import org.json.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class weather {

    static final String apikey = "xaJtVMAcUEmoocLbBBvcFzC16AhkJKlu";
    private static final String website = "https://api.tomorrow.io/v4/timelines";

    /**
     * @param JSONText JSON file text
     * @return ArrayList of data points, in JSONObject form
     */
    static ArrayList<JSONObject> flatten(String JSONText) {
        JSONObject jobj1 = new JSONObject(JSONText).getJSONObject("data");
        JSONArray jarray1 = jobj1.getJSONArray("timelines");
        ArrayList<JSONObject> arr = new ArrayList<>();
        for (int i = 0; i < jarray1.length(); i++) {
            JSONArray subArray = jarray1.getJSONObject(i).getJSONArray("intervals");
            for (int j = 0; j < subArray.length(); j++) {
                arr.add(subArray.getJSONObject(j));
            }
        }
        return arr;
    }

    /**
     * @param latitude
     * @param longitude
     * @param JObj JSON object in a format returned by flatten
     * @return
     */
    public static DataPoint JSONToDataPoint(double latitude, double longitude, JSONObject JObj) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz");
        ZonedDateTime time = ZonedDateTime.from(dtf.parse(JObj.getString("startTime")));

        DataPoint dp = new DataPoint(latitude,longitude,time);
        JObj = JObj.getJSONObject("values");

        dp.setAirTemperature(JObj.getDouble("temperature"));
        dp.setHumidity(JObj.getDouble("humidity"));
        dp.setPrecipitationChance(JObj.getDouble("precipitationProbability"));
        dp.setWindDirection(JObj.getDouble("windDirection"));
        dp.setWindSpeed(JObj.getDouble("windSpeed"));
        dp.setVisibility(JObj.getDouble("visibility"));
        //TODO: wind direction (translation from float -> SE/NW?), precipitation type (api documentation), ground temperature (approximate?)
        return dp;
    }

    static ArrayList<DataPoint> call(double latitude, double longitude, ZonedDateTime startDate, ZonedDateTime endDate) {
        DecimalFormat df = new DecimalFormat("#.####");

        DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("uuuu-MM-dd+HH:mm:ss.SSS");

        String fields = "temperature,precipitationProbability,precipitationType,humidity,windSpeed,windDirection,visibility";
        String startTime = dtf.format(startDate.toLocalDateTime());
        String endTime = dtf.format(endDate.toLocalDateTime());

        startTime = startTime.replace("+", "T");
        startTime = startTime.replace(":", "%3A");
        endTime = endTime.replace("+", "T");
        endTime = endTime.replace(":", "%3A");

        String timesteps = "15m,1h,1d";
        String timezone = "Europe%2FLondon";
        String units = "metric";
        String location = df.format(latitude) + "," + df.format(longitude);

        String parameters = "&fields=" + fields + "&startTime=" + startTime + "Z&endTime=" + endTime +
                "Z&timesteps=" + timesteps + "&timezone=" + timezone + "&units=" + units + "&location=" + location;

        //System.out.println(parameters);

        try {
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
                conn.disconnect();
                return new ArrayList<>(flatten(jsonString).stream().map(x -> JSONToDataPoint(latitude,longitude,x)).collect(Collectors.toList()));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
        ArrayList<DataPoint> arr = call(40.758, -73.9855, ZonedDateTime.now(), ZonedDateTime.now().plusDays(3));
        System.out.println(arr);
    }
}

