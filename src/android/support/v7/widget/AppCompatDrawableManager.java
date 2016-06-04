// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.LruCache;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.v7.widget:
//            ThemeUtils, DrawableUtils, TintInfo, TintResources

public final class AppCompatDrawableManager
{
    private static class AvdcInflateDelegate
        implements InflateDelegate
    {

        public Drawable createFromXmlInner(Context context, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        {
            try
            {
                context = AnimatedVectorDrawableCompat.createFromXmlInner(context, context.getResources(), xmlpullparser, attributeset, theme);
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", context);
                return null;
            }
            return context;
        }

        private AvdcInflateDelegate()
        {
        }

    }

    private static class ColorFilterLruCache extends LruCache
    {

        private static int generateCacheKey(int i, android.graphics.PorterDuff.Mode mode)
        {
            return (i + 31) * 31 + mode.hashCode();
        }

        PorterDuffColorFilter get(int i, android.graphics.PorterDuff.Mode mode)
        {
            return (PorterDuffColorFilter)get(Integer.valueOf(generateCacheKey(i, mode)));
        }

        PorterDuffColorFilter put(int i, android.graphics.PorterDuff.Mode mode, PorterDuffColorFilter porterduffcolorfilter)
        {
            return (PorterDuffColorFilter)put(Integer.valueOf(generateCacheKey(i, mode)), porterduffcolorfilter);
        }

        public ColorFilterLruCache(int i)
        {
            super(i);
        }
    }

    private static interface InflateDelegate
    {

        public abstract Drawable createFromXmlInner(Context context, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme);
    }

    private static class VdcInflateDelegate
        implements InflateDelegate
    {

        public Drawable createFromXmlInner(Context context, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        {
            try
            {
                context = VectorDrawableCompat.createFromXmlInner(context.getResources(), xmlpullparser, attributeset, theme);
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", context);
                return null;
            }
            return context;
        }

        private VdcInflateDelegate()
        {
        }

    }


    private static final int COLORFILTER_COLOR_BACKGROUND_MULTIPLY[];
    private static final int COLORFILTER_COLOR_CONTROL_ACTIVATED[];
    private static final int COLORFILTER_TINT_COLOR_CONTROL_NORMAL[];
    private static final ColorFilterLruCache COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
    private static final boolean DEBUG = false;
    private static final android.graphics.PorterDuff.Mode DEFAULT_MODE;
    private static AppCompatDrawableManager INSTANCE;
    private static final String PLATFORM_VD_CLAZZ = "android.graphics.drawable.VectorDrawable";
    private static final String SKIP_DRAWABLE_TAG = "appcompat_skip_skip";
    private static final String TAG = "AppCompatDrawableManager";
    private static final int TINT_CHECKABLE_BUTTON_LIST[];
    private static final int TINT_COLOR_CONTROL_NORMAL[];
    private static final int TINT_COLOR_CONTROL_STATE_LIST[];
    private final Object mDelegateDrawableCacheLock = new Object();
    private final WeakHashMap mDelegateDrawableCaches = new WeakHashMap(0);
    private ArrayMap mDelegates;
    private boolean mHasCheckedVectorDrawableSetup;
    private SparseArray mKnownDrawableIdTags;
    private WeakHashMap mTintLists;
    private TypedValue mTypedValue;

    public AppCompatDrawableManager()
    {
    }

    private boolean addCachedDelegateDrawable(Context context, long l, Drawable drawable)
    {
        android.graphics.drawable.Drawable.ConstantState constantstate;
        constantstate = drawable.getConstantState();
        if (constantstate == null)
        {
            break MISSING_BLOCK_LABEL_89;
        }
        Object obj = mDelegateDrawableCacheLock;
        obj;
        JVM INSTR monitorenter ;
        LongSparseArray longsparsearray = (LongSparseArray)mDelegateDrawableCaches.get(context);
        drawable = longsparsearray;
        if (longsparsearray != null)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        drawable = new LongSparseArray();
        mDelegateDrawableCaches.put(context, drawable);
        drawable.put(l, new WeakReference(constantstate));
        obj;
        JVM INSTR monitorexit ;
        return true;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
        return false;
    }

    private void addDelegate(String s, InflateDelegate inflatedelegate)
    {
        if (mDelegates == null)
        {
            mDelegates = new ArrayMap();
        }
        mDelegates.put(s, inflatedelegate);
    }

    private void addTintListToCache(Context context, int i, ColorStateList colorstatelist)
    {
        if (mTintLists == null)
        {
            mTintLists = new WeakHashMap();
        }
        SparseArray sparsearray1 = (SparseArray)mTintLists.get(context);
        SparseArray sparsearray = sparsearray1;
        if (sparsearray1 == null)
        {
            sparsearray = new SparseArray();
            mTintLists.put(context, sparsearray);
        }
        sparsearray.append(i, colorstatelist);
    }

    private static boolean arrayContains(int ai[], int i)
    {
        int k = ai.length;
        for (int j = 0; j < k; j++)
        {
            if (ai[j] == i)
            {
                return true;
            }
        }

        return false;
    }

    private ColorStateList createButtonColorStateList(Context context, int i)
    {
        int ai[][] = new int[4][];
        int ai1[] = new int[4];
        i = ThemeUtils.getThemeAttrColor(context, i);
        int j = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlHighlight);
        ai[0] = ThemeUtils.DISABLED_STATE_SET;
        ai1[0] = ThemeUtils.getDisabledThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorButtonNormal);
        int k = 0 + 1;
        ai[k] = ThemeUtils.PRESSED_STATE_SET;
        ai1[k] = ColorUtils.compositeColors(j, i);
        k++;
        ai[k] = ThemeUtils.FOCUSED_STATE_SET;
        ai1[k] = ColorUtils.compositeColors(j, i);
        j = k + 1;
        ai[j] = ThemeUtils.EMPTY_STATE_SET;
        ai1[j] = i;
        return new ColorStateList(ai, ai1);
    }

