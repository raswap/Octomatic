// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.app.BundleCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

private class mCallbacks
    implements mCallbacks
{

    final onLoadChildren mCallbacks;
    Messenger mMessenger;
    final MediaBrowserServiceCompat this$0;

    public IBinder asBinder()
    {
        return mCallbacks.asBinder();
    }

    public void onConnect(String s, android.support.v4.media.session. , Bundle bundle)
        throws RemoteException
    {
        Bundle bundle1 = bundle;
        if (bundle == null)
        {
            bundle1 = new Bundle();
        }
        mMessenger = new Messenger(MediaBrowserServiceCompat.access$100(MediaBrowserServiceCompat.this));
        BundleCompat.putBinder(bundle1, "extra_messenger", mMessenger.getBinder());
        bundle1.putInt("extra_service_version", 1);
        mCallbacks.onConnect(s, .t(), bundle1);
    }

    public void onConnectFailed()
        throws RemoteException
    {
        mCallbacks.onConnectFailed();
    }

    public void onLoadChildren(String s, List list, Bundle bundle)
        throws RemoteException
    {
        bundle = null;
        if (list != null)
        {
            ArrayList arraylist = new ArrayList();
            list = list.iterator();
            do
            {
                bundle = arraylist;
                if (!list.hasNext())
                {
                    break;
                }
                bundle = (onConnectFailed)list.next();
                Parcel parcel = Parcel.obtain();
                bundle.onConnectFailed(parcel, 0);
                arraylist.add(parcel);
            } while (true);
        }
        mCallbacks.onLoadChildren(s, bundle);
    }

    ( )
    {
        this$0 = MediaBrowserServiceCompat.this;
        super();
        mCallbacks = ;
    }
}
