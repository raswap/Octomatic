// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.widget.ListView;

// Referenced classes of package android.support.v4.widget:
//            ListViewCompatKitKat, ListViewCompatDonut

public final class ListViewCompat
{

    private ListViewCompat()
    {
    }

    public static void scrollListBy(ListView listview, int i)
    {
        if (android.os.Build.VERSION.SDK_INT >= 19)
        {
            ListViewCompatKitKat.scrollListBy(listview, i);
            return;
        } else
        {
            ListViewCompatDonut.scrollListBy(listview, i);
            return;
        }
    }
}