    private ColorStateList createCheckableButtonColorStateList(Context context)
    {
        int ai[][] = new int[3][];
        int ai1[] = new int[3];
        ai[0] = ThemeUtils.DISABLED_STATE_SET;
        ai1[0] = ThemeUtils.getDisabledThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlNormal);
        int i = 0 + 1;
        ai[i] = ThemeUtils.CHECKED_STATE_SET;
        ai1[i] = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated);
        i++;
        ai[i] = ThemeUtils.EMPTY_STATE_SET;
        ai1[i] = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlNormal);
        return new ColorStateList(ai, ai1);
    }

    private ColorStateList createColoredButtonColorStateList(Context context)
    {
        return createButtonColorStateList(context, android.support.v7.appcompat.R.attr.colorAccent);
    }

    private ColorStateList createDefaultButtonColorStateList(Context context)
    {
        return createButtonColorStateList(context, android.support.v7.appcompat.R.attr.colorButtonNormal);
    }

    private ColorStateList createDefaultColorStateList(Context context)
    {
        int i = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlNormal);
        int j = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated);
        int ai[][] = new int[7][];
        int ai1[] = new int[7];
        ai[0] = ThemeUtils.DISABLED_STATE_SET;
        ai1[0] = ThemeUtils.getDisabledThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlNormal);
        int k = 0 + 1;
        ai[k] = ThemeUtils.FOCUSED_STATE_SET;
        ai1[k] = j;
        k++;
        ai[k] = ThemeUtils.ACTIVATED_STATE_SET;
        ai1[k] = j;
        k++;
        ai[k] = ThemeUtils.PRESSED_STATE_SET;
        ai1[k] = j;
        k++;
        ai[k] = ThemeUtils.CHECKED_STATE_SET;
        ai1[k] = j;
        k++;
        ai[k] = ThemeUtils.SELECTED_STATE_SET;
        ai1[k] = j;
        j = k + 1;
        ai[j] = ThemeUtils.EMPTY_STATE_SET;
        ai1[j] = i;
        return new ColorStateList(ai, ai1);
    }

    private ColorStateList createEditTextColorStateList(Context context)
    {
        int ai[][] = new int[3][];
        int ai1[] = new int[3];
        ai[0] = ThemeUtils.DISABLED_STATE_SET;
        ai1[0] = ThemeUtils.getDisabledThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlNormal);
        int i = 0 + 1;
        ai[i] = ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET;
        ai1[i] = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlNormal);
        i++;
        ai[i] = ThemeUtils.EMPTY_STATE_SET;
        ai1[i] = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated);
        return new ColorStateList(ai, ai1);
    }

    private ColorStateList createSeekbarThumbColorStateList(Context context)
    {
        int ai[][] = new int[2][];
        int ai1[] = new int[2];
        ai[0] = ThemeUtils.DISABLED_STATE_SET;
        ai1[0] = ThemeUtils.getDisabledThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated);
        int i = 0 + 1;
        ai[i] = ThemeUtils.EMPTY_STATE_SET;
        ai1[i] = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated);
        return new ColorStateList(ai, ai1);
    }

    private ColorStateList createSpinnerColorStateList(Context context)
    {
        int ai[][] = new int[3][];
        int ai1[] = new int[3];
        ai[0] = ThemeUtils.DISABLED_STATE_SET;
        ai1[0] = ThemeUtils.getDisabledThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlNormal);
        int i = 0 + 1;
        ai[i] = ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET;
        ai1[i] = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlNormal);
        i++;
        ai[i] = ThemeUtils.EMPTY_STATE_SET;
        ai1[i] = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated);
        return new ColorStateList(ai, ai1);
    }

    private ColorStateList createSwitchThumbColorStateList(Context context)
    {
        int ai[][] = new int[3][];
        int ai1[] = new int[3];
        ColorStateList colorstatelist = ThemeUtils.getThemeAttrColorStateList(context, android.support.v7.appcompat.R.attr.colorSwitchThumbNormal);
        if (colorstatelist != null && colorstatelist.isStateful())
        {
            ai[0] = ThemeUtils.DISABLED_STATE_SET;
            ai1[0] = colorstatelist.getColorForState(ai[0], 0);
            int i = 0 + 1;
            ai[i] = ThemeUtils.CHECKED_STATE_SET;
            ai1[i] = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated);
            i++;
            ai[i] = ThemeUtils.EMPTY_STATE_SET;
            ai1[i] = colorstatelist.getDefaultColor();
        } else
        {
            ai[0] = ThemeUtils.DISABLED_STATE_SET;
            ai1[0] = ThemeUtils.getDisabledThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorSwitchThumbNormal);
            int j = 0 + 1;
            ai[j] = ThemeUtils.CHECKED_STATE_SET;
            ai1[j] = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated);
            j++;
            ai[j] = ThemeUtils.EMPTY_STATE_SET;
            ai1[j] = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorSwitchThumbNormal);
        }
        return new ColorStateList(ai, ai1);
    }

    private ColorStateList createSwitchTrackColorStateList(Context context)
    {
        int ai[][] = new int[3][];
        int ai1[] = new int[3];
        ai[0] = ThemeUtils.DISABLED_STATE_SET;
        ai1[0] = ThemeUtils.getThemeAttrColor(context, 0x1010030, 0.1F);
        int i = 0 + 1;
        ai[i] = ThemeUtils.CHECKED_STATE_SET;
        ai1[i] = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated, 0.3F);
        i++;
        ai[i] = ThemeUtils.EMPTY_STATE_SET;
        ai1[i] = ThemeUtils.getThemeAttrColor(context, 0x1010030, 0.3F);
        return new ColorStateList(ai, ai1);
    }

    private static PorterDuffColorFilter createTintFilter(ColorStateList colorstatelist, android.graphics.PorterDuff.Mode mode, int ai[])
    {
        if (colorstatelist == null || mode == null)
        {
            return null;
        } else
        {
            return getPorterDuffColorFilter(colorstatelist.getColorForState(ai, 0), mode);
        }
    }

    public static AppCompatDrawableManager get()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new AppCompatDrawableManager();
            installDefaultInflateDelegates(INSTANCE);
        }
        return INSTANCE;
    }

    private Drawable getCachedDelegateDrawable(Context context, long l)
    {
        Object obj = mDelegateDrawableCacheLock;
        obj;
        JVM INSTR monitorenter ;
        LongSparseArray longsparsearray = (LongSparseArray)mDelegateDrawableCaches.get(context);
        if (longsparsearray != null)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        obj;
        JVM INSTR monitorexit ;
        return null;
        Object obj1 = (WeakReference)longsparsearray.get(l);
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_90;
        }
        obj1 = (android.graphics.drawable.Drawable.ConstantState)((WeakReference) (obj1)).get();
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_84;
        }
        context = ((android.graphics.drawable.Drawable.ConstantState) (obj1)).newDrawable(context.getResources());
        obj;
        JVM INSTR monitorexit ;
        return context;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
        longsparsearray.delete(l);
        obj;
        JVM INSTR monitorexit ;
        return null;
    }

    public static PorterDuffColorFilter getPorterDuffColorFilter(int i, android.graphics.PorterDuff.Mode mode)
    {
        PorterDuffColorFilter porterduffcolorfilter1 = COLOR_FILTER_CACHE.get(i, mode);
        PorterDuffColorFilter porterduffcolorfilter = porterduffcolorfilter1;
        if (porterduffcolorfilter1 == null)
        {
            porterduffcolorfilter = new PorterDuffColorFilter(i, mode);
            COLOR_FILTER_CACHE.put(i, mode, porterduffcolorfilter);
        }
        return porterduffcolorfilter;
    }

    private ColorStateList getTintListFromCache(Context context, int i)
    {
        Object obj = null;
        ColorStateList colorstatelist = obj;
        if (mTintLists != null)
        {
            context = (SparseArray)mTintLists.get(context);
            colorstatelist = obj;
            if (context != null)
            {
                colorstatelist = (ColorStateList)context.get(i);
            }
        }
        return colorstatelist;
    }

    private static void installDefaultInflateDelegates(AppCompatDrawableManager appcompatdrawablemanager)
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if (i < 21)
        {
            appcompatdrawablemanager.addDelegate("vector", new VdcInflateDelegate());
            if (i >= 11)
            {
                appcompatdrawablemanager.addDelegate("animated-vector", new AvdcInflateDelegate());
            }
        }
    }

    private static boolean isVectorDrawable(Drawable drawable)
    {
        return (drawable instanceof VectorDrawableCompat) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    private Drawable loadDrawableFromDelegates(Context context, int i)
    {
        if (mDelegates == null || mDelegates.isEmpty())
        {
            break MISSING_BLOCK_LABEL_405;
        }
        if (mKnownDrawableIdTags == null) goto _L2; else goto _L1
_L1:
        Object obj = (String)mKnownDrawableIdTags.get(i);
        if (!"appcompat_skip_skip".equals(obj) && (obj == null || mDelegates.get(obj) != null)) goto _L4; else goto _L3
_L3:
        Object obj1 = null;
_L8:
        return ((Drawable) (obj1));
_L2:
        mKnownDrawableIdTags = new SparseArray();
_L4:
        Drawable drawable;
        TypedValue typedvalue;
        Object obj2;
        long l;
        if (mTypedValue == null)
        {
            mTypedValue = new TypedValue();
        }
        typedvalue = mTypedValue;
        obj2 = context.getResources();
        ((Resources) (obj2)).getValue(i, typedvalue, true);
        l = (long)typedvalue.assetCookie << 32 | (long)typedvalue.data;
        obj = getCachedDelegateDrawable(context, l);
        obj1 = obj;
        if (obj != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        drawable = ((Drawable) (obj));
        if (typedvalue.string == null)
        {
            break MISSING_BLOCK_LABEL_258;
        }
        drawable = ((Drawable) (obj));
        if (!typedvalue.string.toString().endsWith(".xml"))
        {
            break MISSING_BLOCK_LABEL_258;
        }
        drawable = ((Drawable) (obj));
        obj2 = ((Resources) (obj2)).getXml(i);
        drawable = ((Drawable) (obj));
        AttributeSet attributeset = Xml.asAttributeSet(((XmlPullParser) (obj2)));
_L6:
        drawable = ((Drawable) (obj));
        int j = ((XmlPullParser) (obj2)).next();
        if (j != 2 && j != 1) goto _L6; else goto _L5
_L5:
        if (j == 2)
        {
            break; /* Loop/switch isn't completed */
        }
        drawable = ((Drawable) (obj));
        try
        {
            throw new XmlPullParserException("No start tag found");
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            Log.e("AppCompatDrawableManager", "Exception while inflating drawable", context);
        }
_L10:
        obj1 = drawable;
        if (drawable == null)
        {
            mKnownDrawableIdTags.append(i, "appcompat_skip_skip");
            return drawable;
        }
        if (true) goto _L8; else goto _L7
_L7:
        drawable = ((Drawable) (obj));
        obj1 = ((XmlPullParser) (obj2)).getName();
        drawable = ((Drawable) (obj));
        mKnownDrawableIdTags.append(i, obj1);
        drawable = ((Drawable) (obj));
        InflateDelegate inflatedelegate = (InflateDelegate)mDelegates.get(obj1);
        obj1 = obj;
        if (inflatedelegate == null)
        {
            break MISSING_BLOCK_LABEL_351;
        }
        drawable = ((Drawable) (obj));
        obj1 = inflatedelegate.createFromXmlInner(context, ((XmlPullParser) (obj2)), attributeset, context.getTheme());
        drawable = ((Drawable) (obj1));
        if (obj1 == null) goto _L10; else goto _L9
_L9:
        drawable = ((Drawable) (obj1));
        ((Drawable) (obj1)).setChangingConfigurations(typedvalue.changingConfigurations);
        drawable = ((Drawable) (obj1));
        boolean flag = addCachedDelegateDrawable(context, l, ((Drawable) (obj1)));
        drawable = ((Drawable) (obj1));
        if (flag)
        {
            drawable = ((Drawable) (obj1));
        }
          goto _L10
        return null;
    }

    private void removeDelegate(String s, InflateDelegate inflatedelegate)
    {
        if (mDelegates != null && mDelegates.get(s) == inflatedelegate)
        {
            mDelegates.remove(s);
        }
    }

    private static void setPorterDuffColorFilter(Drawable drawable, int i, android.graphics.PorterDuff.Mode mode)
    {
        Drawable drawable1 = drawable;
        if (DrawableUtils.canSafelyMutateDrawable(drawable))
        {
            drawable1 = drawable.mutate();
        }
        drawable = mode;
        if (mode == null)
        {
            drawable = DEFAULT_MODE;
        }
        drawable1.setColorFilter(getPorterDuffColorFilter(i, drawable));
    }

    private Drawable tintDrawable(Context context, int i, boolean flag, Drawable drawable)
    {
        Object obj = getTintList(context, i);
        if (obj == null) goto _L2; else goto _L1
_L1:
        context = drawable;
        if (DrawableUtils.canSafelyMutateDrawable(drawable))
        {
            context = drawable.mutate();
        }
        context = DrawableCompat.wrap(context);
        DrawableCompat.setTintList(context, ((ColorStateList) (obj)));
        drawable = getTintMode(i);
        obj = context;
        if (drawable != null)
        {
            DrawableCompat.setTintMode(context, drawable);
            obj = context;
        }
_L4:
        return ((Drawable) (obj));
_L2:
        if (i == android.support.v7.appcompat.R.drawable.abc_cab_background_top_material)
        {
            return new LayerDrawable(new Drawable[] {
                getDrawable(context, android.support.v7.appcompat.R.drawable.abc_cab_background_internal_bg), getDrawable(context, android.support.v7.appcompat.R.drawable.abc_cab_background_top_mtrl_alpha)
            });
        }
        if (i == android.support.v7.appcompat.R.drawable.abc_seekbar_track_material)
        {
            obj = (LayerDrawable)drawable;
            setPorterDuffColorFilter(((LayerDrawable) (obj)).findDrawableByLayerId(0x1020000), ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(((LayerDrawable) (obj)).findDrawableByLayerId(0x102000f), ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(((LayerDrawable) (obj)).findDrawableByLayerId(0x102000d), ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated), DEFAULT_MODE);
            obj = drawable;
        } else
        if (i == android.support.v7.appcompat.R.drawable.abc_ratingbar_indicator_material || i == android.support.v7.appcompat.R.drawable.abc_ratingbar_small_material)
        {
            obj = (LayerDrawable)drawable;
            setPorterDuffColorFilter(((LayerDrawable) (obj)).findDrawableByLayerId(0x1020000), ThemeUtils.getDisabledThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(((LayerDrawable) (obj)).findDrawableByLayerId(0x102000f), ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated), DEFAULT_MODE);
            setPorterDuffColorFilter(((LayerDrawable) (obj)).findDrawableByLayerId(0x102000d), ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated), DEFAULT_MODE);
            obj = drawable;
        } else
        {
            obj = drawable;
            if (!tintDrawableUsingColorFilter(context, i, drawable))
            {
                obj = drawable;
                if (flag)
                {
                    obj = null;
                }
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static void tintDrawable(Drawable drawable, TintInfo tintinfo, int ai[])
    {
        if (DrawableUtils.canSafelyMutateDrawable(drawable) && drawable.mutate() != drawable)
        {
            Log.d("AppCompatDrawableManager", "Mutated drawable is not the same instance as the input.");
        } else
        {
            if (tintinfo.mHasTintList || tintinfo.mHasTintMode)
            {
                ColorStateList colorstatelist;
                if (tintinfo.mHasTintList)
                {
                    colorstatelist = tintinfo.mTintList;
                } else
                {
                    colorstatelist = null;
                }
                if (tintinfo.mHasTintMode)
                {
                    tintinfo = tintinfo.mTintMode;
                } else
                {
                    tintinfo = DEFAULT_MODE;
                }
                drawable.setColorFilter(createTintFilter(colorstatelist, tintinfo, ai));
            } else
            {
                drawable.clearColorFilter();
            }
            if (android.os.Build.VERSION.SDK_INT <= 23)
            {
                drawable.invalidateSelf();
                return;
            }
        }
    }

    private static boolean tintDrawableUsingColorFilter(Context context, int i, Drawable drawable)
    {
        Object obj1 = DEFAULT_MODE;
        boolean flag = false;
        int j = 0;
        int k = -1;
        Object obj;
        if (arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, i))
        {
            j = android.support.v7.appcompat.R.attr.colorControlNormal;
            flag = true;
            obj = obj1;
        } else
        if (arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, i))
        {
            j = android.support.v7.appcompat.R.attr.colorControlActivated;
            flag = true;
            obj = obj1;
        } else
        if (arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, i))
        {
            j = 0x1010031;
            flag = true;
            obj = android.graphics.PorterDuff.Mode.MULTIPLY;
        } else
        {
            obj = obj1;
            if (i == android.support.v7.appcompat.R.drawable.abc_list_divider_mtrl_alpha)
            {
                j = 0x1010030;
                flag = true;
                k = Math.round(40.8F);
                obj = obj1;
            }
        }
        if (flag)
        {
            obj1 = drawable;
            if (DrawableUtils.canSafelyMutateDrawable(drawable))
            {
                obj1 = drawable.mutate();
            }
            ((Drawable) (obj1)).setColorFilter(getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(context, j), ((android.graphics.PorterDuff.Mode) (obj))));
            if (k != -1)
            {
                ((Drawable) (obj1)).setAlpha(k);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public Drawable getDrawable(Context context, int i)
    {
        return getDrawable(context, i, false);
    }

    public Drawable getDrawable(Context context, int i, boolean flag)
    {
        Drawable drawable1 = loadDrawableFromDelegates(context, i);
        Drawable drawable = drawable1;
        if (drawable1 == null)
        {
            drawable = ContextCompat.getDrawable(context, i);
        }
        drawable1 = drawable;
        if (drawable != null)
        {
            drawable1 = tintDrawable(context, i, flag, drawable);
        }
        if (drawable1 != null)
        {
            DrawableUtils.fixDrawable(drawable1);
        }
        return drawable1;
    }

    public final ColorStateList getTintList(Context context, int i)
    {
        ColorStateList colorstatelist;
        ColorStateList colorstatelist1;
        colorstatelist = getTintListFromCache(context, i);
        colorstatelist1 = colorstatelist;
        if (colorstatelist != null) goto _L2; else goto _L1
_L1:
        if (i != android.support.v7.appcompat.R.drawable.abc_edit_text_material) goto _L4; else goto _L3
_L3:
        colorstatelist = createEditTextColorStateList(context);
_L6:
        colorstatelist1 = colorstatelist;
        if (colorstatelist != null)
        {
            addTintListToCache(context, i, colorstatelist);
            colorstatelist1 = colorstatelist;
        }
_L2:
        return colorstatelist1;
_L4:
        if (i == android.support.v7.appcompat.R.drawable.abc_switch_track_mtrl_alpha)
        {
            colorstatelist = createSwitchTrackColorStateList(context);
        } else
        if (i == android.support.v7.appcompat.R.drawable.abc_switch_thumb_material)
        {
            colorstatelist = createSwitchThumbColorStateList(context);
        } else
        if (i == android.support.v7.appcompat.R.drawable.abc_btn_default_mtrl_shape || i == android.support.v7.appcompat.R.drawable.abc_btn_borderless_material)
        {
            colorstatelist = createDefaultButtonColorStateList(context);
        } else
        if (i == android.support.v7.appcompat.R.drawable.abc_btn_colored_material)
        {
            colorstatelist = createColoredButtonColorStateList(context);
        } else
        if (i == android.support.v7.appcompat.R.drawable.abc_spinner_mtrl_am_alpha || i == android.support.v7.appcompat.R.drawable.abc_spinner_textfield_background_material)
        {
            colorstatelist = createSpinnerColorStateList(context);
        } else
        if (arrayContains(TINT_COLOR_CONTROL_NORMAL, i))
        {
            colorstatelist = ThemeUtils.getThemeAttrColorStateList(context, android.support.v7.appcompat.R.attr.colorControlNormal);
        } else
        if (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, i))
        {
            colorstatelist = createDefaultColorStateList(context);
        } else
        if (arrayContains(TINT_CHECKABLE_BUTTON_LIST, i))
        {
            colorstatelist = createCheckableButtonColorStateList(context);
        } else
        if (i == android.support.v7.appcompat.R.drawable.abc_seekbar_thumb_material)
        {
            colorstatelist = createSeekbarThumbColorStateList(context);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    final android.graphics.PorterDuff.Mode getTintMode(int i)
    {
        android.graphics.PorterDuff.Mode mode = null;
        if (i == android.support.v7.appcompat.R.drawable.abc_switch_thumb_material)
        {
            mode = android.graphics.PorterDuff.Mode.MULTIPLY;
        }
        return mode;
    }

    public final Drawable onDrawableLoadedFromResources(Context context, TintResources tintresources, int i)
    {
        Drawable drawable1 = loadDrawableFromDelegates(context, i);
        Drawable drawable = drawable1;
        if (drawable1 == null)
        {
            drawable = tintresources.superGetDrawable(i);
        }
        if (drawable != null)
        {
            return tintDrawable(context, i, false, drawable);
        } else
        {
            return null;
        }
    }

    static 
    {
        DEFAULT_MODE = android.graphics.PorterDuff.Mode.SRC_IN;
        COLORFILTER_TINT_COLOR_CONTROL_NORMAL = (new int[] {
            android.support.v7.appcompat.R.drawable.abc_textfield_search_default_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_textfield_default_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha
        });
        TINT_COLOR_CONTROL_NORMAL = (new int[] {
            android.support.v7.appcompat.R.drawable.abc_ic_ab_back_mtrl_am_alpha, android.support.v7.appcompat.R.drawable.abc_ic_go_search_api_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_ic_search_api_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_ic_commit_search_api_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_ic_clear_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_ic_menu_share_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_ic_menu_copy_mtrl_am_alpha, android.support.v7.appcompat.R.drawable.abc_ic_menu_cut_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_ic_menu_selectall_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_ic_menu_paste_mtrl_am_alpha, 
            android.support.v7.appcompat.R.drawable.abc_ic_menu_moreoverflow_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_ic_voice_search_api_mtrl_alpha
        });
        COLORFILTER_COLOR_CONTROL_ACTIVATED = (new int[] {
            android.support.v7.appcompat.R.drawable.abc_textfield_activated_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_textfield_search_activated_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_cab_background_top_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_text_cursor_material
        });
        COLORFILTER_COLOR_BACKGROUND_MULTIPLY = (new int[] {
            android.support.v7.appcompat.R.drawable.abc_popup_background_mtrl_mult, android.support.v7.appcompat.R.drawable.abc_cab_background_internal_bg, android.support.v7.appcompat.R.drawable.abc_menu_hardkey_panel_mtrl_mult
        });
        TINT_COLOR_CONTROL_STATE_LIST = (new int[] {
            android.support.v7.appcompat.R.drawable.abc_edit_text_material, android.support.v7.appcompat.R.drawable.abc_tab_indicator_material, android.support.v7.appcompat.R.drawable.abc_textfield_search_material, android.support.v7.appcompat.R.drawable.abc_spinner_mtrl_am_alpha, android.support.v7.appcompat.R.drawable.abc_spinner_textfield_background_material, android.support.v7.appcompat.R.drawable.abc_ratingbar_full_material, android.support.v7.appcompat.R.drawable.abc_switch_track_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_switch_thumb_material, android.support.v7.appcompat.R.drawable.abc_btn_default_mtrl_shape, android.support.v7.appcompat.R.drawable.abc_btn_borderless_material
        });
        TINT_CHECKABLE_BUTTON_LIST = (new int[] {
            android.support.v7.appcompat.R.drawable.abc_btn_check_material, android.support.v7.appcompat.R.drawable.abc_btn_radio_material
        });
    }
}
