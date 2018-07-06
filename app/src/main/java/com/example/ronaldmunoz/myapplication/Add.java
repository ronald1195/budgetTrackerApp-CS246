package com.example.ronaldmunoz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

public class Add extends AppCompatActivity {

    //The necessary variables to get stuff going
    String userName;
    String userEmail;
    Date paymentDueDate;
    String frequency;
    String comments;

    int index;
    String json;

    ArrayList<String> paymentList;

    //Throwback to week 3
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void loadData() {
        Intent intent = getIntent();
        paymentList = intent.getStringArrayListExtra(MainActivity.ARRAY_LIST);

    }

    //Setting the spinner with the proper values
    public void setSpinner() {
        Spinner mySpinner = findViewById(R.id.paymentFreqSp);
        mySpinner.setAdapter
                (new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentFrequency.values()));
    }
}
