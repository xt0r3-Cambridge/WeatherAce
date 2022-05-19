module com.group17.hifiprototype {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.json;
    requires org.apache.commons.lang3;

    exports com.group17.hifiprototype;
    exports com.group17.hifiprototype.backend.dataclasses;
    opens com.group17.hifiprototype.backend.dataclasses to javafx.fxml;

    opens com.group17.hifiprototype to javafx.fxml;
    opens com.group17.hifiprototype.scenes.main_menu_scene to javafx.fxml;
    exports com.group17.hifiprototype.scenes.main_menu_scene;
    opens com.group17.hifiprototype.scenes.utils to javafx.fxml;
    opens com.group17.hifiprototype.scenes.base_scene to javafx.fxml;
    exports com.group17.hifiprototype.scenes.base_scene;
    exports com.group17.hifiprototype.scenes.utils;

    opens com.group17.hifiprototype.scenes.example_scene to javafx.fxml;  // This needs to be duplicated for other scenes
    exports com.group17.hifiprototype.scenes.example_scene;  // This needs to be duplicated for other scenes

    // F1 Exports
    opens com.group17.hifiprototype.scenes.f1_scene.f1_azerbaijan to javafx.fxml;
    exports com.group17.hifiprototype.scenes.f1_scene.f1_azerbaijan;
    opens com.group17.hifiprototype.scenes.f1_scene.f1_britain to javafx.fxml;
    exports com.group17.hifiprototype.scenes.f1_scene.f1_britain;
    opens com.group17.hifiprototype.scenes.f1_scene.f1_canada to javafx.fxml;
    exports com.group17.hifiprototype.scenes.f1_scene.f1_canada;
    opens com.group17.hifiprototype.scenes.f1_scene.f1_miami to javafx.fxml;
    exports com.group17.hifiprototype.scenes.f1_scene.f1_miami;
    opens com.group17.hifiprototype.scenes.f1_scene.f1_monaco to javafx.fxml;
    exports com.group17.hifiprototype.scenes.f1_scene.f1_monaco;
    opens com.group17.hifiprototype.scenes.f1_scene.f1_spain to javafx.fxml;
    exports com.group17.hifiprototype.scenes.f1_scene.f1_spain;

    // Nascar Exports
    opens com.group17.hifiprototype.scenes.nascar_scene.nascar_toyota to javafx.fxml;
    exports com.group17.hifiprototype.scenes.nascar_scene.nascar_toyota;
    opens com.group17.hifiprototype.scenes.nascar_scene.nascar_enjoyillinois to javafx.fxml;
    exports com.group17.hifiprototype.scenes.nascar_scene.nascar_enjoyillinois;
    opens com.group17.hifiprototype.scenes.nascar_scene.nascar_allstaropen to javafx.fxml;
    exports com.group17.hifiprototype.scenes.nascar_scene.nascar_allstaropen;
    opens com.group17.hifiprototype.scenes.nascar_scene.nascar_allstarrace to javafx.fxml;
    exports com.group17.hifiprototype.scenes.nascar_scene.nascar_allstarrace;
    opens com.group17.hifiprototype.scenes.nascar_scene.nascar_cocacola to javafx.fxml;
    exports com.group17.hifiprototype.scenes.nascar_scene.nascar_cocacola;

    // WEC Exports
    opens com.group17.hifiprototype.scenes.wec_scene.wec_bahrain to javafx.fxml;
    exports com.group17.hifiprototype.scenes.wec_scene.wec_bahrain;
    opens com.group17.hifiprototype.scenes.wec_scene.wec_fuji to javafx.fxml;
    exports com.group17.hifiprototype.scenes.wec_scene.wec_fuji;
    opens com.group17.hifiprototype.scenes.wec_scene.wec_lemans to javafx.fxml;
    exports com.group17.hifiprototype.scenes.wec_scene.wec_lemans;
    opens com.group17.hifiprototype.scenes.wec_scene.wec_monza to javafx.fxml;
    exports com.group17.hifiprototype.scenes.wec_scene.wec_monza;





}