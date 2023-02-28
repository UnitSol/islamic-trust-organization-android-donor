package com.example.islamictrustorganization.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.islamictrustorganization.R;

public class EmailVarificationActivity extends AppCompatActivity {
    EditText inputOne , inputTwo ,inputThree , inputFour;
    TextView counterTimeText , textSignInCode;
    Button verifyBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_varification);
        initalizing();
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmailVarificationActivity.this , NewPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initalizing() {
        inputOne = findViewById(R.id.inputOne);
        inputTwo = findViewById(R.id.inputTwo);
        inputThree = findViewById(R.id.inputThree);
        inputFour = findViewById(R.id.inputFour);

        counterTimeText = findViewById(R.id.counterTimeText);
        textSignInCode = findViewById(R.id.textSignInCode);

        verifyBtn = findViewById(R.id.verifyBtn);
    }
}