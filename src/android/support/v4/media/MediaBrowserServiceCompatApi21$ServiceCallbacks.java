// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompatApi21

public static interface 
{

    public abstract IBinder asBinder();

    public abstract void onConnect(String s, Object obj, Bundle bundle)
        throws RemoteException;

    public abstract void onConnectFailed()
        throws RemoteException;

    public abstract void onLoadChildren(String s, List list)
        throws RemoteException;
}
