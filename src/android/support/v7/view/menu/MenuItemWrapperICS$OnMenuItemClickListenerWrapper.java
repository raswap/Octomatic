// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.view.MenuItem;

// Referenced classes of package android.support.v7.view.menu:
//            BaseWrapper, MenuItemWrapperICS

private class this._cls0 extends BaseWrapper
    implements android.view.ListenerWrapper
{

    final MenuItemWrapperICS this$0;

    public boolean onMenuItemClick(MenuItem menuitem)
    {
        return ((android.view.ListenerWrapper)mWrappedObject).mWrappedObject(getMenuItemWrapper(menuitem));
    }

    (android.view.ListenerWrapper listenerwrapper)
    {
        this$0 = MenuItemWrapperICS.this;
        super(listenerwrapper);
    }
}
