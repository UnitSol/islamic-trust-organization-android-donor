package com.example.islamictrustorganization.Controllers;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.islamictrustorganization.BaseClass;
import com.example.islamictrustorganization.Helpers.AppHelper;
import com.example.islamictrustorganization.Helpers.UserHelper;
import com.example.islamictrustorganization.Interfaces.APIResponse;
import com.example.islamictrustorganization.LoadingDialog;
import com.example.islamictrustorganization.NotificationCenter.NotificationCenter;
import com.example.islamictrustorganization.R;
import com.example.islamictrustorganization.ServiceManager.EndPoints;
import com.example.islamictrustorganization.ServiceManager.MySingleton;
import com.example.islamictrustorganization.ServiceManager.ServiceManager;
import com.example.islamictrustorganization.ServiceManager.VolleyMultipartRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserProfileActivity extends AppCompatActivity {
    ImageView goToMore , imgUserProfile;

    EditText txtProfileUserName , txtProfileUserEmail , txtProfileUserPhone , txtProfileUserAdress;

    Button btnSubmitInfo;

    Uri imgUri;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    private Bitmap bitmap;
    private File destination = null;
    private InputStream inputStreamImg;
    private String imgPath = null;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().hide();
        initialUI();
        imgUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //imageChooser();
                selectImage();
            }
        });
        goToMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        try {
            JSONObject dictUser = new JSONObject(UserHelper.getLoggedInUserData(this));
            txtProfileUserName.setText(dictUser.getString("name"));
            txtProfileUserEmail.setText(dictUser.getString("email"));
            txtProfileUserPhone.setText(dictUser.getString("contact_number"));
            txtProfileUserAdress.setText(dictUser.getString("address"));

            if (!dictUser.isNull("image")) {
                Glide.with(this).load(dictUser.getString("image")).into(imgUserProfile);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        btnSubmitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtProfileUserEmail.getText().length() == 0) {
                    displayAlert("Error", "Please enter your email id.");
                } else if (isEmailValid(txtProfileUserEmail.getText().toString()) == false) {
                    displayAlert("Error", "Please enter a valid email id.");
                } else if (txtProfileUserName.getText().length() == 0) {
                    displayAlert("Error", "Please enter your password");
                } else if (txtProfileUserAdress.getText().length() == 0) {
                    displayAlert("Error", "Please enter your Address");
                }else if (txtProfileUserPhone.getText().length() == 0) {
                    displayAlert("Error", "Please enter your Mobile Number");
                }else {
                    apiCallUpdateProfile();
                }
            }
        });
    }

    private void apiCallUpdateProfile() {

        LoadingDialog.getInstance().show(this);
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("user_id", BaseClass.userID);
        mapParam.put("name", txtProfileUserName.getText().toString());
        mapParam.put("email", txtProfileUserEmail.getText().toString());
        mapParam.put("contact_number", txtProfileUserPhone.getText().toString());
        mapParam.put("address", txtProfileUserAdress.getText().toString());
        Log.w("API", "Post Params ==== " + mapParam.toString());

        String requestUrl = EndPoints.kUserProfileUpdate;

        VolleyMultipartRequest stringRequest = new VolleyMultipartRequest(Request.Method.POST, requestUrl, new com.android.volley.Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {

                String resultResponse = new String(response.data);
                Log.d("createActivityNew", "" + resultResponse);

                try {
                    JSONObject responseJsonObject = new JSONObject(resultResponse);
                    if (responseJsonObject.getBoolean("status")) {

                        LoadingDialog.getInstance().dismiss();
                        UserHelper.setLoggedInUserData(UserProfileActivity.this, responseJsonObject.getString("data"));

                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("isSuccess", String.valueOf(false));
                        NotificationCenter.postNotification(UserProfileActivity.this, NotificationCenter.NotificationType.PROFILE_UPDATED, params);

                        Toast.makeText(UserProfileActivity.this, "Profile Updated Successfully!", Toast.LENGTH_LONG).show();
                        //finish();

                    } else {
                        LoadingDialog.getInstance().dismiss();
                        Toast.makeText(UserProfileActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    LoadingDialog.getInstance().dismiss();
                }


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("createActivityNew", "ERROR ===== " + error.toString());
                error.printStackTrace();
                //LoadingDialog.getInstance().dismiss();
                LoadingDialog.getInstance().dismiss();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return mapParam;
            }

            @Override
            protected Map<String, VolleyMultipartRequest.DataPart> getByteData() {
                Map<String, VolleyMultipartRequest.DataPart> params = new HashMap<>();

                if (imgUri == null) {

                } else {
                    params.put("image", new VolleyMultipartRequest.DataPart(
                            "file.jpg", AppHelper.getFileDataFromUri(
                            getApplicationContext(),
                            imgUri
                    ), "image/jpeg"));
                }
                return params;
            }

        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(UserProfileActivity.this).addToRequestQueue(stringRequest);
    }
//
//    private void apiCallingUserProfileUpdate() {
//        LoadingDialog.getInstance().show(this);
//        Map<String , String> mapParam = new HashMap<>();
//        mapParam.put("user_id" , BaseClass.userID);
//        mapParam.put("name" , txtProfileUserName.getText().toString());
//        mapParam.put("email" , txtProfileUserEmail.getText().toString());
//        mapParam.put("contact_number" , txtProfileUserPhone.getText().toString());
//        mapParam.put("address" , txtProfileUserAdress.getText().toString());
//        try {
//            ServiceManager serviceManager = new ServiceManager();
//            serviceManager.apiCaller(EndPoints.kUserProfileUpdate, mapParam, UserProfileActivity.this, new APIResponse() {
//                @Override
//                public void onSuccess(JSONObject response) {
//                    LoadingDialog.getInstance().dismiss();
//                    Log.d("API", "Success API ==== "+ response.toString());
//
//                    try {
//                        UserHelper.setLoggedInUserData(UserProfileActivity.this , response.getJSONObject("data").toString());
//                    } catch (JSONException e) {
//                        throw new RuntimeException(e);
//                    }
//                    HashMap<String, String> params = new HashMap<String, String>();
//                    params.put("isSuccess", String.valueOf(false));
//                    NotificationCenter.postNotification(UserProfileActivity.this, NotificationCenter.NotificationType.PROFILE_UPDATED,params);
//
//                    Toast.makeText(UserProfileActivity.this, "Profile Update", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(UserProfileActivity.this , MoreActivity.class);
//                    startActivity(intent);
//                }
//
//                @Override
//                public void onError(String error) {
//                    LoadingDialog.getInstance().dismiss();
//                    Log.d("API", "Error API ==== "+ error);
//                }
//
//                @Override
//                public void onStart() {
//                    Log.d("API", "Started Calling API");
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        launchSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.i("TAG", ": " + selectedImageBitmap);
                        imgUserProfile.setImageBitmap(selectedImageBitmap);
                    }
                }
            });

    private void initialUI() {
        goToMore = findViewById(R.id.go_to_more);
        imgUserProfile = findViewById(R.id.img_user_profile);

        txtProfileUserName = findViewById(R.id.txt_profile_user_name);
        txtProfileUserEmail = findViewById(R.id.txt_profile_user_email);
        txtProfileUserPhone = findViewById(R.id.txt_profile_user_phone);
        txtProfileUserAdress = findViewById(R.id.txt_profile_user_adress);

        btnSubmitInfo = findViewById(R.id.btn_submit_info);
    }
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
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


    private void selectImage() {
        try {
            PackageManager pm = getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA);
                        } else if (options[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else {
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inputStreamImg = null;
        if (requestCode == PICK_IMAGE_CAMERA) {
            try {
                Uri selectedImage = data.getData();
                imgUri = selectedImage;
                bitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

                Log.e("Activity", "Pick from Camera::>>> ");

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                destination = new File(Environment.getExternalStorageDirectory() + "/" +
                        getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                imgPath = destination.getAbsolutePath();
                imgUserProfile.setImageBitmap(bitmap);

                Log.e("Activity", "Pick from camera URI >>> "+ imgUri);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_GALLERY) {
            Uri selectedImage = data.getData();
            imgUri = selectedImage;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                Log.e("Activity", "Pick from Gallery::>>> ");

                imgPath = getRealPathFromURI(selectedImage);
                destination = new File(imgPath.toString());
                imgUserProfile.setImageBitmap(bitmap);

                Log.e("Activity", "Pick from Gallery URI >>> "+ imgUri);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}