package com.group17.hifiprototype.scenes.f1_scene;

import com.group17.hifiprototype.scenes.utils.SceneController;
import com.group17.hifiprototype.scenes.utils.SceneId;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.spreadsheet.Grid;

public class F1Controller {

    @FXML
    private ListView<String> scrollList;
    @FXML
    private AnchorPane container;
    @FXML
    private GridPane scrollbar;
    @FXML
    private GridPane mainContent;


    public void addData(Iterable<String> data) {
        for (String dataPoint : data) {
            scrollList.getItems().add(dataPoint);
        }
    }


    public void init() {
    }
}
