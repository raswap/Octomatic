// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v7.widget:
//            AppCompatDrawableManager

public class TintResources extends Resources
{

    private final WeakReference mContextRef;

    public TintResources(Context context, Resources resources)
    {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        mContextRef = new WeakReference(context);
    }

    public Drawable getDrawable(int i)
        throws android.content.res.Resources.NotFoundException
    {
        Context context = (Context)mContextRef.get();
        if (context != null)
        {
            return AppCompatDrawableManager.get().onDrawableLoadedFromResources(context, this, i);
        } else
        {
            return super.getDrawable(i);
        }
    }

    final Drawable superGetDrawable(int i)
    {
        return super.getDrawable(i);
    }
}
