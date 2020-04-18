package com.example.ohmycost;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;
import android.widget.ArrayAdapter;


import android.widget.TextView;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.ohmycost.DBHelper;
import com.example.ohmycost.list;

import java.util.ArrayList;
import java.util.Dictionary;

public class MainActivity extends ListActivity {

    private double total;
    private double expense;
    private double total_month;
    private Dictionary sec1;
    private TextView theDate;
    //private ArrayList list;
    private ArrayList listType;
    private ArrayList listExpen;
    private String type;

    DBHelper mHelper;
    List<String> list;
    List<String> list_fi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button graph = (Button) findViewById(R.id.butt_graph);
        Button add = (Button) findViewById(R.id.add);
        TextView total_month = (TextView) findViewById(R.id.monthlyTotal);
        total_month.setText("Total = " + Double.toString(getTotalMonth()));
        final TextView total = (TextView) findViewById(R.id.total);

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page_graph = new Intent(MainActivity.this, graph.class);
                startActivity(page_graph);

            }
        });
        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
        theDate = (TextView) findViewById(R.id.date);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page_add = new Intent(MainActivity.this, chooseExpense.class);
                page_add.putExtra("strDate",theDate.getText().toString());
                String strDate = (String) theDate.getText();
                //page_add.putExtra(com.example.ohmycost.list.column.TYPE,  )
                //เช็คด้วยว่าามี key นี้ยัง
                //sec1.put(strDate, list);

                startActivity(page_add);
            }
        });

        //ส่งค่า
        Intent bundle = getIntent();
        String money = bundle.getStringExtra("expense");
        //TextView eiei = (TextView) findViewById(R.id.textView2);
        //eiei.setText("Expense " + money);
        //Toast.makeText(getApplicationContext(),type+":",Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),money+":",Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),type+":",Toast.LENGTH_SHORT).show(); ขึ้นข้อความจิ๋วๆ
        //getType(type);
        //listType.add(type);
        //listExpen.add(money);


        //list.add(0,listType);
        //list.add(1,listExpen);

        //รวม list


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + month + "/" + year;
                theDate.setText(date);
                total.setText("Total = " + Double.toString(getTotal())); //ไม่แน่ใจว่าควรอยู๋ตรงไหน


            }
        });

        //use data base
        //String[] lists = {""};
        mHelper = new DBHelper(this);
        list = mHelper.getList();
        List<String> mList = mHelper.getList();

        ArrayAdapter<String> adapter = new ArrayAdapter<String >(this,android.R.layout.activity_list_item, list);
        setListAdapter (adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void setTotal(double expense) {
        this.expense = expense;
    }

    public double getTotalMonth(){
        total_month = total_month + expense;
        return total_month; //ค่าใช้จ่ายรายเดือน
    }

    public double getTotal(){
        total = total + this.expense;
        return total; //ค่าใช้จ่ายรายวัน
    }/*
    //เอาชนิดมาเก็บใน list1
    public abstract void setType(Object type);
    //เอาเงินมาเก็บใน list2
    public abstract void setExpense(chooseExpense expense);
    //รวม list1,2 ไว้ใน list เพื่อใช้เป็น value ของ dict sec1 (ที่มี ันที่ป็นคีย์)*/

    public String getType(String typer){
        type = typer;
        return type;
    }
    //business ligics

/*
    //คลิกที่เมนูจะไปหน้า choose
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    /*
    @Override
    public boolean onOptionItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.add){
            Intent page_add = new Intent(MainActivity.this, chooseExpense.class);

            startActivity(page_add);
        }
        return super.onOptionItemSelected(item);
    }*/
}
