package com.example.ohmycost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditType extends AppCompatActivity {

    private EditText editType;
    private Button saveType, deleteType, back;
    private static final String TAG = "EditTypeActivity";
    DatabaseHelper SQdb;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_type);

        editType = findViewById(R.id.editType);
        saveType = findViewById(R.id.saveType);
        deleteType = findViewById(R.id.deleteType);
        back = findViewById(R.id.backToTypedata);

        SQdb = new DatabaseHelper(this);

        //รับข้อมูลมาจากหน้าTypeData
        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id" , -1);
        selectedName = receivedIntent.getStringExtra("name");

        final String day = receivedIntent.getStringExtra("day");
        final String month_choose = receivedIntent.getStringExtra("month");
        final String year_choose = receivedIntent.getStringExtra("year");

        editType.setText(selectedName);

        //แก้ไขTypeตามที่ผู้ใช้ต้องการในฐานข้อมูล
        saveType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editType.getText().toString();
                if(!item.equals("")){
                    SQdb.updateName(item, selectedID, selectedName);
                    Toast.makeText(EditType.this, "Edit type Success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EditType.this, "You must enter a name", Toast.LENGTH_SHORT).show(); }
            }
        });

        //ลบTypeออกจากฐานข้อมูล
        deleteType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQdb.deleteName(selectedID, selectedName);
                editType.setText("");
                Toast.makeText(EditType.this, "removed from database", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToTypedata = new Intent(EditType.this, TypeData.class);
                backToTypedata.putExtra("day",day);
                backToTypedata.putExtra("month",month_choose);
                backToTypedata.putExtra("year",year_choose);
                startActivity(backToTypedata);
            }
        });
    }
}
