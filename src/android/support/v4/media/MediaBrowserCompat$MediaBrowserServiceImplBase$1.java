// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.ServiceConnection;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

class val.thisConnection
    implements Runnable
{

    final nFailed this$0;
    final ServiceConnection val$thisConnection;

    public void run()
    {
        if (val$thisConnection == cess._mth700(this._cls0.this))
        {
            cess._mth800(this._cls0.this);
            cess._mth900(this._cls0.this).nFailed();
        }
    }

    ()
    {
        this$0 = final_;
        val$thisConnection = ServiceConnection.this;
        super();
    }
}
