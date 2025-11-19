package com.azure;

import javafx.fxml.FXML;
<<<<<<< HEAD
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Secondary2Controller {

    @FXML private WebView svgPreview;
    @FXML private TextField widthField, heightField, depthField, teethField;


    //once the fxml is loaded this is called
    @FXML
    private void initialize() {
        updatePreview(); 
    }


    //svg (generation and) update 
    @FXML
    private void updatePreview() {
        try {
            double w = Double.parseDouble(widthField.getText());
            double h = Double.parseDouble(heightField.getText());
            String svg = generateBoxSVG(w, h);
            svgPreview.getEngine().loadContent(svg, "text/html");
        } catch (NumberFormatException e) {
            svgPreview.getEngine().loadContent("<html><body><h3 style='color:red'>Invalid input</h3></body></html>");
        }
    }

    //this is the placeholder of the svg. right now it is just a box 
    private String generateBoxSVG(double w, double h) {
        return """
            <html><body style='margin:0;'>
            <svg width='%f' height='%f' xmlns='http://www.w3.org/2000/svg'>
                <rect x='10' y='10' width='%f' height='%f' stroke='black' fill='none' stroke-width='2'/>
            </svg>
            </body></html>
        """.formatted(w + 20, h + 20, w, h);
    }


    //generates svg and puts it in your file. not working right now 
    @FXML
    private void generateSVGFile() throws IOException {
        //double w = Double.parseDouble(widthField.getText());
        //double h = Double.parseDouble(heightField.getText());
        //String svg = generateBoxSVG(w, h);
        //Files.writeString(Path.of("regular_box.svg"), svg);
    }


    //goes back to main menu 
=======
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;

import java.io.IOException;

public class Secondary2Controller {

    @FXML
    private VBox mainVBox;

    // Top controls (teeth + dimensions)
    @FXML
    private VBox topControls;
    @FXML
    private HBox teethHBox;
    @FXML
    private HBox dimensionsHBox;
    @FXML
    private TextField teethField, widthField, heightField, depthField;

    // Fullscreen button container
    @FXML
    private HBox fullscreenBtnContainer;

    // WebView
    @FXML
    private WebView svgPreview;

    // Bottom buttons
    @FXML
    private HBox buttonHBox;
    @FXML
    private Button fullscreenBtn;
    @FXML
    private Button exitFullscreenBtn;

    private boolean isFullscreen = false;

    // Go fullscreen and hide top controls, expand WebView, toggle buttons 
    @FXML
    private void fullscreenSVG() {
        if (!isFullscreen) {
            topControls.setVisible(false);               // hide teeth + dimensions
            topControls.setManaged(false);               // remove from layout calculations
            fullscreenBtnContainer.setVisible(false);    // hide fullscreen button container
            fullscreenBtnContainer.setManaged(false);    // remove from layout calculations
            mainVBox.setPadding(new Insets(0, 0, 20, 0)); // remove top/side padding, keep bottom for buttons
            VBox.setVgrow(svgPreview, Priority.ALWAYS);  // expand WebView vertically
            svgPreview.setMaxHeight(Double.MAX_VALUE);   // allow unlimited height growth

            exitFullscreenBtn.setVisible(true);          // show exit fullscreen button in bottom bar

            isFullscreen = true;
        }
    }

    // exit fullscreen: show top controls, restore padding, toggle buttons
    @FXML
    private void exitFullscreenSVG() {
        if (isFullscreen) {
            topControls.setVisible(true);                // show teeth + dimensions
            topControls.setManaged(true);                // add back to layout calculations
            fullscreenBtnContainer.setVisible(true);     // show fullscreen button container
            fullscreenBtnContainer.setManaged(true);     // add back to layout calculations
            mainVBox.setPadding(new Insets(20));         // restore padding
            svgPreview.setMaxHeight(Double.MAX_VALUE);   // keep unlimited height
            VBox.setVgrow(svgPreview, Priority.ALWAYS);  // keep vgrow priority

            exitFullscreenBtn.setVisible(false);         // hide exit fullscreen button

            isFullscreen = false;
        }
    }

    // back to main menu
>>>>>>> frontend
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
<<<<<<< HEAD
}
=======

    // update SVG preview (placeholder)
    @FXML
    private void updatePreview() {
        
    }

    // Generate SVG file (placeholder)
    @FXML
    private void generateSVGFile() {
       
    }

    //
    @FXML
    private void downloadSVG() {
        
    }
}
>>>>>>> frontend
