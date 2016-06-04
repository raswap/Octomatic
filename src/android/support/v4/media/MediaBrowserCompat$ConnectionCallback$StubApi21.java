// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;


// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

private class <init>
    implements <init>
{

    final onSuspended this$0;

    public void onConnected()
    {
        if ((this._cls0.this) != null)
        {
            (this._cls0.this).onConnected();
        }
        d();
    }

    public void onConnectionFailed()
    {
        if ((this._cls0.this) != null)
        {
            (this._cls0.this).onConnectionFailed();
        }
        onFailed();
    }

    public void onConnectionSuspended()
    {
        if ((this._cls0.this) != null)
        {
            (this._cls0.this).onConnectionSuspended();
        }
        onSuspended();
    }

    private CallbackInternal()
    {
        this$0 = this._cls0.this;
        super();
    }

    this._cls0(this._cls0 _pcls0_1)
    {
        this();
    }
}
