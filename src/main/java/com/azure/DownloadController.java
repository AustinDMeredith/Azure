package com.azure;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadController {

  @FXML
  private Label selectedFileLabel;
  private String svgContent;
  private String defaultFileName = "box.svg";

  @FXML
  //opens a new window with a directory picker to save file
  private void handleSaveSvg(ActionEvent event) {
    DirectoryChooser chooser = new DirectoryChooser();
    chooser.setTitle("Choose folder to save SVG");
    Window window = ((Node) event.getSource()).getScene().getWindow();

    File dir = chooser.showDialog(window);
    if (dir == null) return;

    try {
      Path output = Paths.get(dir.getAbsolutePath(), defaultFileName);
      Files.writeString(output, svgContent);
      selectedFileLabel.setText("Saved: " + output);
    } catch (IOException e) {
      selectedFileLabel.setText("Error saving SVG");
      e.printStackTrace();
    }
  }
 
  @FXML
  // handles the close of the window
  private void handleClose(ActionEvent event) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();
  }

  // setter for the svg content
  public void setSvgContent(String svgContent) {
    this.svgContent = svgContent;
  }
}  
