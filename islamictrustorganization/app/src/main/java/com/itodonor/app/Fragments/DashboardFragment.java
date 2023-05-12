package com.itodonor.app.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.itodonor.app.BaseClass;
import com.itodonor.app.Interfaces.APIResponse;
import com.itodonor.app.LoadingDialog;
import com.itodonor.app.R;
import com.itodonor.app.ServiceManager.EndPoints;
import com.itodonor.app.ServiceManager.ServiceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DashboardFragment extends Fragment {
    View view;
    TextView lblAmount , lblRemainingAmount , lblOngoingProjectAmount , lblCompleteProjectAmount;
    Context thisContext;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //thisContext = container.getContext();
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        thisContext = view.getContext();
        lblAmount = (TextView) view.findViewById(R.id.lbl_amount);
        lblRemainingAmount = (TextView) view.findViewById(R.id.lbl_remaining_amount);
        lblOngoingProjectAmount = (TextView) view.findViewById(R.id.lbl_ongoing_project_amount);
        lblCompleteProjectAmount = (TextView) view.findViewById(R.id.lbl_complete_project_amount);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);


        apiCallDashboardFragment();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiCallDashboardFragment();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

//        DashboardListModel dashboardListModel = new DashboardListModel();
//        dashboardListModel.setItemID(1);
//        dashboardListModel.setTotalFund("Fund");
//        dashboardListModel.setGetTotalFund("14");
//        arrDashBoardList.add(dashboardListModel);
//
//        dashboardListModel = new DashboardListModel();
//        dashboardListModel.setItemID(1);
//        dashboardListModel.setTotalFund("Fund");
//        dashboardListModel.setGetTotalFund("12");
//        arrDashBoardList.add(dashboardListModel);
//
//        dashboardListModel = new DashboardListModel();
//        dashboardListModel.setItemID(1);
//        dashboardListModel.setTotalFund("fund");
//        dashboardListModel.setGetTotalFund("1");
//        arrDashBoardList.add(dashboardListModel);
////        dashboardListModel.setItemID(4);
////        dashboardListModel.setTotalFund("258");
////        dashboardListModel.setGetTotalFund("18");
////        arrDashBoardList.add(dashboardListModel);
//        displayData();
        return view;
    }

    private void apiCallDashboardFragment() {
        LoadingDialog.getInstance().show(thisContext);
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("user_id" , BaseClass.userID);
        try {
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.apiCaller(EndPoints.KGetDashboardDetail, mapParam, thisContext, new APIResponse() {
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

//    private void displayData() {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(thisContext);
//        dashboardListAdapter = new DashboardListAdapter(thisContext, arrDashBoardList);
//        rvDashboardList.setLayoutManager(layoutManager);
//        rvDashboardList.setAdapter(dashboardListAdapter);
//
//    }


}