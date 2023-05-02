package com.example.testandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class CoffeeActivity extends AppCompatActivity {

    final static int SHORT = 0;
    final static int TALL = 1;
    final static int GRANDE = 2;
    final static int VENTI = 3;
    private Spinner spinner1, spinner2;
    private CheckBox sweetCream,frenchVanilla, irishCream, caramel, mocha;
    private Button addToCoffee;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<Integer>adapter2;
    private String [] names = {"Short", "Tall", "Grande", "Venti"};
    private Integer[] quantity2 = {1,2,3,4,5};
    private String drinkSize;
    private int drinkQuant;

    private Store store;

    private Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        store = (Store) getIntent().getSerializableExtra("storeExtra");
        order = (Order) getIntent().getSerializableExtra("orderExtra");

        sweetCream = (CheckBox) findViewById(R.id.sweetCream);
        frenchVanilla = (CheckBox) findViewById(R.id.frenchVanilla);
        irishCream = (CheckBox) findViewById(R.id.irishCream);
        caramel = (CheckBox) findViewById(R.id.caramel);
        mocha = (CheckBox) findViewById(R.id.mocha);
        addToCoffee = (Button) findViewById(R.id.addToCoffee);
        spinner1 = findViewById(R.id.sizeCoffee);
        spinner2 = findViewById(R.id.quantity);
        adapter = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, names);
        spinner1.setAdapter(adapter);
        adapter2 = new ArrayAdapter<Integer>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantity2);
        spinner2.setAdapter(adapter2);

        addToCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAddToCoffee(v);
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //event handler below
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                drinkSize = (String)parent.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //event handler below
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                drinkQuant = (Integer)parent.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra("orderExtra", order);
        data.putExtra("storeExtra", store);
        setResult(RESULT_OK, data);
        finish();
    }

    public void loadAddToCoffee(View view){
        Coffee newCoffee = newCoffee();
        Toast.makeText( this, newCoffee.toString(), Toast.LENGTH_SHORT).show();
        order.add(newCoffee);
        Toast.makeText( this, order.toString(), Toast.LENGTH_SHORT).show();
        confirmation();

    }

    private void confirmation(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Coffee Confirmation");
        alert.setMessage("Coffee order added.");
        alert.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setNegativeButton("Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goMenu();
            }
        });
        alert.create().show();
    }

    private void goMenu(){
        Intent data = new Intent();
            data.putExtra("orderExtra", order);
            data.putExtra("storeExtra", store);

        setResult(RESULT_OK, data);
        finish();
    }

    public Coffee newCoffee(){
        int count = 0;
        if(sweetCream.isChecked()) {
            count++;
        }
        if(frenchVanilla.isChecked()){
            count++;
        }
        if(caramel.isChecked()){
            count++;
        }
        if(mocha.isChecked()){
            count++;
        }
        if(irishCream.isChecked()){
            count++;
        }
        double price = 0;
        String drinkSizeString = drinkSize;
        if(drinkSizeString==null){
            drinkSizeString = "";
        }
        String quantity = String.valueOf(drinkQuant);
        if(quantity==null){
            quantity = "0";
        }

        Coffee currentOrder = new Coffee(drinkSizeString,
                count,drinkQuant , sweetCream.isChecked(),
                frenchVanilla.isChecked(),irishCream.isChecked(),caramel.isChecked(),mocha.isChecked());
        return currentOrder;
    }

}