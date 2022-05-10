package com.group17.hifiprototype;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // SET STAGE PARAMETERS
        stage.setTitle("WeatherAce");
        /*
         TODO: add icon to resources folder and uncomment
         Image icon = new Image("icon.png");
         stage.getIcons().add(icon);

         stage.setWidth(500);  // can be resized during runtime
         stage.setHeight(1300);  // can be resized during runtime
         */

        // LOAD APPLICATION PAGES
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        scene.getStylesheets().add("style.css");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}