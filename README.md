# WeatherAce

## Adding a new scene:
1. Duplicate the f1_scene folder in java/com/group17/hifiprototype/scenes 
2. Rename the folder and the scene and controller class (Have IntellIJ do the refactoring when renaming stuff)
3. Duplicate f1_scene.fxml from resources/group17/hifiprototype 
4. Set the new controller as the controller as fx:controller for the AnchorPane with fx::id="root"
5. Add the relevant opens and export clause in java/module-info.java (similarly to that of f1_scene)
6. Overwrite the static text and static image URLs with the help of the SceneBuilder app or by hand in the fxml code
7. Implement the loadWeatherData() and loadRaces() functions