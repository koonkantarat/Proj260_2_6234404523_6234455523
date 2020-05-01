package com.example.ohmycost;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class  chooseExpense extends AppCompatActivity {

    DBHelper myDB,DB;
    DatabaseHelper SQdb;

    private TextView typeselect;
    private Button select, ok, back , btnView;
    private EditText cost;
    private EditText type_data;
    private Spinner typeSpin;
    private ArrayList<String> type = new ArrayList<>();
    private String typechoose ,day_choose ,month_choose ,year_choose,expenseStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_expense);
        typeselect = findViewById(R.id.typeselect);
        select = findViewById(R.id.select);
        ok = findViewById(R.id.ok);
        cost = findViewById(R.id.cost);
        btnView = findViewById(R.id.btnView);
        back = findViewById(R.id.backToMain);
        typeSpin = findViewById(R.id.typespin);
        //view = findViewById(R.id.view);

        myDB = new DBHelper(this);
        final Spinner typeSpin = findViewById(R.id.typespin);


        //final ArrayList<String> typeMonth = GetTypeMonth(day);

        Intent bundle1 = getIntent();
        day_choose = bundle1.getStringExtra("day");
        //month_choose = bundle1.getStringExtra("month");
        //year_choose = bundle1.getStringExtra("year");
        //AddData_date(day_choose,month_choose,year_choose);

        ArrayList<String> typeList = new ArrayList<>(); //สร้าง ArrayList เพื่อเก็บข้อมูลประเภทที่ผู้ใช้ป้อนเข้ามาใน Database
        SQdb = new DatabaseHelper(this);
        SQLiteDatabase mydata = SQdb.getWritableDatabase();
        Cursor cursor = mydata.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            typeList.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME)));
            cursor.moveToNext();
        }
        typeList.add("Other");
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, typeList);
        typeSpin.setAdapter(adapterType);

        typeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String typeposition = String.valueOf(position);
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Other")){
                    Intent page_other = new Intent(chooseExpense.this, OtherType.class);
                    page_other.putExtra("day",day_choose);
                    startActivity(page_other);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent all = new Intent(chooseExpense.this,ViewListContents.class);
                all.putExtra("day",day_choose);
                all.putExtra("expense",expenseStr);
                startActivity(all);
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typechoose = (String) typeSpin.getSelectedItem();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewData = new Intent(chooseExpense.this, ViewListContents.class);;
                viewData.putExtra("day",day_choose);
                //viewData.putExtra("month",month_choose);
                //viewData.putExtra("year",year_choose);
                //viewData.putExtra("expense",expenseStr);
                //viewData.putExtra("type",typechoose);
                startActivity(viewData);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseStr = cost.getText().toString();

                if ( typeselect.length() != 0 && expenseStr.length() !=0){
                    AddData_ex(typechoose,expenseStr,day_choose,month_choose,year_choose);
                    cost.setText("");
                }else{
                    Toast.makeText(chooseExpense.this, "YOU MUST PUT SOMETHING",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void AddData_ex (String newEntry1,String newEntry2, String day,String month, String year){
        boolean insertData = myDB.addData_ex(newEntry1,newEntry2,day, month, year);
        if(insertData){
            Toast.makeText(chooseExpense.this,"Successfully",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(chooseExpense.this,"Sth wrong",Toast.LENGTH_LONG).show();
        }
    }
}