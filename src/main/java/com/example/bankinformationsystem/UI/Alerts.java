package com.example.bankinformationsystem.UI;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {

    private final Alert alert;

    public Alerts(AlertType type, String alert_title, String alert_header_text, String alert_content_text){
        this.alert = new Alert(type);
        this.alert.setTitle(alert_title);
        this.alert.setHeaderText(alert_header_text);
        this.alert.setContentText(alert_content_text);
    }
    public static void showAlert(Alerts alert){
        alert.alert.showAndWait();
    }
}
