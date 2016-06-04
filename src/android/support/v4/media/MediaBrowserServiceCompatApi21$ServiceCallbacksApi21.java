// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompatApi21, ParceledListSliceAdapterApi21, IMediaBrowserServiceCallbacksAdapterApi21

public static class llbacks
    implements mCallbacks
{

    private static Object sNullParceledListSliceObj;
    private final IMediaBrowserServiceCallbacksAdapterApi21 mCallbacks;

    public IBinder asBinder()
    {
        return mCallbacks.asBinder();
    }

    public void onConnect(String s, Object obj, Bundle bundle)
        throws RemoteException
    {
        mCallbacks.onConnect(s, obj, bundle);
    }

    public void onConnectFailed()
        throws RemoteException
    {
        mCallbacks.onConnectFailed();
    }

    public void onLoadChildren(String s, List list)
        throws RemoteException
    {
        Object obj = null;
        if (list != null)
        {
            ArrayList arraylist = new ArrayList();
            list = list.iterator();
            do
            {
                obj = arraylist;
                if (!list.hasNext())
                {
                    break;
                }
                obj = (Parcel)list.next();
                ((Parcel) (obj)).setDataPosition(0);
                arraylist.add(android.media.browse.i21.allbacksApi21(((Parcel) (obj))));
                ((Parcel) (obj)).recycle();
            } while (true);
        }
        if (android.os.allbacksApi21 > 23)
        {
            if (obj == null)
            {
                list = null;
            } else
            {
                list = ((List) (ParceledListSliceAdapterApi21.newInstance(((List) (obj)))));
            }
        } else
        if (obj == null)
        {
            list = ((List) (sNullParceledListSliceObj));
        } else
        {
            list = ((List) (ParceledListSliceAdapterApi21.newInstance(((List) (obj)))));
        }
        mCallbacks.onLoadChildren(s, list);
    }

    static 
    {
        android.media.browse.hildren hildren = new android.media.browse.hildren((new android.media.onLoadChildren()).onLoadChildren("android.support.v4.media.MediaBrowserCompat.NULL_MEDIA_ITEM").onLoadChildren(), 0);
        ArrayList arraylist = new ArrayList();
        arraylist.add(hildren);
        sNullParceledListSliceObj = ParceledListSliceAdapterApi21.newInstance(arraylist);
    }

    (Object obj)
    {
        mCallbacks = new IMediaBrowserServiceCallbacksAdapterApi21(obj);
    }
}
