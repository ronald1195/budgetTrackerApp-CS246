package com.example.ronaldmunoz.myapplication;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;


public class MainActivity extends ListActivity {

    ArrayList<String> arrayList;

    //Intent Extras
    public static final String EDIT_ITEM = "com.example.ronaldmunoz.myapplication.ITEM";
    public static final String INDEX = "com.example.ronaldmunoz.myapplication.INDEX";
    public static final String ARRAY_LIST = "com.example.ronaldmunoz.myapplication.LIST";

    //This is where the string Set will be stored
    public static String jsonItem;
    Set<String> payments;
    ArrayList<String> paymentsList;
    Set<String> emptySet = new TreeSet<>();


    private ArrayAdapter<String> listAdapter ;
    Gson gson = new Gson();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> amounts = new ArrayList<String>();

    int listIndex;
    public static final String MY_PREFS_NAME = "com.example.favoritescripture.PREFERENCE_FILE_KEY";


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadItems();
        emptySet.add("empty");

        createSimpleViewArray();


        //I made some changes to be able to open the edit activity from selecting a listView item
        updateList();

        //When the user presses and holds on an item in the list he will get a dialog box
        //containing an edit and a delete button, currently the delete one
        //does nothing for the time being
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showInputBox(paymentsList.get(position),position);
                String TAG = getApplication().getPackageName();
                Log.i(TAG, "index: " + position);
                return true;
            }
        });
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
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    //This function is to show the dialog box containing the edit and delete options
    public void showInputBox (String oldItem, final int index){
        //setting up a few things
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Edit");
        dialog.setContentView(R.layout.list_item);
        TextView txtMessage = dialog.findViewById(R.id.txtmessage);
        txtMessage.setText("Edit options");
        //Button btEdit = dialog.findViewById(R.id.btedit);
        Button btDelete = dialog.findViewById(R.id.btdelete);

        listIndex = index;
        dialog.show();
    }

    //Starts the edit activity when the button in the dialog box is pressed
    public void editActivity(View view) {
        Intent intent = new Intent(this, Edit.class);
        jsonItem = paymentsList.get(listIndex);
        intent.putExtra(EDIT_ITEM, jsonItem);
        intent.putExtra(INDEX, Integer.toString(listIndex));
        intent.putStringArrayListExtra(ARRAY_LIST, paymentsList);
        startActivity(intent);
    }

    public void delete(View view) {
        paymentsList.remove(listIndex);
        names.clear();
        amounts.clear();
        createSimpleViewArray();
        updateList();
        savePreferences(paymentsList);
        recreate();
    }

    //Starts the add activity
    public void addActivity(View view) {
        Intent intent = new Intent(this, Add.class);
        intent.putStringArrayListExtra(ARRAY_LIST, paymentsList);
        startActivity(intent);
    }

    //Loading the shared preferences into a set and convert it to a payment list
    public void loadItems() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        payments = prefs.getStringSet("payments", emptySet);
        paymentsList = new ArrayList<>(payments);

    }

    public void createSimpleViewArray(){

        for(int i = 0; i < paymentsList.size(); i++){

            String jsonObject = paymentsList.get(i);
            Membership member = gson.fromJson(jsonObject, Membership.class);
            String name = member.getMembershipName();
            String amount = member.getAmount();
            names.add(name);
            amounts.add(amount);
        }

    }

    public void updateList() {

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        getListView().setAdapter(listAdapter);
    }

    public void savePreferences(ArrayList list) {

        Set<String> paymentSet = new TreeSet<>(list);
        //Saving to Shared preferences
        SharedPreferences.Editor editor = getSharedPreferences(MainActivity.MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putStringSet("payments", paymentSet);
        editor.apply();
    }

    /*
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.custom_list_view,null);
            //ImageView imageView = convertView.findViewById(R.id.imageViewCustom);
            TextView textView_name = convertView.findViewById(R.id.textViewNameCustom);
            TextView textView_amount = convertView.findViewById(R.id.textViewAmountCustom);

            loadItems();
            createSimpleViewArray();

            textView_name.setText(names.get(position));
            textView_amount.setText(amounts.get(position));
            return null;
        }
    }
*/
}
