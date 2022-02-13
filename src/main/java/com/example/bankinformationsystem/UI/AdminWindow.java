package com.example.bankinformationsystem.UI;

import com.example.bankinformationsystem.DB.DataHandler;
import com.example.bankinformationsystem.DB.DatabaseHandler;
import com.example.bankinformationsystem.DB.User;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert.AlertType;

import java.util.List;

public class AdminWindow {

    @FXML
    private ComboBox<String> CardList;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField MoneyField;

    @FXML
    private TextField RealNameField;

    @FXML
    private TextField StatusField;
    @FXML
    private TextField PasswordField;

    @FXML
    private void loadCards(){
        CardList.setItems(new DatabaseHandler().getAllCards());
    }

    @FXML
    private void loadDataFromCards(){
        DatabaseHandler database = new DatabaseHandler();
        String select_card = CardList.getValue();
        if(select_card.trim().length() == 16){
            List<String> info_from_card = database.getInfoFromCard(select_card);
            setTextInFields(info_from_card);
        }
    }

    @FXML
    private void updateUserDate(){
        Alerts alert;
        DatabaseHandler database = new DatabaseHandler();
        User user = createUser();

        if(user.getCard().length() == 16){
            if(DataHandler.cardInBD(user.getCard(), database.getAllCards())) {
                if(DataHandler.loginInBD(user.getLogin(), database.getListLogins())) {
                    database.updateCurrentUser(user);
                    alert = new Alerts(AlertType.INFORMATION, "Обновление данных", "Успех!", "Вы обновили данные для карты!");
                }else{
                    alert = new Alerts(AlertType.ERROR, "Обновление данных", "Ошибка", "Логин изменить нельзя!");
                }
            }
            else {
                alert = new Alerts(AlertType.ERROR, "Обновление данных", "Ошибка", "Карты нет в базе данных!");
            }
        }else{
            alert = new Alerts(AlertType.ERROR, "Обновление данных", "Ошибка","Номер карты меньше 16 символов!");
        }
        Alerts.showAlert(alert);
    }
    @FXML
    private void addNewUser(){
        Alerts alert;
        DatabaseHandler database = new DatabaseHandler();
        User new_user = createUser();

        if(new_user.getCard().length() == 16){
            if(!DataHandler.loginInBD(new_user.getLogin(), database.getListLogins())){
                if (!DataHandler.cardInBD(new_user.getCard(), database.getAllCards())){
                    database.addNewUser(new_user);
                    alert = new Alerts(AlertType.INFORMATION, "Добавление пользователя", "Успех!", "Пользователь добавлен в базу данных");
                }else {
                    alert = new Alerts(AlertType.ERROR, "Добавление пользователя", "Ошибка", "Пользователь с такой картой уже находится в базе данных");
                }
            }else{
                alert = new Alerts(AlertType.ERROR, "Добавление пользователя", "Ошибка", "Пользователь с таким логином уже находится в базе данных");
            }
        }else {
            alert = new Alerts(AlertType.ERROR, "Добавление пользователя", "Ошибка","Номер карты должен иметь 16 символов");
        }
        Alerts.showAlert(alert);
    }

    private User createUser(){
        String real_name = RealNameField.getText().trim();
        String status = StatusField.getText().trim();
        String login = LoginField.getText().trim();
        String password = PasswordField.getText().trim();
        String money = MoneyField.getText().trim();
        String cart = CardList.getValue().trim();
        return new User(login, password, real_name, status, money, cart);
    }
    private void setTextInFields(List<String> list) {
        RealNameField.setText(list.get(0));
        StatusField.setText(list.get(1));
        LoginField.setText(list.get(2));
        PasswordField.setText(list.get(3));
        MoneyField.setText(list.get(4));
    }
}