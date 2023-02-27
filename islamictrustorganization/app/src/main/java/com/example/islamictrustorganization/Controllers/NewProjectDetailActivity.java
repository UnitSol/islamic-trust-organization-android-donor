package com.example.islamictrustorganization.Controllers;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.islamictrustorganization.R;

public class NewProjectDetailActivity extends AppCompatActivity {
    ImageView imgMakeDonationProject , goBackProject;
    TextView lblProjectDetailName , txtStartDate , txtExpectedDate , txtDonationAmount , descriptionMakeProject;
    Button makeDonationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project_detail);
        initialUI();
    }

    private void initialUI() {
        imgMakeDonationProject = findViewById(R.id.img_make_donation_project);
        goBackProject = findViewById(R.id.go_back_project);
        lblProjectDetailName = findViewById(R.id.lbl_project_detail_name);
        txtStartDate = findViewById(R.id.txt_start_date);
        txtExpectedDate = findViewById(R.id.txt_expected_date);
        txtDonationAmount = findViewById(R.id.txt_donation_amount);
        descriptionMakeProject = findViewById(R.id.description_make_project);
        makeDonationBtn = findViewById(R.id.make_donation_btn);
    }
}