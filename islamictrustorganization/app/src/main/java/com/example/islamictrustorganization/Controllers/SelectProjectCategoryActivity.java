package com.example.islamictrustorganization.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.islamictrustorganization.Adapters.ProjectCategoryAdapter;
import com.example.islamictrustorganization.Adapters.ProjectTypeAdapter;
import com.example.islamictrustorganization.BaseClass;
import com.example.islamictrustorganization.Interfaces.APIResponse;
import com.example.islamictrustorganization.LoadingDialog;
import com.example.islamictrustorganization.Models.ProjectCategoryModel;
import com.example.islamictrustorganization.Models.ProjectTypeModel;
import com.example.islamictrustorganization.NotificationCenter.NotificationCenter;
import com.example.islamictrustorganization.R;
import com.example.islamictrustorganization.ServiceManager.EndPoints;
import com.example.islamictrustorganization.ServiceManager.ServiceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectProjectCategoryActivity extends AppCompatActivity {
    ImageView btnGoToRequestProject;
    RecyclerView rvProjectCategory;
    ProjectCategoryAdapter projectCategoryAdapter;
    ArrayList<ProjectCategoryModel> arrProjectCategory = new ArrayList<>();
    private BroadcastReceiver didSelectedProjectCategory = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Project", "Type Selected");
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_project_category);
        getSupportActionBar().hide();
        InitUI();
        NotificationCenter.addObserver(this, NotificationCenter.NotificationType.PROJECT_CATEGORY, didSelectedProjectCategory);

    }

    private void displayAgencies(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        projectCategoryAdapter = new ProjectCategoryAdapter(this, arrProjectCategory);
        rvProjectCategory.setLayoutManager(layoutManager);
        rvProjectCategory.setAdapter(projectCategoryAdapter);
    }

    private void InitUI() {
        btnGoToRequestProject = findViewById(R.id.btn_go_to_request_project);
        btnGoToRequestProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rvProjectCategory = findViewById(R.id.rv_project_category);
        apiCallingForProjectCategory();

    }

    private void apiCallingForProjectCategory() {
        LoadingDialog.getInstance().show(this);
        Map<String, String> mapParams = new HashMap<>();
        ServiceManager sharedManager = new ServiceManager();

        sharedManager.apiCaller(EndPoints.kGetProjectCategory, mapParams, this, new APIResponse() {
            @Override
            public void onSuccess(JSONObject response) {
                Log.e("TAG", "onSuccess: "+ response );
                try {

                    JSONArray arrCarAgencies = response.getJSONArray("data");
                    for(int i =0; i< arrCarAgencies.length(); i++){
                        JSONObject dictType = arrCarAgencies.getJSONObject(i);
                        ProjectCategoryModel projectCategoryModel = new ProjectCategoryModel();
                        projectCategoryModel.setProjectCategoryID(dictType.getInt("id"));
                        projectCategoryModel.setProjectCategoryName(dictType.getString("name"));
                        arrProjectCategory.add(projectCategoryModel);
                    }
                    displayAgencies();
                    LoadingDialog.getInstance().dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {
                Log.e("TAG", "onError: "+ error );
                LoadingDialog.getInstance().dismiss();
            }

            @Override
            public void onStart() {

//                Log.i("Car", "Fetching Car Agencies.....");
            }
        });
    }
}