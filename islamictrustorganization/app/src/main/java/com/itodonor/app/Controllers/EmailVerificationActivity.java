package com.itodonor.app.Controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.itodonor.app.BaseClass;
import com.itodonor.app.Interfaces.APIResponse;
import com.itodonor.app.LoadingDialog;
import com.itodonor.app.R;
import com.itodonor.app.ServiceManager.EndPoints;
import com.itodonor.app.ServiceManager.ServiceManager;

import org.json.JSONException;
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
            if (keyEvent.getAction() != KeyEvent.ACTION_DOWN) {
                inputTwo.requestFocus();
            }
            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                inputOne.requestFocus();
            }
            return false;
        });


        inputTwo = findViewById(R.id.inputTwo);
        inputTwo.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction() != KeyEvent.ACTION_DOWN) {
                inputThree.requestFocus();
            }
            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                inputOne.requestFocus();
            }
            return false;
        });


        inputThree = findViewById(R.id.inputThree);
        inputThree.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction() != KeyEvent.ACTION_DOWN) {
                inputFour.requestFocus();
            }
            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                inputTwo.requestFocus();
            }
            return false;
        });

        inputFour = findViewById(R.id.inputFour);
        inputFour.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction() != KeyEvent.ACTION_DOWN) {
                apiCallVerifyCode();
            }
            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DEL) {
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
                    if (error != null) {
                        try {
                            JSONObject dictError = new JSONObject(error);
                            Toast.makeText(EmailVerificationActivity.this, dictError.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        Toast.makeText(EmailVerificationActivity.this, "Please connect the internet", Toast.LENGTH_SHORT).show();
                    }
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
        if (inputOne.getText().length() == 0) {
            displayAlert("Error", "Please enter code.");
        } else if (inputTwo.getText().length() == 0) {
            displayAlert("Error", "Please enter code.");
        } else if (inputThree.getText().length() == 0) {
            displayAlert("Error", "Please enter code.");
        } else if (inputFour.getText().length() == 0) {
            displayAlert("Error", "Please enter code.");
        } else {
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
                        if (error != null) {
                            try {
                                JSONObject dictError = new JSONObject(error);
                                Toast.makeText(EmailVerificationActivity.this, dictError.getString("message"), Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            Toast.makeText(EmailVerificationActivity.this, "Please Turn On Internet", Toast.LENGTH_LONG).show();
                        }

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