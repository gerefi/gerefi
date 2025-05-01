package com.gerefi.app.util;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

public class AndroidUtil {
    private AndroidUtil() {
    }

    public static void turnScreenOn(Activity gerEFI) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            gerEFI.setTurnScreenOn(true);
        } else {
            Window window = gerEFI.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }
    }
}
