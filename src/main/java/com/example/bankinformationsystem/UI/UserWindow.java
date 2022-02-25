package com.example.bankinformationsystem.UI;

import com.example.bankinformationsystem.DB.FromDatabase;
import com.example.bankinformationsystem.utils.StaticData;
import com.example.bankinformationsystem.utils.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label HistoryButton;

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
        String login_recipient = new FromDatabase().getLoginForCard(TransactionField.getText());
        Transaction transaction = new Transaction(user_login, login_recipient);
        transaction.transaction(Integer.parseInt(SumField.getText().trim()));
    }
}
