// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.util.ArrayMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat, MediaBrowserCompatUtils

class val.options
    implements Runnable
{

    final MediaBrowserServiceCompat this$0;
    final Bundle val$options;
    final String val$parentId;

    public void run()
    {
        Iterator iterator = MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).keySet().iterator();
label0:
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj = (IBinder)iterator.next();
            obj = (nnectionRecord)MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).get(obj);
            Object obj1 = (List)((nnectionRecord) (obj)).subscriptions.get(val$parentId);
            if (obj1 == null)
            {
                continue;
            }
            obj1 = ((List) (obj1)).iterator();
            Bundle bundle;
            do
            {
                if (!((Iterator) (obj1)).hasNext())
                {
                    continue label0;
                }
                bundle = (Bundle)((Iterator) (obj1)).next();
            } while (!MediaBrowserCompatUtils.hasDuplicatedItems(val$options, bundle));
            MediaBrowserServiceCompat.access$1000(MediaBrowserServiceCompat.this, val$parentId, ((nnectionRecord) (obj)), bundle);
        } while (true);
    }

    nnectionRecord()
    {
        this$0 = final_mediabrowserservicecompat;
        val$parentId = s;
        val$options = Bundle.this;
        super();
    }
}
