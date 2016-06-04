// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.example.raswap.octomatic;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

// Referenced classes of package com.example.raswap.octomatic:
//            ProductAdapter, Product_data_provider

public class eshop extends AppCompatActivity
{

    ProductAdapter adapter;
    String brand_name[];
    int img[] = {
        0x7f02004e, 0x7f020051, 0x7f02004d
    };
    ListView listView;
    String price[];
    String product_name[];

    public eshop()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f040019);
        bundle = (ListView)findViewById(0x7f0d0050);
        product_name = getResources().getStringArray(0x7f0b0003);
        brand_name = getResources().getStringArray(0x7f0b0000);
        price = getResources().getStringArray(0x7f0b0002);
        adapter = new ProductAdapter(getApplicationContext(), 0x7f040029);
        bundle.setAdapter(adapter);
        int j = 0;
        bundle = product_name;
        int k = bundle.length;
        for (int i = 0; i < k; i++)
        {
            Object obj = bundle[i];
            obj = new Product_data_provider(img[j], ((String) (obj)), brand_name[j], price[j]);
            adapter.add(obj);
            j++;
        }

    }
}
