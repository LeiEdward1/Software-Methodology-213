package com.example.assignment_4;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;

import java.util.ArrayList;
public class Store {
    //store the list of
    private ObservableList<Order> storeOrders;

    public Store() {
        storeOrders = FXCollections.observableArrayList();
    }

    public boolean add(Order order){
        return storeOrders.add(order);
    }

    public boolean remove(Order order){
        return storeOrders.remove(order);
    }

    public ObservableList<Order> getStoreOrders(){
        return storeOrders;
    }

    public boolean isEmpty(){
        return storeOrders.isEmpty();
    }

    public Order get(int orderNumber){
        for(int i = 0; i < storeOrders.size(); i++){
            if(orderNumber==storeOrders.get(i).getOrderNumber()){
                return storeOrders.get(i);
            }
        }
        return null;
    }

    public ObservableList<Integer> getOrderIndices(){
        System.out.println("START");
        ObservableList<Integer> indices = FXCollections.observableArrayList();

        for(int i = 0; i < storeOrders.size(); i++){
            indices.add(storeOrders.get(i).getOrderNumber());
            System.out.println("ORdernum: "+storeOrders.get(i).getOrderNumber()+"\n");
        }
        System.out.println("end");
        return indices;
    }

    public String toString(){
        String output = "";
        int size = storeOrders.size();

        for  (int i = 0; i < size; i++){
            output += storeOrders.get(i);

            if(i != size-1){
                output += ":";
            }
        }
        return output;
    }
}
