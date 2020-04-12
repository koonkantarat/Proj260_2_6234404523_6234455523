package com.example.ohmycost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class chooseExpense extends AppCompatActivity{

    private TextView typeselect;
    private Button select, ok, back;
    private EditText cost;
    private Spinner typeSpin;
    private ArrayList<String> type = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_expense);
        typeselect = findViewById(R.id.typeselect);
        select = findViewById(R.id.select);
        ok = findViewById(R.id.ok);
        cost = findViewById(R.id.cost);
        back = findViewById(R.id.backToMain);
        typeSpin = findViewById(R.id.typespin);
        CreateTypeSelection();

        ArrayAdapter<String> adapterType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, type);
        typeSpin.setAdapter(adapterType);

        typeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String typeposition = String.valueOf(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typechoose = (String) typeSpin.getSelectedItem();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMain;
                backToMain = new Intent(chooseExpense.this, MainActivity.class);
                startActivity(backToMain);
            }
                });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yourcost = cost.getText().toString();
            }
        });
    }

    private void CreateTypeSelection() {

        type.add("Food");
        type.add("Bus");
        type.add("Other");
    }
}
