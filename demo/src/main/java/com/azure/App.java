package com.azure;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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


    //runs the app 
    public static void main(String[] args) {
        launch();
    }
}