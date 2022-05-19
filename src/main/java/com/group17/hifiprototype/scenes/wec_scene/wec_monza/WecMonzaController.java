package com.group17.hifiprototype.scenes.wec_scene.wec_monza;

import com.group17.hifiprototype.dataclasses.*;
import com.group17.hifiprototype.scenes.base_scene.BaseMotorsportController;
import com.group17.hifiprototype.scenes.utils.GridControls;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import com.group17.hifiprototype.scenes.utils.VBoxControls;

import java.util.ArrayList;
import java.util.List;

public class WecMonzaController extends BaseMotorsportController {

    public void loadWeatherData(){
        try {
            Race currentRace = RaceLoader.getGroup(RaceGroups.F1).getRace("6 Hours of Monza");
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
        VBoxControls.addBlock("24 Hours of Le Mans", "11-12 Jun", "images/wec/france.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.WEC_LEMANS));
        VBoxControls.addBlock("6 Hours of Monza", "10 Jul", "images/wec/italy.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.WEC_MONZA));
        VBoxControls.addBlock("6 Hours of Fuji", "11 Sep", "images/wec/japan.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.WEC_FUJI));
        VBoxControls.addBlock("8 Hours of Bahrain", "12 Nov", "images/wec/bahrain.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.WEC_BAHRAIN));
    }

    public void setMainWeatherData(){
        /**
         * Sets the main weather data for the non-scrollable box in the middle of the screen
         * Needs to be implemented
         */
        try {
            Race currentRace = RaceLoader.getGroup(RaceGroups.F1).getRace("6 Hours of Monza");
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


