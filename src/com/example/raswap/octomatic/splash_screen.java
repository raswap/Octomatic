

package com.example.raswap.octomatic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

// Referenced classes of package com.example.raswap.octomatic:
//            MainActivity

public class splash_screen extends Activity
{

    private static int SPLASH_TIME_OUT = 3000;

    public splash_screen()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f04001c);
        (new Handler()).postDelayed(new Runnable() {

            final splash_screen this$0;

            public void run()
            {
                Intent intent = new Intent(splash_screen.this, com/example/raswap/octomatic/MainActivity);
                startActivity(intent);
                finish();
            }

            
            {
                this$0 = splash_screen.this;
                super();
            }
        }, SPLASH_TIME_OUT);
    }

}
