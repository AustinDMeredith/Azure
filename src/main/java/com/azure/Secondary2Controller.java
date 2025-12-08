package com.azure;

import java.io.IOException;
import java.util.ArrayList;

import com.azure.objects.BasedBox;
import com.azure.objects.BoxSpec;
import com.azure.objects.Panel;
import com.azure.util.services.ToleranceService;
import com.azure.util.services.KerfService;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.scene.control.ComboBox;

public class Secondary2Controller {

    @FXML
    private VBox mainVBox;

    // Top controls (teeth + dimensions)
    @FXML
    private VBox topControls;
    @FXML
    private VBox advancedSettingVBox;
    @FXML
    private HBox teethHBox;
    @FXML
    private HBox dimensionsHBox;
    @FXML
    private TextField teethField, widthField, heightField, depthField, engravingField, engravingSizeField;
    @FXML
    private ComboBox<String> dimensionTypeCombo;  
    @FXML
    private ComboBox<String> globalTolCombo;  
    @FXML
    private ComboBox<String> frontTolCombo;  
    @FXML
    private ComboBox<String> backTolCombo;  
    @FXML
    private ComboBox<String> leftTolCombo;  
    @FXML
    private ComboBox<String> rightTolCombo;  
    @FXML
    private ComboBox<String> topTolCombo;  
    @FXML
    private ComboBox<String> bottomTolCombo;  
    @FXML
    private ComboBox<String> kerfCombo;  
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

    @FXML
    // initializes the page and adds listeners to fields
    private void initialize() {
        dimensionTypeCombo.getSelectionModel().select(0);
        globalTolCombo.getSelectionModel().select(1);
        changePerPanelCombo();
        kerfCombo.getSelectionModel().select(1);

        // add listeners
        teethField.textProperty().addListener((obs, oldValue, newValue) -> { if(!teethField.getText().isEmpty()) updatePreview(); });
        widthField.textProperty().addListener((obs, oldValue, newValue) -> { if(!widthField.getText().isEmpty()) updatePreview(); });
        heightField.textProperty().addListener((obs, oldValue, newValue) -> { if(!heightField.getText().isEmpty()) updatePreview(); });
        depthField.textProperty().addListener((obs, oldValue, newValue) -> { if(!depthField.getText().isEmpty()) updatePreview(); });
        engravingField.textProperty().addListener((obs, oldValue, newValue) -> { if(!depthField.getText().isEmpty()) updatePreview(); });
        engravingSizeField.textProperty().addListener((obs, oldValue, newValue) -> { if(!depthField.getText().isEmpty()) updatePreview(); });
        dimensionTypeCombo.getSelectionModel().selectedIndexProperty().addListener((obs, oldValue, newValue) -> { updatePreview(); });
        globalTolCombo.getSelectionModel().selectedIndexProperty().addListener((obs, oldValue, newValue) -> { changePerPanelCombo(); updatePreview(); });
        frontTolCombo.getSelectionModel().selectedIndexProperty().addListener((obs, oldValue, newValue) -> { changeGlobalCombo(); updatePreview(); });
        backTolCombo.getSelectionModel().selectedIndexProperty().addListener((obs, oldValue, newValue) -> { changeGlobalCombo(); updatePreview(); });
        leftTolCombo.getSelectionModel().selectedIndexProperty().addListener((obs, oldValue, newValue) -> { changeGlobalCombo(); updatePreview(); });
        rightTolCombo.getSelectionModel().selectedIndexProperty().addListener((obs, oldValue, newValue) -> { changeGlobalCombo(); updatePreview(); });
        topTolCombo.getSelectionModel().selectedIndexProperty().addListener((obs, oldValue, newValue) -> { changeGlobalCombo(); updatePreview(); });
        bottomTolCombo.getSelectionModel().selectedIndexProperty().addListener((obs, oldValue, newValue) -> { changeGlobalCombo(); updatePreview(); });
        kerfCombo.getSelectionModel().selectedIndexProperty().addListener((obs, oldValue, newValue) -> { updatePreview(); });

        // udates preview so there is a box already made when the page opens
        updatePreview(); 
    }

