package com.example.islamictrustorganization.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamictrustorganization.Adapters.DashboardListAdapter;
import com.example.islamictrustorganization.Models.DashboardListModel;
import com.example.islamictrustorganization.R;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    View view;
    RecyclerView rvDashboardList;
    DashboardListAdapter dashboardListAdapter;
    ArrayList<DashboardListModel> arrDashBoardList = new ArrayList<>();
    Context thisContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //thisContext = container.getContext();
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        rvDashboardList = (RecyclerView) view.findViewById(R.id.dashboard_fragment_list);
        DashboardListModel dashboardListModel = new DashboardListModel();
        dashboardListModel.setItemID(1);
        dashboardListModel.setTotalFund("Fund");
        dashboardListModel.setGetTotalFund("14");
        arrDashBoardList.add(dashboardListModel);

        dashboardListModel = new DashboardListModel();
        dashboardListModel.setItemID(1);
        dashboardListModel.setTotalFund("Fund");
        dashboardListModel.setGetTotalFund("12");
        arrDashBoardList.add(dashboardListModel);

        dashboardListModel = new DashboardListModel();
        dashboardListModel.setItemID(1);
        dashboardListModel.setTotalFund("fund");
        dashboardListModel.setGetTotalFund("1");
        arrDashBoardList.add(dashboardListModel);
//        dashboardListModel.setItemID(4);
//        dashboardListModel.setTotalFund("258");
//        dashboardListModel.setGetTotalFund("18");
//        arrDashBoardList.add(dashboardListModel);
        displayData();
        return view;
    }

    private void displayData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(thisContext);
        dashboardListAdapter = new DashboardListAdapter(thisContext, arrDashBoardList);
        rvDashboardList.setLayoutManager(layoutManager);
        rvDashboardList.setAdapter(dashboardListAdapter);

    }


}