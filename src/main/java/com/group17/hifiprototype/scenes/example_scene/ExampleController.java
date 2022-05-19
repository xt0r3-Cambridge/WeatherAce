package com.group17.hifiprototype.scenes.example_scene;

import com.group17.hifiprototype.scenes.base_scene.BaseMotorsportController;
import com.group17.hifiprototype.scenes.utils.VBoxControls;

public class ExampleController extends BaseMotorsportController {

    public void loadRaceData() {
        /**
         * This will add the rows containing all other scenes in the overlay.
         * This needs to be implemented for all controllers.
         */
        VBoxControls.addBlock("Miami Grand Prix", "06-08 May", "images/flags/usa.png", super.overlay, super.popupvbox, () -> System.out.println("I am the best"));
        VBoxControls.addBlock("Fasz2", "soinfqoienfoqi", "images/flags/usa.png", super.overlay, super.popupvbox, () -> System.out.println("click"));
        VBoxControls.addBlock("Fasz3", "soinfqoienfoqi", "images/flags/usa.png", super.overlay, super.popupvbox, () -> System.out.println("click2"));
        VBoxControls.addBlock("Fasz4", "soinfqoienfoqi", "images/flags/usa.png", super.overlay, super.popupvbox, () -> System.out.println("click3"));
    }

}


