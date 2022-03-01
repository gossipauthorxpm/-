package com.example.bankinformationsystem.utils;

import com.example.bankinformationsystem.StartApplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

public class Form {
    Stage stage;
    String name_fxml_file;
    String title_form;
    public Form(String name_fxml_file, String title_form){
        this.name_fxml_file = name_fxml_file;
        this.title_form = title_form;
        formInitialize();
    }
    private void formInitialize(){
        try {
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource(name_fxml_file));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle(title_form);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println("Fail" + e);
        }
    }
    public static void hideStage(Parent control){
        Stage stage = (Stage) control.getScene().getWindow();
        stage.close();
    }

}
