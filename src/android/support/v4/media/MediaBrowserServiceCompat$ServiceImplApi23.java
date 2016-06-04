// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.support.v4.os.ResultReceiver;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

private class <init> extends <init>
    implements pi23
{

    final MediaBrowserServiceCompat this$0;

    public void getMediaItem(String s,  )
    {
         = new ResultReceiver() {

            final MediaBrowserServiceCompat.ServiceImplApi23 this$1;
            final MediaBrowserServiceCompatApi23.ItemCallback val$cb;

            protected void onReceiveResult(int i, Bundle bundle)
            {
                MediaBrowserCompat.MediaItem mediaitem = (MediaBrowserCompat.MediaItem)bundle.getParcelable("media_item");
                Parcel parcel = null;
                if (mediaitem != null)
                {
                    parcel = Parcel.obtain();
                    mediaitem.writeToParcel(parcel, 0);
                }
                cb.onItemLoaded(i, bundle, parcel);
            }

            
            {
                this$1 = MediaBrowserServiceCompat.ServiceImplApi23.this;
                cb = itemcallback;
                super(final_handler);
            }
        };
        mServiceImpl.diaItem(s, );
    }

    private _cls1.val.cb()
    {
        this$0 = MediaBrowserServiceCompat.this;
        super(MediaBrowserServiceCompat.this);
    }

    <init>(<init> <init>1)
    {
        this();
    }
}
