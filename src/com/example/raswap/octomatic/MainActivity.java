
package com.example.raswap.octomatic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

// Referenced classes of package com.example.raswap.octomatic:
//            News_list, eshop

public class MainActivity extends AppCompatActivity
{

    static final boolean $assertionsDisabled;

    public MainActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f04001a);
        bundle = (Button)findViewById(0x7f0d0052);
        Button button = (Button)findViewById(0x7f0d0053);
        if (!$assertionsDisabled && bundle == null)
        {
            throw new AssertionError();
        }
        bundle.setOnClickListener(new android.view.View.OnClickListener() {

            final MainActivity this$0;

            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, com/example/raswap/octomatic/News_list));
            }

            
            {
                this$0 = MainActivity.this;
                super();
            }
        });
        if (!$assertionsDisabled && button == null)
        {
            throw new AssertionError();
        } else
        {
            button.setOnClickListener(new android.view.View.OnClickListener() {

                final MainActivity this$0;

                public void onClick(View view)
                {
                    startActivity(new Intent(MainActivity.this, com/example/raswap/octomatic/eshop));
                }

            
            {
                this$0 = MainActivity.this;
                super();
            }
            });
            return;
        }
    }

    static 
    {
        boolean flag;
        if (!com/example/raswap/octomatic/MainActivity.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        $assertionsDisabled = flag;
    }
}
