package com.example.assignment_4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.PrintWriter;

public class StoreController extends SecondViewController{
    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox<Integer> orderIndex;

    @FXML
    private TextField storeAmount;

    @FXML
    private ListView<MenuItem> storeView;

    @FXML
    private Button storeCancel, storeExport;

    private ObservableList<Integer> orderIndices;


    @FXML
    protected void onOrderIndexClick(){
        displayOrder();
    }

    @FXML
    protected void onStoreCancelClick(){
        if(orderIndex.getSelectionModel().getSelectedIndex()==-1){
            return;
        }
        Store store = this.getMainController().getStore();
        int index = orderIndex.getSelectionModel().getSelectedItem();
        Order temp = store.get(index);
        store.remove(temp);
        reset();
        setup();
    }

    private void reset(){
        orderIndex.setItems(FXCollections.observableArrayList());
        storeView.setItems(FXCollections.observableArrayList());
    }

    public void setup(){
        if(this.getMainController().getStore().isEmpty()){
            return;
        }
        orderIndices = this.getMainController().getStore().getOrderIndices();

        if(orderIndices.isEmpty()){
            return;
        }

        orderIndex.setItems(orderIndices);
        orderIndex.getSelectionModel().selectFirst();

        displayOrder();

        return;
    }
    @FXML
    public void totalCost(){
        Store store = this.getMainController().getStore();
        int index = orderIndex.getSelectionModel().getSelectedItem();
        double totalCosts = store.get(index).totalCost();
        double roundOff = Math.round(totalCosts * 100.0) / 100.0;
        storeAmount.setText(Double.toString(roundOff));
    }
    private void displayOrder(){
        Store store = this.getMainController().getStore();
        if(orderIndex.getSelectionModel().getSelectedItem()==null){
            return;
        }
        int index = orderIndex.getSelectionModel().getSelectedItem();
        Order temp = store.get(index);
        storeView.setItems(temp.getOrderObserve());
        totalCost();
    }

    @FXML
    public void write(){
        Store store = this.getMainController().getStore();
        String textFile = "";
        for(int i = 0;i<store.getStoreOrders().size();i++){
            textFile+="Order Number:"+store.getStoreOrders().get(i).getOrderNumber()+" Details: "+ store.getStoreOrders().get(i)+" Cost:"+" $"+
                    store.getStoreOrders().get(i).totalCost() + "\n";
        }
        try{
            PrintWriter out = new PrintWriter("code.txt");
            System.out.println("working");
            out.println(textFile);
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}