package com.example.demoobservablelist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;

import java.util.Collections;

/**
 * An example to demonstrate how an ObservableList can be used in a ListView.
 * @author Lily Chang
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    ObservableArrayList<String> list;
    String [] fruits = {"apple", "banana", "strawberry", "blueberry", "watermelon", "orange"};
    ArrayAdapter<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.testList);
        list = new ObservableArrayList<>();
        Collections.addAll(list, fruits); //add objects to the ObservableList
        //The statement below create an adapter for the ListView and set the data source to the
        //ObservableList.
        items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(items); //set the adapter of the ListView to the source
        listView.setOnItemClickListener(this); //add a listener to the ListView
    }

    /**
     * The event Handler for the onItemClick event on the ListView
     * @param adapterView The AdapterView where the click happened.
     * @param view The View within the AdapterView that was clicked (in this example is ListView)
     * @param i the index/position of the view that was clicked in the adapter.
     * @param l the row id (index) of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        list.remove(i);
        //list.remove((int) l); //type cast a long to an int if you use the row id
        items.notifyDataSetChanged(); //notify the attached observer the underlying data has been changed.
        //for (String s: list)  //a test to print out the data source to see if they are in sync
        //    System.out.println(s);
    }
}