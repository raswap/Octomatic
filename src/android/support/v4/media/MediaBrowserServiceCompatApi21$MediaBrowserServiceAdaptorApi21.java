// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompatApi21

static class ServiceBinderProxyApi21.mServiceImpl
{
    static class ServiceBinderProxyApi21 extends IMediaBrowserServiceAdapterApi21.Stub
    {

        final MediaBrowserServiceCompatApi21.ServiceImplApi21 mServiceImpl;

        public void addSubscription(String s, Object obj)
        {
            mServiceImpl.addSubscription(s, new MediaBrowserServiceCompatApi21.ServiceCallbacksApi21(obj));
        }

        public void connect(String s, Bundle bundle, Object obj)
        {
            mServiceImpl.connect(s, bundle, new MediaBrowserServiceCompatApi21.ServiceCallbacksApi21(obj));
        }

        public void disconnect(Object obj)
        {
            mServiceImpl.disconnect(new MediaBrowserServiceCompatApi21.ServiceCallbacksApi21(obj));
        }

        public void getMediaItem(String s, ResultReceiver resultreceiver)
        {
        }

        public void removeSubscription(String s, Object obj)
        {
            mServiceImpl.removeSubscription(s, new MediaBrowserServiceCompatApi21.ServiceCallbacksApi21(obj));
        }

        ServiceBinderProxyApi21(MediaBrowserServiceCompatApi21.ServiceImplApi21 serviceimplapi21)
        {
            mServiceImpl = serviceimplapi21;
        }
    }


    ServiceBinderProxyApi21 mBinder;

    public IBinder onBind(Intent intent)
    {
        if ("android.media.browse.MediaBrowserService".equals(intent.getAction()))
        {
            return mBinder;
        } else
        {
            return null;
        }
    }

    public void onCreate(mBinder mbinder)
    {
        mBinder = new ServiceBinderProxyApi21(mbinder);
    }

    ServiceBinderProxyApi21.mServiceImpl()
    {
    }
}
