package com.example.ronaldmunoz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Spinner mySpinner = findViewById(R.id.paymentFreqSp);

        mySpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentFrequency.values()));
    }
}
