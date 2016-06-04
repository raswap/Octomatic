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

private static class mServiceImpl extends mServiceImpl
{

    _cls1.val.receiver mServiceImpl;

    public void getMediaItem(String s, final ResultReceiver receiver)
    {
        final String KEY_MEDIA_ITEM = (String)android/service/media/MediaBrowserService.getDeclaredField("KEY_MEDIA_ITEM").get(null);
        mServiceImpl.mServiceImpl(s, new MediaBrowserServiceCompatApi23.ItemCallback() {

            final MediaBrowserServiceCompatApi23.MediaBrowserServiceAdaptorApi23.ServiceBinderProxyApi23 this$0;
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
                this$0 = MediaBrowserServiceCompatApi23.MediaBrowserServiceAdaptorApi23.ServiceBinderProxyApi23.this;
                KEY_MEDIA_ITEM = s;
                receiver = resultreceiver;
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

    _cls1.val.receiver(_cls1.val.receiver receiver)
    {
        super(receiver);
        mServiceImpl = receiver;
    }
}
