package com.itodonor.app.Controllers;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.itodonor.app.BaseClass;
import com.itodonor.app.R;

public class ZoomActivity extends AppCompatActivity {
    ImageView zoomImage , zoomImageGoBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        getSupportActionBar().hide();
        initialUI();

    }

    private void initialUI() {
        zoomImage = findViewById(R.id.zoom_image);
        zoomImageGoBackBtn = findViewById(R.id.zoomImageGoBackBtn);
        Glide.with(this).asBitmap().load(BaseClass.selectedUpdateDetailImageURL).into(zoomImage);
        zoomImageGoBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}