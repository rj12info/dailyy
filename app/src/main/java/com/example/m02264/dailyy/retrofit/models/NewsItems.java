package com.example.m02264.dailyy.retrofit.models;

import java.util.ArrayList;

/**
 * Created by Jayanth on 18/10/15.
 */

public class NewsItems
{
    private ArrayList<Articles> articles;

    public ArrayList<Articles> getArticles ()
    {
        return articles;
    }

    public void setArticles (ArrayList<Articles> articles)
    {
        this.articles = articles;
    }
}

