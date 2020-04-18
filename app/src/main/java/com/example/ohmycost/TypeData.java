package com.example.ohmycost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TypeData extends AppCompatActivity {

    DatabaseHelper SQdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_data);
        Button back = findViewById(R.id.backToOthertype);

        ListView listView = (ListView) findViewById(R.id.listView);
        SQdb = new DatabaseHelper(this);

        ArrayList<String> TypeList = new ArrayList<>();
        Cursor data = SQdb.getlistContents();
        if(data.getCount() == 0){
            Toast.makeText(TypeData.this, "The data was empty", Toast.LENGTH_SHORT).show();
        }
        else{
            while (data.moveToNext()){
                TypeList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,TypeList);
                listView.setAdapter(listAdapter);
            }
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToOthertype = new Intent(TypeData.this,OtherType.class);
                startActivity(backToOthertype);
            }
        });

    }

}
