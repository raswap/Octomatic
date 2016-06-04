// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            TintInfo, AppCompatDrawableManager, DrawableUtils

class AppCompatBackgroundHelper
{

    private TintInfo mBackgroundTint;
    private final AppCompatDrawableManager mDrawableManager;
    private TintInfo mInternalBackgroundTint;
    private TintInfo mTmpInfo;
    private final View mView;

    AppCompatBackgroundHelper(View view, AppCompatDrawableManager appcompatdrawablemanager)
    {
        mView = view;
        mDrawableManager = appcompatdrawablemanager;
    }

    private void compatTintDrawableUsingFrameworkTint(Drawable drawable)
    {
        if (mTmpInfo == null)
        {
            mTmpInfo = new TintInfo();
        }
        TintInfo tintinfo = mTmpInfo;
        tintinfo.clear();
        Object obj = ViewCompat.getBackgroundTintList(mView);
        if (obj != null)
        {
            tintinfo.mHasTintList = true;
            tintinfo.mTintList = ((ColorStateList) (obj));
        }
        obj = ViewCompat.getBackgroundTintMode(mView);
        if (obj != null)
        {
            tintinfo.mHasTintMode = true;
            tintinfo.mTintMode = ((android.graphics.PorterDuff.Mode) (obj));
        }
        if (tintinfo.mHasTintList || tintinfo.mHasTintMode)
        {
            AppCompatDrawableManager.tintDrawable(drawable, tintinfo, mView.getDrawableState());
        }
    }

    private boolean shouldCompatTintUsingFrameworkTint(Drawable drawable)
    {
        return android.os.Build.VERSION.SDK_INT == 21 && (drawable instanceof GradientDrawable);
    }

    void applySupportBackgroundTint()
    {
        Drawable drawable = mView.getBackground();
        if (drawable != null)
        {
            if (mBackgroundTint != null)
            {
                AppCompatDrawableManager.tintDrawable(drawable, mBackgroundTint, mView.getDrawableState());
            } else
            {
                if (mInternalBackgroundTint != null)
                {
                    AppCompatDrawableManager.tintDrawable(drawable, mInternalBackgroundTint, mView.getDrawableState());
                    return;
                }
                if (shouldCompatTintUsingFrameworkTint(drawable))
                {
                    compatTintDrawableUsingFrameworkTint(drawable);
                    return;
                }
            }
        }
    }

    ColorStateList getSupportBackgroundTintList()
    {
        if (mBackgroundTint != null)
        {
            return mBackgroundTint.mTintList;
        } else
        {
            return null;
        }
    }

    android.graphics.PorterDuff.Mode getSupportBackgroundTintMode()
    {
        if (mBackgroundTint != null)
        {
            return mBackgroundTint.mTintMode;
        } else
        {
            return null;
        }
    }

    void loadFromAttributes(AttributeSet attributeset, int i)
    {
        attributeset = mView.getContext().obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.ViewBackgroundHelper, i, 0);
        ColorStateList colorstatelist;
        if (!attributeset.hasValue(android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_android_background))
        {
            break MISSING_BLOCK_LABEL_59;
        }
        colorstatelist = mDrawableManager.getTintList(mView.getContext(), attributeset.getResourceId(android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_android_background, -1));
        if (colorstatelist == null)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        setInternalBackgroundTint(colorstatelist);
        if (attributeset.hasValue(android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_backgroundTint))
        {
            ViewCompat.setBackgroundTintList(mView, attributeset.getColorStateList(android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_backgroundTint));
        }
        if (attributeset.hasValue(android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_backgroundTintMode))
        {
            ViewCompat.setBackgroundTintMode(mView, DrawableUtils.parseTintMode(attributeset.getInt(android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
        }
        attributeset.recycle();
        return;
        Exception exception;
        exception;
        attributeset.recycle();
        throw exception;
    }

    void onSetBackgroundDrawable(Drawable drawable)
    {
        setInternalBackgroundTint(null);
    }

    void onSetBackgroundResource(int i)
    {
        ColorStateList colorstatelist;
        if (mDrawableManager != null)
        {
            colorstatelist = mDrawableManager.getTintList(mView.getContext(), i);
        } else
        {
            colorstatelist = null;
        }
        setInternalBackgroundTint(colorstatelist);
    }

    void setInternalBackgroundTint(ColorStateList colorstatelist)
    {
        if (colorstatelist != null)
        {
            if (mInternalBackgroundTint == null)
            {
                mInternalBackgroundTint = new TintInfo();
            }
            mInternalBackgroundTint.mTintList = colorstatelist;
            mInternalBackgroundTint.mHasTintList = true;
        } else
        {
            mInternalBackgroundTint = null;
        }
        applySupportBackgroundTint();
    }

    void setSupportBackgroundTintList(ColorStateList colorstatelist)
    {
        if (mBackgroundTint == null)
        {
            mBackgroundTint = new TintInfo();
        }
        mBackgroundTint.mTintList = colorstatelist;
        mBackgroundTint.mHasTintList = true;
        applySupportBackgroundTint();
    }

    void setSupportBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if (mBackgroundTint == null)
        {
            mBackgroundTint = new TintInfo();
        }
        mBackgroundTint.mTintMode = mode;
        mBackgroundTint.mHasTintMode = true;
        applySupportBackgroundTint();
    }
}
