package com.example.ohmycost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class chooseExpense extends AppCompatActivity {

    private TextView typeselect;
    private Button select, ok, back;
    private EditText cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_expense);
        typeselect = findViewById(R.id.typeselect);
        select = findViewById(R.id.select);
        ok = findViewById(R.id.ok);
        cost = findViewById(R.id.cost);

        back = findViewById(R.id.backToMain);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMain = new Intent(chooseExpense.this,MainActivity.class);
                startActivity(backToMain);
            }
        });
        Spinner typespin;
        typespin = findViewById(R.id.typespin);
        String [] typeselectList = getResources().getStringArray(R.array.typeselect);

        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this,android.R.layout.select_dialog_multichoice, typeselectList);
        typespin.setAdapter(adapterType);
    }
}
