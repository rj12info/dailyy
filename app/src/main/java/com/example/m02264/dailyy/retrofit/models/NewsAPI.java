package com.example.m02264.dailyy.retrofit.models;

import com.google.gson.JsonObject;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Jayanth on 18/10/15.
 */
public interface NewsAPI {
     interface NewsItems{
         @GET("/")
         JsonObject getNewsItems(@Query("type")String type, @Query("query") String querType);
     }

    interface NewsAPIHits{
        @GET("/")
        JsonObject getNewsItems(@Query("type")String type, @Query("query") String querType);
    }
}
