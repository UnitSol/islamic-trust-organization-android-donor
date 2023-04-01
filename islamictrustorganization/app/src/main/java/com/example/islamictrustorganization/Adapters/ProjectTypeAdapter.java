package com.example.islamictrustorganization.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamictrustorganization.BaseClass;
import com.example.islamictrustorganization.Models.ProjectTypeModel;
import com.example.islamictrustorganization.NotificationCenter.NotificationCenter;
import com.example.islamictrustorganization.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ProjectTypeAdapter extends RecyclerView.Adapter<ProjectTypeAdapter.ViewHolder> {
    Context context;
    ArrayList<ProjectTypeModel> arrProjectType;

    public ProjectTypeAdapter(Context context, ArrayList<ProjectTypeModel> arrProjectType) {
        this.context = context;
        this.arrProjectType = arrProjectType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_select_project_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        context = holder.itemView.getContext();
        ProjectTypeModel projectTypeModel = arrProjectType.get(position);
        holder.lblTitle.setText(projectTypeModel.getProjectTypeName());
        holder.cmdTitle.setOnClickListener(view -> {

            HashMap<String, String> params = new HashMap<String, String>();
            params.put("isSuccess", String.valueOf(false));
            BaseClass.SelectedProjectType = projectTypeModel.getProjectTypeName();
            BaseClass.SelectedProjectTypeID = String.valueOf(projectTypeModel.getProjectTypeID());

            NotificationCenter.postNotification(context, NotificationCenter.NotificationType.PROJECT_TYPE, params);
        });
    }

    @Override
    public int getItemCount() {
        return arrProjectType.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView lblTitle;
        RelativeLayout cmdTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lblTitle = itemView.findViewById(R.id.lbl_title);
            cmdTitle = itemView.findViewById(R.id.cmd_title);

        }
    }
}
