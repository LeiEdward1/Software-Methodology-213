package com.example.testandroid;

import java.io.Serializable;

public class Donut extends MenuItem implements Serializable {
    private int donutType;
    private int amount;
    private int donutFlavor;

    public final static int yeast = 1;

    public final static int cake = 2;

    public final static int donutHole = 3;
    public final static double yeastPrice = 1.59;
    public final static double cakePrice = 1.79;
    public final static double donutHolePrice = 0.39;

    public static final int JELLY = 0;

    public static final int GLAZED = 1;

    public static final int CHOCOLATE_FROSTED = 2;

    public static final int STRAWBERRY_FROSTED = 3;
    public static final int SUGAR = 4;

    public static final int LEMON_FILLED = 5;

    public static final int CINNAMON_SUGAR = 6;

    public static final int OLD_FASHION = 7;

    public static final int BLUEBERRY = 8;

    public static final int VANILLA = 9;

    public static final int CHOCOLATE = 10;

    public static final int STRAWBERRY = 11;


    public Donut(){
        donutType = 0;
        amount = 0;
        donutFlavor = 0;
    }
    public Donut(int donutType, int donutFlavor, int amount){
        this.donutType = donutType;
        this.donutFlavor = donutFlavor;
        this.amount = amount;
    }

//    public void setDonutFlavor(int donutFlavor){
//        this.donutFlavor = donutFlavor;
//    }
    public double itemPrice(){
        if(donutType == yeast){
            return yeastPrice*amount;
        }
        else if(donutType == cake){
            return cakePrice*amount;
        }
        else if(donutType == donutHole){
            return donutHolePrice*amount;
        }
        return 0;
    }

    public int getDonutType(){
        return donutType;
    }
    public String getDonutFlavor(){
        System.out.println(donutFlavor + "\n");
        if(this.donutFlavor == JELLY){
            return "jelly";
        }
        if(this.donutFlavor == GLAZED){
            return "glazed";
        }
        if(this.donutFlavor == CHOCOLATE_FROSTED){
            return "chocolate frosted";
        }
        if(this.donutFlavor == STRAWBERRY_FROSTED){
            return "strawberry frosted";
        }
        if(this.donutFlavor == SUGAR){
            return "sugar";
        }
        if(this.donutFlavor == LEMON_FILLED){
            return "lemon filled";
        }
        if(this.donutFlavor == CINNAMON_SUGAR){
            return "cinnamon sugar";
        }
        if(this.donutFlavor == OLD_FASHION){
            return "old fashion";
        }
        if(this.donutFlavor == BLUEBERRY){
            return "blueberry";
        }
        if(this.donutFlavor == VANILLA){
            return "vanilla";
        }
        if(this.donutFlavor == CHOCOLATE){
            return "chocolate";
        }
        if(this.donutFlavor == STRAWBERRY){
            return "strawberry";
        }
        return "";
    }
    @Override
    public String toString(){
        return amount + " " + this.getDonutFlavor();
    }

}
