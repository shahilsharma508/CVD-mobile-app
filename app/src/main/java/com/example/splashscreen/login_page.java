package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_page extends AppCompatActivity {

    private Button register,login;
    private EditText email,password;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        register = findViewById(R.id.btn_register);
        login = findViewById(R.id.btn_login);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if (TextUtils.isEmpty(txt_email) ||  TextUtils.isEmpty(txt_password)){
                    Toast.makeText(login_page.this, "Enter credential details", Toast.LENGTH_SHORT).show();
                }


                else if (txt_password.length()<6){
                    Toast.makeText(login_page.this, "password to weak", Toast.LENGTH_SHORT).show();
                } else if (Patterns.EMAIL_ADDRESS.matcher(txt_email).matches()) {
                    registerUser(txt_email,txt_password);
                }
                  else {
                    Toast.makeText(login_page.this, "Please reenter credentials", Toast.LENGTH_SHORT).show();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                if (TextUtils.isEmpty(txt_email) ||  TextUtils.isEmpty(txt_password)){
                    Toast.makeText(login_page.this, "Enter credential details", Toast.LENGTH_SHORT).show();
                }
                else {
                    loginUser(txt_email,txt_password);
                }
            }
        });

    }

    private void loginUser(String txt_email, String txt_password) {
        auth.signInWithEmailAndPassword(txt_email,txt_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(login_page.this, "Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login_page.this,MainActivity.class);
                startActivity(intent);
            finish();
            }
        });
        auth.signInWithEmailAndPassword(txt_email,txt_password).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(login_page.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void registerUser(String txt_email, String txt_password) {
        auth.createUserWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(login_page.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(login_page.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login_page.this,prediction_form.class);
                    startActivity(new Intent(login_page.this,MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(login_page.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}