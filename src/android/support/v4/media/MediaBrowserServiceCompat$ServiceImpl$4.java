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

    final val.id this$1;
    final ks val$callbacks;
    final String val$id;
    final Bundle val$options;

    public void run()
    {
        Object obj = val$callbacks.asBinder();
        obj = (rd)MediaBrowserServiceCompat.access$500(_fld0).get(obj);
        if (obj == null)
        {
            Log.w("MediaBrowserServiceCompat", (new StringBuilder()).append("removeSubscription for callback that isn't registered id=").append(val$id).toString());
        } else
        if (!MediaBrowserServiceCompat.access$800(_fld0, val$id, ((rd) (obj)), val$options))
        {
            Log.w("MediaBrowserServiceCompat", (new StringBuilder()).append("removeSubscription called for ").append(val$id).append(" which is not subscribed").toString());
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
