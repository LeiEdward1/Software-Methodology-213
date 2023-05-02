package com.example.assignment_4;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.List;

public class DonutsController extends SecondViewController{


    private double subtotal;
    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox<String> donutCombo;

    @FXML
    private ComboBox<Integer> flavQuant;

    @FXML
    private ListView<String> flavorLeft;

    @FXML
    private ListView<Donut> flavorRight;

    @FXML
    private Button flavMoveR, flavMoveL, donutAdd;

    @FXML
    private TextField donutSubtotal;

    public static final ObservableList<String> donutTypes = FXCollections.observableArrayList("yeast donuts", "cake donuts", "donut holes");

    public static final ObservableList<String> YEAST_FLAVOR = FXCollections.observableArrayList("jelly", "glazed", "chocolate frosted", "strawberry frosted", "sugar", "lemon filled");
    public static final ObservableList<String> CAKE_FLAVOR = FXCollections.observableArrayList("cinnamon sugar", "old fashion", "blueberry");
    public static final ObservableList<String> DONUTHOLE_FLAVOR = FXCollections.observableArrayList("vanilla", "chocolate", "strawberry");
    public static final ObservableList<Integer> QUANTITY = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);



    private ObservableList<String> currYeast = FXCollections.observableArrayList("jelly", "glazed", "chocolate frosted", "strawberry frosted", "sugar", "lemon filled");

    private ObservableList<String> currCake = FXCollections.observableArrayList("cinnamon sugar", "old fashion", "blueberry");

    private ObservableList<String> currDonutHole = FXCollections.observableArrayList("vanilla", "chocolate", "strawberry");

    private ObservableList<Donut> currOrder = FXCollections.observableArrayList();
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onDonutAddClick(){
        this.closeStage();
    }

    @FXML
    protected void onFlavMoveRClick(){
        if(donutCombo.getSelectionModel().getSelectedIndex() == -1 ||
                flavQuant.getSelectionModel().getSelectedIndex() == -1 ||
                flavorLeft.getSelectionModel().getSelectedIndex() == -1){

            subtotal = 0.00;
            donutSubtotal.setText("0.00");
            return;
        }
        System.out.println(233);
        String currType = donutCombo.getSelectionModel().getSelectedItem();
        int type = getType(currType);

        String currFlavor = flavorLeft.getSelectionModel().getSelectedItem();
        int flavor = 0;
        flavor = getFlavor(currFlavor);
        System.out.println(currFlavor+ " " + flavor+"\n");

        int amount = flavQuant.getSelectionModel().getSelectedIndex()+1;

        Donut currDonut = new Donut(type, flavor, amount);

        subtotal += currDonut.itemPrice();

        System.out.println(currDonut.getDonutFlavor() + "\n");
        //currDonut.setDonutFlavor(flavor);

        if(type == Donut.yeast){
            currYeast.remove(currFlavor);
        } else if (type == Donut.cake){
            currCake.remove(currFlavor);
        } else if (type == Donut.donutHole){
            currDonutHole.remove(currFlavor);
        }

        currOrder.add(currDonut);
        updateSubtotal();
    }


    @FXML
    protected void onFlavMoveLClick(){
        if(flavorRight.getSelectionModel().getSelectedIndex() == -1){
            return;
        }
        Donut currDonut = flavorRight.getSelectionModel().getSelectedItem();

        //get flavor, donut type
        String flavor = currDonut.getDonutFlavor();
        int type = currDonut.getDonutType();
        //remove it from the right side
        currOrder.remove(currDonut);
        //figure
        //readd it to corresponding observable list
        if(type == Donut.yeast){
            currYeast.add(flavor);
        } else if (type == Donut.cake){
            currCake.add(flavor);
        } else if (type == Donut.donutHole){
            currDonutHole.add(flavor);
        }

        subtotal -= currDonut.itemPrice();

        updateSubtotal();
    }

    private void updateFlavors(){
        int selectedIndex = donutCombo.getSelectionModel().getSelectedIndex()+1;

        if(selectedIndex == Donut.yeast){
            //yeast flavors
            flavorLeft.setItems(currYeast);

        }
        else if(selectedIndex == Donut.cake){
            //cake flavors
            flavorLeft.setItems(currCake);
        }
        else if(selectedIndex == Donut.donutHole){
            //donutHole flavors
            flavorLeft.setItems(currDonutHole);
        }
        flavorRight.setItems(currOrder);


    }

    private void updateSubtotal(){
        if(currOrder.size()==0){
            subtotal=0;
        }
        double roundOff = Math.round(subtotal * 100.0) / 100.0;
        donutSubtotal.setText(Double.toString(roundOff));
    }

    private int getType(String string){
        if(string.equals("yeast donuts")){
            return Donut.yeast;
        } else if (string.equals("cake donuts")){
            return Donut.cake;
        } else if (string.equals("donut holes")){
            return Donut.donutHole;
        } else {
            return -1;
        }
    }
    private int getFlavor(String string){
        if (string.equals("jelly")){
            return Donut.JELLY;
        } else if (string.equals("glazed")){
            return Donut.GLAZED;
        } else if (string.equals("chocolate frosted")){
            return Donut.CHOCOLATE_FROSTED;
        } else if (string.equals("strawberry frosted")){
            return Donut.STRAWBERRY_FROSTED;
        } else if (string.equals("sugar")){
            return Donut.SUGAR;
        } else if (string.equals("lemon filled")){
            return Donut.LEMON_FILLED;
        } else if (string.equals("cinnamon sugar")){
            return Donut.CINNAMON_SUGAR;
        } else if (string.equals("old fashion")){
            return Donut.OLD_FASHION;
        } else if (string.equals("blueberry")){
            return Donut.BLUEBERRY;
        } else if (string.equals("vanilla")){
            return Donut.VANILLA;
        } else if (string.equals("chocolate")){
            return Donut.CHOCOLATE;
        } else if (string.equals("strawberry")){
            return Donut.STRAWBERRY;
        } else{
            return -1;
        }
    }

    private void resetFlavors(){
        currYeast = FXCollections.observableArrayList("jelly", "glazed", "chocolate frosted", "strawberry frosted", "sugar", "lemon filled");

        currCake = FXCollections.observableArrayList("cinnamon sugar", "old fashion", "blueberry");

        currDonutHole = FXCollections.observableArrayList("vanilla", "chocolate", "strawberry");

        subtotal = 0;

        donutSubtotal.setText("0.00");
    }

    @FXML
    protected void onDonutComboClick(){
        updateFlavors();
    }

    public void setup(){
        donutCombo.setItems(donutTypes);
        donutCombo.getSelectionModel().selectFirst();
        updateFlavors();
        System.out.println("hi");
        flavQuant.setItems(QUANTITY);
        flavQuant.getSelectionModel().selectFirst();
        return;
    }

    @FXML
    protected void onAddBasket(){
        for(int i = 0;i<currOrder.size();i++){
            this.getMainController().getOrder().add(currOrder.get(i));
        }
        currOrder.clear();
        resetFlavors();
        setup();
    }



}