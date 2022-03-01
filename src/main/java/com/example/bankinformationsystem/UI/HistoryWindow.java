package com.example.bankinformationsystem.UI;

import com.example.bankinformationsystem.DB.FromDatabase;
import com.example.bankinformationsystem.utils.History;
import com.example.bankinformationsystem.utils.StaticData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class HistoryWindow {
    public void initialize(){
        ObservableList<String[]> user_history = new FromDatabase().getHistoryUser(StaticData.user_login);
        ObservableList<History> treatments_history = treatmentHistory(user_history);

        fromColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("recipient"));
        sumColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        HistoryTable.setItems(treatments_history);

    }
    @FXML
    private TableView<History> HistoryTable;
    @FXML
    private TableColumn<History, String> sumColumn;
    @FXML
    private TableColumn<History, String> toColumn;
    @FXML
    private TableColumn<History, String> fromColumn;
    @FXML
    private TableColumn<History, String> timeColumn;

    private ObservableList<History> treatmentHistory(ObservableList<String[]> user_history){
        ObservableList<History> obj_list = FXCollections.observableArrayList();
        for(String[] item : user_history){
            obj_list.add(new History(item[0], item[1], item[2], item[3]));
        }
        return obj_list;
    }
}
