package com.example.bankinformationsystem.UI;

import com.example.bankinformationsystem.DB.DataHandler;
import com.example.bankinformationsystem.DB.FromDatabase;
import com.example.bankinformationsystem.DB.ToDatabase;
import com.example.bankinformationsystem.utils.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class UserWindow {

    String user_login = StaticData.user_login;

    public void initialize(){
        FromDatabase database = new FromDatabase();
        ArrayList<String> user_data = database.getInfoToUserUI(user_login);
        setUserFields(user_data);
    }
    @FXML
    private TextField CardField;

    @FXML
    private Button HistoryButton;

    @FXML
    private TextField MoneyField;

    @FXML
    private Button TransactionButton;

    @FXML
    private TextField TransactionField;

    @FXML
    private TextField SumField;

    @FXML
    private void goTransaction() {
        Transaction transaction;
        Alerts alert;
        try {
            if (!TransactionField.getText().equals("") && !SumField.getText().equals("")) {
                if (DataHandler.cardInBD(TransactionField.getText().trim(), new FromDatabase().getAllCards())) {
                    String login_recipient = new FromDatabase().getLoginForCard(TransactionField.getText());
                    transaction = new Transaction(user_login, login_recipient);
                    transaction.transaction(Integer.parseInt(SumField.getText().trim()));

                    HistoryTime time = new HistoryTime();
                    new ToDatabase().updateHistory(time.getLocalDate(), time.getLocalTime(), user_login, login_recipient, SumField.getText().trim());

                    updateUserFields();

                    alert = new Alerts(Alert.AlertType.INFORMATION, "Транзакция", "Успех", "Транзакция успешно проведена!");
                } else {
                    alert = new Alerts(Alert.AlertType.ERROR, "Транзакция", "Ошибка", "Пользователь не найден!");
                }
            } else {
                alert = new Alerts(Alert.AlertType.ERROR, "Транзакция", "Ошибка", "Заполните поля для транзакции!");
            }
        }catch (NumberFormatException e){
            alert = new Alerts(Alert.AlertType.ERROR, "Транзакция", "Ошибка", "Введите целое число для перевода!");
        }catch (Exception e){
            alert = new Alerts(Alert.AlertType.ERROR, "Транзакция", "Ошибка", "Ошибка перевода!");
        }
        Alerts.showAlert(alert);
    }
    @FXML
    private void goToHistory(){
        Form form = new Form("history.fxml", "История переводов");
    }
    private void setUserFields(ArrayList<String> user_data){
        MoneyField.setText(user_data.get(0));
        CardField.setText(user_data.get(1));
    }
    private void updateUserFields(){
        ArrayList<String> user_data = new FromDatabase().getInfoToUserUI(user_login);
        setUserFields(user_data);
    }
}
