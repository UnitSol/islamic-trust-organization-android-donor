package com.itodonor.app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.itodonor.app.BaseClass;
import com.itodonor.app.Controllers.ProjectDetailActivity;
import com.itodonor.app.Models.FragmentProjectListModel;
import com.itodonor.app.R;


import java.util.ArrayList;

public class FragmentProjectListAdapter extends RecyclerView.Adapter<FragmentProjectListAdapter.ViewHolder> {

    Context mContext;
    ArrayList<FragmentProjectListModel> arrProjectList;

    public FragmentProjectListAdapter(Context context, ArrayList<FragmentProjectListModel> arrProjectList) {
        this.mContext = context;
        this.arrProjectList = arrProjectList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_in_progress_projects, parent, false);
        return new FragmentProjectListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mContext = holder.itemView.getContext();
        FragmentProjectListModel projectListModel = arrProjectList.get(position);
        holder.lblProjectName.setText(projectListModel.getProjectName());
        holder.lblProjectCast.setText(projectListModel.getProjectCast());
        Glide.with(mContext).load(projectListModel.getImgProjectLogoURL()).circleCrop().into(holder.imgProjectLogo);
        holder.cmdProject.setOnClickListener(view->{
            BaseClass.selectedProjectID = String.valueOf(projectListModel.getProjectID());
            Intent intent = new Intent(mContext, ProjectDetailActivity.class);
            mContext.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return arrProjectList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProjectLogo;
        TextView lblProjectName, lblProjectCast;
        RelativeLayout cmdProject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cmdProject = itemView.findViewById(R.id.cmd_project_list);
            imgProjectLogo = itemView.findViewById(R.id.img_project_logo);
            lblProjectName = itemView.findViewById(R.id.lbl_project_name);
            lblProjectCast = itemView.findViewById(R.id.lbl_project_donation);

        }
    }
}
