package com.itodonor.app.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.itodonor.app.Adapters.FragmentProjectListAdapter;
import com.itodonor.app.BaseClass;
import com.itodonor.app.Interfaces.APIResponse;
import com.itodonor.app.LoadingDialog;
import com.itodonor.app.Models.FragmentProjectListModel;

import com.itodonor.app.R;
import com.itodonor.app.ServiceManager.EndPoints;
import com.itodonor.app.ServiceManager.ServiceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OnGoingProjectFragment extends Fragment {
    View view;
    RecyclerView rvOnGoingProjectList;
    FragmentProjectListAdapter projectListAdapter;
    ArrayList<FragmentProjectListModel> arrOnGoingProjects = new ArrayList<>();
    Context thisContext;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_on_going_project, container, false);
        rvOnGoingProjectList = view.findViewById(R.id.on_going_project_fragment);

        thisContext = view.getContext();
        apiCallGetOnGoingProjects();

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiCallGetOnGoingProjects();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        return view;
    }

    private void apiCallGetOnGoingProjects() {

        if (arrOnGoingProjects.size() > 0){
            for (int i=0; i<arrOnGoingProjects.size(); i++){
                arrOnGoingProjects.remove(i);
            }
        }

        LoadingDialog.getInstance().show(thisContext);
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("user_id" , BaseClass.userID);
        try {
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.apiCaller(EndPoints.kGetOnGoingProjects, mapParam, thisContext, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    try {
                        JSONArray arrCompletedProjectList = response.getJSONArray("data");
                        for (int i = 0 ; i < arrCompletedProjectList.length() ; i++ ){
                            JSONObject dictCompleteProject = arrCompletedProjectList.getJSONObject(i);
                            FragmentProjectListModel fragmentProjectListModel = new FragmentProjectListModel();
                            fragmentProjectListModel.setProjectID(dictCompleteProject.getInt("id"));
                            fragmentProjectListModel.setProjectName(dictCompleteProject.getString("name"));
                            fragmentProjectListModel.setProjectCast((dictCompleteProject.isNull("cost"))?"0":dictCompleteProject.getInt("cost") + "");
                            fragmentProjectListModel.setImgProjectLogoURL(dictCompleteProject.getString("image"));
                            arrOnGoingProjects.add(fragmentProjectListModel);
                        }
                        displayData();
                        LoadingDialog.getInstance().dismiss();
                    } catch (JSONException e) {
                        LoadingDialog.getInstance().dismiss();
                        throw new RuntimeException(e);
                    }

                }

                @Override
                public void onError(String error) {
                    LoadingDialog.getInstance().dismiss();
                    Log.d("API", "onError: " + error);
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
        LinearLayoutManager outManager = new LinearLayoutManager(thisContext);
        projectListAdapter = new FragmentProjectListAdapter(thisContext, arrOnGoingProjects);
        rvOnGoingProjectList.setLayoutManager(new LinearLayoutManager(thisContext));
        rvOnGoingProjectList.setAdapter(projectListAdapter);

    }
}