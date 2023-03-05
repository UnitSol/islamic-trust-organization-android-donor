package com.example.islamictrustorganization.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.islamictrustorganization.R;

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
        goToMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileActivity.this , MoreActivity.class);
                startActivity(intent);
                finish();
            }
        });
        txtProfileUserName.setText("Muhammad Arslam");
        txtProfileUserEmail.setText("abcd123@gmail.com");
        txtProfileUserPhone.setText("+91 4456812");
        txtProfileUserAdress.setText("Wapda Town , Gujranwala");
        btnSubmitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserProfileActivity.this, "Profile Updated" + "User Name : " +txtProfileUserName + "EMail : " + txtProfileUserEmail + "Phone #  :" + txtProfileUserPhone + "Adress : " + txtProfileUserAdress, Toast.LENGTH_SHORT).show();
            }
        });
    }

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