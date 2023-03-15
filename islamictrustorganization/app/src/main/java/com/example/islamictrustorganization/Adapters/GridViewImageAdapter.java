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
            Log.d("TAG", "onBindViewHolder: Video is comming");
            String serverVideoPath = gridViewImageModel.getImgURL();

// create a new thread to avoid blocking the UI thread
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // create a MediaMetadataRetriever object
                        MediaMetadataRetriever retriever = new MediaMetadataRetriever();

                        // set the data source to the server video path
                        retriever.setDataSource(serverVideoPath, new HashMap<String, String>());

                        // get the thumbnail bitmap
                        Bitmap thumbnail = retriever.getFrameAtTime(2);

                        // do something with the thumbnail bitmap, such as displaying it in an ImageView
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
//                                ImageView thumbnailImageView = findViewById(R.id.thumbnailImageView);
//                                thumbnailImageView.setImageBitmap(thumbnail);0
                                holder.imgUpdateProjectDetail.setImageBitmap(thumbnail);
                            }
                        });

                        // release the MediaMetadataRetriever object
                        retriever.release();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }else {
            Log.d("TAG", "onBindViewHolder: Image is comming");
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
