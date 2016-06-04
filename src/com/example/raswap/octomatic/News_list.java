package com.example.raswap.octomatic;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

// Referenced classes of package com.example.raswap.octomatic:
//            NewsAdapter, News_data_provider

public class News_list extends AppCompatActivity
{

    NewsAdapter adapter;
    int headline_img[] = {
        0x7f02004b, 0x7f02004f, 0x7f02004c
    };
    String headlines[];
    ListView listView;

    public News_list()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f04001b);
        adapter = new NewsAdapter(getApplicationContext(), 0x7f04001d);
        ((ListView)findViewById(0x7f0d0054)).setAdapter(adapter);
        headlines = getResources().getStringArray(0x7f0b0001);
        int j = 0;
        bundle = headlines;
        int k = bundle.length;
        for (int i = 0; i < k; i++)
        {
            Object obj = bundle[i];
            obj = new News_data_provider(headline_img[j], ((String) (obj)));
            adapter.add(obj);
            j++;
        }

    }
}
