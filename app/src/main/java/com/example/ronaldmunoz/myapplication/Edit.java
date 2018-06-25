package com.example.ronaldmunoz.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class Edit extends AppCompatActivity {

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
        setContentView(R.layout.activity_edit);

        loadSelectedItem();

        //This is the spinner containing the frequency of payment
        Spinner mySpinner = findViewById(R.id.paymentFreqSp);
        mySpinner.setAdapter
                (new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentFrequency.values()));
    }


    //The "Save" button
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

        serializeAndStore();
    }


    public void serializeAndStore() {
        //Serializing a new object with the edited information... hopefully
        Membership mem = new Membership
                (userName, userEmail, paymentDueDate, frequency, comments, index);
        String newJson = gson.toJson(mem);

        //Adding the items to the arrayList at the index we got from the list and converting
        // to a set.
        paymentList.set(index,newJson);
        Set<String> paymentSet = new TreeSet<>(paymentList);

        //Saving to Shared preferences
        SharedPreferences.Editor editor = getSharedPreferences(MainActivity.MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putStringSet("payments", paymentSet);
        editor.apply();
    }

    //Getting the json and index
    public  void loadSelectedItem() {
        Intent intent = getIntent();
        json = intent.getStringExtra(MainActivity.EDIT_ITEM);
        index = Integer.parseInt(intent.getStringExtra(MainActivity.INDEX));
        paymentList = intent.getStringArrayListExtra(MainActivity.ARRAY_LIST);
        Membership member = gson.fromJson(json, Membership.class);
        fillUpFields(member);
    }

    //Filling up every field with the previously typed information
    public void fillUpFields(Membership mem) {
        EditText text = findViewById(R.id.userNameTb);
        text.setText(mem.getUserName());

        text = findViewById(R.id.userEmailTb);
        text.setText(mem.getUserEmail());

        text = findViewById(R.id.dueDateTb);
        text.setText(mem.getPaymentDueDate().toString());

        /*
        Spinner mySpinner = findViewById(R.id.paymentFreqSp);
        int spinnerPos =
        mySpinner.setS;
        */
        text = findViewById(R.id.editText5);
        text.setText(mem.comments);
    }
}
