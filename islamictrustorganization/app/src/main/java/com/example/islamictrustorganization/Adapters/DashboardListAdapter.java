package com.example.islamictrustorganization.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamictrustorganization.Models.DashboardListModel;
import com.example.islamictrustorganization.R;

import java.util.ArrayList;

public class DashboardListAdapter extends RecyclerView.Adapter<DashboardListAdapter.ViewHolder>{
    Context context;
    ArrayList<DashboardListModel> arrDashboardFragmentList;

    public DashboardListAdapter(Context thisContext, ArrayList<DashboardListModel> arrDashboardFragmentList) {
        this.context = thisContext;
        this.arrDashboardFragmentList = arrDashboardFragmentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_dashboard_fragment_list, parent, false);
        return new DashboardListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DashboardListModel dashboardListModel = arrDashboardFragmentList.get(position);
        holder.lblTotalFund.setText(dashboardListModel.getTotalFund());
        holder.lblAmount.setText(dashboardListModel.getGetTotalFund());
//        Glide.with(context).load(dashboardListModel.getImageUrl()).into(holder.imgDashboardProjectDescription);
//        Glide.with(context).load(dashboardListModel.getImageUrl()).into(holder.imgProjectCornerImage);

    }

    @Override
    public int getItemCount() {
        return arrDashboardFragmentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDashboardProjectDescription , imgProjectCornerImage;
        TextView lblTotalFund, lblAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDashboardProjectDescription = itemView.findViewById(R.id.img_dashboard_project_description);
            lblTotalFund = itemView.findViewById(R.id.lbl_total_fund);
            lblAmount = itemView.findViewById(R.id.lbl_amount);
            imgProjectCornerImage = itemView.findViewById(R.id.img_project_corner_image);
        }
    }
}
