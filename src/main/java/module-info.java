module project.weatherfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens project.weatherfx to javafx.fxml;
    exports project.weatherfx;
    exports project.weatherfx.controller;
    opens project.weatherfx.controller to javafx.fxml;
}