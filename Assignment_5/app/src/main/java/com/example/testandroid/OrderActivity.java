package com.example.testandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private ListView orderList;
    private Button addOrder;


    private Order order;

    private Store store;

    private ArrayList<String> stringOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        store = (Store) getIntent().getSerializableExtra("storeExtra");
        order = (Order) getIntent().getSerializableExtra("orderExtra");

        orderList = (ListView) findViewById(R.id.orderList);
        addOrder = (Button) findViewById(R.id.addOrder);

        String lis [] = {"Apple", "Banana"};

        stringOrders = order.getOrderStrings();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.orderText, stringOrders);
        orderList.setAdapter(arrayAdapter);

        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                store.add(order);
                order = new Order();
                stringOrders.clear();
                arrayAdapter.notifyDataSetChanged();

            }
        });
        orderList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alert = new AlertDialog.Builder(OrderActivity.this);

                alert.setMessage("Delete " + order.getOrderStrings().get(position) + "?");
                alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        order.removeItem(position);
                        stringOrders.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.create().show();

                return false;
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

}