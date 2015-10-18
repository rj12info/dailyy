package com.example.m02264.dailyy.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.m02264.dailyy.NewsDetailActivity;
import com.example.m02264.dailyy.R;
import com.example.m02264.dailyy.retrofit.models.Articles;
import com.example.m02264.dailyy.retrofit.models.NewsItems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by m02264 on 18/10/15.
 */
public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    NewsItems mNewsItems;
    Context mContext;
    List<Articles> mArticlesList;
    public NewsListAdapter(NewsItems newsItems, Context context) {
        this.mNewsItems = newsItems;
        this.mContext = context;
        mArticlesList = new ArrayList<>(mNewsItems.getArticles());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.news_items, parent, false);
        return new ItemsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemsViewHolder  itemsViewHolder= (ItemsViewHolder)holder;
        itemsViewHolder.newsText.setText(mArticlesList.get(position).getTitle());
        itemsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                Articles articles = mArticlesList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("news_item",articles);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
        Picasso.with(mContext).load(mArticlesList.get(position).getImage()).into(itemsViewHolder.newsImg);
    }

    @Override
    public int getItemCount() {
        return mArticlesList.size();
    }

    class ItemsViewHolder extends RecyclerView.ViewHolder{

        TextView newsText;
        ImageView newsImg;
        public ItemsViewHolder(View itemView) {
            super(itemView);
            newsText = ButterKnife.findById(itemView,R.id.newsText);
            newsImg = ButterKnife.findById(itemView,R.id.thumbImg);
        }
    }

    public void animateTo(List<Articles> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Articles> newModels) {
        for (int i = mArticlesList.size() - 1; i >= 0; i--) {
            final Articles model = mArticlesList.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Articles> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Articles model = newModels.get(i);
            if (!mArticlesList.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Articles> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Articles model = newModels.get(toPosition);
            final int fromPosition = mArticlesList.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Articles removeItem(int position) {
        final Articles model = mArticlesList.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Articles model) {
        mArticlesList.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Articles model = mArticlesList.remove(fromPosition);
        mArticlesList.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }


}
