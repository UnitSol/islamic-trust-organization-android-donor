package com.example.islamictrustorganization.Controllers;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamictrustorganization.Adapters.SliderAdapter;
import com.example.islamictrustorganization.Adapters.UpdateProjectDetailAdapter;
import com.example.islamictrustorganization.BaseClass;
import com.example.islamictrustorganization.Interfaces.APIResponse;
import com.example.islamictrustorganization.LoadingDialog;
import com.example.islamictrustorganization.Models.SliderModel;
import com.example.islamictrustorganization.Models.UpdateProjectDetailModel;
import com.example.islamictrustorganization.R;
import com.example.islamictrustorganization.ServiceManager.EndPoints;
import com.example.islamictrustorganization.ServiceManager.ServiceManager;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectDetailActivity extends AppCompatActivity {
    TextView projectName, textStartDate, textEndDate, projectPrize, textStatus, textDescription, textReadMore;
    ImageView projectImage, goBackBtn;
    RecyclerView updateProjectList;
    SliderView imgCompleteProjectDetail;
    SliderAdapter sliderAdapter;
    UpdateProjectDetailAdapter newProjectAdapter;
    ArrayList<UpdateProjectDetailModel> arrUpdateProjects = new ArrayList<>();

    List<SliderModel> projectDetailSliderItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        getSupportActionBar().hide();
        initializing();
        apiCallingProjectDetail();
        apiCallingProjectUpdateList();

    }

    private void apiCallingProjectUpdateList() {
        LoadingDialog.getInstance().show(this);
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("project_id", BaseClass.selectedProjectID);
        try {
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.apiCaller(EndPoints.kGetProjectUpdate, mapParam, ProjectDetailActivity.this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("API", "Success API ==== " + response.toString());
                    try {
                        JSONArray arrProjectUpdateDetail = response.getJSONArray("data");
                        for(int i = 0 ; i<arrProjectUpdateDetail.length() ; i++){
                            JSONObject dictUpdateList = arrProjectUpdateDetail.getJSONObject(i);
                            UpdateProjectDetailModel projectListModel = new UpdateProjectDetailModel();

                            projectListModel.setUpdateID(dictUpdateList.getInt("id"));
                            projectListModel.setUpdateName(dictUpdateList.getString("name"));
                            projectListModel.setUpdateDate(dictUpdateList.getString("created_at"));
                            projectListModel.setUpdateDescription(dictUpdateList.getString("description"));
                            arrUpdateProjects.add(projectListModel);
                            displayListData();
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    LoadingDialog.getInstance().dismiss();
                }

                @Override
                public void onError(String error) {
                    LoadingDialog.getInstance().dismiss();
                    Log.d("API", "Error API ==== " + error);
                }

                @Override
                public void onStart() {
                    Log.d("API", "Started Calling API");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void displayListData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        newProjectAdapter = new UpdateProjectDetailAdapter(this, arrUpdateProjects);
        updateProjectList.setLayoutManager(layoutManager);
        updateProjectList.setAdapter(newProjectAdapter);
    }

    private void apiCallingProjectDetail() {
        LoadingDialog.getInstance().show(this);
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("project_id", BaseClass.selectedProjectID);
        try {
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.apiCaller(EndPoints.kGetProjectDetail, mapParam, ProjectDetailActivity.this, new APIResponse() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("API", "Success API ==== " + response.toString());
                    try {
                        JSONObject dictNEwProjectDetail = response.getJSONObject("data");
                        BaseClass.selectedProjectName = dictNEwProjectDetail.getString("name");
                        projectName.setText(dictNEwProjectDetail.getString("name"));
                        textStartDate.setText(dictNEwProjectDetail.getString("start_date"));
                        textEndDate.setText(dictNEwProjectDetail.getString("end_date"));
                        projectPrize.setText("$" + dictNEwProjectDetail.getInt("budget"));
                        textDescription.setText(dictNEwProjectDetail.getString("description"));
                        JSONArray arrImg = dictNEwProjectDetail.getJSONArray("images");
                        for (int i = 0; i < arrImg.length(); i++) {

                            sliderAdapter = new SliderAdapter();
                            SliderModel sliderModel = new SliderModel();
                            JSONObject dictImgNewProjectDetail = arrImg.getJSONObject(i);


                            sliderModel.setImgUrl(dictImgNewProjectDetail.getString("image"));
                            projectDetailSliderItem.add(sliderModel);
                            imgCompleteProjectDetail.setSliderAdapter(sliderAdapter);
                            displayData();
//                            imgMakeDonationProject.setIndicatorAnimationDuration(100);
                            imgCompleteProjectDetail.setSliderAnimationDuration(200);
                            imgCompleteProjectDetail.setAutoCycle(true);
                            imgCompleteProjectDetail.startAutoCycle();
                            imgCompleteProjectDetail.setScrollTimeInSec(3);
                        }

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    LoadingDialog.getInstance().dismiss();


                }

                @Override
                public void onError(String error) {
                    LoadingDialog.getInstance().dismiss();
                    Log.d("API", "Error API ==== " + error);
                }

                @Override
                public void onStart() {
                    Log.d("API", "Started Calling API");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void displayData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        sliderAdapter = new SliderAdapter( projectDetailSliderItem , this);
        imgCompleteProjectDetail.setSliderAdapter(sliderAdapter);
    }

    private void initializing() {
        projectName = findViewById(R.id.projectName);
        textStartDate = findViewById(R.id.textStartDate);
        textEndDate = findViewById(R.id.textEndDate);
        projectPrize = findViewById(R.id.projectPrize);
        textStatus = findViewById(R.id.textStatus);
        textDescription = findViewById(R.id.textDescription);
//        textReadMore = findViewById(R.id.textReadMore);

        imgCompleteProjectDetail = findViewById(R.id.img_complete_project_detail);
        goBackBtn = findViewById(R.id.goBackBtn);

        updateProjectList = findViewById(R.id.updateProjectList);
    }
}