    // Go fullscreen and hide top controls, expand WebView, toggle buttons 
    @FXML
    private void fullscreenSVG() {
        if (!isFullscreen) {
            topControls.setVisible(false);               // hide teeth + dimensions
            topControls.setManaged(false);               // remove from layout calculations
            advancedSettingVBox.setVisible(false);
            advancedSettingVBox.setManaged(false);
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
            advancedSettingVBox.setVisible(true);
            advancedSettingVBox.setManaged(true);
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
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    // update SVG preview (placeholder)
    @FXML
    private void updatePreview() {
      try {
        resetFieldStyles(); 
        Panel.PanelRole lid = returnLidType(dimensionTypeCombo.getSelectionModel().getSelectedIndex());
        double w = Double.parseDouble(widthField.getText());
        double h = Double.parseDouble(heightField.getText());
        double d = Double.parseDouble(depthField.getText());
        double t = Double.parseDouble(teethField.getText());
        String en = engravingField.getText();
        double enS = Double.parseDouble(engravingSizeField.getText());

        KerfService.setKerf(kerfCombo.getSelectionModel().getSelectedIndex());
        
        BoxSpec box = new BasedBox(h, w, d, lid, t, en, enS, getTols());
        
        svgPreview.getEngine().loadContent(box.svg, "text/html");
      } catch (Exception e) {
        highlightInvalidInputs(e);
      }
    }


    // Generate SVG file (placeholder)
    @FXML
    private String generateSVGFile() {
        Panel.PanelRole lid = returnLidType(dimensionTypeCombo.getSelectionModel().getSelectedIndex());
        double w = Double.parseDouble(widthField.getText());
        double h = Double.parseDouble(heightField.getText());
        double d = Double.parseDouble(depthField.getText());
        double t = Double.parseDouble(teethField.getText());
        String en = engravingField.getText();
        double enS = Double.parseDouble(engravingSizeField.getText());

        KerfService.setKerf(kerfCombo.getSelectionModel().getSelectedIndex());
        
        BoxSpec box = new BasedBox(h, w, d, lid, t, en, enS, getTols());
       
        return box.svg;
    }

    // opens the download page and generates the svg before sending it to the download page 
    @FXML
    private void downloadSVG() throws IOException {
       App.openDownloadPopup("downloadPopup", generateSVGFile());
    }

    private Panel.PanelRole returnLidType (int index) {
        if (index == 0) return Panel.PanelRole.top;
        else if (index == 1) return Panel.PanelRole.slidingLid;
        return Panel.PanelRole.liftingLid;
    }
    
    // finds where the exception occured and highlight that field or fields red
    private void highlightInvalidInputs(Exception e) {
      String msg = e.getMessage();
        if (msg.contains("height")) {
            heightField.getStyleClass().add("text-field-error");
        } else if (msg.contains("width")) {
            widthField.getStyleClass().add("text-field-error");
        } else if (msg.contains("depth")) {
            depthField.getStyleClass().add("text-field-error");
        } else if (msg.contains("tooth")) {
            teethField.getStyleClass().add("text-field-error");
        } else if (msg.contains("font")) {
            engravingField.getStyleClass().add("text-field-error");
            engravingSizeField.getStyleClass().add("text-field-error");
        }
    }

    // resets all the styling for the errors
    private void resetFieldStyles() {
        widthField.getStyleClass().remove("text-field-error");
        heightField.getStyleClass().remove("text-field-error");
        depthField.getStyleClass().remove("text-field-error");
        teethField.getStyleClass().remove("text-field-error");
        engravingField.getStyleClass().remove("text-field-error");
        engravingSizeField.getStyleClass().remove("text-field-error");
    } 

    // change the per panel tolerance boxes when the global tolerance box gets changed
    private void changePerPanelCombo () {
        int index = globalTolCombo.getSelectionModel().getSelectedIndex();
        frontTolCombo.getSelectionModel().select(index);
        backTolCombo.getSelectionModel().select(index);
        leftTolCombo.getSelectionModel().select(index);
        rightTolCombo.getSelectionModel().select(index);
        topTolCombo.getSelectionModel().select(index);
        bottomTolCombo.getSelectionModel().select(index);
        globalTolCombo.getSelectionModel().select(index);
    }

    // sets the global tolerance box to custom when any of the per panel tolerance boxes bet changed
    private void changeGlobalCombo () {
        globalTolCombo.getSelectionModel().select(3);
    }

    // returns a new array list of the tolerances for constrution
    private ArrayList<Double> getTols () {
      ArrayList<Double> tols = new ArrayList<Double>();
      tols.add(ToleranceService.getTolerance(frontTolCombo.getSelectionModel().getSelectedIndex()));
      tols.add(ToleranceService.getTolerance(backTolCombo.getSelectionModel().getSelectedIndex()));
      tols.add(ToleranceService.getTolerance(rightTolCombo.getSelectionModel().getSelectedIndex()));
      tols.add(ToleranceService.getTolerance(leftTolCombo.getSelectionModel().getSelectedIndex()));
      tols.add(ToleranceService.getTolerance(topTolCombo.getSelectionModel().getSelectedIndex()));
      tols.add(ToleranceService.getTolerance(bottomTolCombo.getSelectionModel().getSelectedIndex()));

      return tols;
    }
}
