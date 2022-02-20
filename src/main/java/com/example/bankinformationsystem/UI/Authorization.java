package com.example.bankinformationsystem.UI;

import com.example.bankinformationsystem.utils.Form;
import com.example.bankinformationsystem.DB.DataHandler;
import com.example.bankinformationsystem.DB.FromDatabase;

import com.example.bankinformationsystem.utils.StaticData;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import  javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;


public class Authorization {
    private String user_login;
    public String getUserLogin(){
        return user_login;
    }
    @FXML
    public AnchorPane form;
    @FXML
    private TextField LoginField;
    @FXML
    private PasswordField PasswordField;

    @FXML
    private void authorize(){
        Alerts alert;
        if (!LoginField.getText().equals("") && !PasswordField.getText().equals("")) {
            FromDatabase database = new FromDatabase();
            DataHandler authorization_data = new DataHandler(database.getAuthorizeData());
            boolean valid = authorization_data.validAuthorize(LoginField.getText().trim(), PasswordField.getText().trim());
            boolean admin_valid = authorization_data.validAdmin(LoginField.getText().trim(), PasswordField.getText().trim());
            if(admin_valid){
                new Form("admin.fxml", "admin");
                return;
            }
            if(valid){
                alert = new Alerts(AlertType.INFORMATION, "Успех", "Вход", "Вы успешно вошли!");
                Alerts.showAlert(alert);
                StaticData.login = LoginField.getText().trim();
                Form.hideStage(form);
                new Form("user.fxml", "user");
                return;
            }else{
                alert = new Alerts(AlertType.ERROR, "Ошибка", "Вход", "Пользователь не найден!");
            }
        }
        else {
            alert = new Alerts(AlertType.ERROR, "Ошибка", "Ввод", "Введите логин и пароль");
        }
        Alerts.showAlert(alert);
    }
}
