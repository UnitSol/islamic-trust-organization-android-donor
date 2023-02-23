package com.example.islamictrustorganization;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.islamictrustorganization.Adapters.ViewPageAdapter;
import com.example.islamictrustorganization.Fragments.CompletedProjectFragment;
import com.example.islamictrustorganization.Fragments.DashboardFragment;
import com.example.islamictrustorganization.Fragments.OnGoingProjectFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity implements TabLayoutMediator.TabConfigurationStrategy {
    ImageView userImageId;
    TextView lblUserName;
    BottomNavigationView bottomNavigationView;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    ArrayList<String> titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().hide();
        initUI();
        titles = new ArrayList<String>();
        titles.add("Dashboard");
        titles.add("Completed Project");
        titles.add("On Going Project");
        setViewPageAdapter();
        new TabLayoutMediator(tabLayout , viewPager , (TabLayoutMediator.TabConfigurationStrategy)this).attach();
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//                tabLayout.getTabAt(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    private void initUI() {
        userImageId = findViewById(R.id.user_image_id);
        lblUserName = findViewById(R.id.lbl_user_name);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
//        viewPageAdapter = new ViewPageAdapter(this);
//        viewPager.setAdapter(viewPageAdapter);


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
                    startActivity(new Intent(getApplicationContext(), NewProjectActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
//                case R.id.menu_buyer_order_piece:
//                    startActivity(new Intent(getApplicationContext(), BuyerOrderPieceActivity.class));
//                    overridePendingTransition(0, 0);
//                    finish();
//                    return true;
//                case R.id.menu_buyer_orders:
//                    startActivity(new Intent(getApplicationContext(), BuyerOrdersActivity.class));
//                    overridePendingTransition(0, 0);
//                    finish();
//                    return true;
//                case R.id.menu_buyer_more:
//                    return true;
            }
            return false;
        });
    }
    public void setViewPageAdapter(){
        ViewPageAdapter viewPageAdapter1 = new ViewPageAdapter(this);
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new DashboardFragment());
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