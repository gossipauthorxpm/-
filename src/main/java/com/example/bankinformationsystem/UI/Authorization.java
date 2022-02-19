package com.example.bankinformationsystem.UI;

import com.example.bankinformationsystem.DB.DataHandler;
import com.example.bankinformationsystem.DB.Database;
import com.example.bankinformationsystem.DB.FromDatabase;
import com.example.bankinformationsystem.DB.ToDatabase;
import com.example.bankinformationsystem.StartApplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import  javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

public class Authorization {

    @FXML
    private TextField LoginField;
    @FXML
    private PasswordField PasswordField;

    @FXML
    private void authorize(){
        Alerts alert;
        if (!LoginField.getText().equals("") && !PasswordField.getText().equals("")) {
            FromDatabase database = new FromDatabase();
            DataHandler authorization_data = new DataHandler(database.getAuthorizeDate());
            boolean valid = authorization_data.validAuthorize(LoginField.getText().trim(), PasswordField.getText().trim());
            boolean admin_valid = authorization_data.validAdmin(LoginField.getText().trim(), PasswordField.getText().trim());
            if(admin_valid){
                adminFormInitialize();
                return;
            }
            if(valid){
                alert = new Alerts(AlertType.INFORMATION, "Успех", "Вход", "Вы успешно вошли!");

            }else{
                alert = new Alerts(AlertType.ERROR, "Ошибка", "Вход", "Пользователь не найден!");
            }
        }
        else {
            alert = new Alerts(AlertType.ERROR, "Ошибка", "Ввод", "Введите логин и пароль");
        }
        Alerts.showAlert(alert);
    }
    private void adminFormInitialize() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Admin");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            System.out.println("Fail" + e);
        }
    }
}
