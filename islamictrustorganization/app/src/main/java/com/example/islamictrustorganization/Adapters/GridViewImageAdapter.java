package com.example.islamictrustorganization.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.islamictrustorganization.BaseClass;
import com.example.islamictrustorganization.Controllers.ZoomActivity;
import com.example.islamictrustorganization.Models.GridViewImageModel;
import com.example.islamictrustorganization.R;

import java.util.ArrayList;
import java.util.HashMap;

public class GridViewImageAdapter extends RecyclerView.Adapter<GridViewImageAdapter.ViewHolder>{
    Context context;

    ArrayList<GridViewImageModel> arrUpdateProjectImg;

    public GridViewImageAdapter(Context context, ArrayList<GridViewImageModel> arrProjectImage) {
        this.context = context;
        this.arrUpdateProjectImg = arrProjectImage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_project_detail_image, parent, false);
        return new GridViewImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GridViewImageModel gridViewImageModel = arrUpdateProjectImg.get(position);

        if (gridViewImageModel.isFlagIsVideo() == true) {
            Log.d("Update", "Video URL ===== "+ gridViewImageModel.getImgURL());
            String serverVideoPath = gridViewImageModel.getImgURL();

            RequestOptions options = new RequestOptions().frame(2000);
            Glide.with(context).load(serverVideoPath).apply(options).into(holder.imgUpdateProjectDetail);

        }else {
            Log.d("Update", "Image URL ===== "+ gridViewImageModel.getImgURL());
            Glide.with(context).asBitmap().load(gridViewImageModel.getImgURL()).into(holder.imgUpdateProjectDetail);
        }

        holder.cmdUpdateProjectDetailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseClass.selectedUpdateDetailImageURL = gridViewImageModel.getImgURL();
                Intent intent = new Intent(context, ZoomActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
       return arrUpdateProjectImg.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout cmdUpdateProjectDetailImage;
        ImageView imgUpdateProjectDetail;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cmdUpdateProjectDetailImage = itemView.findViewById(R.id.cmd_update_project_detail_image);
            imgUpdateProjectDetail = itemView.findViewById(R.id.img_update_project_detail);


        }
    }
}
