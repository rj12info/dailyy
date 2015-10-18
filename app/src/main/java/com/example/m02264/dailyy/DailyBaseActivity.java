package com.example.m02264.dailyy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.octo.android.robospice.SpiceManager;

import com.example.m02264.dailyy.network.JsonObjectRetrofitSpiceService;

/**
 * Created by Jayanth on 18/10/15.
 */
public class DailyBaseActivity extends AppCompatActivity {
    SpiceManager spiceManager = new SpiceManager(JsonObjectRetrofitSpiceService.class);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();

    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }
}
