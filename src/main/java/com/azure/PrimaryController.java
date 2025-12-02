package com.azure;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class PrimaryController {
    
    //this stuff connects the matching variables in the fxml to java
    @FXML private BorderPane rootPane; //detetcs window size changes
    @FXML private Label headerLabel; //azure box creations
    @FXML private Label titleLabel; //choose your design
    @FXML private Label subtitleLabel; //which box fits your needs
    @FXML private Label regularLabel; //regular box 
    @FXML private Label basedLabel; //based box
    @FXML private Label hingeLabel; //hinge box
    @FXML private ImageView regularBox; //first box image
    @FXML private ImageView basedBox; //second box image
    @FXML private ImageView dieBox; //third box image
    @FXML private HBox imageContainer; //container to hold all images
    @FXML private HBox labelContainer; //container for text below images
    
    // Base sizes for small window
    private static final double BASE_WIDTH = 800.0; //small window width
    private static final double BASE_HEIGHT = 600.0;//small window height
    // 
    //increase or decrease for font sizes
    private static final double BASE_HEADER_SIZE = 25.0; //azure box creations
    private static final double BASE_TITLE_SIZE = 75.0; //choose your design
    private static final double BASE_SUBTITLE_SIZE = 35.0; //which box fits your needs
    private static final double BASE_LABEL_SIZE = 16.0; //regualr box, based box dice box
    private static final double BASE_IMAGE_SIZE = 150.0; //box image dimensions
    private static final double BASE_IMAGE_SPACING = 60.0; //space between images
    
    @FXML
    public void initialize() {
        // Add listeners to window size changes
        //this stuff is called when then window width changes and calls update scaling
        rootPane.widthProperty().addListener((obs, oldVal, newVal) -> updateScaling());
        //this is called when window height changes
        rootPane.heightProperty().addListener((obs, oldVal, newVal) -> updateScaling());
    }
    
    //this recalculates and applies all sizes when window is resized
    private void updateScaling() {
        //get the current window dimensions
        double width = rootPane.getWidth();
        double height = rootPane.getHeight();
        
        //figure out how much bigger / smaller current window is compared to the base size
        double scaleX = width / BASE_WIDTH;
        double scaleY = height / BASE_HEIGHT;
        //use smaller of the two scales to maintain aspect ratio
        //prevent distortion if it is too wide or tall
        double scale = Math.min(scaleX, scaleY);
        
        // Scale fonts
        //multiply each base font size by the scale factor
        headerLabel.setStyle(String.format("-fx-font-size: %.1fpx; -fx-text-fill: white;", 
            BASE_HEADER_SIZE * scale));
        titleLabel.setStyle(String.format("-fx-font-size: %.1fpx; -fx-font-weight: bold; -fx-text-fill: white;", 
            BASE_TITLE_SIZE * scale));
        subtitleLabel.setStyle(String.format("-fx-font-size: %.1fpx; -fx-font-weight: bold; -fx-text-fill: white;", 
            BASE_SUBTITLE_SIZE * scale));
        
        regularLabel.setStyle(String.format("-fx-font-size: %.1fpx; -fx-font-weight: bold; -fx-text-fill: white;", 
            BASE_LABEL_SIZE * scale));
        basedLabel.setStyle(String.format("-fx-font-size: %.1fpx; -fx-font-weight: bold; -fx-text-fill: white;", 
            BASE_LABEL_SIZE * scale));
        hingeLabel.setStyle(String.format("-fx-font-size: %.1fpx; -fx-font-weight: bold; -fx-text-fill: white;", 
            BASE_LABEL_SIZE * scale));
        
        // Scale images
        double newImageSize = BASE_IMAGE_SIZE * scale;
        regularBox.setFitWidth(newImageSize);
        regularBox.setFitHeight(newImageSize);
        basedBox.setFitWidth(newImageSize);
        basedBox.setFitHeight(newImageSize);
        dieBox.setFitWidth(newImageSize);
        dieBox.setFitHeight(newImageSize);
        
        // Scale spacing
        double newSpacing = BASE_IMAGE_SPACING * scale;
        imageContainer.setSpacing(newSpacing);
        labelContainer.setSpacing(newSpacing);
        
        // Scale label translations
        regularLabel.setTranslateX(-80 * scale);
        basedLabel.setTranslateX(-10 * scale);
        hingeLabel.setTranslateX(70 * scale);
    }
    
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
