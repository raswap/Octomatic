// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v4.graphics.drawable:
//            DrawableWrapperHoneycomb

private static class a extends a
{

    public Drawable newDrawable(Resources resources)
    {
        return new DrawableWrapperHoneycomb(this, resources);
    }

    a(a a, Resources resources)
    {
        super(a, resources);
    }
}
