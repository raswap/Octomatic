// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Window;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImplV11, TwilightManager, AppCompatCallback

class AppCompatDelegateImplV14 extends AppCompatDelegateImplV11
{
    class AppCompatWindowCallbackV14 extends AppCompatDelegateImplBase.AppCompatWindowCallbackBase
    {

        final AppCompatDelegateImplV14 this$0;

        public ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback)
        {
            if (isHandleNativeActionModesEnabled())
            {
                return startAsSupportActionMode(callback);
            } else
            {
                return super.onWindowStartingActionMode(callback);
            }
        }

        final ActionMode startAsSupportActionMode(android.view.ActionMode.Callback callback)
        {
            callback = new android.support.v7.view.SupportActionModeWrapper.CallbackWrapper(mContext, callback);
            android.support.v7.view.ActionMode actionmode = startSupportActionMode(callback);
            if (actionmode != null)
            {
                return callback.getActionModeWrapper(actionmode);
            } else
            {
                return null;
            }
        }

        AppCompatWindowCallbackV14(android.view.Window.Callback callback)
        {
            this$0 = AppCompatDelegateImplV14.this;
            super(AppCompatDelegateImplV14.this, callback);
        }
    }


    private static final String KEY_LOCAL_NIGHT_MODE = "appcompat:local_night_mode";
    private static TwilightManager sTwilightManager;
    private boolean mApplyDayNightCalled;
    private boolean mHandleNativeActionModes;
    private int mLocalNightMode;

    AppCompatDelegateImplV14(Context context, Window window, AppCompatCallback appcompatcallback)
    {
        super(context, window, appcompatcallback);
        mLocalNightMode = -100;
        mHandleNativeActionModes = true;
    }

    private int getNightModeToApply()
    {
        int i;
        if (mLocalNightMode == -100)
        {
            i = getDefaultNightMode();
        } else
        {
            i = mLocalNightMode;
        }
        return mapNightModeToYesNo(i);
    }

    private TwilightManager getTwilightManager()
    {
        if (sTwilightManager == null)
        {
            sTwilightManager = new TwilightManager(mContext.getApplicationContext());
        }
        return sTwilightManager;
    }

    private int mapNightModeToYesNo(int i)
    {
        byte byte1 = 2;
        byte byte0 = byte1;
        switch (i)
        {
        case 1: // '\001'
        default:
            byte0 = 1;
            break;

        case 0: // '\0'
            byte0 = byte1;
            if (!getTwilightManager().isNight())
            {
                return 1;
            }
            continue;

        case -1: 
            byte0 = byte1;
            switch (((UiModeManager)mContext.getSystemService("uimode")).getNightMode())
            {
            case 1: // '\001'
            default:
                return 1;

            case 0: // '\0'
                return 0;

            case 2: // '\002'
                break;
            }
            break;

        case 2: // '\002'
            break;
        }
        while (true) 
        {
            return byte0;
        }
    }

    private boolean updateConfigurationForNightMode(int i)
    {
        Resources resources;
        Configuration configuration;
        boolean flag;
        int j;
        resources = mContext.getResources();
        configuration = resources.getConfiguration();
        j = configuration.uiMode;
        flag = false;
        i;
        JVM INSTR tableswitch 1 2: default 44
    //                   1 77
    //                   2 83;
           goto _L1 _L2 _L3
_L3:
        break MISSING_BLOCK_LABEL_83;
_L1:
        i = ((flag) ? 1 : 0);
_L4:
        if ((j & 0x30) != i)
        {
            configuration.uiMode = configuration.uiMode & 0xffffffcf | i;
            resources.updateConfiguration(configuration, null);
            return true;
        } else
        {
            return false;
        }
_L2:
        i = 16;
          goto _L4
        i = 32;
          goto _L4
    }

    public boolean applyDayNight()
    {
        mApplyDayNightCalled = true;
        return updateConfigurationForNightMode(getNightModeToApply());
    }

    public boolean isHandleNativeActionModesEnabled()
    {
        return mHandleNativeActionModes;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null && mLocalNightMode == -100)
        {
            mLocalNightMode = bundle.getInt("appcompat:local_night_mode", -100);
        }
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        if (mLocalNightMode != -100)
        {
            bundle.putInt("appcompat:local_night_mode", mLocalNightMode);
        }
    }

    public void setHandleNativeActionModesEnabled(boolean flag)
    {
        mHandleNativeActionModes = flag;
    }

    public void setLocalNightMode(int i)
    {
        i;
        JVM INSTR tableswitch -1 2: default 32
    //                   -1 41
    //                   0 41
    //                   1 41
    //                   2 41;
           goto _L1 _L2 _L2 _L2 _L2
_L1:
        Log.d("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
_L4:
        return;
_L2:
        if (mLocalNightMode != i)
        {
            mLocalNightMode = i;
            if (mApplyDayNightCalled)
            {
                applyDayNight();
                return;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    android.view.Window.Callback wrapWindowCallback(android.view.Window.Callback callback)
    {
        return new AppCompatWindowCallbackV14(callback);
    }
}
