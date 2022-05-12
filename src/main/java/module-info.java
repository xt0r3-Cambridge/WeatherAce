module com.group17.hifiprototype {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.group17.hifiprototype to javafx.fxml;
    exports com.group17.hifiprototype;
    exports com.group17.hifiprototype.dataclasses;
    opens com.group17.hifiprototype.dataclasses to javafx.fxml;
}