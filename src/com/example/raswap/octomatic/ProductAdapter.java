// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.example.raswap.octomatic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.example.raswap.octomatic:
//            Product_data_provider

public class ProductAdapter extends ArrayAdapter
{
    static class DataHandler
    {

        TextView b_name;
        ImageView img;
        TextView price;
        TextView pro_name;

        DataHandler()
        {
        }
    }


    List list;

    public ProductAdapter(Context context, int i)
    {
        super(context, i);
        list = new ArrayList();
    }

    public void add(Object obj)
    {
        list.add(obj);
    }

    public int getCount()
    {
        return list.size();
    }

    public Object getItem(int i)
    {
        return list.get(i);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        View view1 = view;
        if (view == null)
        {
            view1 = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f040029, viewgroup, false);
            view = new DataHandler();
            view.img = (ImageView)view1.findViewById(0x7f0d0067);
            view.pro_name = (TextView)view1.findViewById(0x7f0d0068);
            view.b_name = (TextView)view1.findViewById(0x7f0d0069);
            view.price = (TextView)view1.findViewById(0x7f0d006a);
            view1.setTag(view);
        } else
        {
            view = (DataHandler)view1.getTag();
        }
        viewgroup = (Product_data_provider)getItem(i);
        ((DataHandler) (view)).img.setImageResource(viewgroup.getProduct_pic());
        ((DataHandler) (view)).pro_name.setText(viewgroup.getProduct_name());
        ((DataHandler) (view)).b_name.setText(viewgroup.getBrand_name());
        ((DataHandler) (view)).price.setText(viewgroup.getPricing());
        return view1;
    }
}
