package com.example.islamictrustorganization.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamictrustorganization.Models.ProjectDetailListModel;
import com.example.islamictrustorganization.R;

import java.util.ArrayList;

public class ProjectDetailListAdapter extends RecyclerView.Adapter<ProjectDetailListAdapter.ViewHolder> {
    Context context;

    public ProjectDetailListAdapter(Context context, ArrayList<ProjectDetailListModel> arrProjectDetail) {
        this.context = context;
        this.arrProjectDetail = arrProjectDetail;
    }

    ArrayList<ProjectDetailListModel> arrProjectDetail;

    @NonNull
    @Override
    public ProjectDetailListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_list_item, parent, false);
        return new ProjectDetailListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectDetailListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
