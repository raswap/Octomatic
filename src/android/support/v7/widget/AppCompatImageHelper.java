// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

// Referenced classes of package android.support.v7.widget:
//            TintTypedArray, AppCompatDrawableManager, DrawableUtils

public class AppCompatImageHelper
{

    private final AppCompatDrawableManager mDrawableManager;
    private final ImageView mView;

    public AppCompatImageHelper(ImageView imageview, AppCompatDrawableManager appcompatdrawablemanager)
    {
        mView = imageview;
        mDrawableManager = appcompatdrawablemanager;
    }

    public void loadFromAttributes(AttributeSet attributeset, int i)
    {
        attributeset = TintTypedArray.obtainStyledAttributes(mView.getContext(), attributeset, android.support.v7.appcompat.R.styleable.AppCompatImageView, i, 0);
        android.graphics.drawable.Drawable drawable = attributeset.getDrawableIfKnown(android.support.v7.appcompat.R.styleable.AppCompatImageView_android_src);
        if (drawable == null)
        {
            break MISSING_BLOCK_LABEL_37;
        }
        mView.setImageDrawable(drawable);
        i = attributeset.getResourceId(android.support.v7.appcompat.R.styleable.AppCompatImageView_srcCompat, -1);
        if (i == -1)
        {
            break MISSING_BLOCK_LABEL_79;
        }
        drawable = mDrawableManager.getDrawable(mView.getContext(), i);
        if (drawable == null)
        {
            break MISSING_BLOCK_LABEL_79;
        }
        mView.setImageDrawable(drawable);
        drawable = mView.getDrawable();
        if (drawable == null)
        {
            break MISSING_BLOCK_LABEL_95;
        }
        DrawableUtils.fixDrawable(drawable);
        attributeset.recycle();
        return;
        Exception exception;
        exception;
        attributeset.recycle();
        throw exception;
    }

    public void setImageResource(int i)
    {
        if (i != 0)
        {
            android.graphics.drawable.Drawable drawable;
            if (mDrawableManager != null)
            {
                drawable = mDrawableManager.getDrawable(mView.getContext(), i);
            } else
            {
                drawable = ContextCompat.getDrawable(mView.getContext(), i);
            }
            if (drawable != null)
            {
                DrawableUtils.fixDrawable(drawable);
            }
            mView.setImageDrawable(drawable);
            return;
        } else
        {
            mView.setImageDrawable(null);
            return;
        }
    }
}
