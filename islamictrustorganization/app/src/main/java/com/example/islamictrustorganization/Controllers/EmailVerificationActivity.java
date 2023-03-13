package com.example.islamictrustorganization.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.islamictrustorganization.BaseClass;
import com.example.islamictrustorganization.Interfaces.APIResponse;
import com.example.islamictrustorganization.LoadingDialog;
import com.example.islamictrustorganization.R;
import com.example.islamictrustorganization.ServiceManager.EndPoints;
import com.example.islamictrustorganization.ServiceManager.ServiceManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EmailVerificationActivity extends AppCompatActivity {
    EditText inputOne, inputTwo, inputThree, inputFour;
    TextView lblCountDownTimer;
    TextView lblCodeSentMsg;
    TextView textSignInCode;
    Button cmdVerify;

    LinearLayout viewResendCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_varification);
        getSupportActionBar().hide();

        initUI();

        startCounter();

    }

    private void initUI() {

        inputOne = findViewById(R.id.inputOne);
        inputOne.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction()!= KeyEvent.ACTION_DOWN) {
                inputTwo.requestFocus();
            }
            if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_DEL){
                inputOne.requestFocus();
            }
            return false;
        });


        inputTwo = findViewById(R.id.inputTwo);
        inputTwo.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction()!= KeyEvent.ACTION_DOWN) {
                inputThree.requestFocus();
            }
            if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_DEL){
                inputOne.requestFocus();
            }
            return false;
        });


        inputThree = findViewById(R.id.inputThree);
        inputThree.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction()!= KeyEvent.ACTION_DOWN) {
                inputFour.requestFocus();
            }
            if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_DEL){
                inputTwo.requestFocus();
            }
            return false;
        });

        inputFour = findViewById(R.id.inputFour);
        inputFour.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction()!= KeyEvent.ACTION_DOWN) {
                apiCallVerifyCode();
            }
            if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_DEL){
                inputThree.requestFocus();
            }
            return false;
        });


        viewResendCode = findViewById(R.id.view_resend_code);
        viewResendCode.setVisibility(View.GONE);
        viewResendCode.setOnClickListener(view -> {
            apiCallSendResetPasswordCodeAgain();
        });

        lblCountDownTimer = findViewById(R.id.lbl_count_down_timer);

        textSignInCode = findViewById(R.id.textSignInCode);

        cmdVerify = findViewById(R.id.cmd_verify);
        cmdVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiCallVerifyCode();
            }
        });
    }

    private void apiCallSendResetPasswordCodeAgain() {
        LoadingDialog.getInstance().show(this);
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("email", BaseClass.userEmail);

        viewResendCode.setVisibility(View.GONE);

        try {
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.apiCaller(EndPoints.kSendResetPasswordCode, mapParams, EmailVerificationActivity.this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("API", "Success API ==== " + response.toString());
                    LoadingDialog.getInstance().dismiss();
                    startCounter();
                }

                @Override
                public void onError(String error) {
                    LoadingDialog.getInstance().dismiss();
                    Log.d("API", "Error API ==== " + error);
                }

                @Override
                public void onStart() {
                    Log.d("API", "Started Calling API");
                }
            });
        } catch (Exception e) {
            LoadingDialog.getInstance().dismiss();
            e.printStackTrace();
        }
    }

    private void apiCallVerifyCode() {
        LoadingDialog.getInstance().show(this);
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("code", inputOne.getText().toString() + "" + inputTwo.getText().toString() + "" + inputThree.getText().toString() + "" + inputFour.getText().toString());

        viewResendCode.setVisibility(View.GONE);

        try {
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.apiCaller(EndPoints.kValidateResetPasswordCode, mapParams, EmailVerificationActivity.this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("API", "Success API ==== " + response.toString());
                    LoadingDialog.getInstance().dismiss();

                    Intent intent = new Intent(EmailVerificationActivity.this, NewPasswordActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onError(String error) {
                    LoadingDialog.getInstance().dismiss();
                    Log.d("API", "Error API ==== " + error);
                }

                @Override
                public void onStart() {
                    Log.d("API", "Started Calling API");
                }
            });
        } catch (Exception e) {
            LoadingDialog.getInstance().dismiss();
            e.printStackTrace();
        }
    }

    private void startCounter() {
        // count down timer for verification code
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                // update the TextView with the remaining time
                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(minutes);
                String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
                lblCountDownTimer.setText(timeLeftFormatted);
            }

            public void onFinish() {
                viewResendCode.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}