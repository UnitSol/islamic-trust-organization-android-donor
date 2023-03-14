package com.example.islamictrustorganization.Controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        txtForgotPasswordEmail.setText("hajiusm281@gmail.com");

        cmdSendResetPasswordCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtForgotPasswordEmail.getText().length() == 0) {
                    displayAlert("Error", "Please enter your email id.");
                } else if (isEmailValid(txtForgotPasswordEmail.getText().toString()) == false) {
                    displayAlert("Error", "Please enter a valid email id.");
                }
                apiCallSendResetPasswordCode();
            }
        });
    }

    private void apiCallSendResetPasswordCode(){
        LoadingDialog.getInstance().show(this);

        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("email", txtForgotPasswordEmail.getText().toString());

        BaseClass.userEmail = txtForgotPasswordEmail.getText().toString();

        try {
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.apiCaller(EndPoints.kSendResetPasswordCode, mapParams, ForgetPasswordActivity.this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("API", "Success API ==== "+ response.toString());
                    LoadingDialog.getInstance().dismiss();
                    Intent intent = new Intent(ForgetPasswordActivity.this,EmailVerificationActivity.class);
                    startActivity(intent);
                }
                @Override
                public void onError(String error) {
                    LoadingDialog.getInstance().dismiss();
                    Log.d("API", "Error API ==== "+ error);
                    if(error != null){
                        try {
                            JSONObject dictError = new JSONObject(error);
                            Toast.makeText(ForgetPasswordActivity.this, dictError.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        Toast.makeText(ForgetPasswordActivity.this, "Please Turn On Internet", Toast.LENGTH_LONG).show();
                    }


                }
                @Override
                public void onStart() {
                    Log.d("API", "Started Calling API");
                }
            });
            //Toast.makeText(TestAPIActivity.this, "POST API called", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            LoadingDialog.getInstance().dismiss();
            e.printStackTrace();
        }
    }
    public void displayAlert(String title, String body) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(body)
                .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked

                    }
                }).show();
    }
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}