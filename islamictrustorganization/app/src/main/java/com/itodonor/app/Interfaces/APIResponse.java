package com.itodonor.app.Interfaces;

import org.json.JSONObject;

public interface APIResponse {

    public void onSuccess(JSONObject response);
    public void onError(String error);
    public void onStart();
}
