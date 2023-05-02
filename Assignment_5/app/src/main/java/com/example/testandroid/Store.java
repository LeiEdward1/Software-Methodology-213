package com.example.testandroid;

import java.io.Serializable;
import java.util.ArrayList;
public class Store implements Serializable {
    //store the list of
   // private ObservableList<Order> storeOrders;
    private ArrayList<Order> storeOrders;
    public Store() {
            storeOrders = new ArrayList<Order>();
    }

    public boolean add(Order order){
        return storeOrders.add(order);
    }

    public boolean remove(Order order){
        return storeOrders.remove(order);
    }

    public ArrayList<Order> getStoreOrders(){
        return storeOrders;
    }

    public boolean isEmpty(){
        return storeOrders.isEmpty();
    }

    public ArrayList<String> getStoreOrderStrings(){
        ArrayList<String> stringOrders = new ArrayList<String>();

        for (Order order : storeOrders){
            stringOrders.add(order.toString());
        }

        return stringOrders;
    }

    public Order get(int orderNumber){
        for(int i = 0; i < storeOrders.size(); i++){
            if(orderNumber==storeOrders.get(i).getOrderNumber()){
                return storeOrders.get(i);
            }
        }
        return null;
    }

    public ArrayList<Integer> getOrderIndices(){
        System.out.println("START");
        ArrayList<Integer> indices = new ArrayList<Integer>();

        for(int i = 0; i < storeOrders.size(); i++){
            indices.add(storeOrders.get(i).getOrderNumber());
            System.out.println("Ordernum: "+storeOrders.get(i).getOrderNumber()+"\n");
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
