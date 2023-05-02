package com.example.testandroid;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

public class Coffee extends MenuItem implements Serializable {
    private StringBuilder cupSize;
    private int addIn; //this may be needed to be changed into an int if you need specific addin
    private int quantity;
    private boolean sweet_cream;
    private boolean french;
    private boolean irish;
    private boolean caramel;
    private boolean mocha;
    public final static double SIZE = .40;
    public final static double ADD_IN_PRICE = .30;
    public final static double BASE_PRICE = 1.89;
    public Coffee(){
        cupSize = new StringBuilder("");
        addIn = 0;
        quantity = 0;
        sweet_cream = false;
        french = false;
        irish = false;
        caramel = false;
        mocha = false;
    }
    public Coffee(String cupSize,int addIn){
        this.cupSize = new StringBuilder(cupSize);
        this.addIn = addIn;
    }
    public Coffee(String cupSize,int addIn, int quantity,boolean sweet_cream, boolean french,
                  boolean irish, boolean caramel, boolean mocha){
        this.cupSize = new StringBuilder(cupSize);
        this.addIn = addIn;
        this.quantity = quantity;
        this.sweet_cream = sweet_cream;
        this.french = french;
        this.irish = irish;
        this.caramel = caramel;
        this.mocha = mocha;
    }
    public double checkCupSize(String cupSize){
        if(cupSize.equals("Short")){
            return 0;
        }
        else if(cupSize.equals("Tall")){
            return SIZE;
        }
        else if(cupSize.equals("Venti")){
            return SIZE*2;
        }
        else if(cupSize.equals("Large")){
            return SIZE*3;
        }
        return 0;
    }
    public double itemPrice(){
        double itemPrice = 0;
        System.out.println("Quant: " + quantity +"\n");
        itemPrice = (BASE_PRICE+checkCupSize(String.valueOf(this.cupSize)) + (this.addIn*ADD_IN_PRICE))*quantity;
        return itemPrice;
    }
    public String addOns(){
        String addOn = "";
        if(sweet_cream){
            addOn+="Sweet Cream, ";
        }
        if(french){
            addOn +="French Vanilla, ";
        }
        if(irish){
            addOn+="Irish Cream, ";
        }
        if(caramel){
            addOn+="Caramel, ";
        }
        if(mocha){
            addOn+="Mocha ";
        }
        return addOn;
    }

    @Override
    public String toString() {
        return "Coffee ("+quantity+")"+","+cupSize;
    }
}