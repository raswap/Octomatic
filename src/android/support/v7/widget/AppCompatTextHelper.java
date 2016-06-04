// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.text.AllCapsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;

// Referenced classes of package android.support.v7.widget:
//            AppCompatTextHelperV17, AppCompatDrawableManager, TintInfo

class AppCompatTextHelper
{

    private static final int TEXT_APPEARANCE_ATTRS[];
    private static final int VIEW_ATTRS[] = {
        0x1010034, 0x101016f, 0x101016d, 0x1010170, 0x101016e
    };
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableTopTint;
    final TextView mView;

    AppCompatTextHelper(TextView textview)
    {
        mView = textview;
    }

    static AppCompatTextHelper create(TextView textview)
    {
        if (android.os.Build.VERSION.SDK_INT >= 17)
        {
            return new AppCompatTextHelperV17(textview);
        } else
        {
            return new AppCompatTextHelper(textview);
        }
    }

    protected static TintInfo createTintInfo(Context context, AppCompatDrawableManager appcompatdrawablemanager, int i)
    {
        context = appcompatdrawablemanager.getTintList(context, i);
        if (context != null)
        {
            appcompatdrawablemanager = new TintInfo();
            appcompatdrawablemanager.mHasTintList = true;
            appcompatdrawablemanager.mTintList = context;
            return appcompatdrawablemanager;
        } else
        {
            return null;
        }
    }

    final void applyCompoundDrawableTint(Drawable drawable, TintInfo tintinfo)
    {
        if (drawable != null && tintinfo != null)
        {
            AppCompatDrawableManager.tintDrawable(drawable, tintinfo, mView.getDrawableState());
        }
    }

    void applyCompoundDrawablesTints()
    {
        if (mDrawableLeftTint != null || mDrawableTopTint != null || mDrawableRightTint != null || mDrawableBottomTint != null)
        {
            Drawable adrawable[] = mView.getCompoundDrawables();
            applyCompoundDrawableTint(adrawable[0], mDrawableLeftTint);
            applyCompoundDrawableTint(adrawable[1], mDrawableTopTint);
            applyCompoundDrawableTint(adrawable[2], mDrawableRightTint);
            applyCompoundDrawableTint(adrawable[3], mDrawableBottomTint);
        }
    }

    void loadFromAttributes(AttributeSet attributeset, int i)
    {
        Context context = mView.getContext();
        AppCompatDrawableManager appcompatdrawablemanager = AppCompatDrawableManager.get();
        TypedArray typedarray1 = context.obtainStyledAttributes(attributeset, VIEW_ATTRS, i, 0);
        int j = typedarray1.getResourceId(0, -1);
        if (typedarray1.hasValue(1))
        {
            mDrawableLeftTint = createTintInfo(context, appcompatdrawablemanager, typedarray1.getResourceId(1, 0));
        }
        if (typedarray1.hasValue(2))
        {
            mDrawableTopTint = createTintInfo(context, appcompatdrawablemanager, typedarray1.getResourceId(2, 0));
        }
        if (typedarray1.hasValue(3))
        {
            mDrawableRightTint = createTintInfo(context, appcompatdrawablemanager, typedarray1.getResourceId(3, 0));
        }
        if (typedarray1.hasValue(4))
        {
            mDrawableBottomTint = createTintInfo(context, appcompatdrawablemanager, typedarray1.getResourceId(4, 0));
        }
        typedarray1.recycle();
        if (!(mView.getTransformationMethod() instanceof PasswordTransformationMethod))
        {
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag = false;
            boolean flag1 = false;
            if (j != -1)
            {
                TypedArray typedarray = context.obtainStyledAttributes(j, android.support.v7.appcompat.R.styleable.TextAppearance);
                flag2 = flag3;
                flag = flag1;
                if (typedarray.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps))
                {
                    flag = true;
                    flag2 = typedarray.getBoolean(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps, false);
                }
                typedarray.recycle();
            }
            attributeset = context.obtainStyledAttributes(attributeset, TEXT_APPEARANCE_ATTRS, i, 0);
            if (attributeset.hasValue(0))
            {
                flag = true;
                flag2 = attributeset.getBoolean(0, false);
            }
            attributeset.recycle();
            if (flag)
            {
                setAllCaps(flag2);
            }
        }
    }

    void onSetTextAppearance(Context context, int i)
    {
        context = context.obtainStyledAttributes(i, TEXT_APPEARANCE_ATTRS);
        if (context.getBoolean(0, false))
        {
            setAllCaps(true);
        }
        context.recycle();
    }

    void setAllCaps(boolean flag)
    {
        TextView textview = mView;
        AllCapsTransformationMethod allcapstransformationmethod;
        if (flag)
        {
            allcapstransformationmethod = new AllCapsTransformationMethod(mView.getContext());
        } else
        {
            allcapstransformationmethod = null;
        }
        textview.setTransformationMethod(allcapstransformationmethod);
    }

    static 
    {
        TEXT_APPEARANCE_ATTRS = (new int[] {
            android.support.v7.appcompat.R.attr.textAllCaps
        });
    }
}
