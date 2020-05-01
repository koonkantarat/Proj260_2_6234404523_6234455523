package com.example.ohmycost;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {

    private static final String TAG = "ListActivity";
    private String day_y, expense_e;

    DBHelper myDB;
    ListAdapter listAdapter;
    Button btnbacktomain, btnAdd;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        listView = (ListView)findViewById(R.id.listview);
        myDB = new DBHelper(this);
        btnbacktomain = findViewById(R.id.backtomain);
        btnAdd = findViewById(R.id.btnAdd);

        Intent intent = getIntent();
        day_y = intent.getStringExtra("day");
        expense_e = intent.getStringExtra("expense");

        populateListView();

        btnbacktomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewListContents.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewListContents.this, chooseExpense.class);
                intent.putExtra("day",day_y);
                //intent.putExtra("month",month_m);
                //intent.putExtra("year",year_y);
                startActivity(intent);
            }
        });
    }

    private void populateListView(){
        Log.d(TAG, "populateListView: Display data in the ListView. ");
        //final String day_y = getIntent().getExtras().getString("day");
        //final Strin0g expense_e = getIntent().getExtras().getString("expense");
        Cursor data = myDB.getData(day_y);
        ArrayList<String> TypeList = new ArrayList<>();
        while(data.moveToNext()){
            TypeList.add(data.getString(1)+"   :           "+data.getInt(2));
        }

        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, TypeList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String name = parent.getItemAtPosition(position).toString();
                String[] arr = name.split("   :           ");
                String expense_e = arr[1];
                Log.d(String.valueOf(ViewListContents.class), "onItemClick: You Click on:" + expense_e);

                Cursor mydata = myDB.getItemID(day_y,expense_e);
                int itemID = -1;
                while (mydata.moveToNext()) {
                    itemID = mydata.getInt(0);

                }
                System.out.println(itemID);
                if (itemID > -1) {
                    Log.d(String.valueOf(ViewListContents.class), "OnItemClick: The ID is:" + itemID);
                    Intent intent_i = new Intent(ViewListContents.this, EditList.class);
                    intent_i.putExtra("id", itemID);
                    intent_i.putExtra("expense", expense_e);
                    intent_i.putExtra("day",day_y);
                    startActivity(intent_i);
                } else {
                    Toast.makeText(ViewListContents.this, "No ID with that name", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
