package com.example.islamictrustorganization.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamictrustorganization.Adapters.FragmentProjectListAdapter;
import com.example.islamictrustorganization.Models.FragmentProjectListModel;
import com.example.islamictrustorganization.R;

import java.util.ArrayList;

public class OnGoingProjectFragment extends Fragment {
    View view;
    RecyclerView rvOnGoingProjectList;
    FragmentProjectListAdapter projectListAdapter;
    ArrayList<FragmentProjectListModel> arrCompleteProjectList = new ArrayList<>();
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_on_going_project, container, false);
        rvOnGoingProjectList = view.findViewById(R.id.on_going_project_fragment);

        mContext = getContext();
        FragmentProjectListModel fragmentProjectListModel = new FragmentProjectListModel();
        fragmentProjectListModel.setProjectID(1);
        fragmentProjectListModel.setProjectName("Project Name");
        fragmentProjectListModel.setProjectCast("5873");
        arrCompleteProjectList.add(fragmentProjectListModel);
        fragmentProjectListModel.setProjectID(1);
        fragmentProjectListModel.setProjectName("Project Name");
        fragmentProjectListModel.setProjectCast("51113");
        arrCompleteProjectList.add(fragmentProjectListModel);
        fragmentProjectListModel.setProjectID(1);
        fragmentProjectListModel.setProjectName("Project Name");
        fragmentProjectListModel.setProjectCast("5883");
        arrCompleteProjectList.add(fragmentProjectListModel);

        displayData();

        return view;
    }
    private void displayData() {
        LinearLayoutManager outManager = new LinearLayoutManager(mContext);
        projectListAdapter = new FragmentProjectListAdapter(mContext, arrCompleteProjectList);
        rvOnGoingProjectList.setLayoutManager(new LinearLayoutManager(mContext));
        rvOnGoingProjectList.setAdapter(projectListAdapter);

    }
}