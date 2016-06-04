// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Parcel;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

private class <init>
    implements <init>
{

    final ed this$0;

    public void onError(String s)
    {
        <init>.this.<init>(s);
    }

    public void onItemLoaded(Parcel parcel)
    {
        parcel.setDataPosition(0);
        <init> <init>1 = (<init>)<init>.CREATOR(parcel);
        parcel.recycle();
        ed(<init>1);
    }

    private ()
    {
        this$0 = this._cls0.this;
        super();
    }

    this._cls0(this._cls0 _pcls0_1)
    {
        this();
    }
}
