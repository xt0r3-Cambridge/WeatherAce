package com.group17.hifiprototype.scenes.f1_scene.f1_monaco;

import com.group17.hifiprototype.dataclasses.*;
import com.group17.hifiprototype.scenes.base_scene.BaseMotorsportController;
import com.group17.hifiprototype.scenes.utils.GridControls;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import com.group17.hifiprototype.scenes.utils.VBoxControls;

import java.util.ArrayList;
import java.util.List;

public class F1MonacoController extends BaseMotorsportController {

    public void loadWeatherData(){
        /**
         * This will add the rows containing weather data to the current scene.
         * This needs to be implemented for all controllers.
         */
        try {
            Race currentRace = RaceLoader.getGroup(RaceGroups.F1).getRace("Monaco Grand Prix");
            currentRace.loadWeatherData();
            System.out.println("HERE");

            ArrayList<Session> sessions = currentRace.getSessions();

            for (Session session : sessions) {
                GridControls.newRowFromData(super.detailedDataContainer, session.getName(), "", "", "", "", "");
                System.out.println(session.getName());
                List<DataPoint> dataPointList = session.getDataPoints();
                for (DataPoint dataPoint : dataPointList) {
                    GridControls.newRowFromData(
                            super.detailedDataContainer,
                            dataPoint.prettyTime(),
                            dataPoint.prettyPrecipitation(),
                            dataPoint.prettyAirTemperature(),
                            dataPoint.prettyGroundTemperature(),
                            dataPoint.prettyWind(),
                            dataPoint.prettyVisibility()
                    );
                }
                GridControls.newRowFromData(super.detailedDataContainer, "", "", "", "", "", "");
            }
        } catch (Exception ignored){
            GridControls.newRowFromData(super.detailedDataContainer, "ERROR:", "Race at", "an invalid", "time", "Filled with", "mock data");
            GridControls.fillWithMock(super.detailedDataContainer);
        }
    }

    public void loadRaceData(){
        /**
         * This will add the rows containing all other scenes in the overlay.
         * This needs to be implemented for all controllers.
         */
        VBoxControls.addBlock("Spanish Grand Prix", "20-22 May", "images/f1/spain.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.F1_SPAIN));
        VBoxControls.addBlock("Monaco Grand Prix", "27-29 May", "images/f1/monaco.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.F1_MONACO));
        VBoxControls.addBlock("Azerbaijan Grand Prix", "10-12 Jun", "images/f1/azerbaijan.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.F1_AZERBAIJAN));
    }

    //TODO copy paste
    public void setMainWeatherData(){
        /**
         * Sets the main weather data for the non-scrollable box in the middle of the screen
         * Needs to be implemented
         */
        try {
            Race currentRace = RaceLoader.getGroup(RaceGroups.F1).getRace("Monaco Grand Prix");
            currentRace.loadWeatherData();
            DataPoint mainDataPoint = currentRace.getMainDataPoint();

            super.mainWind.setText(mainDataPoint.prettyWind());
            super.mainRain.setText(mainDataPoint.prettyAirTemperature());
            super.mainTemp.setText(mainDataPoint.prettyGroundTemperature());
        } catch (Exception ignored){
            super.mainWind.setText("4 m/s " + Direction.NE);
            super.mainRain.setText("36°C");
            super.mainTemp.setText("42°C");
        }
    }
}


