// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

private static class mCallbackImpl extends Handler
{

    private final iceCallbackImpl mCallbackImpl;
    private WeakReference mCallbacksMessengerRef;

    public void handleMessage(Message message)
    {
        if (mCallbacksMessengerRef == null)
        {
            return;
        }
        Bundle bundle = message.getData();
        bundle.setClassLoader(android/support/v4/media/session/MediaSessionCompat.getClassLoader());
        switch (message.what)
        {
        default:
            Log.w("MediaBrowserCompat", (new StringBuilder()).append("Unhandled message: ").append(message).append("\n  Client version: ").append(1).append("\n  Service version: ").append(message.arg1).toString());
            return;

        case 1: // '\001'
            mCallbackImpl.onServiceConnected((Messenger)mCallbacksMessengerRef.get(), bundle.getString("data_media_item_id"), (android.support.v4.media.session.ksMessengerRef)bundle.getParcelable("data_media_session_token"), bundle.getBundle("data_root_hints"));
            return;

        case 2: // '\002'
            mCallbackImpl.onConnectionFailed((Messenger)mCallbacksMessengerRef.get());
            return;

        case 3: // '\003'
            mCallbackImpl.onLoadChildren((Messenger)mCallbacksMessengerRef.get(), bundle.getString("data_media_item_id"), bundle.getParcelableArrayList("data_media_item_list"), bundle.getBundle("data_options"));
            break;
        }
    }

    void setCallbacksMessenger(Messenger messenger)
    {
        mCallbacksMessengerRef = new WeakReference(messenger);
    }

    iceCallbackImpl(iceCallbackImpl icecallbackimpl)
    {
        mCallbackImpl = icecallbackimpl;
    }
}
