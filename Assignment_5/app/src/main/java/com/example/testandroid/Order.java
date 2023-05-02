package com.example.testandroid;

import java.io.Serializable;
import java.util.ArrayList;
public class Order implements Serializable {
    public static int GLOBAL_INDEX = 0;
    private int orderNumber;
    private ArrayList<MenuItem> order;
    private double subtotal;
    public Order(){
        this(GLOBAL_INDEX);
    }
    public Order(int orderNumber){
        super();
        this.orderNumber = orderNumber;
        this.order = new ArrayList<MenuItem>(1);
        this.subtotal = 0;
    }

    public ArrayList<MenuItem> getOrderObserve(){
        return order;
    }

    public ArrayList<String> getOrderStrings(){
        ArrayList<String> stringItems = new ArrayList<String>();

        for(MenuItem item: order){
            stringItems.add(item.toString());
        }
        return stringItems;
    }
    public boolean add(MenuItem item){
        if(item!=null){
            order.add(item);
        }
        this.subtotal += item.itemPrice();
        return true;
    }
    public double displayCost(){
        double roundOff = Math.round(subtotal * 100.0) / 100.0;
        return roundOff;
    }
    public boolean removeItem(MenuItem item){
       if(!order.contains(item)){
           return false;
       }
       else {
           this.subtotal -= item.itemPrice();
           order.remove(item);
       }
       return true;
    }

    public boolean removeItem(int index){
        if(order.size()<index+1){
            return false;
        }
        else {
            MenuItem item = order.get(index);
            this.subtotal -= item.itemPrice();
            order.remove(item);
        }
        return true;
    }

    public int getOrderNumber(){
        return orderNumber;
    }

    public double calculateTax(){
        double tax = 0.0625 * this.subtotal;
        double roundOff = Math.round(tax * 100.0) / 100.0;
        return roundOff;
    }

    public double totalCost(){
        double totalCost = calculateTax()+this.subtotal;
        double roundOff = Math.round(totalCost * 100.0) / 100.0;
        return roundOff;
    }
    public String toString(){
        String output = "";
        int size = order.size();

        for  (int i = 0; i < size; i++){
            output += order.get(i);

            if(i != size-1){
                output += ";";
            }
        }

        return output;
    }
}
