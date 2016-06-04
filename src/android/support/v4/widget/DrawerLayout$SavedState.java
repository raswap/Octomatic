// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.support.v4.widget:
//            DrawerLayout

protected static class openDrawerGravity extends android.view.t.SavedState
{

    public static final android.os.out.SavedState.lockModeEnd CREATOR = new android.os.Parcelable.Creator() {

        public DrawerLayout.SavedState createFromParcel(Parcel parcel)
        {
            return new DrawerLayout.SavedState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DrawerLayout.SavedState[] newArray(int i)
        {
            return new DrawerLayout.SavedState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    };
    int lockModeEnd;
    int lockModeLeft;
    int lockModeRight;
    int lockModeStart;
    int openDrawerGravity;

    public void writeToParcel(Parcel parcel, int i)
    {
        super.eToParcel(parcel, i);
        parcel.writeInt(openDrawerGravity);
        parcel.writeInt(lockModeLeft);
        parcel.writeInt(lockModeRight);
        parcel.writeInt(lockModeStart);
        parcel.writeInt(lockModeEnd);
    }


    public _cls1(Parcel parcel)
    {
        super(parcel);
        openDrawerGravity = 0;
        openDrawerGravity = parcel.readInt();
        lockModeLeft = parcel.readInt();
        lockModeRight = parcel.readInt();
        lockModeStart = parcel.readInt();
        lockModeEnd = parcel.readInt();
    }

    public lockModeEnd(Parcelable parcelable)
    {
        super(parcelable);
        openDrawerGravity = 0;
    }
}
