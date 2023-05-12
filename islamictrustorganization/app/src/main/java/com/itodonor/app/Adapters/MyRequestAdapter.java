package com.itodonor.app.Adapters;

import
        android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itodonor.app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyRequestAdapter extends RecyclerView.Adapter<MyRequestAdapter.ViewHolder> {
    Context mContext;

    ArrayList<JSONObject> arrRequests;

    public MyRequestAdapter(Context mContext, ArrayList<JSONObject> arrRequestsParam) {
        this.mContext = mContext;
        this.arrRequests = arrRequestsParam;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_my_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        JSONObject dictRequest = arrRequests.get(position);
        try {
            holder.lblProjectName.setText(dictRequest.getString("name"));
            holder.lblProjectType.setText(dictRequest.getJSONObject("projecType").getString("name"));
            //holder.lblCountry.setText(dictRequest.getString("name"));
            holder.lblDonnorType.setText(dictRequest.getJSONObject("user").getString("donor_type"));
            holder.lblCost.setText(dictRequest.getString("cost"));
//            holder.lblVillage.setText(dictRequest.getString("village"));
//            holder.lblTaluk.setText(dictRequest.getString("taluk"));
            holder.lblRequestStatus.setText(dictRequest.getString("status"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
//        "id": 6,
//                "name": "hfgg",
//                "village": "grgg",
//                "taluk": "gdgg",
//                "district": "hrtgg",
//                "state": "bfgghh",
//                "status": "pending",
//                "lat": "25.600224161849145",
//                "lon": "55.578203201293945",
//                "address": "JH2H+59X - Old Umm Al Quwain - Umm Al Quawain - United Arab Emirates",
//                "user": {
//            "id": 25,
//                    "name": "Muhammad Arslan",
//                    "username": null,
//                    "email": "arslangfcm@gmail.com",
//                    "contact_number": "+923218588600",
//                    "address": "Test address",
//                    "image": "https:\/\/islamic-trust.bhattimobiles.com\/storage\/app\/public\/user\/111679912830.jpg",
//                    "created_at": "2023-03-27T08:12:40.000000Z"
//        },
//        "projecType": {
//            "id": 6,
//                    "user_id": 1,
//                    "project_category_id": 5,
//                    "name": "1No",
//                    "code": "1No",
//                    "price": 0,
//                    "description": "53 Musalli, 439 Sq.Ft \/ 40.80 sq.mt",
//                    "created_at": "2023-03-02T09:31:16.000000Z",
//                    "updated_at": "2023-03-02T09:31:16.000000Z",
//                    "deleted_at": null
//        }

    }

    @Override
    public int getItemCount() {
        return arrRequests.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgRequest;
        TextView lblProjectName;
        TextView lblProjectType;
        TextView lblCountry;
        TextView lblDonnorType;
        TextView lblCost;
        TextView lblVillage;
        TextView lblTaluk;
        TextView lblRequestStatus;
        RelativeLayout cmdProject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cmdProject = itemView.findViewById(R.id.cmd_project_list);
            imgRequest = itemView.findViewById(R.id.img_request);
            lblProjectName = itemView.findViewById(R.id.lbl_project_name);
            lblProjectType = itemView.findViewById(R.id.lbl_project_type);
//            lblCountry = itemView.findViewById(R.id.lbl_country);
            lblDonnorType = itemView.findViewById(R.id.lbl_donnor_type);
            lblCost = itemView.findViewById(R.id.lbl_cost);
            lblVillage = itemView.findViewById(R.id.lbl_village);
            lblTaluk = itemView.findViewById(R.id.lbl_taluk);
            lblRequestStatus = itemView.findViewById(R.id.lbl_request_status);

        }
    }
}
