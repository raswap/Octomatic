// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.IBinder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package android.support.v4.media:
//            IMediaBrowserServiceCallbacksAdapterApi21

static class 
{

    static Method sAsInterfaceMethod;

    static Object asInterface(IBinder ibinder)
    {
        ibinder = ((IBinder) (sAsInterfaceMethod.invoke(null, new Object[] {
            ibinder
        })));
        return ibinder;
        ibinder;
_L2:
        ibinder.printStackTrace();
        return null;
        ibinder;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static 
    {
        sAsInterfaceMethod = Class.forName("android.service.media.IMediaBrowserServiceCallbacks$Stub").getMethod("asInterface", new Class[] {
            android/os/IBinder
        });
        return;
        Object obj;
        obj;
_L2:
        ((ReflectiveOperationException) (obj)).printStackTrace();
        return;
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    ()
    {
    }
}
