package com.example.ronaldmunoz.myapplication;

import android.app.ActionBar;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeDisplay homeDisplay = new HomeDisplay();
        //homeDisplay.onCreate(savedInstanceState);

        //ListView mainListView = findViewById(R.id.mainListView);


        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1, myStringArray);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);*/

        setContentView(R.layout.activity_main);
    }

    /*String [] myStringArray = new String [15];
    protected static String num() {
        String [] myStringArray = new String[15];
        for (int i = 0; i < 15; i++) {
            myStringArray[i] = Integer.toString(i + 1);
            //return myStringArray[i];
        }
        for (int i = 0; i < 15; i++) {
            myStringArray[0] += myStringArray[i];
        }
        return myStringArray[0];
        return "HELLO";
    }*/





}
