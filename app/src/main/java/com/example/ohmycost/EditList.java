package com.example.ohmycost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditList extends AppCompatActivity {
    DBHelper DB;
    Button back, save, delete;
    EditText edit_item;

    private String day_y;
    private String expense_e;
    private String type_t;
    private int _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        back = findViewById(R.id.btn_backtomain);
        save = findViewById(R.id.btn_edit);
        delete = findViewById(R.id.btndelete);
        edit_item = findViewById(R.id.editText);
        DB = new DBHelper(this);

        Intent intent = getIntent();
        //_id = Integer.parseInt(intent.getStringExtra("id"));
        _id = intent.getIntExtra("id" , -1);
        day_y = intent.getStringExtra("day");
        expense_e = intent.getStringExtra("expense");
        type_t = intent.getStringExtra("type");
        edit_item.setText(expense_e);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_b = new Intent(EditList.this, ViewListContents.class);
                intent_b.putExtra("day", day_y);
                startActivity(intent_b);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = edit_item.getText().toString();
                if (!item.equals("")) {
                    DB.updateName(item, _id, expense_e);
                    Toast.makeText(EditList.this, "Data Successfully Edit!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(EditList.this, "You must enter a data", Toast.LENGTH_LONG).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.deleteName(_id, expense_e);
                edit_item.setText("");
                Toast.makeText(EditList.this, "removed from database", Toast.LENGTH_LONG).show();
            }
        });
    }
}
