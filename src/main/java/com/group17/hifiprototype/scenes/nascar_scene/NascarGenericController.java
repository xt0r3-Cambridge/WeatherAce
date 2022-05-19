package com.group17.hifiprototype.scenes.nascar_scene;

import com.group17.hifiprototype.scenes.base_scene.BaseMotorsportController;
import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import com.group17.hifiprototype.scenes.utils.VBoxControls;

public class NascarGenericController extends BaseMotorsportController {
    public void loadRaceData(){
        VBoxControls.addBlock("NASCAR All-Star Open", "22 May", "images/nascar/nascar_allstar.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_ALLSTAROPEN));
        VBoxControls.addBlock("NASCAR All-Star Race", "23 May", "images/nascar/nascar_allstar.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_ALLSTARRACE));
        VBoxControls.addBlock("Coca-Cola 600", "29 May", "images/nascar/nascar_cocacola.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_COCACOLA));
        VBoxControls.addBlock("Enjoy Illinois 300", "05 Jun", "images/nascar/nascar_enjoyillinois.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_ENJOYILLINOIS));
        VBoxControls.addBlock("Toyota/Save Mart 350", "12 Jun", "images/nascar/nascar_toyota.png", super.overlay, super.popupvbox, () -> SceneController.setScene(SceneId.NASCAR_TOYOTA));
    }
}
