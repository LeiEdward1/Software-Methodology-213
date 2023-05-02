package com.example.testandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {

    private ListView storeList;
    private Store store;

    private Spinner spinner;
    private Order currOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        store = (Store) getIntent().getSerializableExtra("storeExtra");

        ArrayList<Order> listOfOrders = store.getStoreOrders();

        spinner = (Spinner) findViewById(R.id.orderIndices);

        if(!store.isEmpty()){

            storeList = (ListView) findViewById(R.id.storeList);
            ArrayList<Integer> orderIndices = store.getOrderIndices();

            ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, orderIndices);

            spinner.setAdapter(adapter);

            String lis [] = {"Apple", "Banana"};

            currOrder = listOfOrders.get(0);
            ArrayList<String> stringOrders = currOrder.getOrderStrings();

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.orderText, stringOrders);
            storeList.setAdapter(arrayAdapter);
        }
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra("storeExtra", store);
        setResult(RESULT_OK, data);
        finish();
    }

}