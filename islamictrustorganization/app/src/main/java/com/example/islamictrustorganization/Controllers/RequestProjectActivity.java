package com.example.islamictrustorganization.Controllers;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.islamictrustorganization.BaseClass;
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
    RelativeLayout rlSelectProjectType , rlSelectProjectCategory;
    TextView txtProjectType , txtProjectCategory;
    EditText txtRequestName , txtRequestCost , txtActualDonorName  ;
    Button btnRequestProject;
    private BroadcastReceiver didSelectedProjectType = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            txtProjectType.setText(BaseClass.SelectedProjectType);
            Log.e("TAG", "onReceive: " + BaseClass.SelectedProjectType);

        }
    };
    private BroadcastReceiver didSelectedProjectCategory = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            txtProjectCategory.setText(BaseClass.SelectedProjectCategory);
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
        NotificationCenter.addObserver(RequestProjectActivity.this, NotificationCenter.NotificationType.PROJECT_CATEGORY, didSelectedProjectCategory);
        btnRequestProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiCallSubmitNewProjectRequest();
            }
        });
    }

    private void apiCallSubmitNewProjectRequest() {

        if (BaseClass.SelectedProjectTypeID == null) {
            displayAlert("Error", "Please select project type.");
        }else if (txtRequestName.getText().length() == 0){
            displayAlert("Error", "Please enter project request name.");
        }else if(txtRequestCost.getText().length() == 0){
            displayAlert("Error", "Please enter project cost.");
        }else {
            LoadingDialog.getInstance().show(this);
            Map<String, String> mapParams = new HashMap<>();
            mapParams.put("project_type_id", BaseClass.SelectedProjectTypeID);
            mapParams.put("user_id", BaseClass.userID);
            mapParams.put("name", txtRequestName.getText().toString());
            mapParams.put("cost", txtRequestCost.getText().toString());
            mapParams.put("actual_donor_name", txtActualDonorName.getText().toString());
            try {

                ServiceManager serviceManager = new ServiceManager();

                serviceManager.apiCaller(EndPoints.kDonnorRequest, mapParams, RequestProjectActivity.this, new APIResponse() {
                    @Override
                    public void onSuccess(JSONObject response) {

                        Log.d("API", "Success API ==== " + response.toString());
                        LoadingDialog.getInstance().dismiss();

                        try {
                            if (response.getBoolean("status") == true){

                                AlertDialog alertDialog = new AlertDialog.Builder(RequestProjectActivity.this)
                                        .setTitle("Success")
                                        .setMessage("Your project request posted successfully.")
                                        .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                finish();

                                                HashMap<String, String> params = new HashMap<String, String>();
                                                params.put("isSuccess", String.valueOf(false));
                                                NotificationCenter.postNotification(RequestProjectActivity.this, NotificationCenter.NotificationType.NEW_REQUEST_POSTED, params);
                                            }
                                        }).show();

                            }else{
                                displayAlert("Server Error", "There is an issue from server side, please try again later.");
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

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
                if(BaseClass.SelectedProjectCategoryID == null){
                    Toast.makeText(RequestProjectActivity.this, "Please Select Project Category", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(RequestProjectActivity.this , SelectProjectTypeActivity.class);
                    startActivity(intent);
                }
            }
        });
        txtProjectType = findViewById(R.id.txt_project_type);
        txtProjectType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestProjectActivity.this , SelectProjectTypeActivity.class);
                startActivity(intent);
            }
        });

        txtRequestName = findViewById(R.id.txt_request_name);
        txtRequestCost = findViewById(R.id.txt_request_cost);
        btnRequestProject = findViewById(R.id.btn_request_project);
        rlSelectProjectCategory = findViewById(R.id.rl_select_project_category);
        txtActualDonorName = findViewById(R.id.txt_actual_donor_name);
        txtProjectCategory = findViewById(R.id.txt_project_category);
        rlSelectProjectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestProjectActivity.this , SelectProjectCategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    public void displayAlert(String title, String body) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(body)
                .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked

                    }
                }).show();
    }
}