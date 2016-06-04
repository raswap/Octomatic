// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.Intent;
import android.os.IBinder;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

static interface 
{

    public abstract IBinder onBind(Intent intent);

    public abstract void onCreate();
}
