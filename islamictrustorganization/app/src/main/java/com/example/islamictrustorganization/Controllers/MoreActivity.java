package com.example.islamictrustorganization.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.islamictrustorganization.R;

public class MoreActivity extends AppCompatActivity {
    RelativeLayout cmdLogOut , cmdProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        getSupportActionBar().hide();
        initalUI();
        cmdLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoreActivity.this , LogInActivity.class);
                startActivity(intent);
                finish();

            }
        });
        cmdProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoreActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initalUI() {
        cmdLogOut = findViewById(R.id.cmd_log_out);
        cmdProfile = findViewById(R.id.cmd_profile);
    }

}