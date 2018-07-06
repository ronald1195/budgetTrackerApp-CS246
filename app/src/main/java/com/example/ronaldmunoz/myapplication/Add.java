package com.example.ronaldmunoz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void loadData() {

    }

    public void saveItem(View view) {
    }

    public void submitNewMembershipButtonOnClick(View view){
        String[] cars = {"Car1", "Car2", "Car3"};

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cars);

        Intent intent = new Intent();
        intent.putExtra("returningStringArray", "returnedString");
        setResult(RESULT_OK, intent);
        finish();
    }
}
