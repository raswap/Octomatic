// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompatApi21, MediaBrowserCompatApi23, MediaDescriptionCompat, MediaBrowserCompatUtils

public final class MediaBrowserCompat
{
    private static class CallbackHandler extends Handler
    {

        private final MediaBrowserServiceCallbackImpl mCallbackImpl;
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
                mCallbackImpl.onServiceConnected((Messenger)mCallbacksMessengerRef.get(), bundle.getString("data_media_item_id"), (android.support.v4.media.session.MediaSessionCompat.Token)bundle.getParcelable("data_media_session_token"), bundle.getBundle("data_root_hints"));
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

        CallbackHandler(MediaBrowserServiceCallbackImpl mediabrowserservicecallbackimpl)
        {
            mCallbackImpl = mediabrowserservicecallbackimpl;
        }
    }

    public static class ConnectionCallback
    {

        private ConnectionCallbackInternal mConnectionCallbackInternal;
        final Object mConnectionCallbackObj;

        public void onConnected()
        {
        }

        public void onConnectionFailed()
        {
        }

        public void onConnectionSuspended()
        {
        }

        void setInternalConnectionCallback(ConnectionCallbackInternal connectioncallbackinternal)
        {
            mConnectionCallbackInternal = connectioncallbackinternal;
        }


        public ConnectionCallback()
        {
            if (android.os.Build.VERSION.SDK_INT >= 21)
            {
                mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(new StubApi21());
                return;
            } else
            {
                mConnectionCallbackObj = null;
                return;
            }
        }
    }

    static interface ConnectionCallback.ConnectionCallbackInternal
    {

        public abstract void onConnected();

        public abstract void onConnectionFailed();

        public abstract void onConnectionSuspended();
    }

    private class ConnectionCallback.StubApi21
        implements MediaBrowserCompatApi21.ConnectionCallback
    {

        final ConnectionCallback this$0;

        public void onConnected()
        {
            if (mConnectionCallbackInternal != null)
            {
                mConnectionCallbackInternal.onConnected();
            }
            ConnectionCallback.this.onConnected();
        }

        public void onConnectionFailed()
        {
            if (mConnectionCallbackInternal != null)
            {
                mConnectionCallbackInternal.onConnectionFailed();
            }
            ConnectionCallback.this.onConnectionFailed();
        }

        public void onConnectionSuspended()
        {
            if (mConnectionCallbackInternal != null)
            {
                mConnectionCallbackInternal.onConnectionSuspended();
            }
            ConnectionCallback.this.onConnectionSuspended();
        }

        private ConnectionCallback.StubApi21()
        {
            this$0 = ConnectionCallback.this;
            super();
        }

    }

    public static abstract class ItemCallback
    {

        final Object mItemCallbackObj;

        public void onError(String s)
        {
        }

        public void onItemLoaded(MediaItem mediaitem)
        {
        }

        public ItemCallback()
        {
            if (android.os.Build.VERSION.SDK_INT >= 23)
            {
                mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(new StubApi23());
                return;
            } else
            {
                mItemCallbackObj = null;
                return;
            }
        }
    }

    private class ItemCallback.StubApi23
        implements MediaBrowserCompatApi23.ItemCallback
    {

        final ItemCallback this$0;

        public void onError(String s)
        {
            ItemCallback.this.onError(s);
        }

        public void onItemLoaded(Parcel parcel)
        {
            parcel.setDataPosition(0);
            MediaItem mediaitem = (MediaItem)MediaItem.CREATOR.createFromParcel(parcel);
            parcel.recycle();
            ItemCallback.this.onItemLoaded(mediaitem);
        }

        private ItemCallback.StubApi23()
        {
            this$0 = ItemCallback.this;
            super();
        }

    }

    private static class ItemReceiver extends ResultReceiver
    {

        private final ItemCallback mCallback;
        private final String mMediaId;

        protected void onReceiveResult(int i, Bundle bundle)
        {
            bundle.setClassLoader(android/support/v4/media/MediaBrowserCompat.getClassLoader());
            if (i != 0 || bundle == null || !bundle.containsKey("media_item"))
            {
                mCallback.onError(mMediaId);
                return;
            }
            bundle = bundle.getParcelable("media_item");
            if (bundle instanceof MediaItem)
            {
                mCallback.onItemLoaded((MediaItem)bundle);
                return;
            } else
            {
                mCallback.onError(mMediaId);
                return;
            }
        }

        ItemReceiver(String s, ItemCallback itemcallback, Handler handler)
        {
            super(handler);
            mMediaId = s;
            mCallback = itemcallback;
        }
    }

    static interface MediaBrowserImpl
    {

