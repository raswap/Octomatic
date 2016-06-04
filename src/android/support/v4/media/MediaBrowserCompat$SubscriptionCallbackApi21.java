// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat, MediaBrowserCompatApi21, MediaBrowserCompatUtils

static class mOptions extends mOptions
{
    private class StubApi21
        implements MediaBrowserCompatApi21.SubscriptionCallback
    {

        final MediaBrowserCompat.SubscriptionCallbackApi21 this$0;

        public void onChildrenLoaded(String s, List list)
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
                    arraylist.add(MediaBrowserCompat.MediaItem.CREATOR.createFromParcel(((Parcel) (obj))));
                    ((Parcel) (obj)).recycle();
                } while (true);
            }
            if (mOptions != null)
            {
                MediaBrowserCompat.SubscriptionCallbackApi21.this.onChildrenLoaded(s, MediaBrowserCompatUtils.applyOptions(((List) (obj)), mOptions), mOptions);
                return;
            } else
            {
                MediaBrowserCompat.SubscriptionCallbackApi21.this.onChildrenLoaded(s, ((List) (obj)));
                return;
            }
        }

        public void onError(String s)
        {
            if (mOptions != null)
            {
                MediaBrowserCompat.SubscriptionCallbackApi21.this.onError(s, mOptions);
                return;
            } else
            {
                MediaBrowserCompat.SubscriptionCallbackApi21.this.onError(s);
                return;
            }
        }

        private StubApi21()
        {
            this$0 = MediaBrowserCompat.SubscriptionCallbackApi21.this;
            super();
        }

        StubApi21(MediaBrowserCompat._cls1 _pcls1)
        {
            this();
        }
    }


    private Bundle mOptions;
    or mSubscriptionCallback;
    private final Object mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21(null));

    public void onChildrenLoaded(String s, List list)
    {
        mSubscriptionCallback.ldrenLoaded(s, list);
    }

    public void onChildrenLoaded(String s, List list, Bundle bundle)
    {
        mSubscriptionCallback.ldrenLoaded(s, list, bundle);
    }

    public void onError(String s)
    {
        mSubscriptionCallback.or(s);
    }

    public void onError(String s, Bundle bundle)
    {
        mSubscriptionCallback.or(s, bundle);
    }



    public StubApi21(StubApi21 stubapi21, Bundle bundle)
    {
        mSubscriptionCallback = stubapi21;
        mOptions = bundle;
    }
}
