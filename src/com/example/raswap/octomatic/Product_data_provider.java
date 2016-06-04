// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.example.raswap.octomatic;


public class Product_data_provider
{

    private String brand_name;
    private String pricing;
    private String product_name;
    private int product_pic;

    public Product_data_provider(int i, String s, String s1, String s2)
    {
        setProduct_pic(i);
        setProduct_name(s);
        setBrand_name(s1);
        setPricing(s2);
    }

    public String getBrand_name()
    {
        return brand_name;
    }

    public String getPricing()
    {
        return pricing;
    }

    public String getProduct_name()
    {
        return product_name;
    }

    public int getProduct_pic()
    {
        return product_pic;
    }

    public void setBrand_name(String s)
    {
        brand_name = s;
    }

    public void setPricing(String s)
    {
        pricing = s;
    }

    public void setProduct_name(String s)
    {
        product_name = s;
    }

    public void setProduct_pic(int i)
    {
        product_pic = i;
    }
}
