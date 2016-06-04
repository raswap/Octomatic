// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.util.ArrayMap;
import android.util.Log;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

class val.uid
    implements Runnable
{

    final is._cls0 this$1;
    final ks val$callbacks;
    final String val$pkg;
    final Bundle val$rootHints;
    final int val$uid;

    public void run()
    {
        Object obj;
        rd rd;
        obj = val$callbacks.asBinder();
        MediaBrowserServiceCompat.access$500(_fld0).remove(obj);
        rd = new rd(_fld0, null);
        rd.pkg = val$pkg;
        rd.rootHints = val$rootHints;
        rd.callbacks = val$callbacks;
        rd.root = onGetRoot(val$pkg, val$uid, val$rootHints);
        if (rd.root != null)
        {
            break MISSING_BLOCK_LABEL_181;
        }
        Log.i("MediaBrowserServiceCompat", (new StringBuilder()).append("No root for client ").append(val$pkg).append(" from service ").append(getClass().getName()).toString());
        val$callbacks.onConnectFailed();
_L1:
        return;
        obj;
        Log.w("MediaBrowserServiceCompat", (new StringBuilder()).append("Calling onConnectFailed() failed. Ignoring. pkg=").append(val$pkg).toString());
        return;
        try
        {
            MediaBrowserServiceCompat.access$500(_fld0).put(obj, rd);
            if (mSession != null)
            {
                val$callbacks.onConnect(rd.root.tRootId(), mSession, rd.root.tExtras());
                return;
            }
        }
        catch (RemoteException remoteexception)
        {
            Log.w("MediaBrowserServiceCompat", (new StringBuilder()).append("Calling onConnect() failed. Dropping client. pkg=").append(val$pkg).toString());
            MediaBrowserServiceCompat.access$500(_fld0).remove(obj);
            return;
        }
          goto _L1
    }

    ks()
    {
        this$1 = final_ks;
        val$callbacks = ks1;
        val$pkg = s;
        val$rootHints = bundle;
        val$uid = I.this;
        super();
    }
}
