package com.example;

import javafx.fxml.FXML;
import java.io.IOException;
import javafx.scene.input.MouseEvent;

public class PrimaryController {

    @FXML
    private void handleRegularBoxClick(MouseEvent event) throws IOException {
        App.setRoot("secondary1");
    }

    @FXML
    private void handleBasedBoxClick(MouseEvent event) throws IOException {
        App.setRoot("secondary2");
    }

    @FXML
    private void handleDieBoxClick(MouseEvent event) throws IOException {
        App.setRoot("secondary3");
    }
}