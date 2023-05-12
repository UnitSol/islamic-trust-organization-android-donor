package com.itodonor.app.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.itodonor.app.Adapters.ProjectTypeAdapter;
import com.itodonor.app.BaseClass;
import com.itodonor.app.Interfaces.APIResponse;
import com.itodonor.app.LoadingDialog;
import com.itodonor.app.Models.ProjectTypeModel;
import com.itodonor.app.NotificationCenter.NotificationCenter;
import com.itodonor.app.R;
import com.itodonor.app.ServiceManager.EndPoints;
import com.itodonor.app.ServiceManager.ServiceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectProjectTypeActivity extends AppCompatActivity {

    ImageView btnGoToRequestProject;
    RecyclerView rvProjectType;
    ProjectTypeAdapter projectTypeAdapter;
    ArrayList<ProjectTypeModel> arrData = new ArrayList<>();

    private BroadcastReceiver didSelectedProjectType = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Project", "Type Selected");
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_project_type);
//        getActionBar().hide();
        initailUI();
        NotificationCenter.addObserver(this, NotificationCenter.NotificationType.PROJECT_TYPE, didSelectedProjectType);

    }

    private void initailUI() {
        btnGoToRequestProject = findViewById(R.id.btn_go_to_request_project);
        btnGoToRequestProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvProjectType = findViewById(R.id.rv_project_type);
        apiCallGetCarAgencies();
    }

    private void apiCallGetCarAgencies() {
        LoadingDialog.getInstance().show(this);
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("project_category_id", BaseClass.SelectedProjectCategoryID);
        ServiceManager sharedManager = new ServiceManager();

        sharedManager.apiCaller(EndPoints.kGetProjectTypes, mapParams, this, new APIResponse() {
            @Override
            public void onSuccess(JSONObject response) {

                try {

                    JSONArray arrCarAgencies = response.getJSONArray("data");
                    for(int i =0; i< arrCarAgencies.length(); i++){
                        JSONObject dictType = arrCarAgencies.getJSONObject(i);
                        ProjectTypeModel projectTypeModel = new ProjectTypeModel();
                        projectTypeModel.setProjectTypeID(dictType.getInt("id"));
                        projectTypeModel.setProjectTypeName(dictType.getString("name"));
                        arrData.add(projectTypeModel);
                    }
                    displayAgencies();
                    LoadingDialog.getInstance().dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {
                LoadingDialog.getInstance().dismiss();
            }

            @Override
            public void onStart() {

//                Log.i("Car", "Fetching Car Agencies.....");
            }
        });

    }

    private void displayAgencies(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        projectTypeAdapter = new ProjectTypeAdapter(this, arrData);
        rvProjectType.setLayoutManager(layoutManager);
        rvProjectType.setAdapter(projectTypeAdapter);
    }

}