package com.example.islamictrustorganization;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText forgetPasswordEmail;
    TextView textUsePhoneNumber;
    Button sendCodeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        inital();
    }

    private void inital() {
        forgetPasswordEmail = findViewById(R.id.forgetPasswordEmail);
        textUsePhoneNumber = findViewById(R.id.textUsePhoneNumber);
        sendCodeBtn = findViewById(R.id.sendCodeBtn);
    }
}