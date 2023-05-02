package com.example.assignment_4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage secondStage = new Stage();

    private SecondViewController secondViewController;
    private Store store = new Store();

    private Order order = new Order();

    @FXML
    private Label welcomeText;

    @FXML
    private Button donutButton, coffeeButton, basketButton, listButton;


    protected void closeSecondStage(){
        secondStage.close();
    }

    public void setOrder(Order order){
        this.order = order;
    }

    protected Store getStore(){
        return store;
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");

    }

    public Order getOrder() {
        return order;
    }

    private void newStage(String fxml)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        secondStage.setScene(scene);

        secondViewController = fxmlLoader.getController();

        secondViewController.setMainController(this);
        secondViewController.setup();

        secondStage.show();
    }
    @FXML
    protected void onDonutButtonClick(){
        //welcomeText.setText("Welcome to JavaFX Application!");

        try{
            newStage("donuts-view.fxml");
        }
        catch (IOException e){

        }

    }

    @FXML
    protected void onCoffeeButtonClick() {
        //welcomeText.setText("Welcome to JavaFX Application!");

        try{
            newStage("coffee-view.fxml");
        }
        catch (IOException e){

        }
    }

    @FXML
    protected void onBasketButtonClick() {
        //welcomeText.setText("Welcome to JavaFX Application!");

        try{
            newStage("basket-view.fxml");
        }
        catch (IOException e){

        }
    }

    @FXML
    protected void onListButtonClick() {
        //welcomeText.setText("Welcome to JavaFX Application!");

        try{
            newStage("store-view.fxml");
        }
        catch (IOException e){

        }
    }
}