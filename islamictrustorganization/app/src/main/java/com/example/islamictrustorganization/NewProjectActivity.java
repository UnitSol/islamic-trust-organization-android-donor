package com.example.islamictrustorganization;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamictrustorganization.Adapters.FragmentProjectListAdapter;
import com.example.islamictrustorganization.Models.FragmentProjectListModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class NewProjectActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    RecyclerView newProjectList;
    FragmentProjectListAdapter projectListAdapter;
    ArrayList<FragmentProjectListModel> arrNewProjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        getSupportActionBar().hide();
        initalizing();

        FragmentProjectListModel projectListModel = new FragmentProjectListModel();
        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        projectListModel.setProjectID(1);
        projectListModel.setProjectName("Project Name");
        projectListModel.setProjectCast("562452.00");
        arrNewProjects.add(projectListModel);

        displayData();
    }
    private void displayData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        projectListAdapter = new FragmentProjectListAdapter(this, arrNewProjects);
        newProjectList.setLayoutManager(layoutManager);
        newProjectList.setAdapter(projectListAdapter);
    }

    private void initalizing() {;
        newProjectList = findViewById(R.id.newProjectList);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_donner_new_projects);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.menu_donner_new_projects:
//                    startActivity(new Intent(getApplicationContext(), NewProjectActivity.class));
//                    overridePendingTransition(0, 0);
//                    finish();
                    return true;
                case R.id.menu_donner_dashboard:
                    startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
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