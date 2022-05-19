package com.group17.hifiprototype.scenes.wec_scene;

import com.group17.hifiprototype.scenes.base_scene.BaseMotorsportController;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import com.group17.hifiprototype.scenes.utils.VBoxControls;

public class WECGenericController extends BaseMotorsportController {
    public void loadRaceData(){
        VBoxControls.addBlock("24 Hours of Le Mans", "11-12 Jun", "images/wec/france.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.WEC_LEMANS));
        VBoxControls.addBlock("6 Hours of Monza", "10 Jul", "images/wec/italy.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.WEC_MONZA));
        VBoxControls.addBlock("6 Hours of Fuji", "11 Sep", "images/wec/japan.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.WEC_FUJI));
        VBoxControls.addBlock("8 Hours of Bahrain", "12 Nov", "images/wec/bahrain.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.WEC_BAHRAIN));
    }
}
