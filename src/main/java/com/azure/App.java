package com.azure;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.io.IOException;

public class App extends Application {

    private static Stage stage;
    private static Scene scene; 

    //This is the GUI 
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Parent root = fxmlLoader.load();
        
        scene = new Scene(root, 600, 400);
        stage.setTitle("Azure Box Creations");
        stage.setScene(scene);
        stage.show();
    }

    //Lets you switch between scenes 
    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent newRoot = fxmlLoader.load();
        scene.setRoot(newRoot);
    }

    // Used for Downloading SVG file
    public static void openDownloadPopup(String fxml, String currentSvg) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();

        DownloadController controller = fxmlLoader.getController();
        controller.setSvgContent(currentSvg); 

        Stage popup = new Stage();
        popup.setTitle("Popup Window");
        popup.setScene(new Scene(root));
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.showAndWait();
    }

    public static void showError(Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("An exception occurred");
      alert.setContentText(e.getMessage());
      alert.showAndWait();
    }


    //runs the app 
    public static void main(String[] args) {
        launch();
    }
}
