package com.example.islamictrustorganization.Controllers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.islamictrustorganization.Helpers.UserHelper;
import com.example.islamictrustorganization.NotificationCenter.NotificationCenter;
import com.example.islamictrustorganization.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class MoreActivity extends AppCompatActivity {
    RelativeLayout cmdLogOut, cmdProfile;
    BottomNavigationView bottomNavigationView;
    private BroadcastReceiver didProfileUpdate = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("NotificationCenter", "City Selected");
            try {
                JSONObject dictUser = new JSONObject(UserHelper.getLoggedInUserData(MoreActivity.this));
                Log.d("TAG", "onReceive: " + dictUser.getString("name"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        getSupportActionBar().hide();
        initalUI();

        NotificationCenter.addObserver(MoreActivity.this, NotificationCenter.NotificationType.PROFILE_UPDATED , didProfileUpdate);
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
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_donner_more);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_donner_dashboard:
                    startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.menu_donner_new_projects:
                    startActivity(new Intent(getApplicationContext(), MyProjectsActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.menu_donner_more:
//                    startActivity(new Intent(getApplicationContext(), MoreActivity.class));
//                    overridePendingTransition(0, 0);
//                    finish();
                    return true;
//                case R.id.menu_buyer_orders:
//                    startActivity(new Intent(getApplicationContext(), BuyerOrdersActivity.class));
//                    overridePendingTransition(0, 0);
//                    finish();
//                    return true;
//                case R.id.menu_buyer_more:
//                    return true;
            }
            return false;
        });
    }

}