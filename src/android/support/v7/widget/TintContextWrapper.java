// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.widget:
//            TintResources

public class TintContextWrapper extends ContextWrapper
{

    private static final ArrayList sCache = new ArrayList();
    private Resources mResources;
    private final android.content.res.Resources.Theme mTheme = getResources().newTheme();

    private TintContextWrapper(Context context)
    {
        super(context);
        mTheme.setTo(context.getTheme());
    }

    private static boolean shouldWrap(Context context)
    {
        while ((context instanceof TintContextWrapper) || (context.getResources() instanceof TintResources)) 
        {
            return false;
        }
        return true;
    }

    public static Context wrap(Context context)
    {
        if (shouldWrap(context))
        {
            int i = 0;
            Object obj;
            for (int j = sCache.size(); i < j; i++)
            {
                obj = (WeakReference)sCache.get(i);
                if (obj != null)
                {
                    obj = (TintContextWrapper)((WeakReference) (obj)).get();
                } else
                {
                    obj = null;
                }
                if (obj != null && ((TintContextWrapper) (obj)).getBaseContext() == context)
                {
                    return ((Context) (obj));
                }
            }

            context = new TintContextWrapper(context);
            sCache.add(new WeakReference(context));
            return context;
        } else
        {
            return context;
        }
    }

    public Resources getResources()
    {
        if (mResources == null)
        {
            mResources = new TintResources(this, super.getResources());
        }
        return mResources;
    }

    public android.content.res.Resources.Theme getTheme()
    {
        return mTheme;
    }

    public void setTheme(int i)
    {
        mTheme.applyStyle(i, true);
    }

}
