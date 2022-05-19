package com.group17.hifiprototype.scenes.wec_scene.wec_lemans;

import com.group17.hifiprototype.scenes.base_scene.BaseMotorsportController;
import com.group17.hifiprototype.scenes.utils.GridControls;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import com.group17.hifiprototype.scenes.utils.VBoxControls;

public class WecLeMansController extends BaseMotorsportController {

    public void loadWeatherData(){
        GridControls.newRowFromData(super.detailedDataContainer, "FRI", "", "", "", "", "");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");
        GridControls.newRowFromData(super.detailedDataContainer, "14:20", "12%", "36°C", "200°C", "700km/h", "1.5m");

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
        super.mainWind.setText("75km/h ↗");
        super.mainRain.setText("99%");
        super.mainTemp.setText("658°C");
    }
}


