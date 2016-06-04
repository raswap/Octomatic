// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.ComponentName;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

class val.binder
    implements Runnable
{

    final is._cls0 this$1;
    final IBinder val$binder;
    final ComponentName val$name;

    public void run()
    {
        if (!cess._mth1000(this._cls1.this, "onServiceConnected"))
        {
            return;
        }
        this._mth1(_fld0, new is._cls0(val$binder));
        val.binder(_fld0, new Messenger(is._mth0(_fld0)));
        is._mth0(_fld0)._mth0(is._mth0(_fld0));
        is._mth0(_fld0, 1);
        try
        {
            is._mth0(_fld0)._mth0(is._mth0(_fld0), is._mth0(_fld0), is._mth0(_fld0));
            return;
        }
        catch (RemoteException remoteexception)
        {
            Log.w("MediaBrowserCompat", (new StringBuilder()).append("RemoteException during connect for ").append(is._mth0(_fld0)).toString());
        }
    }

    ()
    {
        this$1 = final_;
        val$name = componentname;
        val$binder = IBinder.this;
        super();
    }
}
