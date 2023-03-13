package com.example.islamictrustorganization.Controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.number.LocalizedNumberFormatter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.islamictrustorganization.BaseClass;
import com.example.islamictrustorganization.Helpers.UserHelper;
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
                Intent intent = new Intent(LogInActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }


    private void apiCallLogin() {

        if (txtEmail.getText().length() == 0) {
            displayAlert("Error", "Please enter your email id.");
        } else if (isEmailValid(txtEmail.getText().toString()) == false) {
            displayAlert("Error", "Please enter a valid email id.");
        } else if (txtPassword.getText().length() == 0) {
            displayAlert("Error", "Please enter your password");
        } else if (txtPassword.getText().length() < 6) {
            displayAlert("Error", "Password must contain 6 or more than 6 characters.");
        } else {

            LoadingDialog.getInstance().show(this);
            Map<String, String> mapParams = new HashMap<>();
            mapParams.put("email", txtEmail.getText().toString());
            mapParams.put("password", txtPassword.getText().toString());

            try {

                ServiceManager serviceManager = new ServiceManager();

                serviceManager.apiCaller(EndPoints.kLogin, mapParams, LogInActivity.this, new APIResponse() {
                    @Override
                    public void onSuccess(JSONObject response) {

                        Log.d("API", "Success API ==== " + response.toString());
                        try {

                            JSONObject dictUser = response.getJSONObject("data");
                            BaseClass.userID = String.valueOf(dictUser.getInt("id"));
                            BaseClass.userName = dictUser.getString("name");
                            UserHelper.setLoggedInUserData(LogInActivity.this, dictUser.toString());

                            Intent intent = new Intent(LogInActivity.this, DashBoardActivity.class);
                            startActivity(intent);
                            finish();

                            LoadingDialog.getInstance().dismiss();

                        } catch (JSONException e) {
                            LoadingDialog.getInstance().dismiss();
                            throw new RuntimeException(e);
                        }

                    }

                    @Override
                    public void onError(String error) {
                        LoadingDialog.getInstance().dismiss();

                        try {
                            JSONObject dictError = new JSONObject(error);
                            Toast.makeText(LogInActivity.this, dictError.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        Log.d("API", "Error API ==== " + error);
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
}