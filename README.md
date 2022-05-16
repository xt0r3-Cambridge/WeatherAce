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