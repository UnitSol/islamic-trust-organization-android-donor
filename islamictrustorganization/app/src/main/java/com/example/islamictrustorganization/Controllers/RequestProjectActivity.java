package com.example.islamictrustorganization.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.islamictrustorganization.BaseClass;
import com.example.islamictrustorganization.Helpers.UserHelper;
import com.example.islamictrustorganization.Interfaces.APIResponse;
import com.example.islamictrustorganization.LoadingDialog;
import com.example.islamictrustorganization.NotificationCenter.NotificationCenter;
import com.example.islamictrustorganization.R;
import com.example.islamictrustorganization.ServiceManager.EndPoints;
import com.example.islamictrustorganization.ServiceManager.ServiceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestProjectActivity extends AppCompatActivity {

    ImageView goToMyProjects;
    RelativeLayout rlSelectProjectType;
    TextView txtProjectType;
    EditText txtRequestName , txtRequestCost;
    Button btnRequestProject;
    private BroadcastReceiver didSelectedProjectType = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            txtProjectType.setText(BaseClass.SelectedProjectType);
            Log.e("TAG", "onReceive: " + BaseClass.SelectedProjectType);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_project);
        getSupportActionBar().hide();
        initUI();
        NotificationCenter.addObserver(RequestProjectActivity.this, NotificationCenter.NotificationType.PROJECT_TYPE, didSelectedProjectType);
        btnRequestProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiCallingForRequestProject();
            }
        });
    }

    private void apiCallingForRequestProject() {
        LoadingDialog.getInstance().show(this);
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("project_type_id", BaseClass.SelectedProjectTypeID);
        mapParams.put("user_id", BaseClass.userID);
        mapParams.put("name", txtRequestName.getText().toString());
        mapParams.put("cost", txtRequestCost.getText().toString());

        try {

            ServiceManager serviceManager = new ServiceManager();

            serviceManager.apiCaller(EndPoints.kDonnorRequest, mapParams, RequestProjectActivity.this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {

                    Log.d("API", "Success API ==== " + response.toString());
                    LoadingDialog.getInstance().dismiss();
//                    try {
//
//                        JSONObject dictUser = response.getJSONObject("data");
//                        BaseClass.userID = String.valueOf(dictUser.getInt("id"));
//                        BaseClass.userName = dictUser.getString("name");
//                        UserHelper.setLoggedInUserData(LogInActivity.this, dictUser.toString());
//
//                        Intent intent = new Intent(LogInActivity.this, DashBoardActivity.class);
//                        startActivity(intent);
//                        finish();
//
//
//
//                    } catch (JSONException e) {
//                        LoadingDialog.getInstance().dismiss();
//                        throw new RuntimeException(e);
//                    }

                }

                @Override
                public void onError(String error) {
                    LoadingDialog.getInstance().dismiss();

                    try {
                        JSONObject dictError = new JSONObject(error);
                        Toast.makeText(RequestProjectActivity.this, dictError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    Log.d("API", "Error API ==== " + error);
                }

                @Override
                public void onStart() {
                    Log.d("API", "Started Calling API");
                }
            });
            //Toast.makeText(TestAPIActivity.this, "POST API called", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        goToMyProjects = findViewById(R.id.go_to_my_projects);
        goToMyProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rlSelectProjectType = findViewById(R.id.rl_select_project_type);
        rlSelectProjectType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestProjectActivity.this , SelectProjectTypeActivity.class);
                startActivity(intent);
            }
        });
        txtProjectType = findViewById(R.id.txt_project_type);
        txtRequestName = findViewById(R.id.txt_request_name);
        txtRequestCost = findViewById(R.id.txt_request_cost);
        btnRequestProject = findViewById(R.id.btn_request_project);
    }
}