package com.example.m02264.dailyy.retrofit.models;

import java.io.Serializable;

/**
 * Created by Jayanth on 18/10/15.
 */
public class Articles implements Serializable
{
    private String content;

    private String category;

    private String title;

    private String source;

    private String image;

    private String url;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getSource ()
    {
        return source;
    }

    public void setSource (String source)
    {
        this.source = source;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [content = "+content+", category = "+category+", title = "+title+", source = "+source+", image = "+image+", url = "+url+"]";
    }
}