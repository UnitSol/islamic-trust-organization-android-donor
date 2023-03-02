package com.example.islamictrustorganization.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.islamictrustorganization.Adapters.SliderAdapter;
import com.example.islamictrustorganization.BaseClass;
import com.example.islamictrustorganization.Interfaces.APIResponse;
import com.example.islamictrustorganization.LoadingDialog;
import com.example.islamictrustorganization.Models.SliderModel;
import com.example.islamictrustorganization.R;
import com.example.islamictrustorganization.ServiceManager.EndPoints;
import com.example.islamictrustorganization.ServiceManager.ServiceManager;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewProjectDetailActivity extends AppCompatActivity {
    SliderView imgMakeDonationProject ;
    TextView lblProjectDetailName , txtStartDate , txtExpectedDate , txtDonationAmount , descriptionMakeProject;
    Button makeDonationBtn;
    ImageView goBackProject;
    String iD;
    SliderAdapter sliderAdapter;
    int [] images ;
    List<SliderModel> sliderItem = new ArrayList<>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project_detail);
        getSupportActionBar().hide();
        initialUI();
        apiCallingGetNewProjectDetail();
        makeDonationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewProjectDetailActivity.this , MakeDonationActivity.class);
                startActivity(intent);
            }
        });
        goBackProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBackIntent = new Intent(NewProjectDetailActivity.this , NewProjectActivity.class);
                startActivity(goBackIntent);
            }
        });
    }

    private void apiCallingGetNewProjectDetail() {
        LoadingDialog.getInstance().show(this);
        Map<String , String> mapParam = new HashMap<>();
        mapParam.put("project_id" , BaseClass.selectedProjectID);
        try {
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.apiCaller(EndPoints.kGetProjectDetail, mapParam, NewProjectDetailActivity.this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.e("TAG", "onSuccess: "+ iD );
                    Log.d("API", "Success API ==== "+ response.toString());
                    try {
                        JSONObject dictNEwProjectDetail = response.getJSONObject("data");
                        lblProjectDetailName.setText(dictNEwProjectDetail.getString("name"));
                        txtStartDate.setText(dictNEwProjectDetail.getString("start_date"));
                        txtExpectedDate.setText(dictNEwProjectDetail.getString("end_date"));
                        txtDonationAmount.setText("$" + dictNEwProjectDetail.getInt("budget"));
                        descriptionMakeProject.setText(dictNEwProjectDetail.getString("description"));
                        JSONArray arrImg = dictNEwProjectDetail.getJSONArray("images");
                        for (int i = 0 ; i<arrImg.length(); i++){

                            sliderAdapter = new SliderAdapter();
                            SliderModel sliderModel = new SliderModel();
                            JSONObject dictImgNewProjectDetail = arrImg.getJSONObject(i);


                            sliderModel.setImgUrl(dictImgNewProjectDetail.getString("image"));
                            sliderItem.add(sliderModel);
                            imgMakeDonationProject.setSliderAdapter(sliderAdapter);
                            displayData();
//                            imgMakeDonationProject.setIndicatorAnimationDuration(100);
                            imgMakeDonationProject.setSliderAnimationDuration(200);
                            imgMakeDonationProject.setAutoCycle(true);
                            imgMakeDonationProject.startAutoCycle();
                            imgMakeDonationProject.setScrollTimeInSec(3);
                        }

                    }
                    catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    LoadingDialog.getInstance().dismiss();


                }

                @Override
                public void onError(String error) {
                    LoadingDialog.getInstance().dismiss();
                    Log.d("API", "Error API ==== "+ error);
                }

                @Override
                public void onStart() {
                    Log.d("API", "Started Calling API");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void displayData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        sliderAdapter = new SliderAdapter( sliderItem , this);
        imgMakeDonationProject.setSliderAdapter(sliderAdapter);
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