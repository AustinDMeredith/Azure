package com.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class Secondary1Controller {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}