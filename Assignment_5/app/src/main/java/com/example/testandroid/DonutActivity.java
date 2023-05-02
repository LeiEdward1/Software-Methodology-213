package com.example.testandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class DonutActivity extends AppCompatActivity {
    Button addToDonut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        ArrayList<DonutItems> items = new ArrayList<DonutItems>();
        items.add(new DonutItems("Blueberry Donut",R.drawable.blueberrydonut));
        items.add(new DonutItems("Chocolate Donut",R.drawable.chocolate));
        items.add(new DonutItems("Chocolate Frosted Donut",R.drawable.chocolatefrosted));
        items.add(new DonutItems("Cinammon Donut",R.drawable.cinammonsugar));
        items.add(new DonutItems("Glazed Donut",R.drawable.glazed));
        items.add(new DonutItems("Jelly Donut",R.drawable.blueberrydonut));
        items.add(new DonutItems("Old Fashion Donut",R.drawable.oldfashion));
        items.add(new DonutItems("Strawberry Donut",R.drawable.blueberrydonut));
        items.add(new DonutItems("Strawberry Frosted Donut",R.drawable.strawberryfrosted));
        items.add(new DonutItems("Strawberry Donut",R.drawable.strawberry));
        items.add(new DonutItems("Sugar Donut",R.drawable.sugardonut));
        items.add(new DonutItems("Vanilla Donut",R.drawable.vanilladonut));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));

    }
}