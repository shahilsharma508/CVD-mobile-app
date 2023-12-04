package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button,btn_history;
    private Button button_about;
    private Button btn_predict;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn_ai);
        button_about = (Button) findViewById(R.id.btn_about);
        btn_predict = (Button) findViewById(R.id.btn_predict);
        btn_history = (Button)findViewById(R.id.btn_history);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openAimodel_page();
            }
        });


        button_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbout_page();
            }
        });


        btn_predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,prediction_form.class);
                startActivity(intent);
            }
        });

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,history_page.class);
                startActivity(intent);
            }
        });


    }
    public void openAimodel_page(){
        Intent intent = new Intent(this,Aimodel_page.class);
        startActivity(intent);
    }

    public void openAbout_page(){
        Intent intent = new Intent(this,about_page.class);
        startActivity(intent);
    }


}