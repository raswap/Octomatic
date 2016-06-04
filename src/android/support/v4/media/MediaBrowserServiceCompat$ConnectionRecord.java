// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import java.util.HashMap;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

private class <init>
{

    <init> callbacks;
    String pkg;
    <init> root;
    Bundle rootHints;
    HashMap subscriptions;
    final MediaBrowserServiceCompat this$0;

    private I()
    {
        this$0 = MediaBrowserServiceCompat.this;
        super();
        subscriptions = new HashMap();
    }

    subscriptions(subscriptions subscriptions1)
    {
        this();
    }
}
