package com.example.bankinformationsystem.UI;

import com.example.bankinformationsystem.utils.Encoder;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserWindow {
    @FXML
    private Label Label1;

    @FXML
    private void useButton(){
        Label1.setText(Encoder.encrypt("encrypt my please"));
    }
}
