package com.example.islamictrustorganization.Controllers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.islamictrustorganization.Adapters.ViewPageAdapter;
import com.example.islamictrustorganization.BaseClass;
import com.example.islamictrustorganization.Fragments.CompletedProjectFragment;
import com.example.islamictrustorganization.Fragments.OnGoingProjectFragment;
import com.example.islamictrustorganization.Helpers.UserHelper;
import com.example.islamictrustorganization.NotificationCenter.NotificationCenter;
import com.example.islamictrustorganization.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyProjectsActivity extends AppCompatActivity implements TabLayoutMediator.TabConfigurationStrategy {
    ImageView userImageId;
    TextView lblUserName;
    BottomNavigationView bottomNavigationView;
    TabLayout tabLayout;
    Button btnRequestProject;
    ViewPager2 viewPager;
    ArrayList<String> titles;

    private BroadcastReceiver didRequestPost = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("NotificationCenter", "New Request Posted");
            startActivity(new Intent(getApplicationContext(), MyRequestActivity.class));
            overridePendingTransition(0, 0);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_projects);
        getSupportActionBar().hide();
        initUI();
        titles = new ArrayList<String>();
        titles.add("Completed Project");
        titles.add("On Going Project");
        setViewPageAdapter();
        new TabLayoutMediator(tabLayout , viewPager , this).attach();

        NotificationCenter.addObserver(MyProjectsActivity.this, NotificationCenter.NotificationType.NEW_REQUEST_POSTED, didRequestPost);

    }

    private void initUI() {

        lblUserName = findViewById(R.id.lbl_user_name);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_donner_new_projects);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_donner_dashboard:
                    startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.menu_donner_new_projects:
//                    startActivity(new Intent(getApplicationContext(), NewProjectActivity.class));
//                    overridePendingTransition(0, 0);
//                    finish();
                    return true;
                case R.id.menu_request_view:
                    startActivity(new Intent(getApplicationContext(), MyRequestActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.menu_donner_more:
                    startActivity(new Intent(getApplicationContext(), MoreActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

            }
            return false;
        });
        btnRequestProject = findViewById(R.id.btn_request_project);
        btnRequestProject.setOnClickListener(view -> {
            BaseClass.SelectedProjectCategoryID = null;
            BaseClass.SelectedProjectTypeID = null;
            Intent intent = new Intent(MyProjectsActivity.this , RequestProjectActivity.class);
            startActivity(intent);
        });
    }
    public void setViewPageAdapter(){
        ViewPageAdapter viewPageAdapter1 = new ViewPageAdapter(this);
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new CompletedProjectFragment());
        fragmentList.add(new OnGoingProjectFragment());
        viewPageAdapter1.setData(fragmentList);
        viewPager.setAdapter(viewPageAdapter1);

    }

    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        tab.setText(titles.get(position));
    }

}