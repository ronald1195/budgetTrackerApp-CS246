package com.example.ronaldmunoz.myapplication;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ListActivity {

    ArrayList<String> arrayList;
    public static String jsonItem;
    public static final String EDIT_ITEM = "com.example.ronaldmunoz.myapplication.ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //I made some changes to be able to open the edit activity from selecting a listview item
        final String [] list = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        arrayList = new ArrayList<>(Arrays.asList(list));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getListView().getContext(), android.R.layout.simple_expandable_list_item_1, arrayList);
        getListView().setAdapter(adapter);

        //When the user presses and holds on an item in the list he will get a dialog box
        //containing an edit and a delete button, currently the delete one
        //does nothing for the time being
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showInputBox(arrayList.get(position),position);
                return true;
            }
        });
        //homeDisplay.onCreate(savedInstanceState);

        //ListView mainListView = findViewById(R.id.mainListView);


        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1, myStringArray);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //This function is to show the dialog box containing the edit and delete options
    public void showInputBox (String oldItem, final int index){
        //setting up a few things
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Edit");
        dialog.setContentView(R.layout.list_item);
        TextView txtMessage = dialog.findViewById(R.id.txtmessage);
        txtMessage.setText("Edit options");
        Button btEdit = dialog.findViewById(R.id.btedit);
        //For temporary testing purposes
        jsonItem = oldItem;
        dialog.show();
    }

    public void editActivity(View view) {
        Intent intent = new Intent(this, Edit.class);
        intent.putExtra(EDIT_ITEM, jsonItem);
        startActivity(intent);
    }


    /*String [] myStringArray = new String [15];
    protected static String num() {
        String [] myStringArray = new String[15];
        for (int i = 0; i < 15; i++) {
            myStringArray[i] = Integer.toString(i + 1);
            //return myStringArray[i];
        }
        for (int i = 0; i < 15; i++) {
            myStringArray[0] += myStringArray[i];
        }
        return myStringArray[0];
        return "HELLO";
    }*/





}
