package com.example.assignment_4;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CoffeeController extends SecondViewController{
    @FXML
    private Label welcomeText;
    @FXML
    private CheckBox sweet, french, irish, caramel, mocha;
    @FXML
    private ComboBox <String> drinkSize, drinkQuant;
    @FXML
    private TextField coffeeSubTotal;
    @FXML
    private Button addToOrder;
    ObservableList<String> lists = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> list = FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti");
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void setup(){
        drinkSize.setItems(list);
        drinkQuant.setItems(lists);
    }


    public Coffee newCoffee(){
        int count = 0;
        if(sweet.isSelected()) {
            count++;
        }
        if(french.isSelected()){
            count++;
        }
        if(caramel.isSelected()){
            count++;
        }
        if(mocha.isSelected()){
            count++;
        }
        if(irish.isSelected()){
            count++;
        }
        double price = 0;
        String drinkSizeString = drinkSize.getSelectionModel().getSelectedItem();
        if(drinkSizeString==null){
            drinkSizeString = "";
        }
        String quantity = drinkQuant.getSelectionModel().getSelectedItem();
        if(quantity==null){
            quantity = "0";
        }
        int quantities = Integer.parseInt(quantity);
        Coffee currentOrder = new Coffee(drinkSizeString,
                count,quantities ,sweet.isSelected(),
                french.isSelected(),irish.isSelected(),caramel.isSelected(),mocha.isSelected());
        return currentOrder;
    }
    @FXML
    public void updatePrice(){
        Coffee newCoffee = newCoffee();
        double price = newCoffee.itemPrice();
        double roundOff = Math.round(price * 100.0) / 100.0;
        coffeeSubTotal.setText(Double.toString(roundOff));
    }

    @FXML
   public void onAddToBasket (){
        Coffee newCoffee = newCoffee();
        if(drinkQuant.getSelectionModel().getSelectedItem()!=null&&
                drinkSize.getSelectionModel().getSelectedItem()!=null) {
            this.getMainController().getOrder().add(newCoffee);
        }
        drinkQuant.setValue(null);
        drinkSize.setValue(null);
        sweet.setSelected(false);
        french.setSelected(false);
        irish.setSelected(false);
        caramel.setSelected(false);
        mocha.setSelected(false);
        updatePrice();

   }
}