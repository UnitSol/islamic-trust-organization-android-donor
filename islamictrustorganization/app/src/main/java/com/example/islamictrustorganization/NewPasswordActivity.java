package com.example.islamictrustorganization;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class NewPasswordActivity extends AppCompatActivity {
    EditText setNewPassword, newConfirmPassword;
    ImageView eyeNewPasswordBtn, eyeConfirmPasswordBtn;
    Button confirmpasswordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        initalization();
    }

    private void initalization() {
        setNewPassword = findViewById(R.id.setNewPassword);
        newConfirmPassword = findViewById(R.id.newConfirmPassword);

        eyeNewPasswordBtn = findViewById(R.id.eyeNewPasswordBtn);
        eyeConfirmPasswordBtn = findViewById(R.id.eyeConfirmPasswordBtn);

        confirmpasswordBtn = findViewById(R.id.confirmpasswordBtn);
    }
}