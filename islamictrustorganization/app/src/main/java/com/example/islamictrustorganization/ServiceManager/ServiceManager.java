package com.example.islamictrustorganization.ServiceManager;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.islamictrustorganization.Interfaces.APIResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class ServiceManager {
    public void apiCaller(String apiURL, Map<String, String> params, Context context, APIResponse apiResponse) {
        Log.d("API", "API URL [" + apiURL +"]");
        Log.d("API", "API Params [" + params.toString() +"]");
        if (apiResponse != null){
            apiResponse.onStart();
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, apiURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("API", "Response " + response);
                JSONObject dictData;
                try {
                    dictData = new JSONObject(response);
                    if ( apiResponse != null){
                        if (dictData.getBoolean("status") == true){
                            apiResponse.onSuccess(dictData);
                        }else{
                            apiResponse.onError(dictData.toString());
                        }
                    }
                } catch (JSONException err) {
                    apiResponse.onError(err.getMessage());
                    Log.d("Error", err.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Login", "ERROR ===== " + error.toString());
                error.printStackTrace();
                apiResponse.onError(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> mHeaders = new ArrayMap<String, String>();
//                mHeaders.put("Accept", "application/json");
//                mHeaders.put("Content-Type", "application/json");
                return super.getHeaders();
                //return mHeaders;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        ITOSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
