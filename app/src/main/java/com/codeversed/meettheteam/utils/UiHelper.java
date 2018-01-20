package com.codeversed.meettheteam.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;

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

  /**
   * Overrides the drag behavior for the AppBarLayout.
   */
  public static void fixAppBarOnDrag(AppBarLayout appBarLayout) {
    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
    AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
    if (behavior != null) {
      behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
        @Override public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
          return false;
        }
      });
    }
  }

}
