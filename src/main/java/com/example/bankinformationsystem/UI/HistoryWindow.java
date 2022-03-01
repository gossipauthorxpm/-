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
        ObservableList<String[]> list = new FromDatabase().getHistoryUser(StaticData.login);
        ObservableList<History> object_list = creteHistory(list);

        fromColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("recipient"));
        sumColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        HistoryTable.setItems(object_list);

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

    private ObservableList<History> creteHistory(ObservableList<String[]> list){
        ObservableList<History> obj_list = FXCollections.observableArrayList();
        for(String[] item : list){
            obj_list.add(new History(item[0], item[1], item[2], item[3]));
        }
        return obj_list;
    }
}
