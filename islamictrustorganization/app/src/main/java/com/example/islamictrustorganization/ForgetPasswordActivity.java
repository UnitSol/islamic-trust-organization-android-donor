package com.example.islamictrustorganization;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText forgetPasswordEmail;
    Button sendCodeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().hide();
        inital();
    }

    private void inital() {
        forgetPasswordEmail = findViewById(R.id.forgetPasswordEmail);
        sendCodeBtn = findViewById(R.id.sendCodeBtn);
    }
}