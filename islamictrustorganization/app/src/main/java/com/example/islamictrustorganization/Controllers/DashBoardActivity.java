package com.example.islamictrustorganization.Controllers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.islamictrustorganization.Adapters.ViewPageAdapter;
import com.example.islamictrustorganization.BaseClass;
import com.example.islamictrustorganization.Fragments.DashboardFragment;
import com.example.islamictrustorganization.Helpers.UserHelper;
import com.example.islamictrustorganization.Interfaces.APIResponse;
import com.example.islamictrustorganization.LoadingDialog;
import com.example.islamictrustorganization.NotificationCenter.NotificationCenter;
import com.example.islamictrustorganization.R;
import com.example.islamictrustorganization.ServiceManager.EndPoints;
import com.example.islamictrustorganization.ServiceManager.ServiceManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashBoardActivity extends AppCompatActivity {
    ImageView imgProfile;
    TextView lblUserName;
    BottomNavigationView bottomNavigationView;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    ArrayList<String> titles;

    TextView lblAmount , lblRemainingAmount , lblOngoingProjectAmount , lblCompleteProjectAmount;

    SwipeRefreshLayout swipeRefreshLayout;
    private BroadcastReceiver didProfileUpdate = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("NotificationCenter", "City Selected");
            try {
                JSONObject dictUser = new JSONObject(UserHelper.getLoggedInUserData(DashBoardActivity.this));
                Log.d("TAG", "onReceive: " + dictUser.getString("name"));
                lblUserName.setText(dictUser.getString("name"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().hide();
        initUI();

        apiCallGetDashboardData();

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiCallGetDashboardData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        NotificationCenter.addObserver(DashBoardActivity.this, NotificationCenter.NotificationType.PROFILE_UPDATED , didProfileUpdate);


    }

    private void initUI() {
        imgProfile = findViewById(R.id.img_user_profile);
        lblUserName = findViewById(R.id.lbl_user_name);

        lblUserName.setText(BaseClass.userName);
        try {
            JSONObject dictUser = new JSONObject(UserHelper.getLoggedInUserData(this));
            lblUserName.setText(dictUser.getString("name"));
            if (!dictUser.isNull("image")) {
                Glide.with(this).load(dictUser.getString("image")).into(imgProfile);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        lblAmount = findViewById(R.id.lbl_amount);
        lblRemainingAmount = findViewById(R.id.lbl_remaining_amount);
        lblOngoingProjectAmount = findViewById(R.id.lbl_ongoing_project_amount);
        lblCompleteProjectAmount = findViewById(R.id.lbl_complete_project_amount);



        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_donner_dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_donner_dashboard:
//                    startActivity(new Intent(getApplicationContext(), LandingActivity.class));
//                    overridePendingTransition(0, 0);
//                    finish();
                    return true;
                case R.id.menu_donner_new_projects:
                    startActivity(new Intent(getApplicationContext(), MyProjectsActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.menu_donner_more:
                    startActivity(new Intent(getApplicationContext(), MoreActivity.class));
                    overridePendingTransition(0, 0);
                    //finish();
                    return true;
                case R.id.menu_request_view:
                    startActivity(new Intent(getApplicationContext(), MyRequestActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
//                case R.id.menu_buyer_more:
//                    return true;
            }
            return false;
        });
    }


    private void apiCallGetDashboardData() {
        LoadingDialog.getInstance().show(this);
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("user_id" , BaseClass.userID);
        try {
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.apiCaller(EndPoints.KGetDashboardDetail, mapParam, this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    try {
                        JSONObject dictDashboard = response.getJSONObject("data");
                        lblAmount.setText(String.valueOf(dictDashboard.getInt("total_funds")));
                        lblRemainingAmount.setText(String.valueOf(dictDashboard.getInt("remaining_funds")));
                        lblOngoingProjectAmount.setText(String.valueOf(dictDashboard.getInt("on_going_project")));
                        lblCompleteProjectAmount.setText(String.valueOf(dictDashboard.getInt("complete_project")));
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

}