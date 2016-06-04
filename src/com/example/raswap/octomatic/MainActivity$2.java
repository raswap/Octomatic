// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.example.raswap.octomatic;

import android.content.Intent;
import android.view.View;

// Referenced classes of package com.example.raswap.octomatic:
//            MainActivity, eshop

class this._cls0
    implements android.view.tener
{

    final MainActivity this$0;

    public void onClick(View view)
    {
        startActivity(new Intent(MainActivity.this, com/example/raswap/octomatic/eshop));
    }

    ()
    {
        this$0 = MainActivity.this;
        super();
    }
}
