package com.example.islamictrustorganization.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

public class NewPasswordActivity extends AppCompatActivity {
    EditText setNewPassword, newConfirmPassword;
    String newPass , confirmPass;
    ImageView eyeNewPasswordBtn, eyeConfirmPasswordBtn;
    Button confirmpasswordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        getSupportActionBar().hide();
        initalization();
        confirmpasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiCallUpdatePassword();
            }
        });
    }

    private void apiCallUpdatePassword() {
        LoadingDialog.getInstance().show(this);

        Map<String , String > mapParam = new HashMap<>();
        mapParam.put("email", BaseClass.userEmail);
        mapParam.put("password" , setNewPassword.getText().toString());
        try {
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.apiCaller(EndPoints.kUpdatePassword, mapParam, NewPasswordActivity.this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("API", "Success API ==== " + response.toString());
                    LoadingDialog.getInstance().dismiss();
                    Intent intent = new Intent(NewPasswordActivity.this , LogInActivity.class);
                    startActivity(intent);

                }

                @Override
                public void onError(String error) {
                    LoadingDialog.getInstance().dismiss();
                    Log.d("API", "Error API ==== " + error);
                }

                @Override
                public void onStart() {
                    LoadingDialog.getInstance().dismiss();
                    Log.d("API", "Started Calling API");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void initalization() {
        setNewPassword = findViewById(R.id.txt_set_new_password);
        newConfirmPassword = findViewById(R.id.txt_set_confirm_password);

//        eyeNewPasswordBtn = findViewById(R.id.eyeNewPasswordBtn);
//        eyeConfirmPasswordBtn = findViewById(R.id.eyeConfirmPasswordBtn);

        confirmpasswordBtn = findViewById(R.id.confirmpasswordBtn);

        newPass = setNewPassword.getText().toString().trim();
        confirmPass = newConfirmPassword.getText().toString().trim();
    }
}