package com.example.ohmycost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private double total;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button graph = (Button)findViewById(R.id.butt_graph);
        Button add = (Button)findViewById(R.id.add);
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent page_graph = new Intent(MainActivity.this,graph_monthly.class); มีค่าเหมือนกับบรรทัดล่าง
                startActivity(new Intent(MainActivity.this,graph.class));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page_add = new Intent(MainActivity.this,chooseExpense.class);
                startActivity(page_add);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    public double getTotal(double expense){
        total = total + expense;
        return total;
    }
}
