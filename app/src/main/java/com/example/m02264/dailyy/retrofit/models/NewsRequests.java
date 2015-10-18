package com.example.m02264.dailyy.retrofit.models;

import com.google.gson.JsonObject;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import org.json.JSONObject;

/**
 * Created by Jayanth on 18/10/15.
 */
public class NewsRequests extends AbstractSpiceRetrofitRequest<NewsAPI.NewsItems> {
    String type,query;
    public NewsRequests(String type, String query) {
        super(NewsAPI.NewsItems.class);
        this.type = type;
        this.query = query;
    }

    @Override
    public JsonObject loadDataFromNetwork() throws Exception {
        return getService().getNewsItems(type,query);
    }
}
