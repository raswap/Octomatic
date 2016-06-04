// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

static class mRootHints
    implements mRootHints, mpl
{
    private class MediaServiceConnection
        implements ServiceConnection
    {

        final MediaBrowserCompat.MediaBrowserServiceImplBase this$0;

        private boolean isCurrent(String s)
        {
            if (mServiceConnection != this)
            {
                if (mState != 0)
                {
                    Log.i("MediaBrowserCompat", (new StringBuilder()).append(s).append(" for ").append(mServiceComponent).append(" with mServiceConnection=").append(mServiceConnection).append(" this=").append(this).toString());
                }
                return false;
            } else
            {
                return true;
            }
        }

        private void postOrRun(Runnable runnable)
        {
            if (Thread.currentThread() == mHandler.getLooper().getThread())
            {
                runnable.run();
                return;
            } else
            {
                mHandler.post(runnable);
                return;
            }
        }

        public void onServiceConnected(final ComponentName name, IBinder ibinder)
        {
            postOrRun(ibinder. new Runnable() {

                final MediaServiceConnection this$1;
                final IBinder val$binder;
                final ComponentName val$name;

                public void run()
                {
                    if (!isCurrent("onServiceConnected"))
                    {
                        return;
                    }
                    mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper(binder);
                    mCallbacksMessenger = new Messenger(mHandler);
                    mHandler.setCallbacksMessenger(mCallbacksMessenger);
                    mState = 1;
                    try
                    {
                        mServiceBinderWrapper.connect(mContext, mRootHints, mCallbacksMessenger);
                        return;
                    }
                    catch (RemoteException remoteexception)
                    {
                        Log.w("MediaBrowserCompat", (new StringBuilder()).append("RemoteException during connect for ").append(mServiceComponent).toString());
                    }
                }

            
            {
                this$1 = final_mediaserviceconnection;
                name = componentname;
                binder = IBinder.this;
                super();
            }
            });
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            postOrRun(componentname. new Runnable() {

                final MediaServiceConnection this$1;
                final ComponentName val$name;

                public void run()
                {
                    if (!isCurrent("onServiceDisconnected"))
                    {
                        return;
                    } else
                    {
                        mServiceBinderWrapper = null;
                        mCallbacksMessenger = null;
                        mHandler.setCallbacksMessenger(null);
                        mState = 3;
                        mCallback.onConnectionSuspended();
                        return;
                    }
                }

            
            {
            