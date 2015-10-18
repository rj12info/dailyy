package com.example.m02264.dailyy;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m02264.dailyy.adapters.NewsListAdapter;
import com.example.m02264.dailyy.retrofit.models.Articles;
import com.example.m02264.dailyy.retrofit.models.NewsItems;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.m02264.dailyy.network.NewsAPIRequest;
import com.example.m02264.dailyy.network.NewsRequests;

public class MainActivity extends DailyBaseActivity {

    @Bind(R.id.home_recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.newSearch)
    EditText newSearchEditText;
    @Bind(R.id.apiHits)
    TextView newsApiHits;
    private  NewsListAdapter newsListAdapter=null;
    private NewsItems mNewsItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        newSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                if(null != newsListAdapter) {
                    List<Articles>articles = mNewsItems.getArticles();
                    final List<Articles> filteredModelList = filter(articles, cs.toString());
                    newsListAdapter.animateTo(filteredModelList);
                    mRecyclerView.scrollToPosition(0);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private List<Articles> filter(List<Articles> models, String query) {
        query = query.toLowerCase();

        final List<Articles> filteredModelList = new ArrayList<>();
        for (Articles model : models) {
            final String newsText = model.getTitle().toLowerCase();
            final String catText = model.getCategory().toLowerCase();
            if (newsText.contains(query) || catText.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    protected void onStart() {
        super.onStart();
        String query, path, path2;
        query="json";path="list_news";
        path2="api_hits";
        NewsRequests requests1 = new NewsRequests(query, path);
        NewsAPIRequest requests2= new NewsAPIRequest(query, path2);
        spiceManager.removeAllDataFromCache();
        spiceManager.execute(requests1, "news_test", DurationInMillis.ONE_MINUTE, new RequestListener<JsonObject>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(MainActivity.this, "Please come back after some time", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestSuccess(JsonObject jsonObject) {
                Log.d("json", "" + jsonObject);
                Gson gson = new GsonBuilder().create();
                NewsItems newsItems = gson.fromJson(jsonObject, NewsItems.class);
                buildUI(newsItems);
            }
        });

        spiceManager.execute(requests2, "news_api_test", DurationInMillis.ONE_MINUTE, new RequestListener<JsonObject>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                newsApiHits.setText("API Hits - Unable to serve");
            }

            @Override
            public void onRequestSuccess(JsonObject jsonObject) {
                String apiHits = jsonObject.get("api_hits").getAsString();
                newsApiHits.setText("API Hits - "+apiHits);
            }
        });

        spiceManager.execute(requests1, "news_test", DurationInMillis.ONE_MINUTE, new RequestListener<JsonObject>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(MainActivity.this, "Please come back after some time", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestSuccess(JsonObject jsonObject) {
                Log.d("json", "" + jsonObject);
                Gson gson = new GsonBuilder().create();
                NewsItems newsItems = gson.fromJson(jsonObject, NewsItems.class);
                buildUI(newsItems);
            }
        });


    }

    private void buildUI(NewsItems newsItems ) {
        newsListAdapter = new NewsListAdapter(newsItems,this);
        this.mNewsItems = newsItems;
        mRecyclerView.setAdapter(newsListAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(mRecyclerView);
    }
}
