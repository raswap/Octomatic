// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.os.ResultReceiver;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

private static class mMessenger
{

    private Messenger mMessenger;

    private void sendRequest(int i, Bundle bundle, Messenger messenger)
        throws RemoteException
    {
        Message message = Message.obtain();
        message.what = i;
        message.arg1 = 1;
        message.setData(bundle);
        message.replyTo = messenger;
        mMessenger.send(message);
    }

    void addSubscription(String s, Bundle bundle, Messenger messenger)
        throws RemoteException
    {
        Bundle bundle1 = new Bundle();
        bundle1.putString("data_media_item_id", s);
        bundle1.putBundle("data_options", bundle);
        sendRequest(3, bundle1, messenger);
    }

    void connect(Context context, Bundle bundle, Messenger messenger)
        throws RemoteException
    {
        Bundle bundle1 = new Bundle();
        bundle1.putString("data_package_name", context.getPackageName());
        bundle1.putBundle("data_root_hints", bundle);
        sendRequest(1, bundle1, messenger);
    }

    void disconnect(Messenger messenger)
        throws RemoteException
    {
        sendRequest(2, null, messenger);
    }

    void getMediaItem(String s, ResultReceiver resultreceiver)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putString("data_media_item_id", s);
        bundle.putParcelable("data_result_receiver", resultreceiver);
        sendRequest(5, bundle, null);
    }

    void registerCallbackMessenger(Messenger messenger)
        throws RemoteException
    {
        sendRequest(6, null, messenger);
    }

    void removeSubscription(String s, Bundle bundle, Messenger messenger)
        throws RemoteException
    {
        Bundle bundle1 = new Bundle();
        bundle1.putString("data_media_item_id", s);
        bundle1.putBundle("data_options", bundle);
        sendRequest(4, bundle1, messenger);
    }

    public (IBinder ibinder)
    {
        mMessenger = new Messenger(ibinder);
    }
}
