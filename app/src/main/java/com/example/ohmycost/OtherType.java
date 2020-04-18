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


        addtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = typemore.getText().toString();
                if(!type.equals("") && SQdb.insertData(type)){
                    Toast.makeText(OtherType.this, "Type add Success", Toast.LENGTH_SHORT).show();
                    typemore.setText("");
                }
                else{
                    Toast.makeText(OtherType.this, "Please add again", Toast.LENGTH_SHORT).show();
                }
            }
        });

                dataType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent dataType = new Intent(OtherType.this, TypeData.class);
                        startActivity(dataType);
                    }
                });

        backToChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToChoose = new Intent(OtherType.this, chooseExpense.class);
                //backToChoose.putExtra("Type",typemore.getText().toString());
                startActivity(backToChoose);
            }
        });
    }


}

