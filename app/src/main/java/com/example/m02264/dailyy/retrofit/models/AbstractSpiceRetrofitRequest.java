package com.example.m02264.dailyy.retrofit.models;

import com.google.gson.JsonObject;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Jayanth on 18/10/15.
 */
public abstract class AbstractSpiceRetrofitRequest<U> extends RetrofitSpiceRequest<JsonObject, U> {
    public AbstractSpiceRetrofitRequest(Class<U> clazz) {
        super(JsonObject.class, clazz);
    }
}
