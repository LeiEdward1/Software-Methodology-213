package com.example.testandroid;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private ImageButton coffeeButton, donutButton, basketButton, storeButton;
    private Store store = new Store();

    private Order order = new Order();

    public final static int REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coffeeButton = findViewById(R.id.coffeeButton);
        donutButton = findViewById(R.id.donutButton);
        basketButton = findViewById(R.id.basketButton);
        storeButton = findViewById(R.id.storeButton);

        coffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCoffee(v);
            }
        });
        donutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDonut(v);
            }
        });
        basketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadBasket(v);
            }
        });
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadStore(v);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(MainActivity.this, "HI", Toast.LENGTH_SHORT).show();
        if (resultCode == RESULT_OK) {
            order = (Order) data.getSerializableExtra("orderExtra");
            store = (Store) data.getSerializableExtra("storeExtra");
        }
    }

    public void loadDonut(View view){
        Intent intent = new Intent(MainActivity.this, DonutActivity.class);
        intent.putExtra("orderExtra", order);
        intent.putExtra("storeExtra", store);
        Toast.makeText(MainActivity.this, "Order Donuts", Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void loadCoffee(View view){
        Intent intent = new Intent(MainActivity.this, CoffeeActivity.class);
            intent.putExtra("orderExtra", order);
            intent.putExtra("storeExtra", store);
        Toast.makeText(MainActivity.this, "Order Coffee", Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, REQUEST_CODE);
        //someActivityLauncher.launch(intent);
    }

    public void loadBasket(View view){
        Intent intent = new Intent(MainActivity.this, OrderActivity.class);
        intent.putExtra("orderExtra", order);
        intent.putExtra("storeExtra", store);
        Toast.makeText(MainActivity.this, "Your Order", Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void loadStore(View view){
        Intent intent = new Intent(MainActivity.this, StoreActivity.class);
        intent.putExtra("orderExtra", order);
        intent.putExtra("storeExtra", store);
        Toast.makeText(MainActivity.this, "Store Orders", Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, REQUEST_CODE);
    }
}