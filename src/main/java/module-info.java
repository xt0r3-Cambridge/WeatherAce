module com.group17.hifiprototype {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.group17.hifiprototype to javafx.fxml;
    opens com.group17.hifiprototype.scenes.main_menu_scene to javafx.fxml;
    exports com.group17.hifiprototype.scenes.main_menu_scene;
    opens com.group17.hifiprototype.scenes.f1_scene to javafx.fxml;
    exports com.group17.hifiprototype.scenes.f1_scene;
    opens com.group17.hifiprototype.scenes.utils to javafx.fxml;
    opens com.group17.hifiprototype.scenes.base_scene to javafx.fxml;
    exports com.group17.hifiprototype;
    exports com.group17.hifiprototype.scenes.base_scene;
    exports com.group17.hifiprototype.scenes.utils;
}