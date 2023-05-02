package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * @author Edward Lei
 */
public class MainActivity extends AppCompatActivity{
    private ImageButton coffeeButton, donutButton, orderButton, storeButton;

//    protected static Order currentOrder = new Order();
//    protected static StoreOrder currentStoreOrder = new StoreOrder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coffeeButton = findViewById(R.id.coffeeButton);
        donutButton = findViewById(R.id.donutButton);
        orderButton = findViewById(R.id.orderButton);
        storeButton = findViewById(R.id.storeButton);
    }

    public void loadDonut(View view){
        Intent intent = new Intent(MainActivity.this, DonutView.class);
        Toast.makeText(MainActivity.this, "Donuts", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void loadCoffee(View view){
        Intent intent = new Intent(MainActivity.this, CoffeeView.class);
        Toast.makeText(MainActivity.this, "Coffee", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void loadOrder(View view){
        Intent intent = new Intent(MainActivity.this, OrderView.class);
        Toast.makeText(MainActivity.this, "Orders", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void loadStore(View view){
        Intent intent = new Intent(MainActivity.this, StoreView.class);
        Toast.makeText(MainActivity.this, "Store Order", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}