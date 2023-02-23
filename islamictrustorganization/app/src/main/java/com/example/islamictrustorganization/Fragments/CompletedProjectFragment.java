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

public class CompletedProjectFragment extends Fragment {
    View view;
    RecyclerView rvCompleteProjectList;
    FragmentProjectListAdapter projectListAdapter;
    ArrayList<FragmentProjectListModel> arrCompleteProjectList = new ArrayList<>();
    Context thisContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_completed_project, container, false);

        rvCompleteProjectList = view.findViewById(R.id.complete_project_list_fragment);
        FragmentProjectListModel fragmentProjectListModel = new FragmentProjectListModel();
        fragmentProjectListModel.setProjectID(1);
        fragmentProjectListModel.setProjectName("Project Name");
        fragmentProjectListModel.setProjectCast("5873");
        arrCompleteProjectList.add(fragmentProjectListModel);
        fragmentProjectListModel.setProjectID(1);
        fragmentProjectListModel.setProjectName("Project Name");
        fragmentProjectListModel.setProjectCast("5873");
        arrCompleteProjectList.add(fragmentProjectListModel);
        fragmentProjectListModel.setProjectID(1);
        fragmentProjectListModel.setProjectName("Project Name");
        fragmentProjectListModel.setProjectCast("5873");
        arrCompleteProjectList.add(fragmentProjectListModel);
        fragmentProjectListModel.setProjectID(1);
        fragmentProjectListModel.setProjectName("Project Name");
        fragmentProjectListModel.setProjectCast("5873");
        arrCompleteProjectList.add(fragmentProjectListModel);
        fragmentProjectListModel.setProjectID(1);
        fragmentProjectListModel.setProjectName("Project Name");
        fragmentProjectListModel.setProjectCast("5873");
        arrCompleteProjectList.add(fragmentProjectListModel);
        fragmentProjectListModel.setProjectID(1);
        fragmentProjectListModel.setProjectName("Project Name");
        fragmentProjectListModel.setProjectCast("5873");
        arrCompleteProjectList.add(fragmentProjectListModel);
        fragmentProjectListModel.setProjectID(1);
        fragmentProjectListModel.setProjectName("Project Name");
        fragmentProjectListModel.setProjectCast("5873");
        arrCompleteProjectList.add(fragmentProjectListModel);
        displayData();
        return view;
    }
    private void displayData() {
        LinearLayoutManager outManager = new LinearLayoutManager(thisContext);
        projectListAdapter = new FragmentProjectListAdapter(thisContext, arrCompleteProjectList);
        rvCompleteProjectList.setLayoutManager(new LinearLayoutManager(thisContext));
        rvCompleteProjectList.setAdapter(projectListAdapter);

    }
}