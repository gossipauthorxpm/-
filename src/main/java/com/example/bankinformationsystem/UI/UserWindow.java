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

    String user_login = StaticData.login;

    public void initialize(){
        FromDatabase database = new FromDatabase();
        ArrayList<String> user_data = database.getInfoToUserUI(user_login);
        MoneyField.setText(user_data.get(0));
        CardField.setText(user_data.get(1));
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
    private void goTransaction(){
        Transaction transaction;
        Alerts aletr;
        if(!TransactionField.getText().equals("") && !SumField.getText().equals("")) {
            if(DataHandler.cardInBD(TransactionField.getText().trim(), new FromDatabase().getAllCards())){
                String login_recipient = new FromDatabase().getLoginForCard(TransactionField.getText());
                transaction = new Transaction(user_login, login_recipient);
                transaction.transaction(Integer.parseInt(SumField.getText().trim()));
                if(transaction.validTransaction()){
                    HistoryTime time = new HistoryTime();
                    String local_time = time.getLocalTime();
                    String local_date = time.getLocalDate();

                    new ToDatabase().updateHistory(local_date, local_time, user_login, login_recipient, SumField.getText().trim());

                    aletr = new Alerts(Alert.AlertType.INFORMATION, "Транзакция", "Успех", "Транзакция успешно проведена!");
                }else {
                    aletr = new Alerts(Alert.AlertType.ERROR, "Транзакция", "Ошибка", "Перевод невозможен!");
                }
            }else{
                aletr = new Alerts(Alert.AlertType.ERROR, "Транзакция", "Ошибка", "Пользователь не найден!");
            }
        }else{
            aletr = new Alerts(Alert.AlertType.ERROR, "Транзакция", "Ошибка", "Заполните поля для транзакции!");
        }
        Alerts.showAlert(aletr);
    }
    @FXML
    private void goToHistory(){
        Form form = new Form("history.fxml", "История переводов");
    }
}
