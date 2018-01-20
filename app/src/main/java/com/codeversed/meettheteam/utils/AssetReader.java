package com.codeversed.meettheteam.utils;

import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.codeversed.meettheteam.viewmodel.TeamViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

import timber.log.Timber;

/**
 * Created by steve on 1/19/18.
 * steve.albright@gmail.com
 */
public class AssetReader {

  /**
   * Loads json file from assets folder using AssetManager.
   */
  public static void loadJSONFromAsset(AssetManager assetManager, TeamViewModel viewModel) {
    new MyTask(assetManager, viewModel).execute();
  }

  /**
   * AsyncTask with WeakReferences to prevent leaks.
   */
  private static class MyTask extends AsyncTask<Void, Void, String> {

    private WeakReference<AssetManager> assetManagerWeakReference;
    private WeakReference<TeamViewModel> viewModelWeakReference;

    // only retain a weak reference to the activity
    MyTask(AssetManager assetManager, TeamViewModel viewModel) {
      assetManagerWeakReference = new WeakReference<>(assetManager);
      viewModelWeakReference = new WeakReference<>(viewModel);
    }

    @Override protected String doInBackground(Void... params) {

      try {
        InputStream is = assetManagerWeakReference.get().open("team.json");
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

    @Override protected void onPostExecute(String result) {
      // get a reference to the activity if it is still there
      TeamViewModel viewModel = viewModelWeakReference.get();
      if (viewModel == null) return;
      viewModel.setData(result);
    }
  }

}
