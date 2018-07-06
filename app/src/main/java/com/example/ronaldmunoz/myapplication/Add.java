package com.example.ronaldmunoz.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    public static final String USER_PREF = "USER_PREF" ;
    public static final String KEY_NAME = "MEMBERSHIP_NAME";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
    }

    public void loadData() {

    }

    public void saveItemOnClick(View view) {
    }

    public void submitNewMembershipButtonOnClick(View view){
        //
        String TAG = getApplication().getPackageName();
        EditText membershipName = findViewById(R.id.nameInput);
        Log.i(TAG, "getting membership name: " + membershipName.getText().toString());


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
    }
}
