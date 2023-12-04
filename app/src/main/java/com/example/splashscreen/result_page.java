package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ekn.gruzer.gaugelibrary.HalfGauge;
import com.ekn.gruzer.gaugelibrary.Range;

public class result_page extends AppCompatActivity {

    HalfGauge IdMeter;

    com.ekn.gruzer.gaugelibrary.Range range1,range2,range3,range4,range5;

    TextView result,test;
    Button btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        result = (TextView) findViewById(R.id.result_view);
        test = (TextView) findViewById(R.id.test);
        String  get_data = getIntent().getStringExtra("result");
        String result_gauage = String.valueOf(get_data);
        result.setText(get_data);
        btn_menu= (Button)findViewById(R.id.btn_menu);
        IdMeter = findViewById(R.id.IdMeter);
        range1 = new Range();
        range2 = new Range();
        range3 = new Range();
        range4 = new Range();
        range5 = new Range();
        range1.setFrom(0); range1.setTo(10);
        range2.setFrom(10); range2.setTo(20);
        range3.setFrom(20); range3.setTo(30);
        range4.setFrom(30); range4.setTo(40);
        range5.setFrom(40); range5.setTo(50);
        range1.setColor(Color.parseColor("#00FF00"));
        range2.setColor(Color.parseColor("#FFFF00"));
        range3.setColor(Color.parseColor("#FFA500"));
        range4.setColor(Color.parseColor("#FF0000"));
        range5.setColor(Color.parseColor("#8b0000"));
        IdMeter.addRange(range1);
        IdMeter.addRange(range2);
        IdMeter.addRange(range3);
        IdMeter.addRange(range4);
        IdMeter.addRange(range5);
        IdMeter.setMinValue(0);
        IdMeter.setMaxValue(50);
        int meter_value = 0;
        if("Risk level 1 <10%".equals(result_gauage)){
            meter_value = 5;
        }
        else if("Risk level 2 10% to <20%".equals(result_gauage)){
            meter_value = 15;
        }
        else if("Risk level 3 20% to <30%".equals(result_gauage)){
            meter_value = 25;
        }
        else if("Risk level 4 30% to <40%".equals(result_gauage)){
            meter_value = 35;
        }
        else if("Risk level 5 =>50%".equals(result_gauage)){
            meter_value = 45;
        }
        IdMeter.setValue(meter_value);
        btn_menu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(result_page.this,MainActivity.class);
            startActivity(intent);
        }
    });
    }
}