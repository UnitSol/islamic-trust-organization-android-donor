package com.itodonor.app.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.itodonor.app.Adapters.MyRequestAdapter;
import com.itodonor.app.BaseClass;
import com.itodonor.app.Interfaces.APIResponse;
import com.itodonor.app.LoadingDialog;
import com.itodonor.app.R;
import com.itodonor.app.ServiceManager.EndPoints;
import com.itodonor.app.ServiceManager.ServiceManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyRequestActivity extends AppCompatActivity {
    MyRequestAdapter myRequestAdapter;
    RecyclerView rvRequests;
    BottomNavigationView bottomNavigationView;

    ArrayList<JSONObject> arrRequests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_request);
        getSupportActionBar().hide();
        initUI();
        apiCallGetRequests();
    }

    private void apiCallGetRequests() {
        LoadingDialog.getInstance().show(this);
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("user_id" , BaseClass.userID);
        try {
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.apiCaller(EndPoints.kGetMyRequest, mapParam, this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    try {
                        JSONArray arrData = response.getJSONArray("data");

                        for (int i=0; i<arrData.length(); i++ ){
                            JSONObject dictRequest = arrData.getJSONObject(i);
                            arrRequests.add(dictRequest);
                        }
                        displayData();

                        LoadingDialog.getInstance().dismiss();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onError(String error) {
                    LoadingDialog.getInstance().dismiss();
                }

                @Override
                public void onStart() {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initUI() {
        rvRequests = findViewById(R.id.rv_requests);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_request_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_donner_dashboard:
                    startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.menu_donner_new_projects:
                    startActivity(new Intent(getApplicationContext(), MyProjectsActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.menu_request_view:
                    //startActivity(new Intent(getApplicationContext(), RequestResponseActivity.class));
                    //overridePendingTransition(0, 0);
                    return true;
                case R.id.menu_donner_more:
                    startActivity(new Intent(getApplicationContext(), MoreActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
    }

    private void displayData() {
        LinearLayoutManager outManager = new LinearLayoutManager(MyRequestActivity.this);
        myRequestAdapter = new MyRequestAdapter(MyRequestActivity.this, arrRequests);
        rvRequests.setLayoutManager(new LinearLayoutManager(MyRequestActivity.this));
        rvRequests.setAdapter(myRequestAdapter);

    }
}