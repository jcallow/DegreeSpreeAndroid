package com.jvn.degreespree.Utils;

import android.util.Log;

/**
 * Created by john on 10/10/15.
 */
public class ScreenUtils {
    private static String TAG = "ScreenUtils";
    private static float logicalDensity = 1;

    public static int convertToDP(int px) {
        Log.d(TAG, px + "");
        int converted = (int) Math.ceil(px*logicalDensity);
        Log.d(TAG, "converted" + converted);
        return converted;
    }

    public static void setConversionRate(float density) {
        logicalDensity = density;
    }
}
