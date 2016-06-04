// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat, MediaBrowserCompatUtils

private class <init>
    implements <init>
{

    final this._cls0 this$0;

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
                arraylist.add(<init>.i21(((Parcel) (obj))));
                ((Parcel) (obj)).recycle();
            } while (true);
        }
        if ((this._cls0.this) != null)
        {
            Loaded(s, MediaBrowserCompatUtils.applyOptions(((List) (obj)), (this._cls0.this)), (this._cls0.this));
            return;
        } else
        {
            Loaded(s, ((List) (obj)));
            return;
        }
    }

    public void onError(String s)
    {
        if ((this._cls0.this) != null)
        {
            _mth0(s, (this._cls0.this));
            return;
        } else
        {
            _mth0(s);
            return;
        }
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
