package com.example.ohmycost;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class  chooseExpense extends AppCompatActivity {

    private TextView typeselect;
    private Button select, ok;
    private EditText cost;
    private EditText type_data;
    private Spinner typeSpin;
    private ArrayList<String> type = new ArrayList<>();
    private ArrayList<chooseExpense> listType = new ArrayList<chooseExpense>();
    private ArrayList<chooseExpense> listExpen = new ArrayList<chooseExpense>();
    private String typechoose, typeadd;

    private DBHelper mHelper;

    private int ID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_expense);
        typeselect = findViewById(R.id.typeselect);
        select = findViewById(R.id.select);
        ok = findViewById(R.id.ok);
        cost = findViewById(R.id.cost);
        typeSpin = findViewById(R.id.typespin);
        CreateTypeSelection();

        ArrayAdapter<String> adapterType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, type);
        typeSpin.setAdapter(adapterType);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            String typeadd = bundle.getString("Type");
            typechoose = typeadd;
        }


        typeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String typeposition = String.valueOf(position);
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Other")){
                    select.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent page_other = new Intent(chooseExpense.this, OtherType.class);
                            startActivity(page_other);
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typechoose = "";
                typechoose += (String) typeSpin.getSelectedItem();
                //String typechoose = (String) typeSpin.getSelectedItem();
                typeselect.setText(typechoose);
                /*//ส่งข้อมูล
                Intent type = new Intent(chooseExpense.this,chooseExpense.class);
                type.putExtra("type",typechoose);
                startActivity(type);*/

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ส่งข้อมูล
                Intent all = new Intent(chooseExpense.this,MainActivity.class);
                //String expenseStr = cost.getText().toString();
                //String typechooser = typechoose.toUpperCase();
                //all.putExtra("type",typechoose.toString());
                all.putExtra("expense",cost.getText().toString());
                startActivity(all);

                //database

                AlertDialog.Builder builder = new AlertDialog.Builder(chooseExpense.this);
                builder.setTitle("Add this expense?");
                builder.setMessage("Are you sure to add this expense?");


                //ถ้ายืนยันจะเพิ่มข้อมูลนี้ กด ok
                builder.setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list list = new list();
                        list.setType(type_data.getText().toString());
                        list.setExpense(cost.getText().toString());

                        if(ID == -1){
                            mHelper.addList(list);
                        }
                        else {
                            list.setID(ID);
                        }
                        finish();
                    }
                });
                //ถ้ายืนยันจะเพิ่มข้อมูลนี้ กด cancel
                builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
            }
        });
        Intent bundle1 = getIntent();
        String day_choose = bundle1.getStringExtra("strDate");
    }

    private void CreateTypeSelection() {

        type.add("Food");
        type.add("Bus");
        type.add("Other");
    }
/*
    public abstract ArrayList combineList();
    //เอาชนิดมาเก็บใน list1
    public void setType(Object type){
        chooseExpense typechoose = (chooseExpense) type;
        listType.add(typechoose);
    }
    //เอาเงินมาเก็บใน list2
    public void setExpense(chooseExpense expense){
        listExpen.add(expense);
    }
    //รวม list1,2 ไว้ใน list เพื่อใช้เป็น value ของ dict sec1 (ที่มี ันที่ป็นคีย์)
*/
    public ArrayList getListType(){
        return listType;
    }
    public ArrayList getListExpen(){
        return listExpen;
    }

}
