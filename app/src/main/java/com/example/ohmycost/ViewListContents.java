package com.example.ohmycost;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

    DBHelper myDB;
    private Button butt_backtomain;
    ArrayList<String> theList = new ArrayList<>();
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        final ListView listView = (ListView)findViewById(R.id.listview);
        myDB = new DBHelper(this);
        butt_backtomain = findViewById(R.id.backtomain);


        Cursor data = myDB.getListContents_ex();

        if(data.getCount() == 0){
            Toast.makeText(ViewListContents.this,"db was empty",Toast.LENGTH_LONG).show();
        }else{
            while (data.moveToNext()) {
                theList.add(data.getString(1)+": " + data.getInt(2));
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }

        butt_backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back = new Intent(ViewListContents.this,MainActivity.class);
            }
        });

    }

}
