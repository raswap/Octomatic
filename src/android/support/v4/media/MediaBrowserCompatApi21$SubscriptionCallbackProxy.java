// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompatApi21

static class mSubscriptionCallback extends android.media.browse.oxy
{

    protected final or mSubscriptionCallback;

    public void onChildrenLoaded(String s, List list)
    {
        Object obj1 = null;
        Object obj = list;
        if (list != null)
        {
            obj = list;
            if (list.size() == 1)
            {
                obj = list;
                if (((android.media.browse.oxy)list.get(0)).oxy().equals("android.support.v4.media.MediaBrowserCompat.NULL_MEDIA_ITEM"))
                {
                    obj = null;
                }
            }
        }
        list = obj1;
        if (obj != null)
        {
            ArrayList arraylist = new ArrayList();
            obj = ((List) (obj)).iterator();
            do
            {
                list = arraylist;
                if (!((Iterator) (obj)).hasNext())
                {
                    break;
                }
                list = (android.media.browse.oxy)((Iterator) (obj)).next();
                Parcel parcel = Parcel.obtain();
                list.oxy(parcel, 0);
                arraylist.add(parcel);
            } while (true);
        }
        mSubscriptionCallback.ldrenLoaded(s, list);
    }

    public void onError(String s)
    {
        mSubscriptionCallback.or(s);
    }

    public ( )
    {
        mSubscriptionCallback = ;
    }
}
