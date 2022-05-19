package com.group17.hifiprototype.scenes.f1_scene;

import com.group17.hifiprototype.scenes.base_scene.BaseMotorsportController;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import com.group17.hifiprototype.scenes.utils.VBoxControls;

public class F1GenericController extends BaseMotorsportController {
    public void loadRaceData(){
        /**
         * This will add the rows containing all other scenes in the overlay.
         * This needs to be implemented for all controllers.
         */
        VBoxControls.addBlock("Spanish Grand Prix", "20-22 May", "images/f1/spain.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.F1_SPAIN));
        VBoxControls.addBlock("Monaco Grand Prix", "27-29 May", "images/f1/monaco.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.F1_MONACO));
        VBoxControls.addBlock("Azerbaijan Grand Prix", "10-12 Jun", "images/f1/azerbaijan.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.F1_AZERBAIJAN));
    }
}
