package com.example.islamictrustorganization.Controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class NewPasswordActivity extends AppCompatActivity {
    EditText setNewPassword, newConfirmPassword;
    String newPass , confirmPass;
    ImageView eyeNewPasswordBtn, eyeConfirmPasswordBtn;
    Button cmdConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        getSupportActionBar().hide();
        initUI();
        cmdConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setNewPassword.getText().length() == 0) {
                    displayAlert("Error", "Please complete the all field");
                } else if (newConfirmPassword.getText().length() == 0) {
                    displayAlert("Error", "Please complete the all field");
                } else if (!setNewPassword.getText().toString().equals(newConfirmPassword.getText().toString())) {
                    Toast.makeText(NewPasswordActivity.this, "Enter Same Password", Toast.LENGTH_SHORT).show();
                }else{

                    apiCallUpdatePassword();
                }
            }
        });
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
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }

                @Override
                public void onError(String error) {
                    LoadingDialog.getInstance().dismiss();
                    Log.d("API", "Error API ==== " + error);
                    if(error != null){
                        try {
                            JSONObject dictError = new JSONObject(error);
                            Toast.makeText(NewPasswordActivity.this, dictError.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        Toast.makeText(NewPasswordActivity.this, "Please Turn On Internet", Toast.LENGTH_LONG).show();
                    }
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

    private void initUI() {
        setNewPassword = findViewById(R.id.txt_set_new_password);
        newConfirmPassword = findViewById(R.id.txt_set_confirm_password);

        cmdConfirm = findViewById(R.id.confirmpasswordBtn);

        newPass = setNewPassword.getText().toString().trim();
        confirmPass = newConfirmPassword.getText().toString().trim();
    }
}