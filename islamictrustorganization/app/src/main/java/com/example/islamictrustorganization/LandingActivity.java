package com.example.islamictrustorganization;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LandingActivity extends AppCompatActivity {
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
    }
}