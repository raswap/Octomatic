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
//            News_data_provider

public class NewsAdapter extends ArrayAdapter
{
    static class DataHandler
    {

        TextView heading;
        ImageView img;

        DataHandler()
        {
        }
    }


    List list;

    public NewsAdapter(Context context, int i)
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
        DataHandler datahandler = new DataHandler();
        if (view == null)
        {
            view1 = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f04001d, viewgroup, false);
            datahandler.img = (ImageView)view1.findViewById(0x7f0d0057);
            datahandler.heading = (TextView)view1.findViewById(0x7f0d0059);
            view1.setTag(datahandler);
            view = datahandler;
        } else
        {
            view = (DataHandler)view1.getTag();
        }
        viewgroup = (News_data_provider)getItem(i);
        ((DataHandler) (view)).img.setImageResource(viewgroup.getHeadline_img_resource());
        ((DataHandler) (view)).heading.setText(viewgroup.getHeadlines());
        return view1;
    }
}
