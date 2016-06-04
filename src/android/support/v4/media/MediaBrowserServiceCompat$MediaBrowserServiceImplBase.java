// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

class this._cls0
    implements this._cls0
{

    private Messenger mMessenger;
    final MediaBrowserServiceCompat this$0;

    public IBinder onBind(Intent intent)
    {
        if ("android.media.browse.MediaBrowserService".equals(intent.getAction()))
        {
            return mMessenger.getBinder();
        } else
        {
            return null;
        }
    }

    public void onCreate()
    {
        mMessenger = new Messenger(MediaBrowserServiceCompat.access$100(MediaBrowserServiceCompat.this));
    }

    ()
    {
        this$0 = MediaBrowserServiceCompat.this;
        super();
    }
}
