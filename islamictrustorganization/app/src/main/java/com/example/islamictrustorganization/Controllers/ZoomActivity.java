package com.example.islamictrustorganization.Controllers;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.islamictrustorganization.BaseClass;
import com.example.islamictrustorganization.R;

public class ZoomActivity extends AppCompatActivity {
    ImageView zoomImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        getSupportActionBar().hide();
        initialUI();

    }

    private void initialUI() {
        zoomImage = findViewById(R.id.zoom_image);
        Glide.with(this).asBitmap().load(BaseClass.selectedUpdateDetailImageURL).into(zoomImage);
    }
}