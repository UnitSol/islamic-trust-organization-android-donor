package com.example.islamictrustorganization;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class NewProjectActivity extends AppCompatActivity {
    ImageView backThroughBtn;
    ListView newProjectItemsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        initalizing();
    }

    private void initalizing() {
        backThroughBtn = findViewById(R.id.backThroughBtn);
        newProjectItemsList = findViewById(R.id.newProjectItemsList);
    }
}