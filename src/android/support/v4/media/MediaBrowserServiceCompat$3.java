// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat, MediaBrowserCompatUtils

class sult extends sult
{

    final MediaBrowserServiceCompat this$0;
    final nnectionRecord val$connection;
    final Bundle val$options;
    final String val$parentId;

    volatile void onResultSent(Object obj, int i)
    {
        onResultSent((List)obj, i);
    }

    void onResultSent(List list, int i)
    {
        if (MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).get(val$connection.callbacks.asBinder()) != val$connection)
        {
            return;
        }
        if ((i & 1) != 0)
        {
            list = MediaBrowserCompatUtils.applyOptions(list, val$options);
        }
        try
        {
            val$connection.callbacks.onLoadChildren(val$parentId, list, val$options);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (List list)
        {
            Log.w("MediaBrowserServiceCompat", (new StringBuilder()).append("Calling onLoadChildren() failed for id=").append(val$parentId).append(" package=").append(val$connection.pkg).toString());
        }
    }

    nnectionRecord(Bundle bundle)
    {
        this$0 = final_mediabrowserservicecompat;
        val$connection = nnectionrecord;
        val$parentId = String.this;
        val$options = bundle;
        super(final_obj);
    }
}
