package com.example.m02264.dailyy.retrofit.models;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.json.JSONObject;

/**
 * Created by Jayanth on 18/10/15.
 */
public class NewsRequestListener implements RequestListener<JSONObject> {
    ServiceCallback serviceCallback;
    public NewsRequestListener(ServiceCallback serviceCallback){
        this.serviceCallback = serviceCallback;
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        serviceCallback.onFailure(spiceException);
    }

    @Override
    public void onRequestSuccess(JSONObject jsonObject) {
        serviceCallback.onSuccess(jsonObject);
    }
}
