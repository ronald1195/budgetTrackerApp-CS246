package com.example.ronaldmunoz.myapplication;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ethan
    }

    public int i = 0;
    public int counter(int i) {
        for (;i < 20; i++) {
            return i;
        }
        return i;
    }

    //Comment made by Ronald
}
