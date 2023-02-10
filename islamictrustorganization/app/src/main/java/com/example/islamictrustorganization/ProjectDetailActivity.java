package com.example.islamictrustorganization;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProjectDetailActivity extends AppCompatActivity {
    TextView projectName , textStartDate , textEndDate , projectPrize ,textStatus , textDescription , textReadMore;
    ImageView projectImage , goBackBtn;
    ListView updateListItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        initializing();
    }

    private void initializing() {
        projectName = findViewById(R.id.projectName);
        textStartDate = findViewById(R.id.textStartDate);
        textEndDate = findViewById(R.id.textEndDate);
        projectPrize = findViewById(R.id.projectPrize);
        textStatus = findViewById(R.id.textStatus);
        textDescription = findViewById(R.id.textDescription);
        textReadMore = findViewById(R.id.textReadMore);

        projectImage = findViewById(R.id.projectImage);
        goBackBtn = findViewById(R.id.goBackBtn);

        updateListItem = findViewById(R.id.updateListItem);
    }
}