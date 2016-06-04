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

class val.cb extends ResultReceiver
{

    final nItemLoaded this$1;
    final nItemLoaded val$cb;

    protected void onReceiveResult(int i, Bundle bundle)
    {
        val.cb cb1 = (val.cb)bundle.getParcelable("media_item");
        Parcel parcel = null;
        if (cb1 != null)
        {
            parcel = Parcel.obtain();
            cb1.cb(parcel, 0);
        }
        val$cb.nItemLoaded(i, bundle, parcel);
    }

    ( 1)
    {
        this$1 = final_;
        val$cb = 1;
        super(Handler.this);
    }
}
