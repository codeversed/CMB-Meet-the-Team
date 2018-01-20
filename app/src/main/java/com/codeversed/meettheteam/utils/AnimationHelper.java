package com.codeversed.meettheteam.utils;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by steve on 1/20/18.
 * steve.albright@gmail.com
 */
public class AnimationHelper {

  private static final Random random = new Random();

  private static void startKenBurns(
      View view, long duration, float fromScale, float toScale, float fromTranslationX,
      float fromTranslationY, float toTranslationX, float toTranslationY, OnKenBurnsEventListener listener) {

//    view.setAlpha(0.5f);
//    ViewPropertyAnimator alphaAnimator = view.animate().
//        alpha(1f);
//    alphaAnimator.start();

    view.setScaleX(fromScale);
    view.setScaleY(fromScale);
    view.setTranslationX(fromTranslationX);
    view.setTranslationY(fromTranslationY);
    ViewPropertyAnimator propertyAnimator = view.animate().
        translationX(toTranslationX).
        translationY(toTranslationY).
        scaleX(toScale).
        scaleY(toScale).
        withEndAction(()->listener.onKenBurnsEnd(toTranslationX, toTranslationY, toScale)).
        setDuration(duration);
    propertyAnimator.start();
  }

  private static float pickScale() {
    float minScaleFactor = 1.0F;
    float maxScaleFactor = 1.5f;
    return minScaleFactor + random.nextFloat() * (maxScaleFactor - minScaleFactor);
  }

  private static float pickTranslation(int value, float ratio) {
    return value * (ratio - 1.0f) * (random.nextFloat() - 0.5f);
  }

  public static void animateKenBurns(ImageView view, OnKenBurnsEventListener listener, float fromTx, float fromTy, float fromS) {
    float fromScale = fromS == -99999 ? pickScale() : fromS;
    float toScale = pickScale();
    float fromTranslationX = fromTx == -99999 ? pickTranslation(view.getWidth(), fromScale) : fromTx;
    float fromTranslationY = fromTy == -99999 ? pickTranslation(view.getHeight(), fromScale) : fromTy;
    float toTranslationX = pickTranslation(view.getWidth(), toScale);
    float toTranslationY = pickTranslation(view.getHeight(), toScale);
    startKenBurns(view, 11500, fromScale, toScale, fromTranslationX, fromTranslationY,
                  toTranslationX,
                  toTranslationY,
                  listener);
  }

  public interface OnKenBurnsEventListener {
    void onKenBurnsEnd(float toTransX, float toTransY, float toScale);
  }

}
