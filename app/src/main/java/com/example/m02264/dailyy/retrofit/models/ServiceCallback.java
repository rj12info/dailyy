package com.example.m02264.dailyy.retrofit.models;

/**
 * Created by m02264 on 18/10/15.
 */
public interface ServiceCallback<T> {
    public void onSuccess(T t);
    public void onFailure(Exception e);
}