        public abstract void connect();

        public abstract void disconnect();

        public abstract Bundle getExtras();

        public abstract void getItem(String s, ItemCallback itemcallback);

        public abstract String getRoot();

        public abstract ComponentName getServiceComponent();

        public abstract android.support.v4.media.session.MediaSessionCompat.Token getSessionToken();

        public abstract boolean isConnected();

        public abstract void subscribe(String s, Bundle bundle, SubscriptionCallback subscriptioncallback);

        public abstract void unsubscribe(String s, Bundle bundle);
    }

    static class MediaBrowserImplApi21
        implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl, ConnectionCallback.ConnectionCallbackInternal
    {

        private static final boolean DBG = false;
        protected Object mBrowserObj;
        private Messenger mCallbacksMessenger;
        private final CallbackHandler mHandler = new CallbackHandler(this);
        private ServiceBinderWrapper mServiceBinderWrapper;
        private final ComponentName mServiceComponent;
        private final ArrayMap mSubscriptions = new ArrayMap();

        public void connect()
        {
            MediaBrowserCompatApi21.connect(mBrowserObj);
        }

        public void disconnect()
        {
            MediaBrowserCompatApi21.disconnect(mBrowserObj);
        }

        public Bundle getExtras()
        {
            return MediaBrowserCompatApi21.getExtras(mBrowserObj);
        }

        public void getItem(String s, final ItemCallback cb)
        {
            if (TextUtils.isEmpty(s))
            {
                throw new IllegalArgumentException("mediaId is empty.");
            }
            if (cb == null)
            {
                throw new IllegalArgumentException("cb is null.");
            }
            if (!MediaBrowserCompatApi21.isConnected(mBrowserObj))
            {
                Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                mHandler.post(s. new Runnable() {

                    final MediaBrowserImplApi21 this$0;
                    final ItemCallback val$cb;
                    final String val$mediaId;

                    public void run()
                    {
                        cb.onError(mediaId);
                    }

            
            {
                this$0 = final_mediabrowserimplapi21;
                cb = itemcallback;
                mediaId = String.this;
                super();
            }
                });
                return;
            }
            if (mServiceBinderWrapper == null)
            {
                mHandler.post(cb. new Runnable() {

                    final MediaBrowserImplApi21 this$0;
                    final ItemCallback val$cb;

                    public void run()
                    {
                        cb.onItemLoaded(null);
                    }

            
            {
                this$0 = final_mediabrowserimplapi21;
                cb = ItemCallback.this;
                super();
            }
                });
                return;
            }
            ItemReceiver itemreceiver = new ItemReceiver(s, cb, mHandler);
            try
            {
                mServiceBinderWrapper.getMediaItem(s, itemreceiver);
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.i("MediaBrowserCompat", (new StringBuilder()).append("Remote error getting media item: ").append(s).toString());
            }
            mHandler.post(s. new Runnable() {

                final MediaBrowserImplApi21 this$0;
                final ItemCallback val$cb;
                final String val$mediaId;

                public void run()
                {
                    cb.onError(mediaId);
                }

            
            {
                this$0 = final_mediabrowserimplapi21;
                cb = itemcallback;
                mediaId = String.this;
                super();
            }
            });
        }

        public String getRoot()
        {
            return MediaBrowserCompatApi21.getRoot(mBrowserObj);
        }

        public ComponentName getServiceComponent()
        {
            return MediaBrowserCompatApi21.getServiceComponent(mBrowserObj);
        }

        public android.support.v4.media.session.MediaSessionCompat.Token getSessionToken()
        {
            return android.support.v4.media.session.MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(mBrowserObj));
        }

        public boolean isConnected()
        {
            return MediaBrowserCompatApi21.isConnected(mBrowserObj);
        }

        public void onConnected()
        {
            Object obj = MediaBrowserCompatApi21.getExtras(mBrowserObj);
            if (obj != null)
            {
                if ((obj = BundleCompat.getBinder(((Bundle) (obj)), "extra_messenger")) != null)
                {
                    mServiceBinderWrapper = new ServiceBinderWrapper(((IBinder) (obj)));
                    mCallbacksMessenger = new Messenger(mHandler);
                    mHandler.setCallbacksMessenger(mCallbacksMessenger);
                    try
                    {
                        mServiceBinderWrapper.registerCallbackMessenger(mCallbacksMessenger);
                    }
                    catch (RemoteException remoteexception)
                    {
                        Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                    }
                    onServiceConnected(mCallbacksMessenger, null, null, null);
                    return;
                }
            }
        }

        public void onConnectionFailed()
        {
        }

