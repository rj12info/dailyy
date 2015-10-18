package com.example.m02264.dailyy;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.m02264.dailyy.retrofit.models.Articles;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jayanth on 18/10/15.
 */
public class NewsDetailActivity extends DailyBaseActivity {
    @Bind(R.id.newsText)
    TextView newsText;
    @Bind(R.id.thumbImg)
    ImageView thumbmImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        ButterKnife.bind(this);
        Articles articles = (Articles)getIntent().getExtras().getSerializable("news_item");
        newsText.setText(articles.getContent());
        Picasso.with(this).load(articles.getImage()).into(thumbmImg);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
