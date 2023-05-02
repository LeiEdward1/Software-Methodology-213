package com.example.assignment_4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class BasketController extends SecondViewController{
    @FXML
    private Label welcomeText;
    @FXML
    private TextField basketSub, basketTax, basketTotal;
    @FXML
    private ListView<MenuItem> basketList;
    @FXML
    private Button basketRemove, basketOrder;


    public void setup(){

        displaySubTotal();
        displayTax();
        displayTotalAmt();
        printOrder();
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    public void getOrder(){

    }
    @FXML
    public void printOrder(){
        Order order = this.getMainController().getOrder();
        basketList.setItems(order.getOrderObserve());

    }
    @FXML
    public void displaySubTotal(){
        double cost = this.getMainController().getOrder().displayCost();
        basketSub.setText(Double.toString(cost));

    }
    @FXML
    public void displayTax(){
        double tax = this.getMainController().getOrder().calculateTax();
        double roundOff = Math.round(tax * 100.0) / 100.0;
        basketTax.setText(Double.toString(roundOff));
    }
    @FXML
    public void displayTotalAmt(){
        double totalAmt = this.getMainController().getOrder().totalCost();
        double roundOff = Math.round(totalAmt * 100.0) / 100.0;
        basketTotal.setText(Double.toString(roundOff));
    }

    @FXML
    protected void onBasketRemoveClick(){
        Order order = this.getMainController().getOrder();
        MenuItem item = basketList.getSelectionModel().getSelectedItem();
        order.removeItem(item);
        setup();
    }

    @FXML
    protected void onBasketOrderClick(){
        Store store = this.getMainController().getStore();
        Order order = this.getMainController().getOrder();
        store.add(order);
        Order.GLOBAL_INDEX++;
        this.getMainController().setOrder(new Order());

        setup();
    }


}