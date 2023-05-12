package com.itodonor.app.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.itodonor.app.BaseClass;
import com.itodonor.app.R;


public class VedioPlayerActivity extends AppCompatActivity {

    ImageView zoomVideoGoBackBtn  ;
    WebView videoPlayerWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio_player);
        getSupportActionBar().hide();
        initaillizeUI();
    }

    private void initaillizeUI() {
        zoomVideoGoBackBtn = findViewById(R.id.zoom_video_go_back_btn);
        videoPlayerWebView = findViewById(R.id.video_player_web_view);
        zoomVideoGoBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        videoPlayerWebView.loadUrl(BaseClass.selectedUpdateDetailVideoURL);
    }
}