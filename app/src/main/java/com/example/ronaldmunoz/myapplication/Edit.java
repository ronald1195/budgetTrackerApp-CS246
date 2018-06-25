package com.example.ronaldmunoz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Edit extends AppCompatActivity {

    String userName;
    String userEmail;
    Date paymentDueDate;
    String frequency;
    String comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //This is the spinner containing the frequency of payment
        Spinner mySpinner = findViewById(R.id.paymentFreqSp);
        mySpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentFrequency.values()));
    }


    //The button
    public void saveChanges(View view) throws ParseException {
        EditText text = findViewById(R.id.userNameTb);
        userName = text.getText().toString();

        text = findViewById(R.id.userEmailTb);
        userEmail = text.getText().toString();

        text = findViewById(R.id.dueDateTb);
        DateFormat df = new SimpleDateFormat("dd", Locale.ENGLISH);
        paymentDueDate = df.parse(text.getText().toString());

        Spinner mySpinner = findViewById(R.id.paymentFreqSp);
        frequency = mySpinner.getSelectedItem().toString();

        text = findViewById(R.id.editText5);
        comments = text.getText().toString();

    }
}
