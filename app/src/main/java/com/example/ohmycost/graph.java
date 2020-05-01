package com.example.ohmycost;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class graph extends AppCompatActivity {

    Button btnGraph, btnCompare, btnBack;
    DBHelper theDB;
    String mSelected;
    int position = 0;
    ArrayList<Integer> mMultiSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        btnGraph = findViewById(R.id.graph);
        btnCompare = findViewById(R.id.graph_compare);
        btnBack = findViewById(R.id.back);
        theDB = new DBHelper(this);
        ArrayList<String> listData = GetMonthYear();
        final String[] data = new String[listData.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = listData.get(i);
        }
        btnGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(graph.this);
                builder.setTitle("Select Month");
                builder.setSingleChoiceItems(data, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        position = i;
                    }
                });
                builder.setPositiveButton("select", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSelected = data[position];
                        dialog.dismiss();
                        Intent intent = new Intent(graph.this, graphmonth.class);
                        intent.putExtra("month", mSelected);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("back", null);
                builder.create();
                builder.show();
            }
        });
        btnCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMultiSelected = new ArrayList<Integer>();
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(graph.this);
                builder.setTitle("Select Month");
                builder.setMultiChoiceItems(data, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            mMultiSelected.add(which);
                        } else if (mMultiSelected.contains(which)) {
                            mMultiSelected.remove(Integer.valueOf(which));
                        }
                    }
                });
                builder.setPositiveButton("select", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = "";
                        for (Integer team : mMultiSelected) {
                            text += data[team] + " ";
                        }
                        dialog.dismiss();
                        Intent intent = new Intent(graph.this, graphcomparemonth.class);
                        intent.putExtra("month", text);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("back", null);
                builder.create();
                builder.show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(graph.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    public ArrayList<String> GetMonthYear() {
        Cursor data = theDB.getMonthYear();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            String month = data.getString(0);
            if (!listData.contains(month))
                listData.add(month);
        }
        return listData;
    }
}

