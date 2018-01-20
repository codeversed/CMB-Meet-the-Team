package com.codeversed.meettheteam.utils;

import android.content.Context;

import com.codeversed.meettheteam.R;

import java.io.IOException;
import java.io.InputStream;

import timber.log.Timber;

/**
 * Created by steve on 1/19/18.
 * steve.albright@gmail.com
 */
public class AssetReader {

  /**
   * Loads json file from assets folder using AssetManager.
   */
  public static String loadJSONFromAsset(Context context) {

    try {
      InputStream is = context.getAssets().open(context.getString(R.string.json_filename));
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();
      return new String(buffer, "UTF-8");
    } catch (IOException e) {
      Timber.e(e);
      return null;
    }

  }

}
