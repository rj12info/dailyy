package com.example.m02264.dailyy.network;

import com.google.gson.JsonObject;

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
