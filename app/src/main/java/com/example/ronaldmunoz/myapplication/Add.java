package com.example.ronaldmunoz.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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
import java.util.Set;
import java.util.TreeSet;

import static com.example.ronaldmunoz.myapplication.MainActivity.MY_PREFS_NAME;

public class Add extends AppCompatActivity {

    ArrayList<String> paymentList;
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
        Set<String> emptySet = new TreeSet<>();
        Set<String> payments;
        SharedPreferences prefs = getSharedPreferences(MainActivity.MY_PREFS_NAME, MODE_PRIVATE);
        payments = prefs.getStringSet("payments", emptySet);
        paymentList = new ArrayList<>(payments);
    }

    //Setting the spinner with the proper values
    public void setSpinner() {
        Spinner mySpinner = findViewById(R.id.paymentFreqSp);
        mySpinner.setAdapter
                (new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentFrequency.values()));
    }

    //This will get the data from the ui and store it in a membership object
    public Membership getDataFromFiels() throws ParseException {

        String userName;
        String userEmail;
        Date paymentDueDate;
        String frequency;
        String comments;

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

        if(comments == null || comments.isEmpty()){
            comments = "No comments";
        }

        return new Membership (
                userName, userEmail, paymentDueDate, frequency, comments, paymentList.size());
    }

    public void submitNewMembershipButtonOnClick(View view) throws ParseException {

        if(emptyTextFieldsCheck()) {

            Membership mem = getDataFromFiels();
            String newJson = gson.toJson(mem);
            paymentList.add(newJson);
            Set<String> paymentSet = new TreeSet<>(paymentList);

            //Saving to Shared preferences
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putStringSet("payments", paymentSet);
            editor.apply();

            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }

    }

    private boolean emptyTextFieldsCheck() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setTitle("Woops, there was a problem!");
        dialog.setPositiveButton("Gotcha", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                    }
                });


            String userName = null;
            String userEmail = null;
            Date paymentDueDate = null;
            String frequency = null;

            EditText text = findViewById(R.id.nameAdd);
            userName = text.getText().toString();

            text = findViewById(R.id.emailAdd);
            userEmail = text.getText().toString();

            text = findViewById(R.id.dueDateTb1);
            DateFormat df = new SimpleDateFormat("dd", Locale.ENGLISH);

            try {
                paymentDueDate = df.parse(text.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Spinner mySpinner = findViewById(R.id.paymentFreqSp);
            frequency = mySpinner.getSelectedItem().toString();


            if(userName == null || userName.isEmpty()){

                dialog.setMessage("You need to insert a name for your payment" );
                final AlertDialog alert = dialog.create();
                alert.show();
                return false;
            }
            else if(userEmail == null || userEmail.isEmpty()){

                dialog.setMessage("You need to insert a your email address" );
                final AlertDialog alert = dialog.create();
                alert.show();
                return false;
            }
            else if(paymentDueDate == null){

                dialog.setMessage("You need to insert a payment due date" );
                final AlertDialog alert = dialog.create();
                alert.show();
                return false;
            }
            else if(frequency == null || frequency.isEmpty()){

                dialog.setMessage("You need to insert frequency for your payment" );
                final AlertDialog alert = dialog.create();
                alert.show();
                return false;
            }
            else
                return true;
        }
    }
