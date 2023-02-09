package com.example.islamictrustorganization;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EmailVarificationActivity extends AppCompatActivity {
    EditText inputOne , inputTwo ,inputThree , inputFour;
    TextView counterTimeText , textSignInCode;
    Button verifyBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_varification);
        initalizing();
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