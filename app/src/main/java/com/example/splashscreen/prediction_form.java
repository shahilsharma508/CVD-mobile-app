package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class prediction_form extends AppCompatActivity {

    private Button btn_back;
    Button btn_submit;
    RadioGroup gender_group,diabetes_group, smoking_status_group;
    TextView output;
    String return_gender,return_age,return_blood_pressure, return_cholesterol,return_diabetes,return_smoking_status="";
    EditText age,blood_pressure,cholesterol;
    TextView result;

FirebaseDatabase auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction_form);
        btn_back = (Button) findViewById(R.id.btn_back);
        gender_group = findViewById(R.id.gender_group);
        diabetes_group = findViewById(R.id.diabetes);
        smoking_status_group = findViewById(R.id.smoking_status);
        btn_submit = findViewById(R.id.btn_submit);

        age = (EditText) findViewById(R.id.age);
        blood_pressure = (EditText) findViewById(R.id.blood_pressure);
        cholesterol = (EditText) findViewById(R.id.chorestrol);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String useremail = user.getEmail();
        String set_email = custom_primaryKey_encoder(useremail);


        if (!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        Python py = Python.getInstance();
        final PyObject pyobj = py.getModule("try");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_page();
            }
        });
  btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checked_id = gender_group.getCheckedRadioButtonId();
                int checked_id1 = diabetes_group.getCheckedRadioButtonId();
                int checked_id2 = smoking_status_group.getCheckedRadioButtonId();
                return_age = age.getText().toString();
                return_blood_pressure = blood_pressure.getText().toString();
                return_cholesterol = cholesterol.getText().toString();
                String date =  getDate();

                if(checked_id == -1){
                    Toast.makeText(getApplicationContext(), "Please select gender", Toast.LENGTH_LONG).show();
                }

                else if(checked_id1 == -1){
                    Toast.makeText(getApplicationContext(), "Please select diabetic status", Toast.LENGTH_LONG).show();
                }
                else if(checked_id2 == -1){
                    Toast.makeText(getApplicationContext(), "Please select smoking status", Toast.LENGTH_LONG).show();
                }

                else if (return_age.equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter age", Toast.LENGTH_LONG).show();
                }

                else if (return_blood_pressure.equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter blood pressure", Toast.LENGTH_LONG).show();
                }

                else if (return_cholesterol.equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter cholesterol", Toast.LENGTH_LONG).show();
                }

                else {
                    return_gender =  findRadioButton(checked_id);
                    return_diabetes = findRadioButton1(checked_id1);
                    return_smoking_status = findRadioButton2(checked_id2);

                    PyObject obj = pyobj.callAttr("main",return_age,return_gender, return_blood_pressure, return_cholesterol, return_diabetes,return_smoking_status);

                   // result.setText(obj.toString());
                    String predicted_result = obj.toString();
                    // this is the returned results
                    Intent intent = new Intent(prediction_form.this,result_page.class);
                    intent.putExtra("result",predicted_result);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("date",date);
                    map.put("age",return_age);
                    map.put("gender",return_gender);
                    map.put("blood_pressure",return_blood_pressure);
                    map.put("cholesterol",return_cholesterol);
                    map.put("diabetes",return_diabetes);
                    map.put("smoking_status",return_smoking_status);
                    map.put("predicted_risk",predicted_result);
                    FirebaseDatabase.getInstance().getReference().child(set_email).child(date).updateChildren(map);
                    startActivity(intent);
                }
            }
        });
        gender_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checked_id) {
                findRadioButton(checked_id);
            }
        });
    }
    public void menu_page(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private String findRadioButton2(int checked_id2) {
        String smoking_status = "";
        switch (checked_id2){

            case R.id.smoke:
                //Toast.makeText(getApplicationContext(), "Smoker", Toast.LENGTH_LONG).show();
                smoking_status  = "1";
                break;

            case R.id.non_smoker:
                //Toast.makeText(getApplicationContext(), "Non-smoker", Toast.LENGTH_LONG).show();
                smoking_status  = "0";
                break;
        }
        return smoking_status;
    }
    private String findRadioButton1(int checked_id1) {
        String diabetes = "";

        switch (checked_id1){

            case R.id.diabetic:
                //Toast.makeText(getApplicationContext(), "Diabetic", Toast.LENGTH_LONG).show();
                diabetes  = "1";
                break;

            case R.id.non_diabetic:
                //Toast.makeText(getApplicationContext(), "Non-Diabetic", Toast.LENGTH_LONG).show();
                diabetes  = "0";
                break;
        }
        return diabetes ;
    }

    private String findRadioButton(int checked_id) {
        String gender = "";
        switch (checked_id){

            case R.id.male:
                //Toast.makeText(getApplicationContext(), "male", Toast.LENGTH_LONG).show();
                gender = "1";
                break;

            case R.id.female:
                //Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_LONG).show();
                gender = "0";
                break;
        }
        return gender;
    }

    @NonNull
    private String getDate(){
        Date today = new Date();
        today.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        int month = calendar.get(Calendar.MONTH) + 1;
        String month1 = String.valueOf(month);
        String day_of_month = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String date = day_of_month + "-" +month1 + "-" + year;
        return date;
    }


  private String custom_primaryKey_encoder(String email){
      email = email.replace(".", "");
      email = email.replace("@", "");
      return email;
    }


}