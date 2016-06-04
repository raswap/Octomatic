// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.util.Log;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

class val.options
    implements Runnable
{

    final val.options this$1;
    final ks val$callbacks;
    final String val$id;
    final Bundle val$options;

    public void run()
    {
        Object obj = val$callbacks.asBinder();
        obj = (rd)MediaBrowserServiceCompat.access$500(_fld0).get(obj);
        if (obj == null)
        {
            Log.w("MediaBrowserServiceCompat", (new StringBuilder()).append("addSubscription for callback that isn't registered id=").append(val$id).toString());
            return;
        } else
        {
            MediaBrowserServiceCompat.access$700(_fld0, val$id, ((rd) (obj)), val$options);
            return;
        }
    }

    ks()
    {
        this$1 = final_ks;
        val$callbacks = ks1;
        val$id = s;
        val$options = Bundle.this;
        super();
    }
}
