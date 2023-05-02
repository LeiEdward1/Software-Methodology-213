package com.example.assignment_4;

import javafx.stage.Stage;

public abstract class SecondViewController {
    private MainController mainController;

    private Stage stage;

    public void setMainController(MainController controller){
        mainController = controller;
    }

    public abstract void setup();
    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void closeStage(){
        stage.close();
    }

    public MainController getMainController() {
        return mainController;
    }
}
