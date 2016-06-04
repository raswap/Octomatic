// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.example.raswap.octomatic;


public class News_data_provider
{

    private int headline_img_resource;
    private String headlines;

    public News_data_provider(int i, String s)
    {
        setHeadline_img_resource(i);
        setHeadlines(s);
    }

    public int getHeadline_img_resource()
    {
        return headline_img_resource;
    }

    public String getHeadlines()
    {
        return headlines;
    }

    public void setHeadline_img_resource(int i)
    {
        headline_img_resource = i;
    }

    public void setHeadlines(String s)
    {
        headlines = s;
    }
}
