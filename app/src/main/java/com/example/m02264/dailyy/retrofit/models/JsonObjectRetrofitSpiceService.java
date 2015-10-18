package com.example.m02264.dailyy.retrofit.models;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.retrofit.RetrofitObjectPersisterFactory;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;
import com.octo.android.robospice.retrofit.RetrofitSpiceService;

import java.io.File;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

/**
 * Created by m02264 on 18/10/15.
 */
public class JsonObjectRetrofitSpiceService extends RetrofitSpiceService {
    private RequestInterceptor requestInterceptor;
    private Client client;

    private final static String BASE_URL = "https://dailyhunt.0x10.info/api/dailyhunt";
    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        RestAdapter.Builder restAdapter = super.createRestAdapterBuilder();
        restAdapter.setClient(getClient());
        return restAdapter;
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }

    @Override
    protected Converter createConverter() {
        return setUpCustomGson();
    }

    private Converter setUpCustomGson() {
        Gson gson = new GsonBuilder()
                .create();
        return new GsonConverter(gson);
    }

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager cacheManager = new CacheManager();
        cacheManager.addPersister(new RetrofitObjectPersisterFactory(application, getConverter(), getCacheFolder()));
        return cacheManager;
    }

    public File getCacheFolder() {
        return null;
    }

    private Client getClient() {
        if (client == null) {
            client = new OkClient();
        }
        return client;
    }

}
