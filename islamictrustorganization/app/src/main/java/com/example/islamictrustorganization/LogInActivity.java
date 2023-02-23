package com.example.islamictrustorganization;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LogInActivity extends AppCompatActivity {

    EditText loginEmail , loginPassword;
    Button loginBtn;
    TextView textForgetPassword ;
    //TextView textSignUp;
    //ImageButton faceBookLoginBtn , googleLoginBtn , appleLoginBtn;
    ImageView eyePasswordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();
        inital();

        loginBtn.setOnClickListener(view -> {
            Intent intent = new Intent(LogInActivity.this , DashBoardActivity.class);
            startActivity(intent);
        });
        textForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this , ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void inital() {
        loginEmail = findViewById(R.id.txt_email);
        loginPassword = findViewById(R.id.txt_password);

        loginBtn = findViewById(R.id.loginBtn);

        textForgetPassword = findViewById(R.id.textForgetPassword);
//        textSignUp = findViewById(R.id.textSignUp);
//
//        faceBookLoginBtn = findViewById(R.id.faceBookLoginBtn);
//        googleLoginBtn = findViewById(R.id.googleLoginBtn);
//        appleLoginBtn = findViewById(R.id.appleLoginBtn);

//        eyePasswordBtn = findViewById(R.id.eyePasswordBtn);

    }
}