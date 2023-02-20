package com.example.islamictrustorganization;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamictrustorganization.Adapters.ProjectListAdapter;
import com.example.islamictrustorganization.Models.ProjectListModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class LandingActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView userPicture;
    TextView userName, textDollarWallet, textCompletedProject, textInProgressProject;
    RecyclerView completeProjectList;

    ProjectListAdapter projectListAdapter;
    ArrayList<ProjectListModel> arrProjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        initalizing();

        ProjectListModel projectListModel = new ProjectListModel();
        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Testing Model");
        projectListModel.setProjectDonation("562452.00");
        projectListModel.setImgProjectLogoURL("https://media.istockphoto.com/id/1297780288/photo/delivering-quality-construction-for-a-quality-lifestyle.jpg?b=1&s=170667a&w=0&k=20&c=Mdr_fFyIHi2jBYU2ns-Fs2JlYPk0QdP9oK3UW6jhayM=");
        arrProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Testing Model");
        projectListModel.setProjectDonation("562452.00");
        projectListModel.setImgProjectLogoURL("https://media.istockphoto.com/id/1297780288/photo/delivering-quality-construction-for-a-quality-lifestyle.jpg?b=1&s=170667a&w=0&k=20&c=Mdr_fFyIHi2jBYU2ns-Fs2JlYPk0QdP9oK3UW6jhayM=");
        arrProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Testing Model");
        projectListModel.setProjectDonation("562452.00");
        projectListModel.setImgProjectLogoURL("https://media.istockphoto.com/id/1297780288/photo/delivering-quality-construction-for-a-quality-lifestyle.jpg?b=1&s=170667a&w=0&k=20&c=Mdr_fFyIHi2jBYU2ns-Fs2JlYPk0QdP9oK3UW6jhayM=");
        arrProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Testing Model");
        projectListModel.setProjectDonation("562452.00");
        projectListModel.setImgProjectLogoURL("https://media.istockphoto.com/id/1297780288/photo/delivering-quality-construction-for-a-quality-lifestyle.jpg?b=1&s=170667a&w=0&k=20&c=Mdr_fFyIHi2jBYU2ns-Fs2JlYPk0QdP9oK3UW6jhayM=");
        arrProjects.add(projectListModel);

        displayData();
    }
    private void displayData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        projectListAdapter = new ProjectListAdapter(this, arrProjects);
        completeProjectList.setLayoutManager(layoutManager);
        completeProjectList.setAdapter(projectListAdapter);
    }
    private void initalizing() {
        userPicture = findViewById(R.id.userPicture);

        userName = findViewById(R.id.userName);
        textDollarWallet = findViewById(R.id.textDollarWallet);
        textCompletedProject = findViewById(R.id.textCompletedProject);
        textInProgressProject = findViewById(R.id.textInProgressProject);

        completeProjectList = findViewById(R.id.completeProjectList);
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