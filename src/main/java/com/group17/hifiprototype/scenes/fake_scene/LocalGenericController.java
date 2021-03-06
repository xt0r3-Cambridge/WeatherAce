package com.group17.hifiprototype.scenes.fake_scene;

import com.group17.hifiprototype.scenes.base_scene.BaseMotorsportController;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import com.group17.hifiprototype.scenes.utils.VBoxControls;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalGenericController extends BaseMotorsportController {
    public void loadRaceData(){
        /**
         * This will add the rows containing all other scenes in the overlay.
         * This needs to be implemented for all controllers.
         */
        DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("dd MMM");
        LocalDateTime date = LocalDateTime.now();
        String startDate = dtf.format(date);

        VBoxControls.addBlock("Example race", startDate, "images/f1/spain.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.FAKE_RACE));
    }
}
