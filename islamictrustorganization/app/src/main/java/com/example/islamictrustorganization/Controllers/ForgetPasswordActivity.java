package com.example.islamictrustorganization.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.islamictrustorganization.Interfaces.APIResponse;
import com.example.islamictrustorganization.LoadingDialog;
import com.example.islamictrustorganization.R;
import com.example.islamictrustorganization.ServiceManager.EndPoints;
import com.example.islamictrustorganization.ServiceManager.ServiceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText txtForgotPasswordEmail;
    Button cmdSendResetPasswordCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().hide();
        initUI();

    }

    private void initUI() {
        txtForgotPasswordEmail = findViewById(R.id.txt_forgot_password_email);
        cmdSendResetPasswordCode = findViewById(R.id.cmd_send_reset_password_code);

        cmdSendResetPasswordCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(ForgetPasswordActivity.this,EmailVarificationActivity.class);
                //startActivity(intent);
                apiCallSendResetPasswordCode();
            }
        });
    }

    private void apiCallSendResetPasswordCode(){
        LoadingDialog.getInstance().show(this);
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("email", txtForgotPasswordEmail.getText().toString());

        try {

            ServiceManager serviceManager = new ServiceManager();

            serviceManager.apiCaller(EndPoints.kSendResetPasswordCode, mapParams, ForgetPasswordActivity.this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("API", "Success API ==== "+ response.toString());

                }

                @Override
                public void onError(String error) {

                    LoadingDialog.getInstance().dismiss();
                    Log.d("API", "Error API ==== "+ error);
                }


                @Override
                public void onStart() {
                    Log.d("API", "Started Calling API");
                }
            });
            //Toast.makeText(TestAPIActivity.this, "POST API called", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}