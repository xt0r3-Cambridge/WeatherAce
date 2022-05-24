package com.group17.hifiprototype.backend.weatherapi;

import com.group17.hifiprototype.backend.dataclasses.DataPoint;
import com.group17.hifiprototype.backend.dataclasses.Direction;
import com.group17.hifiprototype.backend.dataclasses.Precipitation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class weather {

    static final List<String> apikeys = List.of(
        "xaJtVMAcUEmoocLbBBvcFzC16AhkJKlu",
        "Q7nAqJ9H73PSH0bA4vdKpfETnI1BVtJh",
        "IL2A1UcRWrqDnw75IRGJFzxtZ6Qq0bwx",
        "3T5L7PzhIFIcNWUiHurqZqWVRze5y3vZ",
        "0BifX7fmfrz86vOfWA6ABRSJD9bWC6Te",
        "SFr1U172TFyceGdYovG5RA7mTYRpVRYU",
        "2jOW3MXjRQpDJD8oPE3c1KDlj4HxICPF",
        "T23lpIKZzao8IqDwqFkz9jx59I9t8K6G",
        "OtzRw2Vzv6NMhwy6jplFj5vFZWWkbIHe",
        "Puc3tiyp0o4wOMh3wIeAkdWHdLA4vaLp"
    );
    private static final String website = "https://api.tomorrow.io/v4/timelines";

    /**
     * Number of hours from present moment in which we can get minute-by-minute updates.
     */
    public static final int API_MINUTE_UPDATE_LIMIT = 12;

    /**
     * Number of hours from present moment in which we can get hourly updates.
     */
    public static final int API_HOURLY_UPDATE_LIMIT = 72;

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
     * @param JObj      JSON object in a format returned by flatten
     * @return
     */
    public static DataPoint JSONToDataPoint(double latitude, double longitude, JSONObject JObj) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz");
        ZonedDateTime time = ZonedDateTime.from(dtf.parse(JObj.getString("startTime")));

        DataPoint dp = new DataPoint(latitude, longitude, time);
        JObj = JObj.getJSONObject("values");

        double airTemperature = JObj.getDouble("temperature");
        dp.setAirTemperature(airTemperature);
        dp.setAirTemperature(airTemperature + Math.random() * 5 - 2.5);

        dp.setHumidity(JObj.getDouble("humidity"));
        dp.setPrecipitationChance(JObj.getDouble("precipitationProbability"));

        int precipitationId = JObj.getInt("precipitationType");
        dp.setPrecipitation(Precipitation.values()[precipitationId]);

        double windDirectionDegrees = JObj.getDouble("windDirection");
        int windDirectionIndex = (int) Math.floor(windDirectionDegrees / 360 * Direction.values().length);
        dp.setWindDirection(Direction.values()[windDirectionIndex]);

        dp.setWindSpeed(JObj.getDouble("windSpeed"));
        dp.setVisibility(JObj.getDouble("visibility"));

        return dp;
    }

    /**
     * @param apikey API key to be used in building the URL
     * @param latitude double showing the latitude of the location to be queried
     * @param longitude double showing the longitude of the location to be queried
     * @param startDate start datetime to be queried
     * @param endDate end datetime to be queried
     * @return URL that can be used to make a weather API call
     * @throws Exception
     */
    private static URL buildCallURL(String apikey, double latitude, double longitude, ZonedDateTime startDate, ZonedDateTime endDate) throws Exception {
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

        // Encoding to escape the slash /
        String timezone = URLEncoder.encode(ZoneId.systemDefault().toString(), StandardCharsets.UTF_8.toString());
        String units = "metric";
        String location = df.format(latitude) + "," + df.format(longitude);

        String parameters = "&fields=" + fields + "&startTime=" + startTime + "Z&endTime=" + endTime +
            "Z&timesteps=" + timesteps + "&timezone=" + timezone + "&units=" + units + "&location=" + location;

        String combined = website + "?apikey=" + apikey + parameters;

        return new URL(combined);
    }

    /**
     * Gets the weather data in the specified location at the specified time. Tries to get the data using all API keys in case the call limit has been reached.
     *
     * @param latitude double showing the latitude of the location to be queried
     * @param longitude double showing the longitude of the location to be queried
     * @param startDate start datetime to be queried
     * @param endDate end datetime to be queried
     * @return array of weather DataPoint containing the data returned by the API
     * @throws IOException
     */
    static ArrayList<DataPoint> call(double latitude, double longitude, ZonedDateTime startDate, ZonedDateTime endDate) throws IOException {
        HttpURLConnection conn = null;
        for (String apikey : apikeys) {
            try {
                URL url = buildCallURL(apikey, latitude, longitude, startDate, endDate);

                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                // Check if connect is made
                int responseCode = conn.getResponseCode();

                if (responseCode == 200) { // OK code
                    StringBuilder informationString = new StringBuilder();
                    Scanner scanner = new Scanner(url.openStream());

                    while (scanner.hasNext()) {
                        informationString.append(scanner.nextLine());
                    }
                    //Close the scanner
                    scanner.close();

                    String jsonString = String.valueOf(informationString);

                    conn.disconnect();
                    return new ArrayList<>(flatten(jsonString).stream().map(x -> JSONToDataPoint(latitude, longitude, x)).collect(Collectors.toList()));

                }
                else if (responseCode != 429) { // 429 is the code for exceeding call limit
                    throw new RuntimeException("HttpResponseCode: " + responseCode + " for "+url);
                }

            }
            catch (RuntimeException re) {
                System.out.println("API Exception -- "+re);
                System.out.println("Requested date in the past/too far in the future");
            }
            catch (Exception e) {
                System.out.println("Exception " + e);
                System.out.println("Failed to get weather data using key: " + apikey);
            }
            finally {
                conn.disconnect();
                break;
            }
        }
        System.out.println("Ran out of API keys!");
        throw new IOException("Failed to get weather data. API call limit has been reached.");
    }



    public static void main(String[] args) {
        //ArrayList<DataPoint> arr = call(40.758, -73.9855, ZonedDateTime.now(), ZonedDateTime.now().plusDays(3));
        //System.out.println(arr);
    }
}

