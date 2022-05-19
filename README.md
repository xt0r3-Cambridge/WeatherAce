# WeatherAce

## Adding a new scene:
1. Duplicate the f1_scene folder in java/com/group17/hifiprototype/scenes 
2. Rename the folder and the scene and controller class (Have IntellIJ do the refactoring when renaming stuff)
3. Duplicate f1_scene.fxml from resources/group17/hifiprototype 
4. Set the new controller as the controller as fx:controller for the AnchorPane with fx::id="root"
5. Modify the Scene and the Controller's init() functions. (Maybe reset() too, but that is unlikely to be needed)
6. Add the relevant opens and export clause in java/module-info.java (similarly to that of f1_scene)
7. Overwrite the static text and static image URLs with the help of the SceneBuilder app or by hand in the fxml code
8. Implement the loadWeatherData() and loadRaces() functions
9. Add the Scene to MainApplication.java as seen for F1Scene.

Model race json:
{
  "Group": "F1",
  "Name": "F1 Race 1",
  "ThumbnailPath": "//aaaaaaa//aaaaaaa//bbbbb", (optional)
  "Latitude": 52.1951,
  "Longitude": 0.1313,
  "StartTime": "2022-05-17T05:00:00+01:00",
  "EndTime": "2022-05-25T19:00:00+01:00",
  "Favourite": false,
  
  "Sessions": [
    {"Name": "S1",
      "StartTime": "2022-05-17T07:00:00+01:00",
      "EndTime": "2022-05-17T10:00:00+01:00"
    },
    {
      "Name": "S2",
      "StartTime": "2022-05-18T18:00:00+01:00",
      "EndTime": "2022-05-18T19:00:00+01:00"
    },
    {
      "Name": "Main Race",
      "StartTime": "2022-05-25T13:00:00+01:00",
      "EndTime": "2022-05-25T15:00:00+01:00"
    }
  ]
}


Using the backend:

Getting races/groups and loading weather data:
1. create a raceLoader object -- pass it the path of the directory with ALL the .json race files
2. call init() on it
3. you can now either get a whole group of races or a set of all the races
4. to load the weather data into a race or group (loads it for all races in the group): call loadWeatherData()

Retrieving weather information once loaded:

For a race:
1. getMainDataPoint() -- this gives you a data point for the last day of the event
2. getSessions() -- gives you a list of sessions, sorted by start time, followed by:
3. session.getDataPoints() -- gives you a list of data points for the session, sorted by time

Retrieving weather information from a DataPoint:
dataPoint.prettyAirTemperature, prettyWind, etc... -- these return strings formatted for printing.

