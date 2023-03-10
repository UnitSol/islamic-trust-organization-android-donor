package com.example.islamictrustorganization.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.islamictrustorganization.BaseClass;
import com.example.islamictrustorganization.Helpers.UserHelper;
import com.example.islamictrustorganization.Interfaces.APIResponse;
import com.example.islamictrustorganization.LoadingDialog;
import com.example.islamictrustorganization.NotificationCenter.NotificationCenter;
import com.example.islamictrustorganization.R;
import com.example.islamictrustorganization.ServiceManager.EndPoints;
import com.example.islamictrustorganization.ServiceManager.ServiceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserProfileActivity extends AppCompatActivity {
    ImageView goToMore , imgUserProfile;

    EditText txtProfileUserName , txtProfileUserEmail , txtProfileUserPhone , txtProfileUserAdress;

    Button btnSubmitInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().hide();
        initialUI();
        imgUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });
        goToMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileActivity.this, MoreActivity.class);
                startActivity(intent);
                finish();
            }
        });
        txtProfileUserName.setText("Muhammad Arslam");
        txtProfileUserEmail.setText("hajiusm281@gmail.com");
        txtProfileUserPhone.setText("+91 4456812");
        txtProfileUserAdress.setText("Wapda Town , Gujranwala");
        btnSubmitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiCallingUserProfileUpdate();
            }
        });
    }

    private void apiCallingUserProfileUpdate() {
        LoadingDialog.getInstance().show(this);
        Map<String , String> mapParam = new HashMap<>();
        mapParam.put("user_id" , BaseClass.userID);
        mapParam.put("name" , txtProfileUserName.getText().toString());
        mapParam.put("email" , txtProfileUserEmail.getText().toString());
        mapParam.put("contact_number" , txtProfileUserPhone.getText().toString());
        mapParam.put("address" , txtProfileUserAdress.getText().toString());
        try {
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.apiCaller(EndPoints.kUserProfileUpdate, mapParam, UserProfileActivity.this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    LoadingDialog.getInstance().dismiss();
                    Log.d("API", "Success API ==== "+ response.toString());

                    try {
                        UserHelper.setLoggedInUserData(UserProfileActivity.this , response.getJSONObject("data").toString());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("isSuccess", String.valueOf(false));
                    NotificationCenter.postNotification(UserProfileActivity.this, NotificationCenter.NotificationType.PROFILE_UPDATED,params);

                    Toast.makeText(UserProfileActivity.this, "Profile Update", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserProfileActivity.this , MoreActivity.class);
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        launchSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.i("TAG", ": " + selectedImageBitmap);
                        imgUserProfile.setImageBitmap(selectedImageBitmap);
                    }
                }
            });

    private void initialUI() {
        goToMore = findViewById(R.id.go_to_more);
        imgUserProfile = findViewById(R.id.img_user_profile);

        txtProfileUserName = findViewById(R.id.txt_profile_user_name);
        txtProfileUserEmail = findViewById(R.id.txt_profile_user_email);
        txtProfileUserPhone = findViewById(R.id.txt_profile_user_phone);
        txtProfileUserAdress = findViewById(R.id.txt_profile_user_adress);

        btnSubmitInfo = findViewById(R.id.btn_submit_info);
    }
}