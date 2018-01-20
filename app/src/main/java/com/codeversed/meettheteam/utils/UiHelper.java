package com.codeversed.meettheteam.utils;

import android.content.Context;

/**
 * Created by steve on 1/19/18.
 * steve.albright@gmail.com
 */
public class UiHelper {

  /**
   * Calculates statusbar height at runtime.
   */
  public static int getStatusBarHeight(Context context) {
    int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
    return resourceId > 0 ? context.getResources().getDimensionPixelSize(resourceId) : 0;
  }

}
