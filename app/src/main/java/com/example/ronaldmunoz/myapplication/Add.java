package com.example.ronaldmunoz.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

        setSpinner();
        loadData();

    }

    //Get stuff from main activity
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

    //This will get the data from the ui and store it in a membership object
    public Membership getDataFromFiels() throws ParseException {
        EditText text = findViewById(R.id.nameAdd);
        userName = text.getText().toString();

        text = findViewById(R.id.emailAdd);
        userEmail = text.getText().toString();

        text = findViewById(R.id.dueDateTb1);
        DateFormat df = new SimpleDateFormat("dd", Locale.ENGLISH);
        paymentDueDate = df.parse(text.getText().toString());

        Spinner mySpinner = findViewById(R.id.paymentFreqSp);
        frequency = mySpinner.getSelectedItem().toString();

        text = findViewById(R.id.commentsAdd);
        comments = text.getText().toString();

        return new Membership (
                userName, userEmail, paymentDueDate, frequency, comments, paymentList.size()
        );



    }

    public void saveItemOnClick(View view) {
    }

    public void submitNewMembershipButtonOnClick(View view){
        //
        String TAG = getApplication().getPackageName();
        EditText membershipName = findViewById(R.id.nameAdd);
        Log.i(TAG, "getting membership name: " + membershipName.getText().toString());

/*
        //Save into shared preferences.
        String memName  = membershipName.getText().toString();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_NAME, memName);
        editor.apply();
        editor.commit();
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        intent.putExtra("new membership created", memName);
        setResult(RESULT_OK, intent);
        finish();
        */
    }
}
