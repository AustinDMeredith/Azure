package com.example;

import javafx.fxml.FXML;
import java.io.IOException;

public class Secondary2Controller {
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}