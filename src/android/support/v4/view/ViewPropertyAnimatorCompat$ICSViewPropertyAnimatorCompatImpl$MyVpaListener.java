// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.View;

// Referenced classes of package android.support.v4.view:
//            ViewPropertyAnimatorListener, ViewPropertyAnimatorCompat, ViewCompat

static class mVpa
    implements ViewPropertyAnimatorListener
{

    boolean mAnimEndCalled;
    ViewPropertyAnimatorCompat mVpa;

    public void onAnimationCancel(View view)
    {
        Object obj = view.getTag(0x7e000000);
        ViewPropertyAnimatorListener viewpropertyanimatorlistener = null;
        if (obj instanceof ViewPropertyAnimatorListener)
        {
            viewpropertyanimatorlistener = (ViewPropertyAnimatorListener)obj;
        }
        if (viewpropertyanimatorlistener != null)
        {
            viewpropertyanimatorlistener.onAnimationCancel(view);
        }
    }

    public void onAnimationEnd(View view)
    {
        if (ViewPropertyAnimatorCompat.access$400(mVpa) >= 0)
        {
            ViewCompat.setLayerType(view, ViewPropertyAnimatorCompat.access$400(mVpa), null);
            ViewPropertyAnimatorCompat.access$402(mVpa, -1);
        }
        if (android.os.yVpaListener.mVpa >= 16 || !mAnimEndCalled)
        {
            if (ViewPropertyAnimatorCompat.access$000(mVpa) != null)
            {
                Runnable runnable = ViewPropertyAnimatorCompat.access$000(mVpa);
                ViewPropertyAnimatorCompat.access$002(mVpa, null);
                runnable.run();
            }
            Object obj = view.getTag(0x7e000000);
            ViewPropertyAnimatorListener viewpropertyanimatorlistener = null;
            if (obj instanceof ViewPropertyAnimatorListener)
            {
                viewpropertyanimatorlistener = (ViewPropertyAnimatorListener)obj;
            }
            if (viewpropertyanimatorlistener != null)
            {
                viewpropertyanimatorlistener.onAnimationEnd(view);
            }
            mAnimEndCalled = true;
        }
    }

    public void onAnimationStart(View view)
    {
        mAnimEndCalled = false;
        if (ViewPropertyAnimatorCompat.access$400(mVpa) >= 0)
        {
            ViewCompat.setLayerType(view, 2, null);
        }
        if (ViewPropertyAnimatorCompat.access$100(mVpa) != null)
        {
            Runnable runnable = ViewPropertyAnimatorCompat.access$100(mVpa);
            ViewPropertyAnimatorCompat.access$102(mVpa, null);
            runnable.run();
        }
        Object obj = view.getTag(0x7e000000);
        ViewPropertyAnimatorListener viewpropertyanimatorlistener = null;
        if (obj instanceof ViewPropertyAnimatorListener)
        {
            viewpropertyanimatorlistener = (ViewPropertyAnimatorListener)obj;
        }
        if (viewpropertyanimatorlistener != null)
        {
            viewpropertyanimatorlistener.onAnimationStart(view);
        }
    }

    (ViewPropertyAnimatorCompat viewpropertyanimatorcompat)
    {
        mVpa = viewpropertyanimatorcompat;
    }
}
