package com.example.islamictrustorganization;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LandingActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView userPicture;
    TextView userName , textDollarWallet , textCompletedProject , textInProgressProject;
    ListView projectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        initalizing();

    }

    private void initalizing() {
        userPicture = findViewById(R.id.userPicture);

        userName = findViewById(R.id.userName);
        textDollarWallet = findViewById(R.id.textDollarWallet);
        textCompletedProject = findViewById(R.id.textCompletedProject);
        textInProgressProject = findViewById(R.id.textInProgressProject);

        projectList = findViewById(R.id.projectList);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_donner_dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_donner_dashboard:
//                    startActivity(new Intent(getApplicationContext(), LandingActivity.class));
//                    overridePendingTransition(0, 0);
//                    finish();
                    return true;
                case R.id.menu_donner_new_projects:
                    startActivity(new Intent(getApplicationContext(), NewProjectActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
//                case R.id.menu_buyer_order_piece:
//                    startActivity(new Intent(getApplicationContext(), BuyerOrderPieceActivity.class));
//                    overridePendingTransition(0, 0);
//                    finish();
//                    return true;
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