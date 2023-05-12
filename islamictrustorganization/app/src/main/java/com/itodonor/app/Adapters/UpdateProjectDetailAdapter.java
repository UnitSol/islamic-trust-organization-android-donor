package com.itodonor.app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itodonor.app.BaseClass;
import com.itodonor.app.Controllers.ProjectUpdateDetailActivity;
import com.itodonor.app.Models.UpdateProjectDetailModel;
import com.itodonor.app.R;

import java.util.ArrayList;

public class UpdateProjectDetailAdapter extends RecyclerView.Adapter<UpdateProjectDetailAdapter.ViewHolder>{
    Context context;

    ArrayList<UpdateProjectDetailModel> arrUpdateProjectDetail;

    public UpdateProjectDetailAdapter(Context context, ArrayList<UpdateProjectDetailModel> arrProjectDetail) {
        this.context = context;
        this.arrUpdateProjectDetail = arrProjectDetail;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_list_item, parent, false);
        return new UpdateProjectDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UpdateProjectDetailModel projectListModel = arrUpdateProjectDetail.get(position);
        holder.lblUpdateTitle.setText(projectListModel.getUpdateName());
        holder.lblDate.setText(projectListModel.getUpdateDate());
        holder.cmdProject.setOnClickListener(view->{

            BaseClass.selectedUpdateProjectID = String.valueOf(projectListModel.getUpdateID());
            BaseClass.selectedUpdateProjectName = String.valueOf(projectListModel.getUpdateName());
            BaseClass.selectedUpdateProjectDate = String.valueOf(projectListModel.getUpdateDate());
            BaseClass.selectedUpdateProjectDescription = String.valueOf(projectListModel.getUpdateDescription());
            Intent intent = new Intent(context, ProjectUpdateDetailActivity.class);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return arrUpdateProjectDetail.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView lblUpdateTitle, lblDate;
        LinearLayout cmdProject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cmdProject = itemView.findViewById(R.id.cmd_project_detail_list);
            lblUpdateTitle = itemView.findViewById(R.id.lbl_update_title);
            lblDate = itemView.findViewById(R.id.lbl_date);

        }
    }
}
