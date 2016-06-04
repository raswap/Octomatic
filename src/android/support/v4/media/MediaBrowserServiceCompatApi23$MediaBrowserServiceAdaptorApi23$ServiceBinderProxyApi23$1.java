// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompatApi23

class val.receiver
    implements val.receiver
{

    final val.receiver this$0;
    final String val$KEY_MEDIA_ITEM;
    final ResultReceiver val$receiver;

    public void onItemLoaded(int i, Bundle bundle, Parcel parcel)
    {
        if (parcel != null)
        {
            parcel.setDataPosition(0);
            android.media.browse._cls3._cls1 _lcls1 = (android.media.browse._cls3._cls1)android.media.browse._cls3._fld1._mth1(parcel);
            bundle.putParcelable(val$KEY_MEDIA_ITEM, _lcls1);
            parcel.recycle();
        }
        val$receiver.send(i, bundle);
    }

    ()
    {
        this$0 = final_;
        val$KEY_MEDIA_ITEM = s;
        val$receiver = ResultReceiver.this;
        super();
    }
}
