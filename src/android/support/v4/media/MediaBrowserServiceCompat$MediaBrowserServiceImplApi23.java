// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.Intent;
import android.os.IBinder;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat, MediaBrowserServiceCompatApi23

class this._cls0
    implements this._cls0
{

    private Object mServiceObj;
    final MediaBrowserServiceCompat this$0;

    public IBinder onBind(Intent intent)
    {
        return MediaBrowserServiceCompatApi23.onBind(mServiceObj, intent);
    }

    public void onCreate()
    {
        mServiceObj = MediaBrowserServiceCompatApi23.createService();
        MediaBrowserServiceCompatApi23.onCreate(mServiceObj, new mServiceObj(MediaBrowserServiceCompat.this, null));
    }

    ()
    {
        this$0 = MediaBrowserServiceCompat.this;
        super();
    }
}
