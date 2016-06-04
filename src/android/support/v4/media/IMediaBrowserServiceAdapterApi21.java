// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;

class IMediaBrowserServiceAdapterApi21
{
    static abstract class Stub extends Binder
        implements IInterface
    {

        private static final String DESCRIPTOR = "android.service.media.IMediaBrowserService";
        private static final int TRANSACTION_addSubscription = 3;
        private static final int TRANSACTION_connect = 1;
        private static final int TRANSACTION_disconnect = 2;
        private static final int TRANSACTION_getMediaItem = 5;
        private static final int TRANSACTION_removeSubscription = 4;

        public abstract void addSubscription(String s, Object obj);

        public IBinder asBinder()
        {
            return this;
        }

        public abstract void connect(String s, Bundle bundle, Object obj);

        public abstract void disconnect(Object obj);

        public abstract void getMediaItem(String s, ResultReceiver resultreceiver);

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch (i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.service.media.IMediaBrowserService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.media.IMediaBrowserService");
                String s = parcel.readString();
                if (parcel.readInt() != 0)
                {
                    parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                } else
                {
                    parcel1 = null;
                }
                connect(s, parcel1, IMediaBrowserServiceCallbacksAdapterApi21.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.media.IMediaBrowserService");
                disconnect(IMediaBrowserServiceCallbacksAdapterApi21.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.media.IMediaBrowserService");
                addSubscription(parcel.readString(), IMediaBrowserServiceCallbacksAdapterApi21.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.media.IMediaBrowserService");
                removeSubscription(parcel.readString(), IMediaBrowserServiceCallbacksAdapterApi21.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.media.IMediaBrowserService");
                parcel1 = parcel.readString();
                break;
            }
            if (parcel.readInt() != 0)
            {
                parcel = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
            } else
            {
                parcel = null;
            }
            getMediaItem(parcel1, parcel);
            return true;
        }

        public abstract void removeSubscription(String s, Object obj);

        public Stub()
        {
            attachInterface(this, "android.service.media.IMediaBrowserService");
        }
    }


    IMediaBrowserServiceAdapterApi21()
    {
    }
}
