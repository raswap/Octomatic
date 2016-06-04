// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class IMediaBrowserServiceCallbacksAdapterApi21
{
    static class Stub
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

        Stub()
        {
        }
    }


    private Method mAsBinderMethod;
    Object mCallbackObject;
    private Method mOnConnectFailedMethod;
    private Method mOnConnectMethod;
    private Method mOnLoadChildrenMethod;

    IMediaBrowserServiceCallbacksAdapterApi21(Object obj)
    {
        mCallbackObject = obj;
        try
        {
            obj = Class.forName("android.service.media.IMediaBrowserServiceCallbacks");
            Class class1 = Class.forName("android.content.pm.ParceledListSlice");
            mAsBinderMethod = ((Class) (obj)).getMethod("asBinder", new Class[0]);
            mOnConnectMethod = ((Class) (obj)).getMethod("onConnect", new Class[] {
                java/lang/String, android/media/session/MediaSession$Token, android/os/Bundle
            });
            mOnConnectFailedMethod = ((Class) (obj)).getMethod("onConnectFailed", new Class[0]);
            mOnLoadChildrenMethod = ((Class) (obj)).getMethod("onLoadChildren", new Class[] {
                java/lang/String, class1
            });
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj) { }
        // Misplaced declaration of an exception variable
        catch (Object obj) { }
        ((ReflectiveOperationException) (obj)).printStackTrace();
    }

    IBinder asBinder()
    {
        IBinder ibinder = (IBinder)mAsBinderMethod.invoke(mCallbackObject, new Object[0]);
        return ibinder;
        Object obj;
        obj;
_L2:
        ((ReflectiveOperationException) (obj)).printStackTrace();
        return null;
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    void onConnect(String s, Object obj, Bundle bundle)
        throws RemoteException
    {
        try
        {
            mOnConnectMethod.invoke(mCallbackObject, new Object[] {
                s, obj, bundle
            });
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        s.printStackTrace();
    }

    void onConnectFailed()
        throws RemoteException
    {
        mOnConnectFailedMethod.invoke(mCallbackObject, new Object[0]);
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

    void onLoadChildren(String s, Object obj)
        throws RemoteException
    {
        try
        {
            mOnLoadChildrenMethod.invoke(mCallbackObject, new Object[] {
                s, obj
            });
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        s.printStackTrace();
    }
}
