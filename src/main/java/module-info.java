module com.azure {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.web; 

    opens com.azure to javafx.fxml;
    exports com.azure;
}
