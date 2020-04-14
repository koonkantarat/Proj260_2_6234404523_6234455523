package com.example.ohmycost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OtherType extends AppCompatActivity {

    private TextView message;
    private EditText typemore;
    private Button addtype, backToChooseExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_type);
        message = findViewById(R.id.message);
        typemore = findViewById(R.id.typemore);
        addtype = findViewById(R.id.addtype);
        Button backToChoose = findViewById(R.id.backToChooseExpense);

        addtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typechoose = typemore.getText().toString();
            }
        });

        backToChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToChoose = new Intent(OtherType.this, chooseExpense.class);
                backToChoose.putExtra("Type",typemore.getText().toString());
                startActivity(backToChoose);
            }
        });
    }


}