        public void onConnectionFailed(Messenger messenger)
        {
        }

        public void onConnectionSuspended()
        {
            mServiceBinderWrapper = null;
            mCallbacksMessenger = null;
        }

        public void onLoadChildren(Messenger messenger, String s, List list, Bundle bundle)
        {
            if (mCallbacksMessenger == messenger)
            {
                if ((messenger = (Subscription)mSubscriptions.get(s)) != null)
                {
                    messenger.getCallback(bundle).onChildrenLoaded(s, list, bundle);
                    return;
                }
            }
        }

        public void onServiceConnected(Messenger messenger, String s, android.support.v4.media.session.MediaSessionCompat.Token token, Bundle bundle)
        {
            for (messenger = mSubscriptions.entrySet().iterator(); messenger.hasNext();)
            {
                token = (java.util.Map.Entry)messenger.next();
                s = (String)token.getKey();
                bundle = (Subscription)token.getValue();
                token = bundle.getOptionsList();
                bundle = bundle.getCallbacks();
                int i = 0;
                while (i < token.size()) 
                {
                    if (token.get(i) == null)
                    {
                        MediaBrowserCompatApi21.subscribe(mBrowserObj, s, ((SubscriptionCallbackApi21)bundle.get(i)).mSubscriptionCallbackObj);
                    } else
                    {
                        try
                        {
                            mServiceBinderWrapper.addSubscription(s, (Bundle)token.get(i), mCallbacksMessenger);
                        }
                        catch (RemoteException remoteexception)
                        {
                            Log.d("MediaBrowserCompat", (new StringBuilder()).append("addSubscription failed with RemoteException parentId=").append(s).toString());
                        }
                    }
                    i++;
                }
            }

        }

        public void subscribe(String s, Bundle bundle, SubscriptionCallback subscriptioncallback)
        {
label0:
            {
                SubscriptionCallbackApi21 subscriptioncallbackapi21 = new SubscriptionCallbackApi21(subscriptioncallback, bundle);
                Subscription subscription = (Subscription)mSubscriptions.get(s);
                subscriptioncallback = subscription;
                if (subscription == null)
                {
                    subscriptioncallback = new Subscription();
                    mSubscriptions.put(s, subscriptioncallback);
                }
                subscriptioncallback.setCallbackForOptions(subscriptioncallbackapi21, bundle);
                if (MediaBrowserCompatApi21.isConnected(mBrowserObj))
                {
                    if (bundle != null && mServiceBinderWrapper != null)
                    {
                        break label0;
                    }
                    MediaBrowserCompatApi21.subscribe(mBrowserObj, s, subscriptioncallbackapi21.mSubscriptionCallbackObj);
                }
                return;
            }
            try
            {
                mServiceBinderWrapper.addSubscription(s, bundle, mCallbacksMessenger);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Bundle bundle)
            {
                Log.i("MediaBrowserCompat", (new StringBuilder()).append("Remote error subscribing media item: ").append(s).toString());
            }
        }

        public void unsubscribe(String s, Bundle bundle)
        {
            Subscription subscription;
            if (TextUtils.isEmpty(s))
            {
                throw new IllegalArgumentException("parentId is empty.");
            }
            subscription = (Subscription)mSubscriptions.get(s);
            if (subscription == null || !subscription.remove(bundle)) goto _L2; else goto _L1
_L1:
            if (bundle != null && mServiceBinderWrapper != null) goto _L4; else goto _L3
_L3:
            if (mServiceBinderWrapper != null || subscription.isEmpty())
            {
                MediaBrowserCompatApi21.unsubscribe(mBrowserObj, s);
            }
_L2:
            if (subscription != null && subscription.isEmpty())
            {
                mSubscriptions.remove(s);
            }
            return;
_L4:
            if (mServiceBinderWrapper == null)
            {
                try
                {
                    mServiceBinderWrapper.removeSubscription(s, bundle, mCallbacksMessenger);
                }
                // Misplaced declaration of an exception variable
                catch (Bundle bundle)
                {
                    Log.d("MediaBrowserCompat", (new StringBuilder()).append("removeSubscription failed with RemoteException parentId=").append(s).toString());
                }
            }
            if (true) goto _L2; else goto _L5
_L5:
        }

