// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

private class <init>
    implements ServiceConnection
{

    final this._cls0 this$0;

    private boolean isCurrent(String s)
    {
        if (<init>(this._cls0.this) != this)
        {
            if (this._mth0(this._cls0.this) != 0)
            {
                Log.i("MediaBrowserCompat", (new StringBuilder()).append(s).append(" for ").append(this._mth0(this._cls0.this)).append(" with mServiceConnection=").append(this._mth0(this._cls0.this)).append(" this=").append(this).toString());
            }
            return false;
        } else
        {
            return true;
        }
    }

    private void postOrRun(Runnable runnable)
    {
        if (Thread.currentThread() == this._mth0(this._cls0.this)._mth0().getThread())
        {
            runnable.run();
            return;
        } else
        {
            this._mth0(this._cls0.this)._mth0(runnable);
            return;
        }
    }

    public void onServiceConnected(final ComponentName name, final IBinder binder)
    {
        postOrRun(new Runnable() {

            final MediaBrowserCompat.MediaBrowserServiceImplBase.MediaServiceConnection this$1;
            final IBinder val$binder;
            final ComponentName val$name;

            public void run()
            {
                if (!isCurrent("onServiceConnected"))
                {
                    return;
                }
                MediaBrowserCompat.MediaBrowserServiceImplBase.access$1102(this$0, new MediaBrowserCompat.ServiceBinderWrapper(binder));
                MediaBrowserCompat.MediaBrowserServiceImplBase.access$1202(this$0, new Messenger(MediaBrowserCompat.MediaBrowserServiceImplBase.access$1300(this$0)));
                MediaBrowserCompat.MediaBrowserServiceImplBase.access$1300(this$0).setCallbacksMessenger(MediaBrowserCompat.MediaBrowserServiceImplBase.access$1200(this$0));
                MediaBrowserCompat.MediaBrowserServiceImplBase.access$1402(this$0, 1);
                try
                {
                    MediaBrowserCompat.MediaBrowserServiceImplBase.access$1100(this$0).connect(MediaBrowserCompat.MediaBrowserServiceImplBase.access$1500(this$0), MediaBrowserCompat.MediaBrowserServiceImplBase.access$1600(this$0), MediaBrowserCompat.MediaBrowserServiceImplBase.access$1200(this$0));
                    return;
                }
                catch (RemoteException remoteexception)
                {
                    Log.w("MediaBrowserCompat", (new StringBuilder()).append("RemoteException during connect for ").append(MediaBrowserCompat.MediaBrowserServiceImplBase.access$1700(this$0)).toString());
                }
            }

            
            {
                this$1 = MediaBrowserCompat.MediaBrowserServiceImplBase.MediaServiceConnection.this;
                name = componentname;
                binder = ibinder;
                super();
            }
        });
    }

    public void onServiceDisconnected(final ComponentName name)
    {
        postOrRun(new Runnable() {

            final MediaBrowserCompat.MediaBrowserServiceImplBase.MediaServiceConnection this$1;
            final ComponentName val$name;

            public void run()
            {
                if (!isCurrent("onServiceDisconnected"))
                {
                    return;
                } else
                {
                    MediaBrowserCompat.MediaBrowserServiceImplBase.access$1102(this$0, null);
                    MediaBrowserCompat.MediaBrowserServiceImplBase.access$1202(this$0, null);
                    MediaBrowserCompat.MediaBrowserServiceImplBase.access$1300(this$0).setCallbacksMessenger(null);
                    MediaBrowserCompat.MediaBrowserServiceImplBase.access$1402(this$0, 3);
                    MediaBrowserCompat.MediaBrowserServiceImplBase.access$900(this$0).onConnectionSuspended();
                    return;
                }
            }

            
            {
                this$1 = MediaBrowserCompat.MediaBrowserServiceImplBase.MediaServiceConnection.this;
                name = componentname;
                super();
            }
        });
    }


    private _cls2.val.name()
    {
        this$0 = this._cls0.this;
        super();
    }

    this._cls0(this._cls0 _pcls0_1)
    {
        this();
    }
}
