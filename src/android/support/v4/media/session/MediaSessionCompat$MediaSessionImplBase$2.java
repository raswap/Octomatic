// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.support.v4.media.RatingCompat;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompat

class this._cls0
    implements this._cls0
{

    final this._cls0 this$0;

    public void onSeekTo(long l)
    {
        cess._mth700(this._cls0.this, 11, Long.valueOf(l));
    }

    public void onSetRating(Object obj)
    {
        cess._mth700(this._cls0.this, 12, RatingCompat.fromRating(obj));
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
