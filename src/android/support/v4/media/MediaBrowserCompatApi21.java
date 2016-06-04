// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MediaBrowserCompatApi21
{
    static interface ConnectionCallback
    {

        public abstract void onConnected();

        public abstract void onConnectionFailed();

        public abstract void onConnectionSuspended();
    }

    static class ConnectionCallbackProxy extends android.media.browse.MediaBrowser.ConnectionCallback
    {

        protected final ConnectionCallback mConnectionCallback;

        public void onConnected()
        {
            mConnectionCallback.onConnected();
        }

        public void onConnectionFailed()
        {
            mConnectionCallback.onConnectionFailed();
        }

        public void onConnectionSuspended()
        {
            mConnectionCallback.onConnectionSuspended();
        }

        public ConnectionCallbackProxy(ConnectionCallback connectioncallback)
        {
            mConnectionCallback = connectioncallback;
        }
    }

    static interface SubscriptionCallback
    {

        public abstract void onChildrenLoaded(String s, List list);

        public abstract void onError(String s);
    }

    static class SubscriptionCallbackProxy extends android.media.browse.MediaBrowser.SubscriptionCallback
    {

        protected final SubscriptionCallback mSubscriptionCallback;

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
                    if (((android.media.browse.MediaBrowser.MediaItem)list.get(0)).getMediaId().equals("android.support.v4.media.MediaBrowserCompat.NULL_MEDIA_ITEM"))
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
                    list = (android.media.browse.MediaBrowser.MediaItem)((Iterator) (obj)).next();
                    Parcel parcel = Parcel.obtain();
                    list.writeToParcel(parcel, 0);
                    arraylist.add(parcel);
                } while (true);
            }
            mSubscriptionCallback.onChildrenLoaded(s, list);
        }

        public void onError(String s)
        {
            mSubscriptionCallback.onError(s);
        }

        public SubscriptionCallbackProxy(SubscriptionCallback subscriptioncallback)
        {
            mSubscriptionCallback = subscriptioncallback;
        }
    }


    static final String NULL_MEDIA_ITEM_ID = "android.support.v4.media.MediaBrowserCompat.NULL_MEDIA_ITEM";

    MediaBrowserCompatApi21()
    {
    }

    public static void connect(Object obj)
    {
        ((MediaBrowser)obj).connect();
    }

    public static Object createBrowser(Context context, ComponentName componentname, Object obj, Bundle bundle)
    {
        return new MediaBrowser(context, componentname, (android.media.browse.MediaBrowser.ConnectionCallback)obj, bundle);
    }

    public static Object createConnectionCallback(ConnectionCallback connectioncallback)
    {
        return new ConnectionCallbackProxy(connectioncallback);
    }

    public static Object createSubscriptionCallback(SubscriptionCallback subscriptioncallback)
    {
        return new SubscriptionCallbackProxy(subscriptioncallback);
    }

    public static void disconnect(Object obj)
    {
        ((MediaBrowser)obj).disconnect();
    }

    public static Bundle getExtras(Object obj)
    {
        return ((MediaBrowser)obj).getExtras();
    }

    public static String getRoot(Object obj)
    {
        return ((MediaBrowser)obj).getRoot();
    }

    public static ComponentName getServiceComponent(Object obj)
    {
        return ((MediaBrowser)obj).getServiceComponent();
    }

    public static Object getSessionToken(Object obj)
    {
        return ((MediaBrowser)obj).getSessionToken();
    }

    public static boolean isConnected(Object obj)
    {
        return ((MediaBrowser)obj).isConnected();
    }

    public static void subscribe(Object obj, String s, Object obj1)
    {
        ((MediaBrowser)obj).subscribe(s, (android.media.browse.MediaBrowser.SubscriptionCallback)obj1);
    }

    public static void unsubscribe(Object obj, String s)
    {
        ((MediaBrowser)obj).unsubscribe(s);
    }
}
