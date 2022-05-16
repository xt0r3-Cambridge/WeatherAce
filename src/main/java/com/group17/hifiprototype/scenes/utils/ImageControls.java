package com.group17.hifiprototype.scenes.utils;

import com.group17.hifiprototype.MainApplication;
import javafx.scene.image.Image;

import java.net.URL;

public class ImageControls {
    public static Image createImage(String resourceName) {
        URL _url = MainApplication.class.getResource(resourceName);
        return new Image(_url.toExternalForm());
    }
}