        public MediaBrowserImplApi21(Context context, ComponentName componentname, ConnectionCallback connectioncallback, Bundle bundle)
        {
            mServiceComponent = componentname;
            connectioncallback.setInternalConnectionCallback(this);
            mBrowserObj = MediaBrowserCompatApi21.createBrowser(context, componentname, connectioncallback.mConnectionCallbackObj, bundle);
        }
    }

    static class MediaBrowserImplApi23 extends MediaBrowserImplApi21
    {

        public void getItem(String s, ItemCallback itemcallback)
        {
            MediaBrowserCompatApi23.getItem(mBrowserObj, s, itemcallback.mItemCallbackObj);
        }

        public MediaBrowserImplApi23(Context context, ComponentName componentname, ConnectionCallback connectioncallback, Bundle bundle)
        {
            super(context, componentname, connectioncallback, bundle);
        }
    }

    static interface MediaBrowserServiceCallbackImpl
    {

        public abstract void onConnectionFailed(Messenger messenger);

        public abstract void onLoadChildren(Messenger messenger, String s, List list, Bundle bundle);

        public abstract void onServiceConnected(Messenger messenger, String s, android.support.v4.media.session.MediaSessionCompat.Token token, Bundle bundle);
    }

    static class MediaBrowserServiceImplBase
        implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl
    {

        private static final int CONNECT_STATE_CONNECTED = 2;
        private static final int CONNECT_STATE_CONNECTING = 1;
        private static final int CONNECT_STATE_DISCONNECTED = 0;
        private static final int CONNECT_STATE_SUSPENDED = 3;
        private static final boolean DBG = false;
        private final ConnectionCallback mCallback;
        private Messenger mCallbacksMessenger;
        private final Context mContext;
        private Bundle mExtras;
        private final CallbackHandler mHandler = new CallbackHandler(this);
        private android.support.v4.media.session.MediaSessionCompat.Token mMediaSessionToken;
        private final Bundle mRootHints;
        private String mRootId;
        private ServiceBinderWrapper mServiceBinderWrapper;
        private final ComponentName mServiceComponent;
        private MediaServiceConnection mServiceConnection;
        private int mState;
        private final ArrayMap mSubscriptions = new ArrayMap();

        private void forceCloseConnection()
        {
            if (mServiceConnection != null)
            {
                mContext.unbindService(mServiceConnection);
            }
            mState = 0;
            mServiceConnection = null;
            mServiceBinderWrapper = null;
            mCallbacksMessenger = null;
            mRootId = null;
            mMediaSessionToken = null;
        }

        private static String getStateLabel(int i)
        {
            switch (i)
            {
            default:
                return (new StringBuilder()).append("UNKNOWN/").append(i).toString();

            case 0: // '\0'
                return "CONNECT_STATE_DISCONNECTED";

            case 1: // '\001'
                return "CONNECT_STATE_CONNECTING";

            case 2: // '\002'
                return "CONNECT_STATE_CONNECTED";

            case 3: // '\003'
                return "CONNECT_STATE_SUSPENDED";
            }
        }

        private boolean isCurrent(Messenger messenger, String s)
        {
            if (mCallbacksMessenger != messenger)
            {
                if (mState != 0)
                {
                    Log.i("MediaBrowserCompat", (new StringBuilder()).append(s).append(" for ").append(mServiceComponent).append(" with mCallbacksMessenger=").append(mCallbacksMessenger).append(" this=").append(this).toString());
                }
                return false;
            } else
            {
                return true;
            }
        }

        public void connect()
        {
            MediaServiceConnection mediaserviceconnection;
            Intent intent;
            boolean flag;
            if (mState != 0)
            {
                throw new IllegalStateException((new StringBuilder()).append("connect() called while not disconnected (state=").append(getStateLabel(mState)).append(")").toString());
            }
            if (mServiceBinderWrapper != null)
            {
                throw new RuntimeException((new StringBuilder()).append("mServiceBinderWrapper should be null. Instead it is ").append(mServiceBinderWrapper).toString());
            }
            if (mCallbacksMessenger != null)
            {
                throw new RuntimeException((new StringBuilder()).append("mCallbacksMessenger should be null. Instead it is ").append(mCallbacksMessenger).toString());
            }
            mState = 1;
            intent = new Intent("android.media.browse.MediaBrowserService");
            intent.setComponent(mServiceComponent);
            mediaserviceconnection = new MediaServiceConnection();
            mServiceConnection = mediaserviceconnection;
            flag = false;
            boolean flag1 = mContext.bindService(intent, mServiceConnection, 1);
            flag = flag1;
_L2:
            if (!flag)
            {
                mHandler.post(mediaserviceconnection. new Runnable() {

                    final MediaBrowserServiceImplBase this$0;
                    final ServiceConnection val$thisConnection;

                    public void run()
                    {
                        if (thisConnection == mServiceConnection)
                        {
                            forceCloseConnection();
                            mCallback.onConnectionFailed();
                        }
                    }

            
            {
                this$0 = final_mediabrowserserviceimplbase;
                thisConnection = ServiceConnection.this;
                super();
            }
                });
            }
            return;
            Exception exception;
            exception;
            Log.e("MediaBrowserCompat", (new StringBuilder()).append("Failed binding to service ").append(mServiceComponent).toString());
            if (true) goto _L2; else goto _L1
_L1:
        }

        public void disconnect()
        {
            if (mCallbacksMessenger != null)
            {
                try
                {
                    mServiceBinderWrapper.disconnect(mCallbacksMessenger);
                }
                catch (RemoteException remoteexception)
                {
                    Log.w("MediaBrowserCompat", (new StringBuilder()).append("RemoteException during connect for ").append(mServiceComponent).toString());
                }
            }
            forceCloseConnection();
        }

        void dump()
        {
            Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mServiceComponent=").append(mServiceComponent).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mCallback=").append(mCallback).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mRootHints=").append(mRootHints).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mState=").append(getStateLabel(mState)).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mServiceConnection=").append(mServiceConnection).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mServiceBinderWrapper=").append(mServiceBinderWrapper).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mCallbacksMessenger=").append(mCallbacksMessenger).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mRootId=").append(mRootId).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mMediaSessionToken=").append(mMediaSessionToken).toString());
        }

        public Bundle getExtras()
        {
            if (!isConnected())
            {
                throw new IllegalStateException((new StringBuilder()).append("getExtras() called while not connected (state=").append(getStateLabel(mState)).append(")").toString());
            } else
            {
                return mExtras;
            }
        }

        public void getItem(String s, final ItemCallback cb)
        {
            if (TextUtils.isEmpty(s))
            {
                throw new IllegalArgumentException("mediaId is empty.");
            }
            if (cb == null)
            {
                throw new IllegalArgumentException("cb is null.");
            }
            if (mState != 2)
            {
                Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                mHandler.post(s. new Runnable() {

                    final MediaBrowserServiceImplBase this$0;
                    final ItemCallback val$cb;
                    final String val$mediaId;

                    public void run()
                    {
                        cb.onError(mediaId);
                    }

            
            {
                this$0 = final_mediabrowserserviceimplbase;
                cb = itemcallback;
                mediaId = String.this;
                super();
            }
                });
                return;
            }
            ItemReceiver itemreceiver = new ItemReceiver(s, cb, mHandler);
            try
            {
                mServiceBinderWrapper.getMediaItem(s, itemreceiver);
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.i("MediaBrowserCompat", "Remote error getting media item.");
            }
            mHandler.post(s. new Runnable() {

                final MediaBrowserServiceImplBase this$0;
                final ItemCallback val$cb;
                final String val$mediaId;

                public void run()
                {
                    cb.onError(mediaId);
                }

            
            {
                this$0 = final_mediabrowserserviceimplbase;
                cb = itemcallback;
                mediaId = String.this;
                super();
            }
            });
        }

        public String getRoot()
        {
            if (!isConnected())
            {
                throw new IllegalStateException((new StringBuilder()).append("getRoot() called while not connected(state=").append(getStateLabel(mState)).append(")").toString());
            } else
            {
                return mRootId;
            }
        }

        public ComponentName getServiceComponent()
        {
            if (!isConnected())
            {
                throw new IllegalStateException((new StringBuilder()).append("getServiceComponent() called while not connected (state=").append(mState).append(")").toString());
            } else
            {
                return mServiceComponent;
            }
        }

        public android.support.v4.media.session.MediaSessionCompat.Token getSessionToken()
        {
            if (!isConnected())
            {
                throw new IllegalStateException((new StringBuilder()).append("getSessionToken() called while not connected(state=").append(mState).append(")").toString());
            } else
            {
                return mMediaSessionToken;
            }
        }

        public boolean isConnected()
        {
            return mState == 2;
        }

        public void onConnectionFailed(Messenger messenger)
        {
            Log.e("MediaBrowserCompat", (new StringBuilder()).append("onConnectFailed for ").append(mServiceComponent).toString());
            if (!isCurrent(messenger, "onConnectFailed"))
            {
                return;
            }
            if (mState != 1)
            {
                Log.w("MediaBrowserCompat", (new StringBuilder()).append("onConnect from service while mState=").append(getStateLabel(mState)).append("... ignoring").toString());
                return;
            } else
            {
                forceCloseConnection();
                mCallback.onConnectionFailed();
                return;
            }
        }

        public void onLoadChildren(Messenger messenger, String s, List list, Bundle bundle)
        {
            if (isCurrent(messenger, "onLoadChildren"))
            {
                if ((messenger = (Subscription)mSubscriptions.get(s)) != null && (messenger = messenger.getCallback(bundle)) != null)
                {
                    if (bundle == null)
                    {
                        messenger.onChildrenLoaded(s, list);
                        return;
                    } else
                    {
                        messenger.onChildrenLoaded(s, list, bundle);
                        return;
                    }
                }
            }
        }

        public void onServiceConnected(Messenger messenger, String s, android.support.v4.media.session.MediaSessionCompat.Token token, Bundle bundle)
        {
            if (isCurrent(messenger, "onConnect")) goto _L2; else goto _L1
_L1:
            return;
_L2:
            if (mState != 1)
            {
                Log.w("MediaBrowserCompat", (new StringBuilder()).append("onConnect from service while mState=").append(getStateLabel(mState)).append("... ignoring").toString());
                return;
            }
            mRootId = s;
            mMediaSessionToken = token;
            mExtras = bundle;
            mState = 2;
            mCallback.onConnected();
            try
            {
                messenger = mSubscriptions.entrySet().iterator();
                while (messenger.hasNext()) 
                {
                    token = (java.util.Map.Entry)messenger.next();
                    s = (String)token.getKey();
                    token = ((Subscription)token.getValue()).getOptionsList().iterator();
                    while (token.hasNext()) 
                    {
                        bundle = (Bundle)token.next();
                        mServiceBinderWrapper.addSubscription(s, bundle, mCallbacksMessenger);
                    }
                }
            }
            // Misplaced declaration of an exception variable
            catch (Messenger messenger)
            {
                Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
                return;
            }
            if (true) goto _L1; else goto _L3
_L3:
        }

        public void subscribe(String s, Bundle bundle, SubscriptionCallback subscriptioncallback)
        {
            if (TextUtils.isEmpty(s))
            {
                throw new IllegalArgumentException("parentId is empty.");
            }
            if (subscriptioncallback == null)
            {
                throw new IllegalArgumentException("callback is null");
            }
            Subscription subscription1 = (Subscription)mSubscriptions.get(s);
            Subscription subscription = subscription1;
            if (subscription1 == null)
            {
                subscription = new Subscription();
                mSubscriptions.put(s, subscription);
            }
            subscription.setCallbackForOptions(subscriptioncallback, bundle);
            if (mState != 2)
            {
                break MISSING_BLOCK_LABEL_103;
            }
            mServiceBinderWrapper.addSubscription(s, bundle, mCallbacksMessenger);
            return;
            bundle;
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("addSubscription failed with RemoteException parentId=").append(s).toString());
            return;
        }

        public void unsubscribe(String s, Bundle bundle)
        {
            if (TextUtils.isEmpty(s))
            {
                throw new IllegalArgumentException("parentId is empty.");
            }
            Subscription subscription = (Subscription)mSubscriptions.get(s);
            if (subscription != null && subscription.remove(bundle) && mState == 2)
            {
                try
                {
                    mServiceBinderWrapper.removeSubscription(s, bundle, mCallbacksMessenger);
                }
                // Misplaced declaration of an exception variable
                catch (Bundle bundle)
                {
                    Log.d("MediaBrowserCompat", (new StringBuilder()).append("removeSubscription failed with RemoteException parentId=").append(s).toString());
                }
            }
            if (subscription != null && subscription.isEmpty())
            {
                mSubscriptions.remove(s);
            }
        }



