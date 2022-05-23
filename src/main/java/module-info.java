module com.example.treeinrow {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.treeinrow to javafx.fxml;
    exports com.example.treeinrow;
    exports com.example.treeinrow.controllers;
    opens com.example.treeinrow.controllers to javafx.fxml;
}