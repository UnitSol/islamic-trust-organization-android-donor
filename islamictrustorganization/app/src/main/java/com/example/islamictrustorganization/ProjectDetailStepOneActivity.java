package com.example.islamictrustorganization;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectDetailStepOneActivity extends AppCompatActivity {
    TextView projectHeading , projectNameSubHeading , projectDate , projectDescription;
    RecyclerView imageList;
    GridLayoutManager gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail_step_one);
        initializing();
        gridLayout = new GridLayoutManager(ProjectDetailStepOneActivity.this , 2);
        imageList.setLayoutManager(gridLayout);

    }

    private void initializing() {
        projectHeading = findViewById(R.id.projectHeading);
        projectNameSubHeading = findViewById(R.id.projectNameSubHeading);
        projectDate = findViewById(R.id.projectDate);
        projectDescription = findViewById(R.id.projectDescription);
        imageList = findViewById(R.id.imageList);

//        gridLayout = new GridLayoutManager(imageList.this , 2);
    }
}