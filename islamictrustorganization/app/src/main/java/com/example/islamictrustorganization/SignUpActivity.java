package com.example.islamictrustorganization;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

public class SignUpActivity extends AppCompatActivity {

    EditText signUpFirestName , signUpLastName , signUpEmail , signUpPhoneNumber , signUpPassword , signUpReTypePassword;
    CountryCodePicker signUpCountryCode;
    TextView textTermService ,textPrivacyPolicy , textSignIn;
    Button signUpBtn;
    ImageButton faceBookSignUpBtn , googleSignUpBtn ,appleSignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initalizing();
    }

    private void initalizing() {
        signUpFirestName = findViewById(R.id.signUpFirestName);
        signUpLastName = findViewById(R.id.signUpLastName);
        signUpEmail = findViewById(R.id.signUpEmail);
        signUpPhoneNumber = findViewById(R.id.signUpPhoneNumber);
        signUpPassword = findViewById(R.id.signUpPassword);
        signUpReTypePassword = findViewById(R.id.signUpReTypePassword);

        signUpCountryCode = findViewById(R.id.signUpCountryCode);

        textTermService = findViewById(R.id.textTermService);
        textPrivacyPolicy = findViewById(R.id.textPrivacyPolicy);
        textSignIn = findViewById(R.id.textSignIn);

        signUpBtn = findViewById(R.id.signUpBtn);
        faceBookSignUpBtn = findViewById(R.id.faceBookSignUpBtn);
        googleSignUpBtn = findViewById(R.id.googleSignUpBtn);
        appleSignUpBtn = findViewById(R.id.appleSignUpBtn);
    }
}