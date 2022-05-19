package com.group17.hifiprototype.scenes.nascar_scene.nascar_toyota;

import com.group17.hifiprototype.dataclasses.*;
import com.group17.hifiprototype.scenes.base_scene.BaseMotorsportController;
import com.group17.hifiprototype.scenes.utils.GridControls;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import com.group17.hifiprototype.scenes.utils.VBoxControls;

import java.util.ArrayList;
import java.util.List;

public class NascarToyotaController extends BaseMotorsportController {

    public void loadWeatherData(){
        /**
         * This will add the rows containing weather data to the current scene.
         * This needs to be implemented for all controllers.
         */
        try {
            Race currentRace = RaceLoader.getGroup(RaceGroups.F1).getRace("Toyota/Save Mart 350");
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
        VBoxControls.addBlock("NASCAR All-Star Open", "22 May", "images/nascar/nascar_allstar.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_ALLSTAROPEN));
        VBoxControls.addBlock("NASCAR All-Star Race", "23 May", "images/nascar/nascar_allstar.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_ALLSTARRACE));
        VBoxControls.addBlock("Coca-Cola 600", "29 May", "images/nascar/nascar_cocacola.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_COCACOLA));
        VBoxControls.addBlock("Enjoy Illinois 300", "05 Jun", "images/nascar/nascar_enjoyillinois.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_ENJOYILLINOIS));
        VBoxControls.addBlock("Toyota/Save Mart 350", "12 Jun", "images/nascar/nascar_toyota.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_TOYOTA));
    }

    public void setMainWeatherData(){
        /**
         * Sets the main weather data for the non-scrollable box in the middle of the screen
         * Needs to be implemented
         */
        try {
            Race currentRace = RaceLoader.getGroup(RaceGroups.F1).getRace("Toyota/Save Mart 350");
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


