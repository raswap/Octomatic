// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v4.graphics.drawable:
//            DrawableCompat, DrawableCompatEclair

static class  extends 
{

    public Drawable wrap(Drawable drawable)
    {
        return DrawableCompatEclair.wrapForTinting(drawable);
    }

    ()
    {
    }
}
