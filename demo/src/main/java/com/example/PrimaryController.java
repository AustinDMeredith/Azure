package com.example;

import javafx.fxml.FXML;
import java.io.IOException;

public class PrimaryController {

    @FXML
    private void handleBox1Click() throws IOException {
        App.setRoot("secondary1");
    }

    @FXML
    private void handleBox2Click() throws IOException {
        App.setRoot("secondary2");
    }

    @FXML
    private void handleBox3Click() throws IOException {
        App.setRoot("secondary3");
    }
}