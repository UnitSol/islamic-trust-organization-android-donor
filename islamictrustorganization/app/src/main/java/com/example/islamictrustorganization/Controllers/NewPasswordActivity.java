package com.example.islamictrustorganization.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.islamictrustorganization.R;

public class NewPasswordActivity extends AppCompatActivity {
    EditText setNewPassword, newConfirmPassword;
    ImageView eyeNewPasswordBtn, eyeConfirmPasswordBtn;
    Button confirmpasswordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        initalization();
        confirmpasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPasswordActivity.this,LogInActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initalization() {
        setNewPassword = findViewById(R.id.txt_set_new_password);
        newConfirmPassword = findViewById(R.id.txt_set_confirm_password);

//        eyeNewPasswordBtn = findViewById(R.id.eyeNewPasswordBtn);
//        eyeConfirmPasswordBtn = findViewById(R.id.eyeConfirmPasswordBtn);

        confirmpasswordBtn = findViewById(R.id.confirmpasswordBtn);
    }
}