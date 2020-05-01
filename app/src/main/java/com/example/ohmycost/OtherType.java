package com.example.ohmycost;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OtherType extends AppCompatActivity {

    private TextView message1;
    private EditText typemore;
    private Button addtype, dataType, backToChoose;
    DatabaseHelper SQdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_type);
        typemore = findViewById(R.id.typemore);
        addtype = findViewById(R.id.addtype);
        dataType = findViewById(R.id.dataType);
        backToChoose = findViewById(R.id.backToChooseExpense);



        SQdb = new DatabaseHelper(this);

        Intent bundle1 = getIntent();
        final String day = bundle1.getStringExtra("day");
        final String month_choose = bundle1.getStringExtra("month");
        final String year_choose = bundle1.getStringExtra("year");


        addtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = typemore.getText().toString();
                if(typemore.length() != 0){
                    AddData(newEntry);
                    typemore.setText("");
                }else{
                    Toast.makeText(OtherType.this, "Please add again", Toast.LENGTH_SHORT).show();
                }
            }
        });

                dataType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent dataType = new Intent(OtherType.this, TypeData.class);
                        dataType.putExtra("day",day);
                        dataType.putExtra("month",month_choose);
                        dataType.putExtra("year",year_choose);
                        startActivity(dataType);
                    }
                });

        backToChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToChoose = new Intent(OtherType.this, chooseExpense.class);
                backToChoose.putExtra("day",day);
                backToChoose.putExtra("month",month_choose);
                backToChoose.putExtra("year",year_choose);
                startActivity(backToChoose);
            }
        });
    }

    public void AddData(String newEntry){
        boolean insertData = SQdb.addData(newEntry);
        if(insertData){
            Toast.makeText(OtherType.this, "Type add Success", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(OtherType.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }


}

