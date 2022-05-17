package com.group17.hifiprototype.scenes.example_scene;

import com.group17.hifiprototype.scenes.base_scene.BaseMotorsportController;
import com.group17.hifiprototype.scenes.utils.GridControls;
import com.group17.hifiprototype.scenes.utils.VBoxControls;

public class ExampleController extends BaseMotorsportController {

    public void loadWeatherData(){
        /**
         * This will add the rows containing weather data to the current scene.
         * This needs to be implemented for all controllers.
         */
        GridControls.newRowFromData(super.detailedDataContainer, "FRI", "", "", "", "", "");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "70km/h SE", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "", "", "", "", "", "");
        GridControls.newRowFromData(super.detailedDataContainer, "SAT", "", "", "", "", "");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
    }

    public void loadRaceData(){
        /**
         * This will add the rows containing all other scenes in the overlay.
         * This needs to be implemented for all controllers.
         */
        VBoxControls.addBlock("Miami Grand Prix", "06-08 May", "images/flags/usa.png", super.overlay, super.popupvbox, () -> System.out.println("I am the best"));
        VBoxControls.addBlock("Fasz2", "soinfqoienfoqi", "images/flags/usa.png", super.overlay, super.popupvbox, () -> System.out.println("click"));
        VBoxControls.addBlock("Fasz3", "soinfqoienfoqi", "images/flags/usa.png", super.overlay, super.popupvbox, () -> System.out.println("click2"));
        VBoxControls.addBlock("Fasz4", "soinfqoienfoqi", "images/flags/usa.png", super.overlay, super.popupvbox, () -> System.out.println("click3"));
    }

    public void setMainWeatherData(){
        /**
         * Sets the main weather data for the non-scrollable box in the middle of the screen
         * Needs to be implemented
         */
        super.mainWind.setText("75km/h ↗");
        super.mainRain.setText("99%");
        super.mainTemp.setText("658°C");
    }
}


