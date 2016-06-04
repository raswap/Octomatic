// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Binder;
import android.os.Bundle;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

private class tServiceImpl
    implements pi21
{

    final acks mServiceImpl;
    final MediaBrowserServiceCompat this$0;

    public void addSubscription(String s, acks acks)
    {
        mServiceImpl.bscription(s, null, new pi21(MediaBrowserServiceCompat.this, acks));
    }

    public void connect(String s, Bundle bundle, acks acks)
    {
        mServiceImpl.ct(s, Binder.getCallingUid(), bundle, new pi21(MediaBrowserServiceCompat.this, acks));
    }

    public void disconnect(acks acks)
    {
        mServiceImpl.nnect(new pi21(MediaBrowserServiceCompat.this, acks));
    }

    public void removeSubscription(String s, acks acks)
    {
        mServiceImpl.eSubscription(s, null, new pi21(MediaBrowserServiceCompat.this, acks));
    }

    acks()
    {
        this$0 = MediaBrowserServiceCompat.this;
        super();
        mServiceImpl = MediaBrowserServiceCompat.access$100(MediaBrowserServiceCompat.this).tServiceImpl();
    }
}
