package com.itodonor.app.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.itodonor.app.Adapters.SliderAdapter;
import com.itodonor.app.BaseClass;
import com.itodonor.app.Interfaces.APIResponse;
import com.itodonor.app.LoadingDialog;
import com.itodonor.app.Models.SliderModel;
import com.itodonor.app.R;
import com.itodonor.app.ServiceManager.EndPoints;
import com.itodonor.app.ServiceManager.ServiceManager;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeDonationActivity extends AppCompatActivity {
    SliderView imgMakeDonationProjectLogo;
    SliderAdapter sliderAdapter;
    SliderModel sliderModel;
    Button btnAddDonation;
    ImageView goBackProjectDetail;
    TextView lblProjectDonationName , txtMakeDonationStartDate , txtMakeDonationExpectedDate , txtMakeDonationAmount;
    EditText edtxtDonatedAmount;
    List<SliderModel> makeDonationSlider = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_donation);
        getSupportActionBar().hide();
        initialUI();
        apiCallingNewProjectDetail();
        goBackProjectDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newProjectDetailIntent = new Intent(MakeDonationActivity.this , NewProjectDetailActivity.class);
                startActivity(newProjectDetailIntent);
            }

        });
        btnAddDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiCallingMakeDonation();
            }
        });
    }

    private void apiCallingNewProjectDetail() {
        LoadingDialog.getInstance().show(this);
        Map<String , String> mapParam = new HashMap<>();
        mapParam.put("project_id" , BaseClass.selectedProjectID);
        try {
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.apiCaller(EndPoints.kGetProjectDetail, mapParam, MakeDonationActivity.this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("API", "Success API ==== "+ response.toString());
                    try {
                        JSONObject dictNEwProjectDetail = response.getJSONObject("data");
                        lblProjectDonationName.setText(dictNEwProjectDetail.getString("name"));
                        txtMakeDonationStartDate.setText(dictNEwProjectDetail.getString("start_date"));
                        txtMakeDonationExpectedDate.setText(dictNEwProjectDetail.getString("end_date"));
                        txtMakeDonationAmount.setText("$" + dictNEwProjectDetail.getInt("budget"));
                        JSONArray arrImg = dictNEwProjectDetail.getJSONArray("images");
                        for (int i = 0 ; i<arrImg.length(); i++){

                            sliderAdapter = new SliderAdapter();
                            SliderModel sliderModel = new SliderModel();
                            JSONObject dictImgNewProjectDetail = arrImg.getJSONObject(i);


                            sliderModel.setImgUrl(dictImgNewProjectDetail.getString("image"));
                            makeDonationSlider.add(sliderModel);
                            imgMakeDonationProjectLogo.setSliderAdapter(sliderAdapter);
                            displayData();
//                            imgMakeDonationProject.setIndicatorAnimationDuration(100);
                            imgMakeDonationProjectLogo.setSliderAnimationDuration(200);
                            imgMakeDonationProjectLogo.setAutoCycle(true);
                            imgMakeDonationProjectLogo.startAutoCycle();
                            imgMakeDonationProjectLogo.setScrollTimeInSec(3);
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
        sliderAdapter = new SliderAdapter( makeDonationSlider , this);
        imgMakeDonationProjectLogo.setSliderAdapter(sliderAdapter);
    }

    private void apiCallingMakeDonation() {
        LoadingDialog.getInstance().show(this);
        Map<String , String>  mapParam = new HashMap<>();
        mapParam.put("project_id" , BaseClass.selectedProjectID);
        mapParam.put("user_id" , BaseClass.userID);
        mapParam.put("amount" ,edtxtDonatedAmount.getText().toString().trim() );
        try {
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.apiCaller(EndPoints.kMakeDonation, mapParam, MakeDonationActivity.this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("API", "Success API ==== " + response.toString());
                    LoadingDialog.getInstance().dismiss();
                    Intent intent = new Intent(MakeDonationActivity.this , NewProjectActivity.class);
                    startActivity(intent);
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

    private void initialUI() {
        goBackProjectDetail=findViewById(R.id.go_back_project_detail);
        imgMakeDonationProjectLogo = findViewById(R.id.img_make_donation_project);
        lblProjectDonationName = findViewById(R.id.lbl_project_donation_name);
        txtMakeDonationStartDate = findViewById(R.id.txt_make_donation_start_date);
        txtMakeDonationExpectedDate = findViewById(R.id.txt_make_donation_expected_date);
        txtMakeDonationAmount = findViewById(R.id.txt_make_donation_amount);
        edtxtDonatedAmount = findViewById(R.id.edtxt_donated_amount);
        btnAddDonation = findViewById(R.id.btn_add_donation);
    }
}