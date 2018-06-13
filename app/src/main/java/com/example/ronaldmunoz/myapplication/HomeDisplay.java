package com.example.ronaldmunoz.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeDisplay extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mainListView;
        ArrayAdapter<String> listAdapter;

        //Find the ListView resource.
        mainListView = findViewById(R.id.mainListView);

        //Create and populate a List of planet names.
        String [] num = new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen"};
        ArrayList<String> numList = new ArrayList<>();
        numList.addAll(Arrays.asList(num));

        //Create ArrayAdapter using the list.
        listAdapter = new ArrayAdapter<>(this, R.layout.home_rows, numList);

        //Add more strings.
        /*listAdapter.add("sixteen");
        listAdapter.add("seventeen");
        listAdapter.add("eighteen");
        listAdapter.add("nineteen");
        listAdapter.add("twenty");*/

        mainListView.setAdapter(listAdapter);
    }
}
