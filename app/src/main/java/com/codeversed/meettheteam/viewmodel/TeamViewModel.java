package com.codeversed.meettheteam.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import com.codeversed.meettheteam.vo.Teammate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by steve on 1/19/18.
 * steve.albright@gmail.com
 */
public class TeamViewModel extends ViewModel {

  private MutableLiveData<List<Teammate>> data = new MutableLiveData<>();

  public void setData(String jsonString) {

    if (!TextUtils.isEmpty(jsonString)) {
      // Use TypeToken to load as a list with 'Teammate' object
      data.setValue(new Gson().fromJson(jsonString, new TypeToken<List<Teammate>>() {}.getType()));
    }

  }

  public LiveData<List<Teammate>> getTeam() {
    return data == null ? new MutableLiveData<>() : data;
  }

}
