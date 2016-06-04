// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.example.raswap.octomatic;

import android.content.Intent;

// Referenced classes of package com.example.raswap.octomatic:
//            splash_screen, MainActivity

class this._cls0
    implements Runnable
{

    final splash_screen this$0;

    public void run()
    {
        Intent intent = new Intent(splash_screen.this, com/example/raswap/octomatic/MainActivity);
        startActivity(intent);
        finish();
    }

    ()
    {
        this$0 = splash_screen.this;
        super();
    }
}
