package com.example.islamictrustorganization.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.islamictrustorganization.Models.ProjectListModel;
import com.example.islamictrustorganization.ProjectDetailActivity;
import com.example.islamictrustorganization.R;

import java.util.ArrayList;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {

    Context context;
    ArrayList<ProjectListModel> arrProjectList;

    public ProjectListAdapter(Context context, ArrayList<ProjectListModel> arrProjectList) {
        this.context = context;
        this.arrProjectList = arrProjectList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_in_progress_projects, parent, false);
        return new ProjectListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProjectListModel projectListModel = arrProjectList.get(position);
        holder.lblProjectName.setText(projectListModel.getProjectName());
        holder.lblProjectDonation.setText(projectListModel.getProjectDonation());
        Glide.with(context).load(projectListModel.getImgProjectLogoURL()).into(holder.imgProjectLogo);
        holder.cmdProject.setOnClickListener(view->{
            Intent intent = new Intent(context, ProjectDetailActivity.class);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return arrProjectList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProjectLogo;
        TextView lblProjectName, lblProjectDonation;
        LinearLayout cmdProject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cmdProject = itemView.findViewById(R.id.cmd_project_list);
            imgProjectLogo = itemView.findViewById(R.id.img_project_logo);
            lblProjectName = itemView.findViewById(R.id.lbl_project_name);
            lblProjectDonation = itemView.findViewById(R.id.lbl_project_donation);

        }
    }
}
