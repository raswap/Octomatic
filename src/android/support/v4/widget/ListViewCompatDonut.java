// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

class ListViewCompatDonut
{

    ListViewCompatDonut()
    {
    }

    static void scrollListBy(ListView listview, int i)
    {
        int j = listview.getFirstVisiblePosition();
        View view;
        if (j != -1)
        {
            if ((view = listview.getChildAt(0)) != null)
            {
                listview.setSelectionFromTop(j, view.getTop() - i);
                return;
            }
        }
    }
}
