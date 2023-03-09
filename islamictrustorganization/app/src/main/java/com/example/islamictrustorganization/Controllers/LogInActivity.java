package com.example.islamictrustorganization.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.islamictrustorganization.BaseClass;
import com.example.islamictrustorganization.Interfaces.APIResponse;
import com.example.islamictrustorganization.LoadingDialog;
import com.example.islamictrustorganization.R;
import com.example.islamictrustorganization.ServiceManager.EndPoints;
import com.example.islamictrustorganization.ServiceManager.ServiceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LogInActivity extends AppCompatActivity {

    EditText txtEmail;
    EditText txtPassword;
    Button cmdLogin;
    TextView cmdForgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();

        initUI();
    }

    private void initUI() {
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);

        txtEmail.setText("hajiusm281@gmail.com");
        txtPassword.setText("password");

        cmdLogin = findViewById(R.id.loginBtn);
        cmdLogin.setOnClickListener(view -> {
            //Intent intent = new Intent(LogInActivity.this , DashBoardActivity.class);
            //startActivity(intent);
            apiCallLogin();
        });

        cmdForgotPassword = findViewById(R.id.textForgetPassword);
        cmdForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this , ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }


    private void apiCallLogin(){
        LoadingDialog.getInstance().show(this);
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("email", txtEmail.getText().toString());
        mapParams.put("password", txtPassword.getText().toString());

        try {

            ServiceManager serviceManager = new ServiceManager();

            serviceManager.apiCaller(EndPoints.kLogin, mapParams, LogInActivity.this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    LoadingDialog.getInstance().dismiss();
                    Log.d("API", "Success API ==== "+ response.toString());
                    try {
                        JSONObject dictUser = response.getJSONObject("data");
                        BaseClass.userID = String.valueOf(dictUser.getInt("id"));
                        BaseClass.userName = dictUser.getString("name");


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    Intent intent = new Intent(LogInActivity.this , DashBoardActivity.class);
                    startActivity(intent);
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