/*
        static ServiceBinderWrapper access$1102(MediaBrowserServiceImplBase mediabrowserserviceimplbase, ServiceBinderWrapper servicebinderwrapper)
        {
            mediabrowserserviceimplbase.mServiceBinderWrapper = servicebinderwrapper;
            return servicebinderwrapper;
        }

*/



/*
        static Messenger access$1202(MediaBrowserServiceImplBase mediabrowserserviceimplbase, Messenger messenger)
        {
            mediabrowserserviceimplbase.mCallbacksMessenger = messenger;
            return messenger;
        }

*/




/*
        static int access$1402(MediaBrowserServiceImplBase mediabrowserserviceimplbase, int i)
        {
            mediabrowserserviceimplbase.mState = i;
            return i;
        }

*/







        public MediaBrowserServiceImplBase(Context context, ComponentName componentname, ConnectionCallback connectioncallback, Bundle bundle)
        {
            mState = 0;
            if (context == null)
            {
                throw new IllegalArgumentException("context must not be null");
            }
            if (componentname == null)
            {
                throw new IllegalArgumentException("service component must not be null");
            }
            if (connectioncallback == null)
            {
                throw new IllegalArgumentException("connection callback must not be null");
            } else
            {
                mContext = context;
                mServiceComponent = componentname;
                mCallback = connectioncallback;
                mRootHints = bundle;
                return;
            }
        }
    }

    private class MediaBrowserServiceImplBase.MediaServiceConnection
        implements ServiceConnection
    {

        final MediaBrowserServiceImplBase this$0;

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

                final MediaBrowserServiceImplBase.MediaServiceConnection this$1;
                final IBinder val$binder;
                final ComponentName val$name;

                public void run()
                {
                    if (!isCurrent("onServiceConnected"))
                    {
                        return;
                    }
                    mServiceBinderWrapper = new ServiceBinderWrapper(binder);
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

                final MediaBrowserServiceImplBase.MediaServiceConnection this$1;
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
                this$1 = final_mediaserviceconnection;
                name = ComponentName.this;
                super();
            }
            });
        }


        private MediaBrowserServiceImplBase.MediaServiceConnection()
        {
            this$0 = MediaBrowserServiceImplBase.this;
            super();
        }

    }

    public static class MediaItem
        implements Parcelable
    {

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public MediaItem createFromParcel(Parcel parcel)
            {
                return new MediaItem(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public MediaItem[] newArray(int i)
            {
                return new MediaItem[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        };
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final MediaDescriptionCompat mDescription;
        private final int mFlags;

        public int describeContents()
        {
            return 0;
        }

        public MediaDescriptionCompat getDescription()
        {
            return mDescription;
        }

        public int getFlags()
        {
            return mFlags;
        }

        public String getMediaId()
        {
            return mDescription.getMediaId();
        }

        public boolean isBrowsable()
        {
            return (mFlags & 1) != 0;
        }

        public boolean isPlayable()
        {
            return (mFlags & 2) != 0;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder("MediaItem{");
            stringbuilder.append("mFlags=").append(mFlags);
            stringbuilder.append(", mDescription=").append(mDescription);
            stringbuilder.append('}');
            return stringbuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mFlags);
            mDescription.writeToParcel(parcel, i);
        }


        private MediaItem(Parcel parcel)
        {
            mFlags = parcel.readInt();
            mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }


        public MediaItem(MediaDescriptionCompat mediadescriptioncompat, int i)
        {
            if (mediadescriptioncompat == null)
            {
                throw new IllegalArgumentException("description cannot be null");
            }
            if (TextUtils.isEmpty(mediadescriptioncompat.getMediaId()))
            {
                throw new IllegalArgumentException("description must have a non-empty media id");
            } else
            {
                mFlags = i;
                mDescription = mediadescriptioncompat;
                return;
            }
        }
    }

    public static interface MediaItem.Flags
        extends Annotation
    {
    }

    private static class ServiceBinderWrapper
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

        public ServiceBinderWrapper(IBinder ibinder)
        {
            mMessenger = new Messenger(ibinder);
        }
    }

    private static class Subscription
    {

        private final List mCallbacks = new ArrayList();
        private final List mOptionsList = new ArrayList();

        public SubscriptionCallback getCallback(Bundle bundle)
        {
            for (int i = 0; i < mOptionsList.size(); i++)
            {
                if (MediaBrowserCompatUtils.areSameOptions((Bundle)mOptionsList.get(i), bundle))
                {
                    return (SubscriptionCallback)mCallbacks.get(i);
                }
            }

            return null;
        }

        public List getCallbacks()
        {
            return mCallbacks;
        }

        public List getOptionsList()
        {
            return mOptionsList;
        }

        public boolean isEmpty()
        {
            return mCallbacks.isEmpty();
        }

        public boolean remove(Bundle bundle)
        {
            for (int i = 0; i < mOptionsList.size(); i++)
            {
                if (MediaBrowserCompatUtils.areSameOptions((Bundle)mOptionsList.get(i), bundle))
                {
                    mCallbacks.remove(i);
                    mOptionsList.remove(i);
                    return true;
                }
            }

            return false;
        }

        public void setCallbackForOptions(SubscriptionCallback subscriptioncallback, Bundle bundle)
        {
            for (int i = 0; i < mOptionsList.size(); i++)
            {
                if (MediaBrowserCompatUtils.areSameOptions((Bundle)mOptionsList.get(i), bundle))
                {
                    mCallbacks.set(i, subscriptioncallback);
                    return;
                }
            }

            mCallbacks.add(subscriptioncallback);
            mOptionsList.add(bundle);
        }

        public Subscription()
        {
        }
    }

    public static abstract class SubscriptionCallback
    {

        public void onChildrenLoaded(String s, List list)
        {
        }

        public void onChildrenLoaded(String s, List list, Bundle bundle)
        {
        }

        public void onError(String s)
        {
        }

        public void onError(String s, Bundle bundle)
        {
        }

        public SubscriptionCallback()
        {
        }
    }

    static class SubscriptionCallbackApi21 extends SubscriptionCallback
    {

        private Bundle mOptions;
        SubscriptionCallback mSubscriptionCallback;
        private final Object mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21());

        public void onChildrenLoaded(String s, List list)
        {
            mSubscriptionCallback.onChildrenLoaded(s, list);
        }

        public void onChildrenLoaded(String s, List list, Bundle bundle)
        {
            mSubscriptionCallback.onChildrenLoaded(s, list, bundle);
        }

        public void onError(String s)
        {
            mSubscriptionCallback.onError(s);
        }

        public void onError(String s, Bundle bundle)
        {
            mSubscriptionCallback.onError(s, bundle);
        }



        public SubscriptionCallbackApi21(SubscriptionCallback subscriptioncallback, Bundle bundle)
        {
            mSubscriptionCallback = subscriptioncallback;
            mOptions = bundle;
        }
    }

    private class SubscriptionCallbackApi21.StubApi21
        implements MediaBrowserCompatApi21.SubscriptionCallback
    {

        final SubscriptionCallbackApi21 this$0;

        public void onChildrenLoaded(String s, List list)
        {
            Object obj = null;
            if (list != null)
            {
                ArrayList arraylist = new ArrayList();
                list = list.iterator();
                do
                {
                    obj = arraylist;
                    if (!list.hasNext())
                    {
                        break;
                    }
                    obj = (Parcel)list.next();
                    ((Parcel) (obj)).setDataPosition(0);
                    arraylist.add(MediaItem.CREATOR.createFromParcel(((Parcel) (obj))));
                    ((Parcel) (obj)).recycle();
                } while (true);
            }
            if (mOptions != null)
            {
                SubscriptionCallbackApi21.this.onChildrenLoaded(s, MediaBrowserCompatUtils.applyOptions(((List) (obj)), mOptions), mOptions);
                return;
            } else
            {
                SubscriptionCallbackApi21.this.onChildrenLoaded(s, ((List) (obj)));
                return;
            }
        }

        public void onError(String s)
        {
            if (mOptions != null)
            {
                SubscriptionCallbackApi21.this.onError(s, mOptions);
                return;
            } else
            {
                SubscriptionCallbackApi21.this.onError(s);
                return;
            }
        }

        private SubscriptionCallbackApi21.StubApi21()
        {
            this$0 = SubscriptionCallbackApi21.this;
            super();
        }

    }


    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    private static final String TAG = "MediaBrowserCompat";
    private final MediaBrowserImpl mImpl;

    public MediaBrowserCompat(Context context, ComponentName componentname, ConnectionCallback connectioncallback, Bundle bundle)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            mImpl = new MediaBrowserImplApi23(context, componentname, connectioncallback, bundle);
            return;
        }
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            mImpl = new MediaBrowserImplApi21(context, componentname, connectioncallback, bundle);
            return;
        } else
        {
            mImpl = new MediaBrowserServiceImplBase(context, componentname, connectioncallback, bundle);
            return;
        }
    }

    public void connect()
    {
        mImpl.connect();
    }

    public void disconnect()
    {
        mImpl.disconnect();
    }

    public Bundle getExtras()
    {
        return mImpl.getExtras();
    }

    public void getItem(String s, ItemCallback itemcallback)
    {
        mImpl.getItem(s, itemcallback);
    }

    public String getRoot()
    {
        return mImpl.getRoot();
    }

    public ComponentName getServiceComponent()
    {
        return mImpl.getServiceComponent();
    }

    public android.support.v4.media.session.MediaSessionCompat.Token getSessionToken()
    {
        return mImpl.getSessionToken();
    }

    public boolean isConnected()
    {
        return mImpl.isConnected();
    }

    public void subscribe(String s, Bundle bundle, SubscriptionCallback subscriptioncallback)
    {
        if (bundle == null)
        {
            throw new IllegalArgumentException("options are null");
        } else
        {
            mImpl.subscribe(s, bundle, subscriptioncallback);
            return;
        }
    }

    public void subscribe(String s, SubscriptionCallback subscriptioncallback)
    {
        mImpl.subscribe(s, null, subscriptioncallback);
    }

    public void unsubscribe(String s)
    {
        mImpl.unsubscribe(s, null);
    }

    public void unsubscribe(String s, Bundle bundle)
    {
        if (bundle == null)
        {
            throw new IllegalArgumentException("options are null");
        } else
        {
            mImpl.unsubscribe(s, bundle);
            return;
        }
    }
}
