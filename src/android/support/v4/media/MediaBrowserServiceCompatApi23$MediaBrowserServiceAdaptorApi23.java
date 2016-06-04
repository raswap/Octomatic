// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.service.media.MediaBrowserService;
import android.util.Log;
import java.lang.reflect.Field;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompatApi23

static class ServiceBinderProxyApi23.mServiceImpl extends ServiceBinderProxyApi23.mServiceImpl
{
    private static class ServiceBinderProxyApi23 extends MediaBrowserServiceCompatApi21.MediaBrowserServiceAdaptorApi21.ServiceBinderProxyApi21
    {

        MediaBrowserServiceCompatApi23.ServiceImplApi23 mServiceImpl;

        public void getMediaItem(String s, ResultReceiver resultreceiver)
        {
            final String KEY_MEDIA_ITEM = (String)android/service/media/MediaBrowserService.getDeclaredField("KEY_MEDIA_ITEM").get(null);
            mServiceImpl.getMediaItem(s, resultreceiver. new MediaBrowserServiceCompatApi23.ItemCallback() {

                final ServiceBinderProxyApi23 this$0;
                final String val$KEY_MEDIA_ITEM;
                final ResultReceiver val$receiver;

                public void onItemLoaded(int i, Bundle bundle, Parcel parcel)
                {
                    if (parcel != null)
                    {
                        parcel.setDataPosition(0);
                        android.media.browse.MediaBrowser.MediaItem mediaitem = (android.media.browse.MediaBrowser.MediaItem)android.media.browse.MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel);
                        bundle.putParcelable(KEY_MEDIA_ITEM, mediaitem);
                        parcel.recycle();
                    }
                    receiver.send(i, bundle);
                }

            
            {
                this$0 = final_servicebinderproxyapi23;
                KEY_MEDIA_ITEM = s;
                receiver = ResultReceiver.this;
                super();
            }
            });
            return;
            s;
_L2:
            Log.i("MediaBrowserServiceCompatApi21", "Failed to get KEY_MEDIA_ITEM via reflection", s);
            return;
            s;
            if (true) goto _L2; else goto _L1
_L1:
        }

        ServiceBinderProxyApi23(MediaBrowserServiceCompatApi23.ServiceImplApi23 serviceimplapi23)
        {
            super(serviceimplapi23);
            mServiceImpl = serviceimplapi23;
        }
    }


    public void onCreate(ServiceBinderProxyApi23 servicebinderproxyapi23)
    {
        mBinder = new ServiceBinderProxyApi23(servicebinderproxyapi23);
    }

    ServiceBinderProxyApi23.mServiceImpl()
    {
    }
}
